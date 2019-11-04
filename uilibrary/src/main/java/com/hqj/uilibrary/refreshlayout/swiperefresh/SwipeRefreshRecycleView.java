package com.hqj.uilibrary.refreshlayout.swiperefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hqj.uilibrary.R;

import java.util.List;

/**
 * SwipeRefreshLayout+RecycleView+BRVAH
 * 下拉刷新，上拉加载更多 功能封装
 */
public class SwipeRefreshRecycleView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private Context mContext;
    private SuperSwipeRefreshLayout swipeRefresh;
    private RecyclerView rv_list;
    private BaseQuickAdapter adapter;
    /**
     * 空布局相关
     */
    private View emptyView;
    private ImageView mIvEmpty;
    private TextView mTvEmpty;
    private boolean isAuto;
    private int pageSize;
    private int page=1;
    private boolean isOpenLoadMore;//上拉加载功能是否打开（默认关闭）

    public SwipeRefreshRecycleView(Context context) {
        this(context, null, 0);
    }

    public SwipeRefreshRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeRefreshRecycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        //加载布局
        View.inflate(mContext, R.layout.layout_swipe_refrish_list, this);
        swipeRefresh = (SuperSwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        rv_list = (RecyclerView) findViewById(R.id.rv);

        emptyView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty_view, null);
        mIvEmpty = emptyView.findViewById(R.id.iv_empty);
        mTvEmpty = emptyView.findViewById(R.id.tv_empty);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rv_list.setLayoutManager(layoutManager);
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        //设置下拉时圆圈的背景颜色（这里设置成白色）
        swipeRefresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefresh.setOnRefreshListener(this);
    }


    //设置空布局是否显示
    @SuppressLint("ResourceType")
    public SwipeRefreshRecycleView setEmptyVisibility(boolean isShow) {
        getAdapter().isUseEmpty(isShow);
        return this;
    }

    //设置空布局---图片
    public SwipeRefreshRecycleView setEmptyLayoutIV(@DrawableRes int drawableId) {
        mIvEmpty.setImageDrawable(getResources().getDrawable(drawableId));
        return this;
    }

    public SwipeRefreshRecycleView setEmptyLayout(@DrawableRes int drawableid, String text) {
        mIvEmpty.setImageDrawable(getResources().getDrawable(drawableid));
        mTvEmpty.setText(text);
        return this;
    }

    //设置空布局--文字
    public SwipeRefreshRecycleView setEmptyLayoutTV(String text) {
        mTvEmpty.setText(text);
        return this;
    }

    public SwipeRefreshRecycleView setEmptyLayout(View view) {
        emptyView = view;
        return this;
    }

    public SwipeRefreshRecycleView showHeaderAndFooterView() {
        adapter.setHeaderFooterEmpty(true, true);
        return this;
    }

    public SwipeRefreshRecycleView showHeaderView() {
        adapter.setHeaderFooterEmpty(true, false);
        return this;
    }

    private onSwipeRefreshListener onSwipeRefreshListener;

    public interface onSwipeRefreshListener {
        void onRefresh(int page);

        void onLoading(int page);
    }

    @Override
    public void onRefresh() {
        startRefreshRequest();
    }

    private void startRefreshRequest() {
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        setEnableLoadMore(false);
        //实际的刷新请求
        page=1;
        onSwipeRefreshListener.onRefresh(page);
    }

    @Override
    public void onLoadMoreRequested() {
        //实际的刷新请求
        onSwipeRefreshListener.onLoading(page);
    }

    public SwipeRefreshRecycleView autoRefresh(boolean isAuto) {
        this.isAuto = isAuto;
        return this;
    }

    public SwipeRefreshRecycleView setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public SwipeRefreshRecycleView setRefreshEnable(boolean enable) {
        swipeRefresh.setRefreshing(enable);
        return this;
    }

    /**
     * 打开上拉加载更多功能
     * @return
     */
    public SwipeRefreshRecycleView openLoadMore() {
        this.isOpenLoadMore = true;
        return this;
    }

    public SwipeRefreshRecycleView setEnableLoadMore(boolean enable) {
        adapter.setEnableLoadMore(enable);
        return this;
    }

    /**
     * 刷新结束
     *
     * @return
     */
    public SwipeRefreshRecycleView completeRefresh() {
        setRefreshEnable(false);
        if (isOpenLoadMore){
            setEnableLoadMore(true);
        }
        return this;
    }

    /**
     * 加载更多结束
     *
     * @param isSuccess
     * @return
     */
    public SwipeRefreshRecycleView completeLoadMore(boolean isSuccess) {
        if (isSuccess) {
            adapter.loadMoreComplete();
        } else {
            adapter.loadMoreFail();
        }
        return this;
    }

    /**
     * 设置适配器（自己设置是否 刷新和加载）
     *
     * @param adapter                自己处理数据源问题
     * @param onSwipeRefreshListener
     */
    public void setAdapter(BaseQuickAdapter adapter, onSwipeRefreshListener onSwipeRefreshListener) {
        this.onSwipeRefreshListener = onSwipeRefreshListener;
        this.adapter = adapter;
        initAdapter();
    }

    private void initAdapter() {
        adapter.setEmptyView(emptyView);
        if (isOpenLoadMore){
            adapter.setOnLoadMoreListener(this, rv_list);
        }
        adapter.setLoadMoreView(new CustomLoadMoreView());
        rv_list.setAdapter(this.adapter);
        if (isAuto) {
            setRefreshEnable(true);
            startRefreshRequest();
        }
    }

    public <T> void setData(List<T> data) {
        boolean isRefresh =page ==1;
        page++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            adapter.setNewData(data);
            completeRefresh();
        } else if (size > 0) {
            adapter.addData(data);
        }
        if (size < pageSize) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
        } else {
            completeLoadMore(true);
        }
    }

    /**
     * 设置数据集（替换）  --- 适用于 刷新后 或者平时 调用
     */
    public <T> void setRecycleViewData(List<T> list) {
        if (adapter == null) {
            return;
        }
        adapter.replaceData(list);
    }

    /**
     * 增加数据源  --- 适用于 上拉加载 调用
     */
    public <T> void addRecycleViewData(List<T> list) {
        if (adapter == null) {
            return;
        }
        adapter.addData(list);
    }


    /**
     * 全部刷新
     */
    public void notifyDataSetChanged() {
        if (adapter == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 刷新某一个
     */
    public void notifyItemChanged(int position) {
        if (adapter == null) {
            return;
        }
        adapter.notifyItemChanged(position);
    }

    public SwipeRefreshRecycleView setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        rv_list.setLayoutManager(layoutManager);
        return this;
    }

    public RecyclerView getRecycleView() {
        return rv_list;
    }

    public SwipeRefreshLayout getSwipRefreshLayout() {
        return swipeRefresh;
    }

    public BaseQuickAdapter getAdapter() {
        return adapter;
    }

    public SwipeRefreshRecycleView addItemDecoration(RecyclerView.ItemDecoration decor) {
        rv_list.addItemDecoration(decor);
        return this;
    }

    /**
     * 删除头
     */
    public SwipeRefreshRecycleView removeHeadView(View view) {
        adapter.removeHeaderView(view);
        return this;
    }

    /**
     * 删除全部的头和尾
     */
    public SwipeRefreshRecycleView removeHeaderAndFootVew() {
        adapter.removeAllFooterView();
        adapter.removeAllHeaderView();
        return this;
    }

    public void addHeardVew(View view) {
        adapter.addHeaderView(view);
    }

    public SwipeRefreshRecycleView addHeadVew(View view) {
        adapter.addHeaderView(view);
        return this;
    }

    public void addFootView(View view) {
        adapter.addFooterView(view);
    }

    /**
     * 删除某一个item 位置
     *
     * @param position
     */
    public void removeItem(int position) {
        if (adapter == null) {
            return;
        }
        adapter.remove(position);
    }

    /**
     * 获取适配器的集合
     */
    public <T> List<T> getData() {
        if (adapter == null) {
            return null;
        }
        return adapter.getData();
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        <T> void onItemClick(T item, int position, View view);
    }

    private OnItemChildClickListener mOnItemChildClickListener;

    public interface OnItemChildClickListener {
        void onItemChildClick(BaseQuickAdapter adapter, int position, View view);
    }

    /**
     * 设置点击事件-- {适用于  item被点击的时候调用}
     * 不适用场景：重写后findviewbyid获取子控件 设置点击事件 会产生错乱的问题
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(adapter.getItem(position), position, view);
                }
            }
        });
    }

    /**
     * 设置点击事件---{适用于 item中的子view 被点击的时候调用}
     * 先 在adapter中 helper. addOnClickListener（view）
     * view.getid  获取资源id 分别做业务处理
     *
     * @param onItemChildClickListener
     */
    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(adapter, position, view);
                }
            }
        });
    }


}

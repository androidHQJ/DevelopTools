package com.wxq.developtools.activity;

import android.os.Handler;
import android.os.Looper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hqj.uilibrary.recycleview.itemdecoration.LinearItemDecoration;
import com.hqj.uilibrary.refreshlayout.swiperefresh.SwipeRefreshRecycleView;
import com.wxq.commonlibrary.base.BaseActivity;
import com.wxq.commonlibrary.baserx.Event;
import com.wxq.developtools.R;
import com.wxq.developtools.contract.MvpMainContract;
import com.wxq.developtools.present.MvpMainPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MvpMainActivity extends BaseActivity<MvpMainContract.Presenter> implements MvpMainContract.View {

    private static final int PAGE_SIZE = 15;

    @BindView(R.id.swipeRefreshRV)
    SwipeRefreshRecycleView swipeRefreshRV;

    @Override
    protected void initViews() {}

    @Override
    protected void initEventAndData() {
        BaseQuickAdapter<String, BaseViewHolder> mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_test, null) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv, item);
            }
        };
        swipeRefreshRV
                .openLoadMore()
                .autoRefresh(true)
                .setPageSize(PAGE_SIZE)
                .addItemDecoration(new LinearItemDecoration(this,1,
                        getResources().getColor(R.color.color_3f3f3f)))
                .setAdapter(mAdapter, new SwipeRefreshRecycleView.onSwipeRefreshListener() {
                    @Override
                    public void onRefresh(int page) {
                        refresh(page);
                    }

                    @Override
                    public void onLoading(int page) {
                        loadMore(page);
                    }
                });

    }

    private void refresh(int page) {
        new Request(page, new RequestCallBack() {
            @Override
            public void success(List<String> data) {
                swipeRefreshRV.setData(data);
            }

            @Override
            public void fail(Exception e) {
                showToast("fail");
                swipeRefreshRV.completeRefresh();
            }
        }).start();
    }

    private void loadMore(int page) {
        new Request(page, new RequestCallBack() {
            @Override
            public void success(List<String> data) {
                swipeRefreshRV.setData(data);
            }

            @Override
            public void fail(Exception e) {
                swipeRefreshRV.completeLoadMore(false);
                showToast("loadMore:fail");
            }
        }).start();
    }

    @Override
    public void dealWithRxEvent(int action, Event event) {
        super.dealWithRxEvent(action, event);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_mvpmain;
    }

    @Override
    protected MvpMainContract.Presenter initPresent() {
        return new MvpMainPresent(this);
    }

    @Override
    protected boolean isSetStatusBar() {
        return true;
    }

    @Override
    public void showResult(String result) {
        showToast(result);
    }
}

/**********************************模拟网络请求*****************************************/
interface RequestCallBack {
    void success(List<String> data);

    void fail(Exception e);
}

class Request extends Thread {
    private static final int PAGE_SIZE=15;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private List<String> getSampleData(int lenth) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            list.add("num" + i);
        }
        return list;
    }

    Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            if (mPage == 2 && mFirstError) {
                mFirstError = false;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.fail(new RuntimeException("fail"));
                    }
                });
            } else {
                int size = PAGE_SIZE;
                if (mPage == 1) {
                    if (mFirstPageNoMore) {
                        size = 1;
                    }
                    mFirstPageNoMore = !mFirstPageNoMore;
                    if (!mFirstError) {
                        mFirstError = true;
                    }
                } else if (mPage == 4) {
                    size = 1;
                }

                final int dataSize = size;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.success(getSampleData(dataSize));
                    }
                });
            }
        } catch (InterruptedException ignored) {}
    }
}


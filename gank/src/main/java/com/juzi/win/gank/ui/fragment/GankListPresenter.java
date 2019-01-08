package com.juzi.win.gank.ui.fragment;

import android.widget.Toast;

import com.juzi.win.gank.api.ApiService;
import com.juzi.win.gank.bean.GankBaseResponse;
import com.wxq.commonlibrary.util.LogUtils;
import com.wxq.mvplibrary.base.RxPresenter;
import com.wxq.mvplibrary.baserx.RxTransformer;
import com.wxq.mvplibrary.http.common.Api;

import io.reactivex.functions.Consumer;

/**
 * @author 文庆
 * @date 2019/1/8.
 * @description
 */

public class GankListPresenter extends RxPresenter<GankListContract.View> implements GankListContract.Presenter {

    int pageNum = 1;

    public GankListPresenter(GankListContract.View view) {
        super(view);
    }



    @Override
    public void initEventAndData() {

    }


    @Override
    public void reqInfo(String type) {

        Api.getInstance().getApiService(ApiService.class)
                .getGankData(type, pageNum)
                .compose(RxTransformer.transform())
                .subscribe(new Consumer<GankBaseResponse>() {
                    @Override
                    public void accept(GankBaseResponse gankBaseResponse) throws Exception {
                        pageNum++;
                        mView.setData(gankBaseResponse.results);
                    }
                });
    }
}

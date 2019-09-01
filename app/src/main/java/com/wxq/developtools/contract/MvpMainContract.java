package com.wxq.developtools.contract;


import com.wxq.commonlibrary.base.BasePresenter;
import com.wxq.commonlibrary.base.BaseView;

public interface MvpMainContract {
    interface View extends BaseView {

        void showResult(String result);
    }

    interface Presenter extends BasePresenter<MvpMainContract.View> {

        void getData(int count);

    }
}

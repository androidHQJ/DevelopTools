package com.wxq.developtools.constract;


import com.wxq.commonlibrary.base.BasePresenter;
import com.wxq.commonlibrary.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void naveToMainActivity();
    }

    interface Presenter extends BasePresenter<View> {
        void loginWithAccountAndPwd(String s, String s1);
    }
}

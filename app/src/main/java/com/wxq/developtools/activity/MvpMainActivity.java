package com.wxq.developtools.activity;

import com.wxq.commonlibrary.base.BaseActivity;
import com.wxq.commonlibrary.baserx.Event;
import com.wxq.developtools.contract.MvpMainContract;
import com.wxq.developtools.present.MvpMainPresent;
import com.wxq.developtools.R;

public class MvpMainActivity extends BaseActivity<MvpMainContract.Presenter> implements MvpMainContract.View {

    @Override
    protected void initViews() {
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getData(2);
    }

    @Override
    public void dealWithRxEvent(int action, Event event) {
        super.dealWithRxEvent(action, event);

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
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

package com.wxq.developtools.present;

import com.wxq.commonlibrary.base.RxPresenter;
import com.wxq.developtools.contract.MvpMainContract;

/**
 * Created by hqj on 2018/6/28.
 */

public class MvpMainPresent extends RxPresenter<MvpMainContract.View> implements MvpMainContract.Presenter {

    public MvpMainPresent(MvpMainContract.View view) {
        super(view);
    }

    @Override
    public void getData(int count) {
        String result=count * 3 + "wawawawaa";
        mView.showResult(result);
    }
}

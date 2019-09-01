package com.wxq.developtools.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.wxq.commonlibrary.base.BaseActivity;
import com.wxq.commonlibrary.baserx.Event;
import com.wxq.developtools.contract.MvpMainContract;
import com.wxq.developtools.present.MvpMainPresent;
import com.wxq.developtools.R;

import butterknife.BindView;

/**
 * Created by wxq on 2018/6/28.
 */

public class MvpMainActivity extends BaseActivity<MvpMainContract.Presenter> implements MvpMainContract.View {

    @BindView(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected void initViews() {
        //配置
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MvpMainActivity.this,MainActivity.class));
            }
        });
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

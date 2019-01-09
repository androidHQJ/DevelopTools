package com.heqijun.module_wanandroid.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.heqijun.module_wanandroid.R;
import com.heqijun.module_wanandroid.R2;
import com.wxq.commonlibrary.base.BaseActivity;
import com.wxq.commonlibrary.base.BasePresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.container)
    FrameLayout container;
    @BindView(R2.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    @BindView(R2.id.floating_action_btn)
    FloatingActionButton floatingActionBtn;
    @BindView(R2.id.nav_view)
    NavigationView navView;
    @BindView(R2.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int mIndex;

    public static void navToActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        // 以前使用 BottomNavigationViewHelper.disableShiftMode(this) 方法来设置底部图标和字体都显示并去掉点击动画
        // 升级到 28.0.0 之后，官方重构了 BottomNavigationView ，目前可以使用 labelVisibilityMode = 1 来替代
        // BottomNavigationViewHelper.disableShiftMode(this)
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        showFragment(mIndex);

        click(floatingActionBtn, o -> {

        });
    }

    private void showFragment(int mIndex) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.wan_activity_main;
    }

    @Override
    public boolean isNeedHeardLayout() {
        return false;
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //底部tab
            case R.id.action_home:

                return true;
            case R.id.action_knowledge_system:

                return true;
            case R.id.action_navigation:

                return true;
            case R.id.action_project:

                return true;
            case R.id.action_wechat:

                return true;
            //侧滑tab
            case R.id.nav_collect:

                return true;
            case R.id.nav_setting:

                return true;
            case R.id.nav_about_us:

                return true;
            case R.id.nav_logout:

                return true;
            case R.id.nav_night_mode:

                return true;
            case R.id.nav_todo:

                return true;
            default:
                return false;
        }
    }
}


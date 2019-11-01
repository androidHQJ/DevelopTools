package com.wxq.commonlibrary.baserx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.wxq.commonlibrary.R;
import com.wxq.commonlibrary.util.AppManager;
import com.wxq.commonlibrary.weiget.DialogManager;
import com.wxq.commonlibrary.base.BaseView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    private static AllClickInvocationHandler invocationHandler = null;

    public static void click(View v, Consumer<Object> onNext, boolean... isNeedJudgeLogin) {
        Consumer consumer;
        if (isNeedJudgeLogin != null && isNeedJudgeLogin.length > 0 && isNeedJudgeLogin[0]) {
            if (invocationHandler == null) {
                invocationHandler = new AllClickInvocationHandler();
            }
            consumer = (Consumer) invocationHandler.bind(onNext);
        } else {
            consumer = onNext;
        }
        RxView.clicks(v).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(consumer);
    }

    public static void longClick(View v, Consumer<Object> onNext) {
        RxView.longClicks(v).subscribe(onNext);
    }

    private static class AllClickInvocationHandler implements InvocationHandler {

        private Object object = null;

        public Object bind(Object obj) {
            object = obj;
            return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(object, args);
//            if (Global.isLogin) {
//                return method.invoke(object, args);
//            } else {
//                Activity topActivity = AppManager.getInstance().getTopActivity();
//                Context context;
//                Intent intent = new Intent();
//                if (topActivity == null) {
//                    context = Global.application;
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                } else {
//                    context = topActivity;
//                }
//                intent.setClassName(context, Global.loginActivity);
//                if (intent.resolveActivity(context.getPackageManager()) != null) {
//                    context.startActivity(intent);
//                }
//            }
//            return null;
        }
    }

    //定时任务以及循环任务
    private static Disposable mDisposable;

    /**
     * milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     */
    public static void timer(long milliseconds, final IRxNext next) {
        Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }

    /**
     * 每隔milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     */
    public static void interval(long milliseconds, final IRxNext next) {
        Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 取消订阅
     */
    public static void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public interface IRxNext {
        void doNext(long number);
    }

    public static <T> ObservableTransformer<T,T> io_main(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return  upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}

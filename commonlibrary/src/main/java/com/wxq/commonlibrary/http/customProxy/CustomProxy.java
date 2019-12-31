package com.wxq.commonlibrary.http.customProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 高效处理运营打点
 *
 * 嵌套添加动态代理
 * 简例：https://blog.csdn.net/zhenghuangyu/article/details/102808338
 */
public class CustomProxy implements InvocationHandler {
    //被代理对象，在这里就是 Retrofit.create(service) 的返回值
    private Object mTarget;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String from = "testFrom";
        final String methodName = method.getName();

        switch (methodName) {

            case "savePost": {
                //形参是一个bean类，用这种方式

                //获取第一个请求参数args[0]，这是我们定义该接口形参时的bean类
                EditPostRequest editPostRequest = (EditPostRequest) args[0];
                //以变量形式设置
                editPostRequest.setFrom(from);
                break;
            }

            case "likePost": {
                //形参是一个个值的形式，用这种方式

                //将参数长度+1，作为新的参数数组
                args = Arrays.copyOf(args, (args.length + 1));
                //在新的参数数组末端加上 from
                args[args.length - 1] = from;

                //为了调用带 from 版本的方法，构造新的形参
                Class[] newParams = Arrays.copyOf(method.getParameterTypes(), (method.getParameterTypes().length + 1));
                //新的形参里，最后一个参数 from 是String类型的，这个必须声明，才能准确调用反射
                newParams[newParams.length - 1] = String.class;

                //找出新method对象，就是带 from 版本的那个方法
                method = mTarget.getClass().getDeclaredMethod(method.getName(), newParams);
                break;
            }
        }
        //正式执行方法
        return method.invoke(mTarget, args);
    }

    //在这里嵌套外层的动态代理
    public Object newProxy(Object target) {
        this.mTarget = target;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}

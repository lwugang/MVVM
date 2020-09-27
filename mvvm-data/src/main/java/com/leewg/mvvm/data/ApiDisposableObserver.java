package com.leewg.mvvm.data;


import io.reactivex.observers.DisposableObserver;

import com.leewg.mvvm.data.exception.ResponseThrowable;
import com.leewg.mvvm.tools.KLog;
import com.leewg.mvvm.tools.NetworkUtil;
import com.leewg.mvvm.tools.ToastUtils;
import com.leewg.mvvm.tools.Utils;

import java.net.ConnectException;

/**
 * Created by leewg on 2017/5/10.
 * 统一的Code封装处理。该类仅供参考，实际业务逻辑, 根据需求来定义，
 */

public abstract class ApiDisposableObserver<T> extends DisposableObserver<T> {
    public abstract void onResult(T t);

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ResponseThrowable) {
            ResponseThrowable rError = (ResponseThrowable) e;
            ToastUtils.showShort(rError.message);
            return;
        }
        //其他全部甩锅网络异常
        ToastUtils.showShort("网络异常");
    }

    /**
     * 开始执行
     *
     * @param success 是否正常启动
     */
    public void onStart(boolean success) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isCheckNetwork()) {
            boolean networkAvailable = NetworkUtil.isNetworkAvailable(Utils.getContext());
            this.onStart(networkAvailable);
            if (!networkAvailable) {
                onError(new ConnectException());
            }
        } else {
            this.onStart(true);
        }
    }

    /**
     * 网络是否可用校验
     *
     * @return
     */
    protected boolean isCheckNetwork() {
        return true;
    }

    @Override
    public void onNext(Object o) {
        BaseResponse baseResponse = (BaseResponse) o;
        switch (baseResponse.getCode()) {
            case CodeRule.CODE_200:
                //请求成功, 正确的操作方式
                onResult((T) baseResponse.getData());
                break;
            case CodeRule.CODE_220:
                // 请求成功, 正确的操作方式, 并消息提示
                onResult((T) baseResponse.getData());
                break;
            case CodeRule.CODE_300:
                //请求失败，不打印Message
                KLog.e("请求失败");
                ToastUtils.showShort("错误代码:", baseResponse.getCode());
                break;
            case CodeRule.CODE_330:
                //请求失败，打印Message
                ToastUtils.showShort(baseResponse.getMessage());
                break;
            case CodeRule.CODE_500:
                //服务器内部异常
                ToastUtils.showShort("错误代码:", baseResponse.getCode());
                break;
            case CodeRule.CODE_503:
                //参数为空
                KLog.e("参数为空");
                break;
            case CodeRule.CODE_502:
                //没有数据
                KLog.e("没有数据");
                break;
            case CodeRule.CODE_510:
                //无效的Token，提示跳入登录页
                ToastUtils.showShort("token已过期，请重新登录");
                break;
            case CodeRule.CODE_530:
                ToastUtils.showShort("请先登录");
                break;
            case CodeRule.CODE_551:
                ToastUtils.showShort("错误代码:", baseResponse.getCode());
                break;
            default:
                ToastUtils.showShort("错误代码:", baseResponse.getCode());
                break;
        }
    }

    public static final class CodeRule {
        //请求成功, 正确的操作方式
        static final int CODE_200 = 1;
        //请求成功, 消息提示
        static final int CODE_220 = 220;
        //请求失败，不打印Message
        static final int CODE_300 = 300;
        //请求失败，打印Message
        static final int CODE_330 = 330;
        //服务器内部异常
        static final int CODE_500 = 500;
        //参数为空
        static final int CODE_503 = 503;
        //没有数据
        static final int CODE_502 = 502;
        //无效的Token
        static final int CODE_510 = 510;
        //未登录
        static final int CODE_530 = 530;
        //请求的操作异常终止：未知的页面类型
        static final int CODE_551 = 551;
    }
}
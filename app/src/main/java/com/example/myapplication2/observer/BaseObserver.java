package com.example.myapplication2.observer;

import android.accounts.NetworkErrorException;
import android.content.Context;
import com.example.myapplication2.util.response.UniversalResponseBody;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public abstract class BaseObserver<T> implements Observer<UniversalResponseBody<T>> {
    private static final String TAG = "BaseObserver";

    private Context ctx;

    public BaseObserver() {

    }

    public BaseObserver(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(UniversalResponseBody<T> value) {
        onRequestEnd();
        String message_common = "Oops, something went wrong. Please try again.";
        if (value.getErrorCode() == 0) {//成功
            try {
                onSuccess(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (value == null || value.getMessage().equals("")) {
                    onFailure(value,value.getMessage());
                } else {
                    onFailure(value,message_common);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    protected abstract void onSuccess(UniversalResponseBody<T> responseBody);


    protected abstract void onFailure(UniversalResponseBody<T> responseBody,String errMsg);


    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        String message_common = "Oops, something went wrong. Please try again.";
        String msg_timeout = "Oops, connection timeout, please try again later";
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(null,msg_timeout);
            } else {
                onFailure(null,message_common);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    protected void onRequestStart() {
        showProgressDialog();
    }


    /**
     * 请求结束
     */
    protected void onRequestEnd() {
        closeProgressDialog();
    }

    /**
     * 加载弹窗
     */
    public void showProgressDialog() {

    }

    /**
     * 关闭加载弹窗
     */
    public void closeProgressDialog() {

    }

}

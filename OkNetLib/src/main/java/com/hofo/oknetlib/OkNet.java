package com.hofo.oknetlib;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.hofo.oknetlib.https.HttpsUtils;
import com.hofo.oknetlib.interceptor.LoggingInterceptor;
import com.hofo.oknetlib.request.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkNet {

    public static final long DEFAULT_MILLISECONDS = 30 * 1000;      //默认的超时时间
    private final ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();
    //用于在主线程执行的调度器
    private final Handler mDelivery = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient;
    private Application mApplication;

    private OkNet() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new LoggingInterceptor());

        builder.readTimeout(OkNet.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(OkNet.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.connectTimeout(OkNet.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);

        mOkHttpClient = builder.build();
    }

    public static Request get(String url) {
        return new Request(url);
    }

    public static OkNet getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public void init(Application application) {
        mApplication = application;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Handler getDelivery() {
        return mDelivery;
    }

    public void runUiThread(Runnable runnable) {
        mDelivery.post(runnable);
    }

    public ExecutorService getCachedThreadPool() {
        return mCachedThreadPool;
    }

    private static class SingletonInstance {
        private static final OkNet INSTANCE = new OkNet();
    }
}
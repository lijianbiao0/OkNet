package com.hofo.oknetlib.request;

import com.hofo.oknetlib.OkNet;
import com.hofo.oknetlib.callback.Callback;
import com.hofo.oknetlib.exception.HttpException;
import com.hofo.oknetlib.model.HttpParams;
import com.hofo.oknetlib.response.Response;
import com.hofo.oknetlib.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;


public class Request<T> {
    protected HttpParams params = new HttpParams();     //添加的param
    private String mUrl;
    private String mBaseUrl;
    private okhttp3.Request mRequest;
    private Callback<T> mCallback;
    private Call mCall;

    public Request(String url) {
        mUrl = url;
        mBaseUrl = url;
    }

    public Request params(String key, String value, boolean... isReplace) {
        params.put(key, value, isReplace);
        return this;
    }

    public void execute(Callback callback) {
        mCallback = callback;

        OkNet.getInstance().runUiThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onStart();
            }
        });

        mUrl = HttpUtils.createUrlFromParams(mUrl, params.getUrlParamsMap());
        mRequest = generateRequest(mUrl);


        mCall = OkNet.getInstance().getOkHttpClient().newCall(mRequest);
        mCall.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Response<T> error = Response.error(false, call, null, e);
                onError(error);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) {
                int responseCode = response.code();
                if (responseCode == 404 || responseCode >= 500) {
                    Response<T> error = Response.error(false, mCall, response, HttpException.NET_ERROR());
                    onError(error);
                } else {
                    try {
                        T t = mCallback.convertResponse(response);
                        Response<T> success = Response.success(false, t, mCall, response);
                        onSuccess(success);
                    } catch (Throwable e) {
                        Response<T> error = Response.error(false, call, response, e);
                        onError(error);
                    }
                }
            }
        });
    }

    public okhttp3.Request generateRequest(String url) {
        return new okhttp3.Request.Builder().url(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36")
                .build();
    }
    private void onError(final Response<T> error) {
        OkNet.getInstance().runUiThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(error);
                mCallback.onFinish();
            }
        });
    }

    private void onSuccess(final Response<T> success) {
        OkNet.getInstance().runUiThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(success);
                mCallback.onFinish();
            }
        });
    }
}

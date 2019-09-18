package com.hofo.oknetlib.callback;


import com.hofo.oknetlib.converter.Converter;
import com.hofo.oknetlib.response.Response;

public interface Callback<T> extends Converter<T> {
    /**
     * 请求网络开始前，UI线程
     */
    void onStart();

    /**
     * 对返回数据进行操作的回调， UI线程
     *
     * @param response
     */
    void onSuccess(Response<T> response);

    /**
     * 缓存成功的回调,UI线程
     */
    void onCacheSuccess();

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    void onError(Response<T> errResponse);

    /**
     * 请求网络结束后，UI线程
     */
    void onFinish();

    /**
     * 上传过程中的进度回调，get请求不回调，UI线程
     */
    void uploadProgress();

    /**
     * 下载过程中的进度回调，UI线程
     */
    void downloadProgress();

}

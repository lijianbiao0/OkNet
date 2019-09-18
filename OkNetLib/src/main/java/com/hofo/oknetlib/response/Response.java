package com.hofo.oknetlib.response;

import okhttp3.Call;

public class Response<T> {
    private T body;
    private boolean isFromCache;
    private Call rawCall;
    private okhttp3.Response rawResponse;
    private Throwable throwable;public String message() {
        if (rawResponse == null) return null;
        return rawResponse.message();
    }
    public int code() {
        if (rawResponse == null) return -1;
        return rawResponse.code();
    }
    public static <T> Response<T> error(boolean isFromCache, Call rawCall, okhttp3.Response rawResponse, Throwable throwable) {
        Response<T> response = new Response<>();
        response.setFromCache(isFromCache);
        response.setRawCall(rawCall);
        response.setRawResponse(rawResponse);
        response.setException(throwable);
        return response;
    }

    public void setException(Throwable exception) {
        this.throwable = exception;
    }

    public void setFromCache(boolean fromCache) {
        isFromCache = fromCache;
    }

    public void setRawCall(Call rawCall) {
        this.rawCall = rawCall;
    }

    public void setRawResponse(okhttp3.Response rawResponse) {
        this.rawResponse = rawResponse;
    }

    public static <T> Response<T> success(boolean isFromCache, T body, Call rawCall, okhttp3.Response rawResponse) {
        Response<T> response = new Response<>();
        response.setFromCache(isFromCache);
        response.setBody(body);
        response.setRawCall(rawCall);
        response.setRawResponse(rawResponse);
        return response;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public T body() {
        return body;
    }

}

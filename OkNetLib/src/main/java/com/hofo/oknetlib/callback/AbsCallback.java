package com.hofo.oknetlib.callback;

import com.hofo.oknetlib.response.Response;

public abstract class AbsCallback<T> implements Callback<T> {
    @Override
    public void onStart() {
    }

    @Override
    public void onSuccess(Response<T> response) {
    }

    @Override
    public void onCacheSuccess() {
    }

    @Override
    public void onError(Response<T> errResponse) {
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void uploadProgress() {
    }

    @Override
    public void downloadProgress() {
    }


}

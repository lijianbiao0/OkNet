package com.hofo.oknetlib.callback;

import com.hofo.oknetlib.converter.StringConvert;

import okhttp3.Response;

public class StringCallback extends AbsCallback<String> {

    private StringConvert mStringConvert = new StringConvert();

    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = mStringConvert.convertResponse(response);
        response.close();
        return s;
    }
}

package com.hofo.oknetlib.callback;

import com.hofo.oknetlib.converter.StringConvert;

import org.json.JSONObject;

import okhttp3.Response;

public class JSONObjectCallBack extends AbsCallback<JSONObject> {

    private StringConvert mStringConvert = new StringConvert();

    @Override
    public JSONObject convertResponse(Response response) throws Throwable {
        String bodyStr = mStringConvert.convertResponse(response);
        JSONObject jobj = new JSONObject(bodyStr);
        return jobj;
    }
}

package com.hofo.oknetlib.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class HttpParams {
    private static boolean IS_REPLACE = false;
    private LinkedHashMap<String, List<String>> mUrlParamsMap = new LinkedHashMap<>();

    public LinkedHashMap<String, List<String>> getUrlParamsMap() {
        return mUrlParamsMap;
    }

    public static void setIsReplace(boolean isReplace) {
        IS_REPLACE = isReplace;
    }


    private void put(String key, String value, boolean isReplace) {
        if (key != null && value != null) {
            List<String> urlValues = mUrlParamsMap.get(key);
            if (urlValues == null) {
                urlValues = new ArrayList<>();
                mUrlParamsMap.put(key, urlValues);
            }
            if (isReplace) urlValues.clear();
            urlValues.add(value);
        }
    }

    public void put(String key, String value, boolean... isReplace) {
        if (isReplace != null && isReplace.length > 0) {
            put(key, value, isReplace[0]);
        } else {
            put(key, value, IS_REPLACE);
        }
    }
}

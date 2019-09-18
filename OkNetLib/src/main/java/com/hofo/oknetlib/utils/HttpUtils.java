package com.hofo.oknetlib.utils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static String createUrlFromParams(String url, Map<String, List<String>> params) {
        if (params.size() == 0) {
            return url;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);

            if (url.indexOf('?') <= 0) {
                sb.append("?");
            } else if (url.indexOf('&') > 0 && !url.endsWith("&") || url.indexOf('&') < 0) {
                sb.append("&");
            }
            for (Map.Entry<String, List<String>> urlParams : params.entrySet()) {
                List<String> urlValues = urlParams.getValue();
                for (String value : urlValues) {
                    //对参数进行 utf-8 编码,防止头信息传中文
                    String urlValue = URLEncoder.encode(value, "UTF-8");
                    sb.append(urlParams.getKey()).append("=").append(urlValue).append("&");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}

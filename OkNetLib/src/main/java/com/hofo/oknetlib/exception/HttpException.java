package com.hofo.oknetlib.exception;

import com.hofo.oknetlib.response.Response;

public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 8773734741709178425L;

    private int code;                               //HTTP status code
    private String message;                         //HTTP status message
    private transient Response<?> response;         //The full HTTP response. This may be null if the exception was serialized

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Response response) {
        super(getMessage(response));
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }

    private static String getMessage(Response response) {
        return "HTTP " + response.code() + " " + response.message();
    }

    public static HttpException NET_ERROR() {
        return new HttpException("network error! http response code is 404 or 5xx!");
    }

    public static HttpException COMMON(String message) {
        return new HttpException(message);
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public Response<?> response() {
        return response;
    }
}

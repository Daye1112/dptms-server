package com.darren1112.dptms.common.core.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 响应对象工具类
 *
 * @author luyuhao
 * @since 19/12/14 16:49
 */
public class ResponseEntityUtil {

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    public static <T> ResponseEntity<T> badRequest(T body) {
        return ResponseEntity.badRequest()
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .body(body);
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .body(body);
    }

    public static <T> ResponseEntity<T> custom(HttpStatus status, T body) {
        return ResponseEntity.status(status)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .body(body);
    }

    public static <T> ResponseEntity<T> internalServerError(T body) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .body(body);
    }

    public static <T> ResponseEntity<T> forbidden(T body) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .body(body);
    }

    public static <T> ResponseEntity<T> unauthorized(T body) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .body(body);
    }

    public static <T> ResponseEntity<T> ok(HttpHeaders headers, T body) {
        return ResponseEntity.ok()
                .headers(headers)
                .body(body);
    }
}

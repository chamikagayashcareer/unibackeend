package com.uni.unibakeend.common;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        Instant timestamp) {

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<T>(true, data, message, Instant.now());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<T>(false, null, message, null);
    }
}

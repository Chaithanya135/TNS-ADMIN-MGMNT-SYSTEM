package com.placement.admin.dto.common;

public class ApiResponse<T> {

    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static <T> ApiResponse<T> of(String message) {
        return new ApiResponse<>(message, null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

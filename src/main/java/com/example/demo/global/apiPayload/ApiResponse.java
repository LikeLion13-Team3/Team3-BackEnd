
package com.example.demo.global.apiPayload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;  // "success" or "error"
    private String message; // 응답 메시지
    private T data;         // 실제 응답 데이터

    public static <T> ApiResponse<T> onSuccess(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> onFailure(String message, T data) {
        return new ApiResponse<>("error", message, data);
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>("error", "[" + code + "] " + message, data);
    }
}

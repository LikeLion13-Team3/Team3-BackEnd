
package com.example.demo.global.apiPayload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;  // "success", "fail", "error"
    private String message;
    private T data;

    // 성공 응답
    public static <T> ApiResponse<T> onSuccess(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }

    // 일반 실패 응답
    public static <T> ApiResponse<T> onFailure(String message, T data) {
        return new ApiResponse<>("error", message, data);
    }

    // 코드 포함 실패 응답
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>("error", "[" + code + "] " + message, data);
    }

    // 간단 실패 (data 없이)
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("error", message, null);
    }

    // 실패 상태명 "fail"로 분리
    public static <T> ApiResponse<T> fail(String message, T data) {
        return new ApiResponse<>("fail", message, data);
    }
}

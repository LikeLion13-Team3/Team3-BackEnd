package com.example.demo.global.apiPayload.code;

import com.example.demo.global.apiPayload.ApiResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

    default ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(getCode(), getMessage(), null);
    }
}
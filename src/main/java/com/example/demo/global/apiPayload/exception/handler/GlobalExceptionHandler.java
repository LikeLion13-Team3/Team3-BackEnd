package com.example.demo.global.apiPayload.exception.handler;

import com.example.demo.domain.user.exception.UsernameNotFoundException;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.code.BusinessException;
import com.example.demo.global.apiPayload.code.GeneralErrorCode;
import com.example.demo.global.apiPayload.exception.CustomException;
import com.example.demo.global.apiPayload.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        log.warn("[BusinessException] {}: {}", ex.getErrorCode().getCode(), ex.getErrorCode().getMessage());
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(ApiResponse.onFailure(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage(), null));
    }
    //애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        log.warn("[CustomException] {}", ex.getCode().getMessage());
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ex.getCode().getErrorResponse());
    }

    // 리소스 없음: NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException ex) {
        log.warn("[NotFoundException] {}", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.NOT_FOUND_404;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode.getCode(), ex.getMessage(), null));
    }

    // 스프링 시큐리티 UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.warn("[UsernameNotFoundException] {}", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.NOT_FOUND_404;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode.getCode(), ex.getMessage(), null));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleAllException(Exception ex) {
        log.error("[Exception] Internal Server Error: {}", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        ApiResponse<String> errorResponse = ApiResponse.onFailure(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorResponse);
    }
}
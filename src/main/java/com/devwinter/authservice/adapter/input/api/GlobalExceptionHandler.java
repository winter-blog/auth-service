package com.devwinter.authservice.adapter.input.api;

import com.devwinter.authservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.authservice.application.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseResponse<?>> userExceptionHandler(AuthException e) {
        log.error("AuthException: ", e);

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                             .body(BaseResponse.error(e.getErrorCode().getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<MultiValueMap<String, String>>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidExceptionHandler: ", e);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errors.add(fieldError.getField(), fieldError.getDefaultMessage());
        }

        BaseResponse<MultiValueMap<String, String>> messageBody = BaseResponse.error("필드 유효성 검사에 실패하였습니다.", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(messageBody);
    }
}

package com.uno.getinline.controller.error;

import com.uno.getinline.constant.ErrorCode;
import com.uno.getinline.dto.APIErrorResponse;
import com.uno.getinline.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class) // API 대상으로만 에러를 잡기위해서 설정
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
// extends ResponseEntityExceptionHandler --> spring web에서 나타나는 error 상속

    @ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;


//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                                false,
//                                errorCode,
//                                errorCode.getMessage(e)
//                        )
//                );
        return super.handleExceptionInternal(
                e,
                // body 구현
                APIErrorResponse.of(
                        false,
                        errorCode.getCode(),
                        errorCode.getMessage(e)
                ),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                                false,
//                                errorCode
//                        )
//                );
        return super.handleExceptionInternal(
                e,
                // body 구현
                APIErrorResponse.of(
                        false,
                        errorCode.getCode(),
                        errorCode.getMessage(e)
                ),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // SPRING_BAD_REQUEST -- SPRING ERROR 구분
        ErrorCode errorCode = status.is4xxClientError() ?
                ErrorCode.SPRING_BAD_REQUEST :
                ErrorCode.SPRING_INTERNAL_ERROR;

        return super.handleExceptionInternal(
                ex,
                // body 구현
                APIErrorResponse.of(
                        false,
                        errorCode.getCode(),
                        errorCode.getMessage(ex)
                ),
                headers,
                status,
                request
        );
    }
}

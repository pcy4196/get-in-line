package com.uno.getinline.controller.error;

import com.uno.getinline.constant.ErrorCode;
import com.uno.getinline.dto.APIErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class BasicErrorController implements ErrorController {

    // implements ErrorController Error 피에지 컨트롤 하기 위해 ErrorController inerface 구현 필요
    @RequestMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletResponse response) {
        // response의 값을 받아서 Status, ErroCode 값 반환
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return new ModelAndView(
                "error",
                Map.of(
                        "statusCode", status.value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage(status.getReasonPhrase())
                ),
                status
        );
    }

    @RequestMapping("/error")
    public ResponseEntity<APIErrorResponse> error(HttpServletResponse response) {
        // response의 값을 받아서 Status, ErroCode 값 반환
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false,
                        errorCode
                )
        );
    }
}

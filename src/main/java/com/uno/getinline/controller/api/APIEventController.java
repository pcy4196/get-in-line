package com.uno.getinline.controller.api;

import com.uno.getinline.constant.ErrorCode;
import com.uno.getinline.dto.APIErrorResponse;
import com.uno.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public List<String> getEvents() throws Exception {
//        throw new GeneralException("테스트 메시지");
        throw new HttpRequestMethodNotSupportedException("GET");
//        return List.of("event1", "event2");
    }

    @PostMapping("/events")
    public Boolean createEvent() {
        throw new RuntimeException("runtime 테스트 메시지");
//        return true;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@PathVariable Integer eventId) {
        throw new GeneralException("테스트 메시지");
//        return "event" + eventId;
    }

    @PutMapping("/events/{eventId}")
    public Boolean modifyEvent(@PathVariable Integer eventId) {
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean deleteEvent(@PathVariable Integer eventId) {
        return true;
    }

//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e) {
//        ErrorCode errorCode = e.getErrorCode();
//        HttpStatus status = errorCode.isClientSideError() ?
//                HttpStatus.BAD_REQUEST :
//                HttpStatus.INTERNAL_SERVER_ERROR;
//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(
//                        false,
//                        errorCode,
//                        errorCode.getMessage(e)
//                ));
//    }
}

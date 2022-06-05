package com.uno.getinline.dto;

import com.uno.getinline.constant.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.assertj.core.api.Assertions.assertThat;

class APIDataResponseTest {

    @DisplayName("문자열 데이터가 주어지면, 표준 성공 응답을 생성한다.")
    @Test
    void givenStringData_whenCreatingResponse_thenReturnsSuccessfulResponse() {
        // Given
        String data = "test data";

        // When
        APIDataResponse<String> response =  APIDataResponse.of(
                data
        );

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data", data)
        ;
    }

    @DisplayName("데이터가 없을때, 비어있는 표준 성공 응답을 생성한다.")
    @Test
    void givenNoting_whenCreatingResponse_thenReturnsSuccessfulResponse() {
        // Given

        // When
//        APIDataResponse<String> response =  APIDataResponse.of(null);
        APIDataResponse<String> response =  APIDataResponse.empty();        // empty 메서드 작성

        // Then
        assertThat(response)
                .hasFieldOrPropertyWithValue("success", true)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message", ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data", null)
        ;
    }
}
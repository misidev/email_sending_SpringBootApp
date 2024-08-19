package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.STATUS_SUCCESS;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
class ErrorResponseTestSmall {
    @Test
    void testEmailConstructorAndGetters() {
        ErrorResponse.Error error =  ErrorResponse.Error.builder()
                .status(STATUS_SUCCESS)
                .code(CODE_OK)
                .message(TEST_ERROR_MESSAGE)
                .details(TEST_DETAILS)
                .build();

        ErrorResponse errorResponse = ErrorResponse.builder().error(error).build();

        assertEquals(STATUS_SUCCESS, errorResponse.getError().getStatus());
        assertEquals(CODE_OK, errorResponse.getError().getCode());
        assertEquals(TEST_ERROR_MESSAGE, errorResponse.getError().getMessage());
        assertEquals(TEST_DETAILS, errorResponse.getError().getDetails());
    }

    @Test
    void testSetters() {
        ErrorResponse.Error error = ErrorResponse.Error.builder().build();
        error.setStatus(STATUS_SUCCESS);
        error.setCode(CODE_OK);
        error.setMessage(TEST_ERROR_MESSAGE);
        error.setDetails(TEST_DETAILS);

        ErrorResponse errorResponse = ErrorResponse.builder().build();
        errorResponse.setError(error);

        assertEquals(STATUS_SUCCESS, errorResponse.getError().getStatus());
        assertEquals(CODE_OK, errorResponse.getError().getCode());
        assertEquals(TEST_ERROR_MESSAGE, errorResponse.getError().getMessage());
        assertEquals(TEST_DETAILS, errorResponse.getError().getDetails());
    }

}

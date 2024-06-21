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
        ErrorResponse.Error error = new ErrorResponse.Error(STATUS_SUCCESS,
                CODE_OK,
                TEST_ERROR_MESSAGE,
                TEST_DETAILS);

        ErrorResponse errorResponse = new ErrorResponse(error);

        assertEquals(STATUS_SUCCESS, errorResponse.getError().getStatus());
        assertEquals(CODE_OK, errorResponse.getError().getCode());
        assertEquals(TEST_ERROR_MESSAGE, errorResponse.getError().getMessage());
        assertEquals(TEST_DETAILS, errorResponse.getError().getDetails());
    }

    @Test
    void testSetters() {
        ErrorResponse.Error error = new ErrorResponse.Error(null, null, null, null);
        error.setStatus(STATUS_SUCCESS);
        error.setCode(CODE_OK);
        error.setMessage(TEST_ERROR_MESSAGE);
        error.setDetails(TEST_DETAILS);

        ErrorResponse errorResponse = new ErrorResponse(null);
        errorResponse.setError(error);

        assertEquals(STATUS_SUCCESS, errorResponse.getError().getStatus());
        assertEquals(CODE_OK, errorResponse.getError().getCode());
        assertEquals(TEST_ERROR_MESSAGE, errorResponse.getError().getMessage());
        assertEquals(TEST_DETAILS, errorResponse.getError().getDetails());
    }

}

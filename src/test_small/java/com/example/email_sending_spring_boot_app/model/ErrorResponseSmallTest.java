package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseSmallTest {
    @Test
    void testEmailConstructorAndGetters() {
        ErrorResponse.Error error=new ErrorResponse.Error(ApplicationConstants.STATUS_SUCCESS,
                ApplicationConstants.CODE_OK,
                ApplicationConstants.TEST_ERROR_MESSAGE,
                ApplicationConstants.TEST_DETAILS);

        ErrorResponse errorResponse=new ErrorResponse(error);

        assertEquals(ApplicationConstants.STATUS_SUCCESS, errorResponse.getError().getStatus());
        assertEquals(ApplicationConstants.CODE_OK, errorResponse.getError().getCode());
        assertEquals(ApplicationConstants.TEST_ERROR_MESSAGE,errorResponse.getError().getMessage());
        assertEquals(ApplicationConstants.TEST_DETAILS, errorResponse.getError().getDetails());
    }

    @Test
    void testSetters() {
        ErrorResponse.Error error=new ErrorResponse.Error(null,null,null,null);
        error.setStatus(ApplicationConstants.STATUS_SUCCESS);
        error.setCode(ApplicationConstants.CODE_OK);
        error.setMessage(ApplicationConstants.TEST_ERROR_MESSAGE);
        error.setDetails(ApplicationConstants.TEST_DETAILS);


        ErrorResponse errorResponse=new ErrorResponse(null);
        errorResponse.setError(error);

        assertEquals(ApplicationConstants.STATUS_SUCCESS, errorResponse.getError().getStatus());
        assertEquals(ApplicationConstants.CODE_OK, errorResponse.getError().getCode());
        assertEquals(ApplicationConstants.TEST_ERROR_MESSAGE,errorResponse.getError().getMessage());
        assertEquals(ApplicationConstants.TEST_DETAILS, errorResponse.getError().getDetails());
    }
}

package com.example.email_sending_spring_boot_app.advice;


import com.example.email_sending_spring_boot_app.exception.UsernameException;
import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.LOGG_EXCEPTION;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private HandleDbInputAndResponses handleDBInputAndResponses;

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        ErrorResponse errorResponse = handleDBInputAndResponses.handleInternalServerError(ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Username already exists
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
    }

    //Error while saving user in DB
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(handleDBInputAndResponses.handleInternalServerError(ex.getClass().getSimpleName()));
    }

    //Error while saving user in DB
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(handleDBInputAndResponses.handleBadRequest(ex.getClass().getSimpleName()));
    }

    //Error while saving user in DB
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(handleDBInputAndResponses.handleInternalServerError(ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(handleDBInputAndResponses.handleInternalServerError(ex.getClass().getSimpleName()).toString());
    }

    @ExceptionHandler(MailPreparationException.class)
    public ResponseEntity<ErrorResponse> handleMailPreparationException(MailPreparationException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(handleDBInputAndResponses.handleBadRequest(ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ErrorResponse> handleMessagingException(MessagingException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(handleDBInputAndResponses.handleInternalServerError(ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(MailParseException.class)
    public ResponseEntity<ErrorResponse> handleMessagingException(MailParseException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(handleDBInputAndResponses.handleInternalServerError(ex.getClass().getSimpleName()));
    }

    //Problem with app password
    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleMailAuthenticationException(MailAuthenticationException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(handleDBInputAndResponses.handleUnauthorized(ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(UsernameException.class)
    public ResponseEntity<ErrorResponse> handleUsernameException(UsernameException ex) {
        LOGGER.info(LOGG_EXCEPTION, ex.getClass().getSimpleName(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(handleDBInputAndResponses.handleUsernameNotFound(ex.getClass().getSimpleName()));
    }

}

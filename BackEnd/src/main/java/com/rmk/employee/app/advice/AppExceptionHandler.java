package com.rmk.employee.app.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rmk.employee.app.exception.AppException;
import com.rmk.employee.app.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException appException) {
        ObjectNode error = new ObjectMapper().createObjectNode();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("error", appException.getErrorMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AppConstants.INTERNAL_SERVER_ERR_MSG);
    }

}

package com.rmk.employee.app.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;

public class AppException extends RuntimeException {

    private String message;

    public AppException(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }

}

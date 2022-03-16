package com.onboarding.api.controller.exception;

public class QueryLimitExceededException extends RuntimeException {

    public QueryLimitExceededException(String message) {
        super(message);
    }
}
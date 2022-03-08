package com.zenhomes.Consumption.exception;

public class ErrorDetail {
    private String errorMessage;
    private int errorCode;

    public ErrorDetail(final String errorMessage, final int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ErrorDetail() {}

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }
}

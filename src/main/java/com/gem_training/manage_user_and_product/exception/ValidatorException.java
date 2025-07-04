package com.gem_training.manage_user_and_product.exception;

public class ValidatorException extends RuntimeException {
    private boolean isErrorCode = true;
    public ValidatorException(String message, boolean isErrorCode) {
        super(message);
        this.isErrorCode = isErrorCode;
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public boolean isErrorCode() {
        return isErrorCode;
    }

    public void setErrorCode(boolean errorCode) {
        isErrorCode = errorCode;
    }
}

package com.project.ks8.simpleexample.domain.exception;

public class ProductNotFound extends RuntimeException {

    public ProductNotFound(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public ProductNotFound(final String msg) {
        super(msg);
    }
}

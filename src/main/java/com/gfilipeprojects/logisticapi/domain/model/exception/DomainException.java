package com.gfilipeprojects.logisticapi.domain.model.exception;

import java.io.Serial;

public class DomainException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 576578223863485126L;

    public DomainException(String message){
        super(message);
    }

}

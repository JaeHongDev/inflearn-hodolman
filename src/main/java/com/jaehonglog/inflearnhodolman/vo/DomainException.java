package com.jaehonglog.inflearnhodolman.vo;

import java.util.concurrent.ExecutionException;
import lombok.Getter;

@Getter
public class DomainException extends ExecutionException {

    private final int domainExceptionCode;
    public DomainException(DomainExceptionCode code) {
        super(code.getMessage());
        this.domainExceptionCode = code.getCode();
    }
}

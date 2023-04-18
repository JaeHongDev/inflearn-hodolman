package com.jaehonglog.inflearnhodolman.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DomainExceptionCode {

    FIRST(1000, "message");

    private final int code;
    private final String message;

    public DomainException newError(){
        return new DomainException(this);
    }
}

package com.jaehonglog.inflearnhodolman.response;


import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> validation = new LinkedHashMap<>();

    public void addValidation(String key, String value){
        validation.put(key, value);
    }

}

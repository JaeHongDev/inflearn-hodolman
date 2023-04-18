package com.jaehonglog.inflearnhodolman.response;


import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public final class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> validation = new LinkedHashMap<>();

    @Builder
    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String key, String value){
        validation.put(key, value);
    }

}

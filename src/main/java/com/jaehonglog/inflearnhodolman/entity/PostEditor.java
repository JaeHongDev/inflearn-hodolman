package com.jaehonglog.inflearnhodolman.entity;


import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

@Builder
public record PostEditor (
        String title,
        String content
){}

package com.wsousa.demo.logger;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Builder
@ToString
@Component
public class Log5wBuilder {

    protected static Method method;
    protected static WhatIsHappening whatIsHappening;
    protected static Map<String, String> infoAdd = new HashMap<>();

    public static Method loadMethod(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        method = new Method(stackTrace[2].getClassName().concat("#").concat(stackTrace[2].getMethodName()));
        return method;
    }



}

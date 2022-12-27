package com.wsousa.demo.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Method {
    public Method(String methodText){
        this.methodText = methodText;
    }
    private String clazz;
    private String methodText;
    protected static WhatIsHappening whatIsHappening;
    private Map<String, String> infoAdd = new LinkedHashMap<>();

    public static WhatIsHappening loadWhatIsHappening(String whatIsHappeningTxt){
        Assert.hasText(whatIsHappeningTxt, "O momento não pode ser vazio");
        whatIsHappening = new WhatIsHappening(whatIsHappeningTxt);
        return whatIsHappening;
    }

}

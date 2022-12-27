package com.wsousa.demo.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class WhatIsHappening {

    public WhatIsHappening(String whatIsHappeningText){
        this.whatIsHappeningText = whatIsHappeningText;
    }

    private String whatIsHappeningText;
    protected static Map<String, String> infoAdd = new LinkedHashMap<>();

    public static ReadyToLog loadAddInfo(String key, String info){
        Assert.hasText(key, "A chave não pode ser vazia");
        Assert.hasText(info, "A Informação não pode ser vazia");
      //  Assert.isNull(infoAdd.get(key), "A chave já foi adicionada");
        infoAdd.put(key, info);
        return new ReadyToLog();
    }

}

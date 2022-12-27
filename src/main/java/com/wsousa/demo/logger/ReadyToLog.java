package com.wsousa.demo.logger;

import lombok.*;
import org.slf4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReadyToLog {
    protected Method method;
    protected WhatIsHappening whatIsHappening;
    protected Map<String, String> infoAdd = new HashMap<>();

    public Logger info(@NonNull Logger logger){
        setMethod(Log5wBuilder.method);
        setWhatIsHappening(Method.whatIsHappening);
        setInfoAdd(WhatIsHappening.infoAdd);
        logger.info(readLog());
        return logger;
    }

    public String readLog() {
        ResponseLog responseLog = new ResponseLog();
        responseLog.setTimestamp(new Date());
        responseLog.setMethod(method.getMethodText());
        responseLog.setClazz(method.getMethodText());
        responseLog.setWhatIsHappening(whatIsHappening.getWhatIsHappeningText());
        responseLog.setInfos(getInfo());
        return responseLog.toString();
    }

    private String getInfo(){
        String info = "";
        for (String value : infoAdd.values()) {
            System.out.println(String.format("value: %s", value));
            info = info.concat(value);
        }
        return info;
    }


}

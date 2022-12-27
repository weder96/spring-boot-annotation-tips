package com.wsousa.demo.logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLog {
    private Date timestamp;
    private String clazz;
    private String method;
    private String infos;
    private String whatIsHappening;
}

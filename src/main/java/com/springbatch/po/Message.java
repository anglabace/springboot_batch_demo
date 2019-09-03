package com.springbatch.po;

import lombok.Data;

import java.util.Date;

/**
 * @author xiongchenyang
 * @Date 2019/9/2
 **/
@Data
public class Message {

    private Long id;

    private int code;

    private String msg;

    private Date startTime;

    private Date sendTime;

    private String logPath;
}

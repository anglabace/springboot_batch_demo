package com.springbatch.controller;

import com.springbatch.demo.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongchenyang
 * @Date 2019/9/2
 **/
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaSender sender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendKafka(String message) {
        try {
            log.info("kafka的消息={}", message);
            sender.send(message);
            log.info("发送kafka成功.");
            return "success";
        } catch (Exception e) {

            log.error("发送kafka失败", e);
            return "fail";
        }
    }
}
package com.springbatch.demo;

import com.alibaba.fastjson.JSON;
import com.springbatch.constant.BatchCnst;
import com.springbatch.po.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author xiongchenyang
 * @Date 2019/9/2
 **/
@Slf4j
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {BatchCnst.BATCH_TOPIC})
    public void listen(String message){
        log.info("--------------接收消息 message = {}",message);
//        Message msg = JSON.parseObject(message, Message.class);
//        log.info("MessageConsumer: onMessage: message is: [" + msg + "]");
        log.info("------------------ message =" + message);

    }
}

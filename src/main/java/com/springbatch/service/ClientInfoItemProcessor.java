package com.springbatch.service;

import com.springbatch.po.ClientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author xiongchenyang
 * @Date 2019/8/27
 **/
public class ClientInfoItemProcessor implements ItemProcessor<ClientInfo,ClientInfo> {

    public  final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientInfo process(ClientInfo item) throws Exception {
        final String sex = item.getSex();
        final String idType = item.getIdType();
        if("M".equals(sex)){
            item.setSex("女");
        }else{
            item.setSex("男");
        }
        if("01".equals(idType)){
            item.setIdType("身份证");
        }
//        if("六二".equals(item.getClientName())){
//            System.exit(0);
//        }
        log.info("Converting (" + item + ")");
        return item;
    }
}

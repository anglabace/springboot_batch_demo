package com.springbatch.service;

import com.springbatch.po.ClientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author xiongchenyang
 * @Date 2019/8/27
 **/
public class ClientInfoItemProcessor2 implements ItemProcessor<ClientInfo,ClientInfo> {

    public  final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientInfo process(ClientInfo item) throws Exception {
        final String sex = item.getSex();
        final String idType = item.getIdType();
        if("女".equals(sex)){
            item.setSex("FEMALE");
        }else{
            item.setSex("MALE");
        }
        if("身份证".equals(idType)){
            item.setIdType("身份证件");
        }
        return item;
    }
}

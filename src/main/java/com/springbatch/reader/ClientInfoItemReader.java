package com.springbatch.reader;

import com.springbatch.po.ClientInfo;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author xiongchenyang
 * @Date 2019/9/2
 **/
public class ClientInfoItemReader implements ItemReader<ClientInfo> {
    @Override
    public ClientInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}

package com.springbatch.demo;

import com.springbatch.po.ClientInfo;
import com.springbatch.po.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiongchenyang
 * @Date 2019/8/27
 **/
@Component
public class ClientInfoNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(ClientInfoNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientInfoNotificationListener(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!!CLIENTINFO JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT * FROM client_info",
                    (rs, row) -> new ClientInfo(
                            rs.getString(1),
                            rs.getString(2))
            ).forEach(person -> {
                log.info("Found <" + person + "> in the database.");});
        }
    }
}

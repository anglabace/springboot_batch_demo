package com.springbatch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiongchenyang
 * @Date 2019/8/27
 **/
@Slf4j
@RestController
@RequestMapping("/clientInfo")
public class ClientInfoController {

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job importClientInfoJob;
    @Resource
    private SimpleJobOperator jobOperator;
    @Resource
    private JobExplorer jobExplorer;

    @RequestMapping("runJob")
    public String runJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        jobLauncher.run(importClientInfoJob,new JobParametersBuilder().addString("myDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).toJobParameters());
        return "success!";
    }

    @RequestMapping("stopJob")
    public String stopJob() throws NoSuchJobException {
        Set<Long> jobIdSet = jobOperator.getRunningExecutions("importClientInfoJob");
        if(jobIdSet.size()>0){
            jobIdSet.forEach(i-> {
                    JobExecution jobExecution = jobExplorer.getJobExecution(i);
                    jobExecution.stop();
                    log.info("clienInfoJob stopJob id:{}",i);
            });
        }


        return "success!";
    }
}

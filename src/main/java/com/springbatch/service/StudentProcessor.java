package com.springbatch.service;

import com.springbatch.po.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;


/**
 * @author xiongchenyang
 * @Date 2019/8/23
 **/
public class StudentProcessor implements ItemProcessor<Student,Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentProcessor.class);

    @Override
    public Student process(Student item) throws Exception {
        String name = item.getName().toUpperCase();
        Integer age = item.getAge() + 12;
        Student student = new Student(name,age, LocalDateTime.now(),LocalDateTime.now());
        log.info("Converting("+ item+") into (" + student+")");
        return student;
    }
}

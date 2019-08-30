package com.springbatch.dao;

import com.springbatch.po.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author xiongchenyang
 * @Date 2019/8/22
 **/

public interface StudentDao extends CrudRepository<Student, Integer> , JpaSpecificationExecutor<Student> {

}

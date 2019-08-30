package com.springbatch.dao;

import com.springbatch.po.ClientInfo;
import com.springbatch.po.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xiongchenyang
 * @Date 2019/8/22
 **/

public interface ClientInfoDao extends CrudRepository<ClientInfo, Integer> , JpaSpecificationExecutor<ClientInfo> {

}

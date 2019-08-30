package com.springbatch.demo;

import com.springbatch.DemoApplication;
import com.springbatch.dao.StudentDao;
import com.springbatch.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private StudentDao studentDao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSave(){
		Student student = new Student();
		student.setAge(11);
		student.setName("熊晨阳");
		student.setCreatedAt(LocalDateTime.now());
		student.setUpdateAt(LocalDateTime.now());
		studentDao.save(student);
	}

}

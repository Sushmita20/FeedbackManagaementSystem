package com.cg.fms.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FeedbackException;

public class UserDaoImplTest {
	
	static UserDaoImpl userdaoimpl;
	static Employee employee;
	

	@BeforeClass
	public static void intialize(){
		System.out.println("test");
		userdaoimpl = new UserDaoImpl();
		employee = new Employee();
		
		
	}

	@Test
	public void testgetUserByName() throws FeedbackException{
		assertNotNull(userdaoimpl.getUserById(1001));
	}
	
	@Test
	public void testgetEmployeeDetails() throws FeedbackException {
		assertNotNull(userdaoimpl.getEmployeeDetails(10001));
	}

}

package com.cg.fms.dao;

import java.util.List;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FeedbackException;


public interface IUserDao {

	Employee getUserById(int employeeId) throws FeedbackException;

	List<Employee> getEmployeeDetails(int employeeId) throws FeedbackException;
}

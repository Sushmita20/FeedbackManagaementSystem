package com.cg.fms.service;

import java.util.List;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FeedbackException;

public interface IUserService {
	public String getRole(int employeeId,String password) throws FeedbackException;
	public List<Employee> getEmployeeDetails(int employeeId) throws FeedbackException;
}

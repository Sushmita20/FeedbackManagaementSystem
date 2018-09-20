package com.cg.fms.service;

import java.util.List;

import com.cg.fms.bean.Employee;
import com.cg.fms.dao.IUserDao;
import com.cg.fms.dao.UserDaoImpl;
import com.cg.fms.exception.FeedbackException;


public class UserServiceImpl implements IUserService{

	private IUserDao dao = new UserDaoImpl();
	
	@Override
	public String getRole(int employeeId, String password) throws FeedbackException {
		String role=null;
		Employee employee = dao.getUserById(employeeId);
		if(employee==null)
			throw new FeedbackException("No Such Employee Found");
		else if(!password.equals(employee.getPassword())){
			throw new FeedbackException("Password Mismatch");
		}
		else
			role=employee.getRole();
		return role;
	}

	@Override
	public List<Employee> getEmployeeDetails(int employeeId)
			throws FeedbackException {
		return dao.getEmployeeDetails(employeeId);
	}

}

package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.util.DBConnection;
import com.cg.fms.util.SQLQueries;


public class UserDaoImpl implements IUserDao{

	Connection connection;
	@Override
	public Employee getUserById(int employeeId) throws FeedbackException {
		
		final	Logger LOGGER = Logger.getLogger(TrainingProgramDaoImpl.class);
		Employee employee = null;
		connection = DBConnection.getInstance();
		try(
				
				PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_USER_QUERY)
			){
			
			preparedStatement.setInt(1, employeeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				employee = new Employee();
				employee.setEmployeeId(resultSet.getString(1));
				employee.setPassword(resultSet.getString(2));
				employee.setRole(resultSet.getString(3));
			}
		} catch (SQLException e) {
			LOGGER.error("SQL Exception occured!");
			throw new FeedbackException("Unable To FEtch Records");
		}
		System.out.println(employee);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeDetails(int employeeId) throws FeedbackException {
		// TODO Auto-generated method stub
		
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = null;
		try {
			System.out.println("1");
			 connection = DBConnection.getInstance();
			 System.out.println("2");
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Employee_Details_Query);
			System.out.println("3");
			preparedStatement.setInt(1, employeeId);
			System.out.println("4");
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet.next());
			while (resultSet.next()) {
				employee = new Employee();
				employee.setEmployeeId(resultSet.getString(1));
				System.out.println(employee.getEmployeeId());
				employee.setEmployeeName(resultSet.getString(2));
				employeeList.add(employee);
				System.out.println(employeeList);
			}
		} catch (SQLException exception) {
			throw new FeedbackException("Error in fetching Employee details with Id "+employeeId);
		}
		
		return employeeList;
	}

	
}

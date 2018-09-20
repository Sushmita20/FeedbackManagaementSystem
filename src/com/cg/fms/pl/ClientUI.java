package com.cg.fms.pl;

import java.util.List;
import java.util.Scanner;

import com.cg.fms.bean.Employee;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.service.IUserService;
import com.cg.fms.service.UserServiceImpl;

public class ClientUI {

public static void main(String[] args) {
	
	
	List<Employee> employeeList;
	Scanner scanner = new Scanner(System.in);
	IUserService userService=new UserServiceImpl();
	String employeeName=null;
	int choice = -1;
	int loginAttempts=0;
	while(choice!=2 && loginAttempts<3){
		System.out.println("Select your choice");
		System.out.println("--------------------");
		System.out.print("[1]Login\t [2]Quit >");
		choice = scanner.nextInt();
		if(choice==1){
			System.out.print("Enter Employee Id ");
			int employeeId = scanner.nextInt();
			System.out.println(employeeId);
			System.out.print("Enter password ");
			String password = scanner.next();
			System.out.println(password);
			loginAttempts++;
			try {
				
				employeeList=userService.getEmployeeDetails(employeeId);
				for (Employee employee : employeeList) {
					employeeName=employee.getEmployeeName();
					System.out.println(employeeName);
				}
				String role = userService.getRole(employeeId, password);
				System.out.println("Role of the employee: "+role);
				if("admin".equals(role)){
					AdminConsole adminConsole = new AdminConsole();
					adminConsole.adminFunctions();
				}else if("coordinator".equals(role)){
					CoordinatorConsole coordinatorConsole = new CoordinatorConsole();
					coordinatorConsole.coordinatorFunctions();
				}
				else if("participant".equals(role))
				{
					ParticipantConsole participantConsole = new ParticipantConsole();
					participantConsole.participantFunctions(employeeId,employeeName);
				}
				else
				{
					System.out.println("Employee role not found");
				}
			} catch (FeedbackException exception) {
				System.err.println(exception.getMessage());
			}				
		}
		if(choice!=1 && choice!=2){
			System.out.println("Select correct choice");
		}
	}
	scanner.close();
	System.out.println("Multiple Incorrect login Attempts!!!Please try later.");
}	
}

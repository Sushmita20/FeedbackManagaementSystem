package com.cg.fms.pl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.fms.bean.Course;
import com.cg.fms.bean.FacultySkill;
import com.cg.fms.bean.Feedback;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.service.CourseServiceImpl;
import com.cg.fms.service.FacultySkillServiceImpl;
import com.cg.fms.service.FeedbackServiceImpl;
import com.cg.fms.service.ICourseService;
import com.cg.fms.service.IFacultySkillService;
import com.cg.fms.service.IFeedbackService;

public class AdminConsole {

	IFacultySkillService facultySkillService = new FacultySkillServiceImpl();
	Course courseDto = new Course();
	ICourseService courseServiceImpl = new CourseServiceImpl();
	public void adminFunctions() throws FeedbackException {
		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		char value='a';
		do{
			
		System.out.println("Select your choice\n[1]View Skill Set  of Faculty\n[2]Course Maintainance\n[3]View Feedback Reorts\n[4]Exit");
		choice = scanner.nextInt();
		switch(choice){
		case 1:
			System.out.println("Skill Set of Faculty: ");
			  ArrayList<FacultySkill> facultySkillList = facultySkillService.showFacultySkillSet();
			  for (FacultySkill facultySkill : facultySkillList) {
					/*System.out.println(facultySkill.getFacultyId() + "     "
							+ facultySkill.getSkillSet());*/
				  System.out.println(facultySkill);
							}
			break;
		case 2:
			int option=-1;
			char question ='z';
			do {
			System.out.println("Select your choice:\n"
					+ "[1].Add course details\n"
					+ "[2].View course details(with course Id)\n"
					+ "[3].Display course details\n"
					+ "[4].Update course details\n"
					+ "[5].Delete course details\n"
					+ "[6].Go to Main Menu\n"
					+ "[7].Exit");
			option = scanner.nextInt();
			switch(option)
			{
			case 1:
				System.out.println("Enter Name of  the Course:");
				courseDto.setCourseName(scanner.next());
				System.out.println("Enter Duration of the course:");
				courseDto.setNoOfDays(scanner.next());
				try {
					String courseId = courseServiceImpl
							.addCourse(courseDto);
					System.out.println(" Course details with Id "
							+ courseId+" inserted successfully");
				} catch (FeedbackException e) {
					System.err.println(e.getMessage());
				}
				break;
				
		case 2:
			System.out.println("Enter Course Id");
			String courseId = scanner.next();
			courseDto = courseServiceImpl.viewCourseDetails(courseId);
			try {
				if (courseDto != null) {
					System.out.println("Course Name :"
							+ courseDto.getCourseName());
					
					System.out.println("Course Duration:"
							+ courseDto.getNoOfDays()+"days");
				} else {
						
					System.out.println("Course details with Id " + courseId +" does not exists");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			break;
			
   		case 3:
				System.out.println("Course Details Are\n");
				ArrayList<Course> list = courseServiceImpl.displayCourseDetails();
				System.out.println("Course ID   Course Name  	  No. of Days " );
				System.out.println("--------------------------------------------");
				for (Course courseDetails : list) {
					System.out.println(courseDetails.getCourseId() + "     \t"
							+ courseDetails.getCourseName()+ "     \t "
							+ courseDetails.getNoOfDays() + "      \t"
							);
				}
				break; 
				
   		case 4:
   			System.out.println("Update Course Details");
	    	System.out.println("Enter Course Id");
	    	String cId=scanner.next();
	    	courseDto = courseServiceImpl.viewCourseDetails(cId);
	    	try {
				if (courseDto != null) {
					System.out.println("Course details with course Id "+cId+"are:");
					System.out.println("Course Name :"
							+ courseDto.getCourseName());
					
					System.out.println("Course Duration:"
							+ courseDto.getNoOfDays());
				} else {
						
					System.out.println("Course Id "+ cId+ " does not exist!!!" );
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
	    	try{
				if (courseDto != null) {
	    	System.out.println("Enter Course name to update :");
	    	String courseName = scanner.next();
	    	courseDto.setCourseName(courseName);
			System.out.println("Enter No. of days to update:");
			String noOfDays = scanner.next();
			courseDto.setNoOfDays(noOfDays);
		
						boolean flag=courseServiceImpl.updateCourserDetails(cId,courseDto);
			 if(flag){
		    	  System.out.println("Course details with Id "+cId+" has been updated");
		      }
		      else{
		    	  System.out.println("enter the correct data");
		      }}
			}
					catch(Exception e1){
						System.err.println(e1.getMessage());
						
					}

		      break;
		      
	    case 5:
	    	System.out.println("Delete Course Details");
	    	System.out.println("Enter Course Id");
	    	String cID = scanner.next();
	    	courseDto = courseServiceImpl.viewCourseDetails(cID);
	    	try {
				if (courseDto != null) {
					System.out.println("Course details with course Id "+cID+"are:");
					System.out.println("Course Name :"
							+ courseDto.getCourseName());
					
					System.out.println("Course Duration:"
							+ courseDto.getNoOfDays());
				} else {
						
					System.out.println("Course Id "+ cID+ " does not exist!!!" );
				}
			} catch (Exception e) { 
				System.err.println(e.getMessage());
			}
	    	if(courseDto != null){
	    	System.out.println("Enter DELETE/delete to confirm Delete");
	    	String select = scanner.next();
	    	if(select.equalsIgnoreCase("DELETE")){
	    	 courseServiceImpl.deleteCourseDetails(cID);
	    	 System.out.println("Course details with Id "+cID+" has been deleted");
	    	}else
	    	{
	    		System.out.println("Delete operation for Id "+cID+" is not confirmed");
	    	}}
	    	break; 
	    	
	    	case 6:
	    	
	    		adminFunctions();
	    	break;
			case 7:
				System.out.println("Exit");
				System.exit(0);
			default:
				System.out.println("Enter correct choice");
				}
			System.out.println("Do you want to continue enter Y:");
			question = scanner.next().charAt(0);

			} while (question == 'Y' || question == 'y');

			scanner.close();			
			break;
			
		case 3:
			System.out.println("Feedback Reports");
			IFeedbackService iFeedbackService = new FeedbackServiceImpl();
			
		do{
			System.out.println("\nSelect the feedback view type:");
			System.out.println("[1]View Feedback of all");
			System.out.println("[2]View Feedback by Training Code");
			System.out.println("[3]View Feedback entered on a particular date");
			System.out.println("[4]Go to Main Menu");
			System.out.println("[5]Exit");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				ArrayList<Feedback> feedbackList = iFeedbackService.showFeedback();
				  for (Feedback feedbackdetails : feedbackList) {
					  System.out.println(feedbackdetails);
					 /* System.out.println(feedbackdetails.getTrainingCode()
							    +"    "+feedbackdetails.getParticipantId()
							    +"    "+feedbackdetails.getParticipantName()
								+"    "+feedbackdetails.getFbPrsComm()
								+"    "+feedbackdetails.getFbClrfyDbts()
								+"    "+feedbackdetails.getFbTm()
								+"    "+feedbackdetails.getFbHndOut()
								+"    "+feedbackdetails.getComments()
								+"    "+feedbackdetails.getSuggestions()
								+"    "+feedbackdetails.getFeedbackDate());*/
					}
					
			break;
				
			case 2:
				System.out.println("Enter the training code");
				//Feedback feedback = new Feedback();
				int trainingcode = scanner.nextInt();
				List<Feedback> feedBackListById = iFeedbackService.getFeedBack(trainingcode);
				List<Float> averageList = new ArrayList<Float>();
				averageList=iFeedbackService.getAverages(feedBackListById);
				for (Feedback feedbackDetailsById : feedBackListById) {
				
						if(feedbackDetailsById.getTrainingCode()!=0){
				/*System.out.println(feedback.getTrainingCode()
						+"    "+feedback.getParticipantId()
						+"    "+feedback.getFbPrsComm()
						+"    "+feedback.getFbClrfyDbts()
						+"    "+feedback.getFbTm()
						+"    "+feedback.getFbHndOut()
						+"    "+feedback.getComments()
						+"    "+feedback.getSuggestions()
						+"    "+feedback.getFeedbackDate());
*/		
							System.out.println(feedbackDetailsById);	
}				
				
				else
				{
					System.out.println("The Feedback details with the training Id "+trainingcode+" does not exist.Enter a valid training Id.");
				}}
				
				System.out.println("ClarifyingDoubts  Handout Given   Soft/Hard Conn.   Communication   Time Manag.");
				for (Float float1 : averageList) {
					System.out.print(float1);
					System.out.print("\t  ");
				}
				System.out.println("\n");
				
				break;
			
						
			case 3:
				System.out.println("Enter Feedback Date : (in format:dd/mm/yyyy)");
				String date = scanner.next();
				try {
					List<Feedback> feedBackList = iFeedbackService.getFeedBackByDate(date);
					for (Feedback feedbackByDate : feedBackList) {
				if(feedbackByDate.getTrainingCode()!=0){
					/*System.out.println(feedbackByDate.getTrainingCode()
							+"    "+feedbackByDate.getParticipantId()
							+"    "+feedbackByDate.getFbPrsComm()
							+"    "+feedbackByDate.getFbClrfyDbts()
							+"    "+feedbackByDate.getFbTm()
							+"    "+feedbackByDate.getFbHndOut()
							+"    "+feedbackByDate.getComments()
							+"    "+feedbackByDate.getSuggestions()
							+"    "+feedbackByDate.getFeedbackDate());*/
				System.out.println(feedbackByDate);
				}
					else
					{
						System.out.println("Enter valid date");
					}
					}
				} catch (FeedbackException e) {
					e.printStackTrace();
				}
				break;
			case 4:
		    	
				adminFunctions();
		    	break;
			case 5:
				System.out.println("Exit");
		    	System.exit(0);
				break;
				
			default:
				System.out.println("Enter correct choice");
				
			}
			System.out.println("Do you want to continue enter Y:");
			 value = scanner.next().charAt(0);

			} while (value == 'Y' || value == 'y');

			scanner.close();		
	
			break;
		case 4:
			System.out.println("Exit");
	    	System.exit(0);
			break;
			
			default:
			System.out.println("Select correct choice");
}
		System.out.println("Do you want to continue enter Y:");
		 value = scanner.next().charAt(0);

		} while (value == 'Y' || value == 'y');

		scanner.close();		
		}
}

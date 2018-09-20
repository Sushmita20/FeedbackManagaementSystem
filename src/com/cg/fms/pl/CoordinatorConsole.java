package com.cg.fms.pl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.fms.bean.Course;
import com.cg.fms.bean.FacultySkill;
import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.service.FacultySkillServiceImpl;
import com.cg.fms.service.IFacultySkillService;
import com.cg.fms.service.ITrainingProgramService;
import com.cg.fms.service.TrainingProgramServiceImpl;

public class CoordinatorConsole {

	TrainingProgram program = new TrainingProgram();
	ITrainingProgramService trainer= new TrainingProgramServiceImpl();
	IFacultySkillService facultySkillService = new FacultySkillServiceImpl();
	TrainingProgram trainingProgram;
	Scanner scanner = new Scanner(System.in);
	int choice;
	char option;
	int trainingCode = 0;
	public void coordinatorFunctions() throws FeedbackException {
		
		List<Course> courseList;
		ITrainingProgramService iTrainer;
		do{
			System.out.println("Select your choice:\n[1].Participant Enrolment\n[2].Training Program Maintainance\n[3].Exit");
			choice=scanner.nextInt();
			TrainingProgram program = new TrainingProgram();
		    switch (choice)
		    {
		    case 1:
		    	iTrainer= new TrainingProgramServiceImpl();
				System.out.println("Course Details");
				courseList= iTrainer.getCourses();
				for (Course course : courseList) {
					System.out.println(course.getCourseName());
				}
				System.out.println("Enter Participant ID:");
				String participantId= scanner.next();
				System.out.println("Enter Course Name (Select from list displayed):");
				String courseName= scanner.next();
				String trainingCode1=iTrainer.enrollParticipant(participantId,courseName);
				if(trainingCode1!=null){
				System.out.println("Participant with Id "+participantId+" enrolled for "+courseName+" with Training code "+trainingCode1);
				}else{
					System.out.println("Sorry!!! Currently no training programs available for Course "+courseName);
				}
				break;
		    case 2:
		    	do{
		    		System.out.println("Select your choice:\n"
		    				+ "[1].Add Training Details\n"
		    				+ "[2].Show Training Details\n"
		    				+ "[3].View Training Details\n"
		    				+ "[4].Update Training Details\n"
		    				+ "[5].Delete Training Details\n"
		    				+ "[6].Go to Main Menu\n[7].Exit");
		    		choice=scanner.nextInt();
		    		
		    		 switch (choice)
		 		    {
		 		    case 1:
		 		    	iTrainer= new TrainingProgramServiceImpl();
						System.out.println("Course Details");
						courseList= iTrainer.getCourses();
						for (Course course : courseList) {
							System.out.println(course.getCourseId()+" "+course.getCourseName());
						}
				        System.out.println("Add Trainer Details");
						System.out.println("Enter Course Code :");
						program.setCourseCode(scanner.nextInt());
						System.out.println("Enter faculty Code :");
						  ArrayList<FacultySkill> facultySkillList = facultySkillService.showFacultySkillSet();
						  for (FacultySkill facultySkill : facultySkillList) {
								/*System.out.println(facultySkill.getFacultyId() + "     "
										+ facultySkill.getSkillSet());*/
							  System.out.println(facultySkill);
						  }
						program.setFacultyCode(scanner.nextInt());
						System.out.println("Enter Start Date : (in format:dd/mm/yyyy)");
						program.setStartDate(scanner.next());
						System.out.println("Enter End Date : (in format:dd/mm/yyyy)");
						program.setEndDate(scanner.next());
						
						try{
								String trainingCode=trainer.addTrainer(program);
				      if(trainingCode!=null){
				    	  System.out.println("Your data successfully entered");
				    	  System.out.println("Training program registered successfully with Training code "+trainingCode);
				      }
				      else{
				    	  System.out.println("Error while inserting Training Program details");
				      }}
							catch(Exception ex){
								System.err.println(ex.getMessage());
								
							}
				      break;
				     
		 		    case 2:
		 				  ArrayList<TrainingProgram> trainerList = trainer.showTrainerDetails();
		 				  for (TrainingProgram trainingDetails : trainerList) {
		 						/*System.out.println(bean.getTrainingCode() + "     "
		 								+ bean.getCourseCode()+ "      "
		 								+ bean.getFacultyCode()+ "      "
		 								+bean.getStartDate()+"     "
		 								+ bean.getEndDate());*/
		 					  System.out.println(trainingDetails);
		 					  
		 					}

		 				  break;
		 				  
		 		   case 3:
				    	System.out.println("Enter Training Code");
				    	trainingCode=scanner.nextInt();
				    	trainingProgram=trainer.viewTrainerDetails(trainingCode);
				    	if(trainingProgram.getTrainingCode()!=0){
				    	/*System.out.println(trainingProgram.getTrainingCode()+" "+trainingProgram.getCourseCode()+" "+trainingProgram.getFacultyCode()+" "+trainingProgram.getStartDate()+" "+trainingProgram.getEndDate());*/
				    	System.out.println(trainingProgram);
				    	}else{
				    		System.out.println("Trainig Code "+trainingCode+" does not exist!!!");
				    	}
				    	break;
				    
		 		   case 4:
		 			  System.out.println("Enter Training Code");
		 		    	trainingCode=scanner.nextInt();
		 		    	System.out.println("Training Program details with training code "+ trainingCode +" are");
		 		    	
		 		    	trainingProgram=trainer.viewTrainerDetails(trainingCode);
				    	if(trainingProgram.getTrainingCode()!=0){
				    	/*System.out.println(trainingProgram.getTrainingCode()+" "+trainingProgram.getCourseCode()+" "+trainingProgram.getFacultyCode()+" "+trainingProgram.getStartDate()+" "+trainingProgram.getEndDate());*/
				    		System.out.println(trainingProgram);
				    	}else{
				    		System.out.println("Trainig Code "+trainingCode+" does not exist!!!");
				    	}
		 		    	
		 				try{
		 					if(trainingProgram!=null){
		 						System.out.println("Enter faculty Code :");
				 		    	int facultyCode = scanner.nextInt();
				 		    	program.setFacultyCode(facultyCode);
				 		    	System.out.println("Enter Start Date : (in format:dd/mm/yyyy)");
								program.setStartDate(scanner.next());
								System.out.println("Enter End Date : (in format:dd/mm/yyyy)");
								program.setEndDate(scanner.next());
				 				int result=trainer.updateTrainerDetails(trainingCode,program);
				 				 if(result!=0){
				 			    	  System.out.println("Training program details with Training code "+trainingCode+" has been updated!!!");
				 			      }
				 			      else{
				 			    	  System.out.println("Error while updating Training program details");
				 			      }
				 				}}
				 						catch(Exception e1){
				 							System.err.println(e1.getMessage());
				 							
				 						}

		 			   break;
				    case 5:
				    	System.out.println("Enter Training Code");
				    	trainingCode = scanner.nextInt();
				    	trainingProgram=trainer.viewTrainerDetails(trainingCode);
				    	if(trainingProgram.getTrainingCode()!=0){
				    	/*System.out.println(trainingProgram.getTrainingCode()+" "+trainingProgram.getCourseCode()+" "+trainingProgram.getFacultyCode()+" "+trainingProgram.getStartDate()+" "+trainingProgram.getEndDate());*/
				    	System.out.println(trainingProgram);
				    	}else{
				    		System.out.println("Trainig Code "+trainingCode+" does not exist!!!");
				    	}
				    	if(trainingProgram.getCourseCode()!=0){
				    		System.out.println("Enter YES to delete the training program");
				    		String delete = scanner.next();
				    		if(delete.equalsIgnoreCase("YES")){
				    	boolean flag=trainer.deleteTrainerDetails(trainingCode);
				    	if(flag){
				    		System.out.println("Training Program details with Id "+trainingCode+" has been deleted");
				    	}
				    	
				    	else{
				    		System.out.println("Error in deleting training program details");
				    	}}
				    		else{
				    			System.out.println("Delete operation for training program with training code "+trainingCode+" is not confirmed");
				    		}}
				    	break;
				    case 6:
				    	
				    	coordinatorFunctions();
				    	break;
				    	
				    case 7:
				    	System.out.println("Exit");
				    	System.exit(0);
						break;
						
				    default:
				      System.out.println("Enter correct Choice.!!");
		    	}
		    		 System.out.println("Do you Want to Continue (Y) :");
		 		    option=scanner.next().charAt(0);
		    	}while(option== 'Y' || option=='y');
		    	
		    case 3:
		    	System.out.println("Exit");
		    	System.exit(0);
				break;
				
		    default:
		      System.out.println("Enter correct Choice.!!");

		    }
		    System.out.println("Do you Want to Continue (Y) :");
		    option=scanner.next().charAt(0);
			}while(option== 'Y'|| option=='y');

			}
}
	

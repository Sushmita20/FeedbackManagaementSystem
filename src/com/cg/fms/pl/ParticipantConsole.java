package com.cg.fms.pl;

import java.util.List;
import java.util.Scanner;

import com.cg.fms.bean.Feedback;
import com.cg.fms.bean.ParticipantEnrollment;
import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.service.FeedbackServiceImpl;
import com.cg.fms.service.IFeedbackService;
import com.cg.fms.service.ITrainingProgramService;
import com.cg.fms.service.TrainingProgramServiceImpl;

public class ParticipantConsole {
	Scanner scanner = new Scanner(System.in);
	public void participantFunctions(int participantId,String participantName) throws FeedbackException {
	
	int choice = -1;
	char option='a';
	IFeedbackService feedbackService = new FeedbackServiceImpl();
	Feedback feedback = new Feedback();
	TrainingProgram trainingProgram = new TrainingProgram();
	ITrainingProgramService trainer= new TrainingProgramServiceImpl();
	do{
		
	
		System.out.print("[1]Enter Feedback [2]Quit >");
		choice = scanner.nextInt();
		switch(choice){
		case 1:
			 System.out.println("Add Feedback Details");
			 List<ParticipantEnrollment> enrollment = feedbackService.fetchTrainingCode(participantId);
			 System.out.println("Training Programs Codes, That you have enrolled:");
			 	for (ParticipantEnrollment participantEnrollment : enrollment) {
					System.out.println(participantEnrollment.getTrainingCode());
				}
			 
			 
				System.out.println("Enter Training code :");
			    int trainingCode =scanner.nextInt();
				trainingProgram=trainer.viewTrainerDetails(trainingCode);
		    	if(trainingProgram.getTrainingCode()!=0){
		    	feedback.setTrainingCode(trainingCode);
				feedback.setParticipantId(participantId);
				feedback.setParticipantName(participantName);
				System.out.println("5-Excellent:“Ideal way of doing it”");
			 	System.out.println("4-Good:“No pain areas or concern but could have been better”");
			 	System.out.println("3-Average:“There are concerns but not significant”");
			 	System.out.println("2-Below Average:“Needs improvement and is salvageable”");
			 	System.out.println("1-Poor:“This way of doing things must change”");
			 	System.out.println("--------------------------------------------------");
				System.out.println("Enter Presentation and communication skills of faculty(1 to 5):");
				feedback.setFbPrsComm(scanner.nextInt());
				System.out.println("Enter Ability to clarify doubts and explain difficult points(1 to 5):");
				feedback.setFbClrfyDbts(scanner.nextInt());
				System.out.println("Enter Time management in completing the contents(1 to 5):");
				feedback.setFbTm(scanner.nextInt());
				System.out.println("Enter Handout provided(Student Guide)(1 to 5) :");
				feedback.setFbHndOut(scanner.nextInt());
				System.out.println("Enter Hardware, software and network availability(1 to 5) :");
				feedback.setFbHwSwNtwrk(scanner.nextInt());
				System.out.println("Enter Comments :");
				feedback.setComments(scanner.next());
				System.out.println("Enter Suggestions :");
				feedback.setSuggestions(scanner.next());
				
				try {
					if(feedbackService.addFeedback(feedback)){
						System.out.println("Thanks for the feedback!!");
					}else
					{
						System.out.println("Feedback details are not inserted");
					}
					  }
				catch (FeedbackException e) {
				System.out.println(e.getMessage());
				}}else
				{
					System.out.println("Training Code "+trainingCode+" does not exist!!!Please enter a valid training code");
				}
		break;
		case 2:
			System.out.println("Exit");
	    	System.exit(0);
			break;
			
			default:
			System.out.println("Select correct choice");
		}System.out.println("Do you want to continue enter Y:");
		 option = scanner.next().charAt(0);

		} while (option == 'Y' || option ==  'y');

		scanner.close();		
	}
	
}
	

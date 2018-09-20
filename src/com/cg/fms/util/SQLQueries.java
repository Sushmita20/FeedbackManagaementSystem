package com.cg.fms.util;

public interface SQLQueries {

	public static final String GET_USER_QUERY="SELECT employeeId,password,role FROM employee_master where employeeId=?";
	public static final String Employee_Details_Query="SELECT employeeId,employeeName,role FROM employee_master where employeeId=?";
	
	public static final String TrainingCodeseq_SEQUENCE = "SELECT TrainingCodeseq.CURRVAL FROM DUAL";
	public static final String QUERY_SEQUENCE="SELECT CourseId_seq.CURRVAL FROM DUAL";
	
	public static final String Faculty_Skill_Set="SELECT facultyId,skillSet FROM Faculty_Skill";
	
	public static final String INSERT_COURSE_QUERY="INSERT INTO course_master VALUES(CourseId_seq.NEXTVAL,?,?)";
	public static final String GET_COURSE_LIST = "select distinct coursename,courseId from course_master";
	public static final String VIEW_COURSE_DETAILS_QUERY="SELECT coursename,noofdays FROM course_master WHERE courseid=?";
	public static final String UPDATE_QUERY ="UPDATE COURSE_MASTER SET coursename=?,noofdays=? WHERE courseid=?";
	public static final String DELETE_COURSE_QUERY ="DELETE FROM course_master WHERE courseid=?";
	
	public static final String GET_FEEDBACK_QUERY="SELECT trainingcode,participantid,fb_prs_comm,fb_clrfy_dbts,fb_tm,fb_hnd_out,fb_hw_sw_ntwrk,comments,suggestions,feedbackdate FROM FEEDBACK_MASTER WHERE trainingcode=?";
	public static final String GET_FEEDBACKDATE_QUERY="SELECT trainingcode,participantid,fb_prs_comm,fb_clrfy_dbts,fb_tm,fb_hnd_out,fb_hw_sw_ntwrk,comments,suggestions,feedbackdate FROM FEEDBACK_MASTER WHERE feedbackdate like ?";
	public static final String GET_ALLFEEDBACK_QUERY="SElECT trainingcode,participantid,participantName,fb_prs_comm,fb_clrfy_dbts,fb_tm,fb_hnd_out,fb_hw_sw_ntwrk,comments,suggestions,feedbackdate FROM FEEDBACK_MASTER";
	
	public static final String INSERT_QUERY = "INSERT into TRAINING_PROGRAM VALUES(TrainingCodeseq.NEXTVAL,?,?,?,?) ";
	public static final String SHOW_trainerDetails_QUERY = "SELECT TrainingCode,courseCode,facultyCode,startDate,endDate FROM TRAINING_PROGRAM ";
	public static final String View_Query = "SELECT TrainingCode,courseCode,facultyCode,startDate,endDate FROM TRAINING_PROGRAM where TrainingCode=?";
	public static final String DELETE_QUERY = "delete from TRAINING_PROGRAM  where TrainingCode=? ";
	public static final String UPDATE_TRAINING_PROGRAM_QUERY="UPDATE training_program set facultycode=?,startDate=?,endDate=? WHERE trainingCode=?";
	public static final String GET_TRAINING_CODE ="select trainingcode from training_program where coursecode = (select courseid from course_master where coursename like ?)";
	
	public static final String DISPLAY_COURSE_LIST_QUERY="SELECT courseid,coursename,noofdays FROM course_master";
	public static final String ENROLL_PARTICIPANT="INSERT into TRAININGPARTICIPANT_ENROLLMENT values(?,?)";
	
	public static final String ADD_FEEDBACK_QUERY="INSERT INTO feedback_master(TrainingCode,ParticipantId,FB_Prs_comm,FB_Clrfy_dbts,FB_TM,FB_Hnd_out,FB_Hw_Sw_Ntwrk,Comments,Suggestions,feedbackDate,participantName) values(?,?,?,?,?,?,?,?,?,SYSDATE,?)";
	public static final String GET_TRAINING_CODES = "select  Trainingcode from TRAININGPARTICIPANT_ENROLLMENT where ParticipantId=?";
}
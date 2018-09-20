package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.fms.bean.Course;
import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.util.DBConnection;
import com.cg.fms.util.SQLQueries;

public class TrainingProgramDaoImpl implements ITrainingProgramDao{
	public static final	Logger LOGGER = Logger.getLogger(TrainingProgramDaoImpl.class);

	Connection connection = null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet = null;
	@Override
	public String addTrainer(TrainingProgram program) throws FeedbackException {
		LOGGER.info("Inserting Training Program details into database");
		
		String trainingCode=null;
		int queryResult = 0;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Date startDate=java.sql.Date.valueOf(LocalDate.parse(program.getStartDate(), dateTimeFormatter));
		Date endDate=java.sql.Date.valueOf(LocalDate.parse(program.getEndDate(), dateTimeFormatter));
		try{
			connection=DBConnection.getInstance();
			preparedStatement=connection.prepareStatement(SQLQueries.INSERT_QUERY);
			preparedStatement.setInt(1, program.getCourseCode());
			preparedStatement.setInt(2, program.getFacultyCode());
			preparedStatement.setDate(3, startDate);
			preparedStatement.setDate(4, endDate);
			queryResult=preparedStatement.executeUpdate();
			preparedStatement=connection.prepareStatement(SQLQueries.TrainingCodeseq_SEQUENCE);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				trainingCode=resultSet.getString(1);
			}
			if (queryResult==0) {
				throw new FeedbackException("Error in inserting Training program details");
			}
		}catch(SQLException exception){
			LOGGER.error("SQL Exception occured!");
			throw new FeedbackException("SQL Exception occured" + exception.getMessage());
		}
		finally
		{
			if (preparedStatement!=null && connection!=null&&resultSet!=null) {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new FeedbackException("Error in closing resorces" +e.getMessage());
			}
		}
		}
		return trainingCode;
	}
	@Override
	public ArrayList<TrainingProgram> showTrainerDetails()
			throws FeedbackException { 
		ArrayList<TrainingProgram> trainerList= new ArrayList<TrainingProgram>();
		try
		{
			connection = DBConnection.getInstance();
			preparedStatement=connection.prepareStatement(SQLQueries.SHOW_trainerDetails_QUERY);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){		
			TrainingProgram bean=new TrainingProgram();
			bean.setTrainingCode(resultSet.getInt(1));
			bean.setCourseCode(resultSet.getInt(2));
			bean.setFacultyCode(resultSet.getInt(3));
			bean.setStartDate(resultSet.getString(4));
			bean.setEndDate(resultSet.getString(5));
			trainerList.add(bean);
						}			
			
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching Training Program details" + exception.getMessage());
			throw new FeedbackException(exception.getMessage());
		}
		finally{
			if(preparedStatement!=null&&resultSet!=null&&connection!=null){
				try {
					resultSet.close();
					preparedStatement.close();
					connection.close();
				} catch (SQLException exception) {
					throw new FeedbackException(exception.getMessage());	}
			}
		
		}
		return trainerList;
	}

	@Override
	public TrainingProgram viewTrainerDetails(int TrainingCode)
			throws FeedbackException {
		TrainingProgram bean=new TrainingProgram();
		try
		{
			connection = DBConnection.getInstance();
			preparedStatement=connection.prepareStatement(SQLQueries.View_Query);
			preparedStatement.setInt(1, TrainingCode);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{		
				
			bean.setTrainingCode(resultSet.getInt(1));
			bean.setCourseCode(resultSet.getInt(2)); 
			bean.setFacultyCode(resultSet.getInt(3));
			bean.setStartDate(resultSet.getString(4));
			bean.setEndDate(resultSet.getString(5));
	}			
			
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching  Training Program details" + exception.getMessage());
			throw new FeedbackException(exception.getMessage());
		}
		finally{
			if(preparedStatement!=null&&resultSet!=null&&connection!=null){
				try {
					resultSet.close();
					preparedStatement.close();
					connection.close();
				} catch (SQLException exception) {
					throw new FeedbackException(exception.getMessage());	}
			}
		
		}

		return bean;
	}

	@Override
	public boolean deleteTrainerDetails(int trainingCode)
			throws FeedbackException {
 
		//int TrainingCode=0;
		try {
			connection = DBConnection.getInstance();
			preparedStatement=connection.prepareStatement(SQLQueries.DELETE_QUERY);
			preparedStatement.setInt(1, trainingCode);
			int result=preparedStatement.executeUpdate();
			if(result==1){
				
				connection.commit();
			}
			else{
				connection.rollback();
			}
		} catch (SQLException exception) {
			throw new FeedbackException("SQL exception occured:"+exception.getMessage());
		}
		finally{
			if(preparedStatement!=null&&connection!=null&&resultSet!=null){
				try {
					resultSet.close();
					preparedStatement.close();
					connection.close();
				} catch (SQLException exception) {
					throw new FeedbackException("Error in closing connections");
				}
			}
		}
		return true;
	}

	public List<Course> getCourses() throws FeedbackException {
		List<Course> courses = new ArrayList<Course>();
		Course course= null;
		try {
			 connection = DBConnection.getInstance();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_COURSE_LIST);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				course = new Course();
				course.setCourseName(resultSet.getString(1));
				course.setCourseId(resultSet.getString(2));
				courses.add(course);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching course details" + exception.getMessage());
			throw new FeedbackException("Unable To get course names");
		}
		
		return courses;
	}

	public String enrollParticipant(String participantId, String courseName) throws FeedbackException {
		String trainingCode = null;
		try {
			 connection = DBConnection.getInstance();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_TRAINING_CODE);
			preparedStatement.setString(1, courseName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				trainingCode=resultSet.getString(1);
			}
			
			preparedStatement = connection.prepareStatement(SQLQueries.ENROLL_PARTICIPANT);
			preparedStatement.setString(1, trainingCode);
			preparedStatement.setString(2, participantId);
			int result = preparedStatement.executeUpdate();
			
			if(result==1){
				connection.commit();
			}
			else{
				connection.rollback();
			}
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching Training Program details" + exception.getMessage());
			throw new FeedbackException("Enter valid details");
		}
		
		return trainingCode;
	}

	@Override
	public int updateTrainerDetails(int trainingCode,
			TrainingProgram program) throws FeedbackException {
		int result=0;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Date startDate=java.sql.Date.valueOf(LocalDate.parse(program.getStartDate(), dateTimeFormatter));
		Date endDate=java.sql.Date.valueOf(LocalDate.parse(program.getEndDate(), dateTimeFormatter));
		try {
			connection = DBConnection.getInstance();
			preparedStatement=connection.prepareStatement(SQLQueries.UPDATE_TRAINING_PROGRAM_QUERY);
			preparedStatement.setInt(1,program.getFacultyCode());
			preparedStatement.setDate(2, startDate);
			preparedStatement.setDate(3, endDate);
			preparedStatement.setInt(4, trainingCode);
			result=preparedStatement.executeUpdate();
			if(result==1){
				connection.commit();
			}else{
				connection.rollback();
			}
			
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching Training Program details" + exception.getMessage());
			try {
				connection.rollback();
			} catch (SQLException exception1) {
				exception1.printStackTrace();
			}
			exception.printStackTrace();
		}
		finally{
			if(preparedStatement!=null&&resultSet!=null&&connection!=null){
				try {
					resultSet.close();
					preparedStatement.close();
					connection.close();
				} catch (SQLException exception) {
					throw new FeedbackException(exception.getMessage());	}
			}
		
		}
		
		return result;
		}

}

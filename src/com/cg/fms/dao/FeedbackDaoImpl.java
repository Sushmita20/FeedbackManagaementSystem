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

import com.cg.fms.bean.Feedback;
import com.cg.fms.bean.ParticipantEnrollment;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.util.DBConnection;
import com.cg.fms.util.SQLQueries;

public class FeedbackDaoImpl implements IFeedbackDao{

	public static final	Logger LOGGER = Logger.getLogger(FeedbackDaoImpl.class);
	List<Feedback> feedBackList= new ArrayList<Feedback>();
	List<Feedback> feedBackListById= new ArrayList<Feedback>();
	@Override
	public boolean addFeedback(Feedback feedback) throws FeedbackException {
		
		LOGGER.info("Inserting feedback details into database");
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		//String stuId=null;
		int qResult = 0;
		try
		{
			preparedStatement=connection.prepareStatement(SQLQueries.ADD_FEEDBACK_QUERY);
			preparedStatement.setInt(1, feedback.getTrainingCode());
			preparedStatement.setInt(2, feedback.getParticipantId());
			preparedStatement.setInt(3, feedback.getFbPrsComm());
			preparedStatement.setInt(4, feedback.getFbClrfyDbts());
			preparedStatement.setInt(5, feedback.getFbTm());
			preparedStatement.setInt(6, feedback.getFbHndOut());
			preparedStatement.setInt(7, feedback.getFbHwSwNtwrk());
			preparedStatement.setString(8, feedback.getComments());
			preparedStatement.setString(9, feedback.getSuggestions());
			preparedStatement.setString(10, feedback.getParticipantName());
			qResult = preparedStatement.executeUpdate();
			if (qResult==0) {
				connection.rollback();
			}else
			{
				connection.commit();
			}
		}catch(SQLException exp){
			LOGGER.error("SQL Exception occured!"+exp.getMessage());
			throw new FeedbackException("SQL Exception occured. Entered values range should be in between 1 to 5  ");
		}
		finally
		{
			if (preparedStatement!=null && connection!=null&&resultSet!=null) {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException exception) {
				LOGGER.info("Error in retriving data" +exception.getMessage());
				throw new FeedbackException("Error in closing resorces" +exception.getMessage());
			}
		}
			
		}
return true;
	}
	@Override
	public List<Feedback> getFeedBack(int trainingcode) throws FeedbackException {
		
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			LOGGER.info("Fetching feedback details using Training code");
			preparedStatement = connection
					.prepareStatement(SQLQueries.GET_FEEDBACK_QUERY);
			preparedStatement.setInt(1, trainingcode);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Feedback feedback = new Feedback();
				feedback.setTrainingCode(resultSet.getInt(1));
				feedback.setParticipantId(resultSet.getInt(2));
				feedback.setFbPrsComm(resultSet.getInt(3));
				feedback.setFbClrfyDbts(resultSet.getInt(4));
				feedback.setFbTm(resultSet.getInt(5));
				feedback.setFbHndOut(resultSet.getInt(6));
				feedback.setFbHwSwNtwrk(resultSet.getInt(7));
				feedback.setComments(resultSet.getString(8));
				feedback.setSuggestions(resultSet.getString(9));
				feedback.setFeedbackDate(resultSet.getString(10));
				feedBackListById.add(feedback);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching feedback data"+exception.getMessage());
		throw new FeedbackException("Error in fetching feedback data");
		}
		return feedBackListById;
	}

	@Override
	public ArrayList<Feedback> showFeedback() throws FeedbackException {
			PreparedStatement pst=null;
			ResultSet resultSet = null;
			Connection connection = DBConnection.getInstance();
			ArrayList<Feedback> feedbackList=new ArrayList<Feedback>();
			try
			{
				LOGGER.info("Fetching all feedback details");
				pst=connection.prepareStatement(SQLQueries.GET_ALLFEEDBACK_QUERY);
				resultSet=pst.executeQuery();
				
				while(resultSet.next())
				{		Feedback feedback=new Feedback();
				feedback.setTrainingCode(resultSet.getInt(1));
				feedback.setParticipantId(resultSet.getInt(2));
				feedback.setParticipantName(resultSet.getString(3));
				feedback.setFbPrsComm(resultSet.getInt(4));
				feedback.setFbClrfyDbts(resultSet.getInt(5));
				feedback.setFbTm(resultSet.getInt(6));
				feedback.setFbHndOut(resultSet.getInt(7));
				feedback.setFbHwSwNtwrk(resultSet.getInt(8));
				feedback.setComments(resultSet.getString(9));
				feedback.setSuggestions(resultSet.getString(10));
				feedback.setFeedbackDate(resultSet.getString(11));
				feedbackList.add(feedback);
				}			
				
			} catch (SQLException exception) {
				LOGGER.error("Error in fetching feedback data"+exception.getMessage());
				throw new FeedbackException("Error in fetching feedback data");
			}
			finally{
				if(pst!=null&&resultSet!=null&&connection!=null){
					try {
						resultSet.close();
						pst.close();
						connection.close();
					} catch (SQLException exception) {
						throw new FeedbackException("Error in closing connections");
					}
				}
			}
			return feedbackList;
			}

	@Override
	public List<Feedback> getFeedBackByDate(String feedbackdate)
			throws FeedbackException {
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Date fbDate=java.sql.Date.valueOf(LocalDate.parse(feedbackdate, dateTimeFormatter));
		try {
			preparedStatement = connection
					.prepareStatement(SQLQueries.GET_FEEDBACKDATE_QUERY);
			preparedStatement.setDate(1, fbDate);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				Feedback feedback = new Feedback();
				feedback.setTrainingCode(resultSet.getInt(1));
				feedback.setParticipantId(resultSet.getInt(2));
				feedback.setFbPrsComm(resultSet.getInt(3));
				feedback.setFbClrfyDbts(resultSet.getInt(4));
				feedback.setFbTm(resultSet.getInt(5));
				feedback.setFbHndOut(resultSet.getInt(6));
				feedback.setFbHwSwNtwrk(resultSet.getInt(7));
				feedback.setComments(resultSet.getString(8));
				feedback.setSuggestions(resultSet.getString(9));
				feedback.setFeedbackDate(resultSet.getString(10));
				feedBackList.add(feedback);
			}
		} catch (SQLException exception) {
			LOGGER.error("Unable to fetch FeedBack"+exception.getMessage());
			throw new FeedbackException("Unable to fetch FeedBack");
		}
		return feedBackList;
	}
	@Override
	public List<ParticipantEnrollment> fetchTrainingCode(int participantId)
			throws FeedbackException {
		List<ParticipantEnrollment> enrollments= new ArrayList<ParticipantEnrollment>();
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
		preparedStatement = connection
				.prepareStatement(SQLQueries.GET_TRAINING_CODES);
		preparedStatement.setInt(1, participantId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			ParticipantEnrollment enrollment= new ParticipantEnrollment();
			enrollment.setTrainingCode(resultSet.getInt(1));
			enrollments.add(enrollment);
		}
		
		} catch (SQLException exception) {
			LOGGER.error("Unable to fetch Training Codes"+exception.getMessage());
			throw new FeedbackException("Unable to fetch Training Codes");
		}
		
		return enrollments;
	}

}

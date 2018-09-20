package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cg.fms.bean.Course;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.util.DBConnection;
import com.cg.fms.util.SQLQueries;

public class CourseDaoImpl implements ICourseDao{
	public static final	Logger LOGGER = Logger.getLogger(CourseDaoImpl.class);
	@SuppressWarnings("resource")
	@Override
	public String addCourse(Course courseDto) throws FeedbackException {

		LOGGER.info("Inserting course details into database");
		String courseId=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int queryResult = 0;
		try {
			connection = DBConnection.getInstance();
			preparedStatement = connection
					.prepareStatement(SQLQueries.INSERT_COURSE_QUERY);
			preparedStatement.setString(1, courseDto.getCourseName());
			preparedStatement.setString(2, courseDto.getNoOfDays());
			
			queryResult = preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(SQLQueries.QUERY_SEQUENCE);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				courseId = resultSet.getString(1);
				
			}
			if (queryResult == 0) {
				connection.rollback();
				throw new FeedbackException("Error in entering course details");
				
			} else {
				connection.commit();
			}
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception occured!");
			throw new FeedbackException("SQL Exception occured!"+exception.getMessage());
		} finally {
			try {
				if (resultSet != null && preparedStatement != null) {
					resultSet.close();
					preparedStatement.close();
				}
			} catch (SQLException exp) {
				throw new FeedbackException("Error in closing db connection");
			}
		}

		return courseId;
	}

	@Override
	public ArrayList<Course> displayCourseDetails() throws FeedbackException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = DBConnection.getInstance();
		ArrayList<Course> list=new ArrayList<Course>();
		try {
			preparedStatement = connection
					.prepareStatement(SQLQueries.DISPLAY_COURSE_LIST_QUERY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course courseDto=new Course();
				courseDto.setCourseId(resultSet.getInt(1)+"");
				courseDto.setCourseName(resultSet.getString(2)); 
				courseDto.setNoOfDays(resultSet.getString(3));
				list.add(courseDto);
			}
		} catch (SQLException exception) {
			LOGGER.info("Error in retriving data" +exception.getMessage());
			throw new FeedbackException(exception.getMessage());
		} finally {
			if (preparedStatement != null && resultSet != null && connection != null) {
				try {
					resultSet.close();
					preparedStatement.close();
				} catch (SQLException exception) {
					throw new FeedbackException(exception.getMessage());
				}
			}

		}
		return list;
	}

	@Override
	public Course viewCourseDetails(String courseId) throws FeedbackException {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Course courseDto = null;
		try {

			preparedStatement = con.prepareStatement(SQLQueries.VIEW_COURSE_DETAILS_QUERY);
			preparedStatement.setString(1, courseId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				courseDto = new Course();
				courseDto.setCourseName(resultSet.getString(1));
				courseDto.setNoOfDays(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null && preparedStatement != null) {
					resultSet.close();
					preparedStatement.close();
				}
			} catch (SQLException exception) {
				LOGGER.error("Error in fetching course details" + exception.getMessage());
				exception.printStackTrace();

			}
		}

		return courseDto;
	}

	@Override
	public boolean updateCourserDetails(String cId, Course courseDto)
			throws FeedbackException {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		
		Connection connection = DBConnection.getInstance();
		try {
			
			preparedStatement=connection.prepareStatement(SQLQueries.UPDATE_QUERY);
			preparedStatement.setString(1,courseDto.getCourseName());
			preparedStatement.setInt(2,Integer.parseInt(courseDto.getNoOfDays()));
			preparedStatement.setInt(3,Integer.parseInt(cId));
			int result=preparedStatement.executeUpdate();
			if(result==1){
				connection.commit();
				return true;
			}
			else {
				connection.rollback();
				return false;
			}
			
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching course details" + exception.getMessage());
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
		
		return true;
	}
	@Override
	public boolean deleteCourseDetails(String cID) throws FeedbackException {

		Connection connection = DBConnection.getInstance();
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		
		try {
			pst=connection.prepareStatement(SQLQueries.DELETE_COURSE_QUERY);
			pst.setString(1, cID);
			int result=pst.executeUpdate();
			if(result==1){
				connection.commit();
				return true;
			}
			else{
				connection.rollback();
				return false;
			}
		} catch (SQLException exception) {
			LOGGER.error("Error in fetching course details" + exception.getMessage());
			try {
				connection.rollback();
			
			} catch (SQLException exception1) {
				throw new FeedbackException(exception1.getMessage());
			}
		}
		finally{
			if(pst!=null&&connection!=null&&resultSet!=null){
				try {
					resultSet.close();
					pst.close();
					connection.close();
				} catch (SQLException e) {
					throw new FeedbackException(e.getMessage());
				}
			}
		}
		return true;
	}
}

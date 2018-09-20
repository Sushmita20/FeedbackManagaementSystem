package com.cg.fms.dao;

import java.util.ArrayList;

import com.cg.fms.bean.Course;
import com.cg.fms.exception.FeedbackException;

public interface ICourseDao {

	String addCourse(Course courseDto) throws FeedbackException;
	ArrayList<Course> displayCourseDetails()throws FeedbackException;
	Course viewCourseDetails(String courseId)  throws FeedbackException;
	public boolean updateCourserDetails(String cId, Course courseDto) throws FeedbackException;
	boolean deleteCourseDetails(String cID) throws FeedbackException;
}

package com.cg.fms.service;

import java.util.ArrayList;

import com.cg.fms.bean.Course;
import com.cg.fms.exception.FeedbackException;

public interface ICourseService {

	public String addCourse(Course courseDto) throws FeedbackException;
	public ArrayList<Course> displayCourseDetails()throws FeedbackException;
	public Course viewCourseDetails(String courseId)  throws FeedbackException;
	public boolean updateCourserDetails(String cId, Course courseDto) throws FeedbackException;
	public boolean deleteCourseDetails(String cID) throws FeedbackException;
}

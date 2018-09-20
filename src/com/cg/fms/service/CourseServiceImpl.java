package com.cg.fms.service;

import java.util.ArrayList;

import com.cg.fms.bean.Course;
import com.cg.fms.dao.CourseDaoImpl;
import com.cg.fms.dao.ICourseDao;
import com.cg.fms.exception.FeedbackException;

public class CourseServiceImpl implements ICourseService{
	ICourseDao daoImpl = new CourseDaoImpl();

	@Override
	public String addCourse(Course courseDto) throws FeedbackException {
		String courseId = daoImpl.addCourse(courseDto);
		return courseId;
	}

	@Override
	public ArrayList<Course> displayCourseDetails() throws FeedbackException {
		ArrayList<Course> courseList = daoImpl.displayCourseDetails();
		return courseList;	}

	@Override
	public Course viewCourseDetails(String courseId) throws FeedbackException {
		Course courseDto=daoImpl.viewCourseDetails(courseId);
		return courseDto;
	}

	@Override
	public boolean updateCourserDetails(String cId, Course courseDto)
			throws FeedbackException {
		return daoImpl.updateCourserDetails(cId, courseDto);
	}

	@Override
	public boolean deleteCourseDetails(String cID) throws FeedbackException {
		return daoImpl.deleteCourseDetails(cID);
	}
}

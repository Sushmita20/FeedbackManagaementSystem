package com.cg.fms.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.fms.bean.Feedback;
import com.cg.fms.bean.ParticipantEnrollment;
import com.cg.fms.exception.FeedbackException;

public interface IFeedbackDao {

	boolean addFeedback(Feedback feedback) throws FeedbackException;
	public List<Feedback> getFeedBack(int trainingCode) throws FeedbackException;
	public ArrayList<Feedback> showFeedback() throws FeedbackException;
	public List<Feedback> getFeedBackByDate(String feedbackdate) throws FeedbackException;
	List<ParticipantEnrollment> fetchTrainingCode(int participantId)throws FeedbackException;
	
}

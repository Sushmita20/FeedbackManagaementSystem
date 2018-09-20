package com.cg.fms.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.fms.bean.Feedback;
import com.cg.fms.bean.ParticipantEnrollment;
import com.cg.fms.exception.FeedbackException;

public interface IFeedbackService {

	public boolean addFeedback(Feedback feedback) throws FeedbackException;
	public List<Feedback> getFeedBack(int trainingcode) throws FeedbackException;
	public List<Feedback> getFeedBackByDate(String feedbackdate) throws FeedbackException;
	public ArrayList<Feedback> showFeedback() throws FeedbackException;
	public List<ParticipantEnrollment> fetchTrainingCode(int participantId)throws FeedbackException;
	public List<Float> getAverages(List<Feedback> feedBackListById) throws FeedbackException;
	
}

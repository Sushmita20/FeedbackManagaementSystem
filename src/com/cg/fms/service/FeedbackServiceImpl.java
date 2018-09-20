package com.cg.fms.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.fms.bean.Feedback;
import com.cg.fms.bean.ParticipantEnrollment;
import com.cg.fms.dao.FeedbackDaoImpl;
import com.cg.fms.dao.IFeedbackDao;
import com.cg.fms.exception.FeedbackException;

public class FeedbackServiceImpl implements IFeedbackService {

	IFeedbackDao feedbackDao = new FeedbackDaoImpl();
	@Override
	public boolean addFeedback(Feedback feedback) throws FeedbackException {
		return feedbackDao.addFeedback(feedback);
	}

	@Override
	public List<Feedback> getFeedBack(int trainingcode) throws FeedbackException {
		return feedbackDao.getFeedBack(trainingcode);
	}

	@Override
	public ArrayList<Feedback> showFeedback() throws FeedbackException {
		ArrayList<Feedback> feedbackList=null;
		feedbackList=feedbackDao.showFeedback();
		return feedbackList;
	
	}

	@Override
	public List<Feedback> getFeedBackByDate(String feedbackdate) throws FeedbackException {
		return feedbackDao.getFeedBackByDate(feedbackdate);
		
	}

	@Override
	public List<ParticipantEnrollment> fetchTrainingCode(int participantId)
			throws FeedbackException {
		return feedbackDao.fetchTrainingCode(participantId);
	}

	@Override
	public List<Float> getAverages(List<Feedback> feedBackListById)
			throws FeedbackException {
		List<Float> averageList= new ArrayList<Float>();
		float fbClrfyDbts = 0,fbHndOut=0;
		float fbHwSwNtwrk=0;
		float fbPrsComm=0,fbTm=0;int count=0;
		for (Feedback feedback : feedBackListById) {
			fbClrfyDbts+=feedback.getFbClrfyDbts();
			fbHndOut += feedback.getFbHndOut();
			fbHwSwNtwrk+= feedback.getFbHwSwNtwrk();
			fbPrsComm+=feedback.getFbPrsComm();
			fbTm+=feedback.getFbTm();
			count++;
		}
		averageList.add((fbClrfyDbts/count));
		averageList.add((fbHndOut/count));
		averageList.add((fbHwSwNtwrk/count));
		averageList.add((fbPrsComm/count));
		averageList.add((fbTm/count));
		
		return averageList;
	}
	


}

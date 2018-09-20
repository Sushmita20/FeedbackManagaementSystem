package com.cg.fms.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.fms.bean.Course;
import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.exception.FeedbackException;

public interface ITrainingProgramService {

	public String addTrainer(TrainingProgram program) throws FeedbackException;

	public ArrayList<TrainingProgram> showTrainerDetails() throws FeedbackException;

	public TrainingProgram viewTrainerDetails(int trainingCode) throws FeedbackException;
	public int updateTrainerDetails(int TrainingCode, TrainingProgram program)throws FeedbackException;

	public boolean deleteTrainerDetails(int trainingCode) throws FeedbackException;

	public List<Course> getCourses() throws FeedbackException;

	public String enrollParticipant(String participantId, String courseName) throws FeedbackException;
}

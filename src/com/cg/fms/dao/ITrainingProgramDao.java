package com.cg.fms.dao;

import java.util.ArrayList;

import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.exception.FeedbackException;

public interface ITrainingProgramDao {

public String addTrainer(TrainingProgram program) throws FeedbackException;
	
	public ArrayList<TrainingProgram> showTrainerDetails() throws FeedbackException ;

	public TrainingProgram viewTrainerDetails(int TrainingCode)
			throws FeedbackException;

	public boolean deleteTrainerDetails(int trainingCode)
			throws FeedbackException;
	public int updateTrainerDetails(int TrainingCode, TrainingProgram program)
			throws FeedbackException;
}

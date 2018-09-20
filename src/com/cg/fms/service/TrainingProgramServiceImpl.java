package com.cg.fms.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.fms.bean.Course;
import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.dao.TrainingProgramDaoImpl;
import com.cg.fms.exception.FeedbackException;

public class TrainingProgramServiceImpl implements ITrainingProgramService {

	TrainingProgram program=new TrainingProgram();
	TrainingProgramDaoImpl dao=new TrainingProgramDaoImpl();
	@Override
	public String addTrainer(TrainingProgram program) throws FeedbackException {
		return dao.addTrainer(program);
	}

	@Override
	public ArrayList<TrainingProgram> showTrainerDetails() throws FeedbackException {
		return dao.showTrainerDetails();
	}

	@Override
	public TrainingProgram viewTrainerDetails(int trainingCode) throws FeedbackException {
		return dao.viewTrainerDetails(trainingCode);
	}

	@Override
	public boolean deleteTrainerDetails(int trainingCode) throws FeedbackException {
		return dao.deleteTrainerDetails(trainingCode);
	}

	@Override
	public List<Course> getCourses() throws FeedbackException {
		return dao.getCourses();
	}

	@Override
	public String enrollParticipant(String participantId, String courseName) throws FeedbackException {
		return dao.enrollParticipant(participantId,courseName);
	}

	@Override
	public int updateTrainerDetails(int TrainingCode,TrainingProgram program) throws FeedbackException {
		return dao.updateTrainerDetails(TrainingCode,program);
	}

}

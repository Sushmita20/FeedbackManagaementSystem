package com.cg.fms.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.fms.bean.TrainingProgram;
import com.cg.fms.exception.FeedbackException;

public class TrainingProgramDaoImplTest {
	static TrainingProgramDaoImpl trainingdaoimpl;
	static TrainingProgram trainingprogram;
	
	@BeforeClass
	public static void intialize() {
		System.out.println("test");
		trainingdaoimpl = new TrainingProgramDaoImpl();
		trainingprogram = new TrainingProgram();
	}

	@Test
	public void testshowTrainerDetails() throws FeedbackException {
		assertNotNull(trainingdaoimpl.showTrainerDetails());
	}
	
	@Test 
	public void testviewTrainerDetails() throws FeedbackException {
		assertNotNull(trainingdaoimpl.viewTrainerDetails(10001));
	}
	
	@Test
	public void testdeleteTrainerDetails() throws FeedbackException {
		assertNotNull(trainingdaoimpl.deleteTrainerDetails(10001));
	}
	
	@Test
	public void testupdateTrainerDetails() throws FeedbackException {
		assertNotNull(trainingdaoimpl.deleteTrainerDetails(10001));
	}

}

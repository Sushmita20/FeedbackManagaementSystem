package com.cg.fms.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.fms.bean.Feedback;
import com.cg.fms.exception.FeedbackException;

public class FeedbackDaoImplTest {
	
	static FeedbackDaoImpl feedbackdao;
	static Feedback feedback;

	@BeforeClass
	public static void intialize() {
		System.out.println("test");
		feedbackdao = new FeedbackDaoImpl();
		feedback = new Feedback();
	}

	@Test
	public void testshowFeedback() throws FeedbackException{
		assertNotNull(feedbackdao.showFeedback());
	}
	@Test
	public void testgetFeedBack() throws FeedbackException{
		assertNotNull(feedbackdao.getFeedBack(15896));
	}
		@Test
	public void testgetFeedBackByDate() throws FeedbackException{
			assertNotNull(feedbackdao.getFeedBackByDate("01/04/2018"));
	}

}

package com.cg.fms.service;

import java.util.ArrayList;

import com.cg.fms.bean.FacultySkill;
import com.cg.fms.exception.FeedbackException;

public interface IFacultySkillService {

	public ArrayList<FacultySkill> showFacultySkillSet() throws FeedbackException;
}

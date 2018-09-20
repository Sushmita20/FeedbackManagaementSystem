package com.cg.fms.dao;

import java.util.ArrayList;

import com.cg.fms.bean.FacultySkill;
import com.cg.fms.exception.FeedbackException;

public interface IFacultySkillSetDao {

	ArrayList<FacultySkill> showFacultySkillSet() throws FeedbackException;
}

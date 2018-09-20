package com.cg.fms.service;

import java.util.ArrayList;

import com.cg.fms.bean.FacultySkill;
import com.cg.fms.dao.FacultySkillSetDaoImpl;
import com.cg.fms.dao.IFacultySkillSetDao;
import com.cg.fms.exception.FeedbackException;

public class FacultySkillServiceImpl implements IFacultySkillService {
	IFacultySkillSetDao facultySkillSetDao = new FacultySkillSetDaoImpl();
	@Override
	public ArrayList<FacultySkill> showFacultySkillSet()
			throws FeedbackException {

		return facultySkillSetDao.showFacultySkillSet();
	}

}

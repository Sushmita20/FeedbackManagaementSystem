package com.cg.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cg.fms.bean.FacultySkill;
import com.cg.fms.exception.FeedbackException;
import com.cg.fms.util.DBConnection;
import com.cg.fms.util.SQLQueries;

 
public class FacultySkillSetDaoImpl implements IFacultySkillSetDao{
	public static final	Logger LOGGER = Logger.getLogger(FacultySkillSetDaoImpl.class);
	
	@Override
	public ArrayList<FacultySkill> showFacultySkillSet() throws FeedbackException {
		LOGGER.info("Inserting Feedback details into database");
		PreparedStatement pst=null;
		ResultSet resultSet = null;
		Connection connection = DBConnection.getInstance();
		ArrayList<FacultySkill> facultySkillList= new ArrayList<FacultySkill>();
		try
		{
			pst=connection.prepareStatement(SQLQueries.Faculty_Skill_Set);
			resultSet=pst.executeQuery();
			
			while(resultSet.next()){	
				FacultySkill facultySkill = new FacultySkill();
				facultySkill.setFacultyId(resultSet.getInt(1));
				facultySkill.setSkillSet(resultSet.getString(2));
				facultySkillList.add(facultySkill);
		}			
			
		} catch (SQLException exception) {
			LOGGER.error("SQL Exception occured!"+exception.getMessage());
			throw new FeedbackException(exception.getMessage());
		}
		finally{
			if(pst!=null&&resultSet!=null&&connection!=null){
				try {
					resultSet.close();
					pst.close();
					connection.close();
				} catch (SQLException exception) {
					throw new FeedbackException(exception.getMessage());	}
			}
		
		}
		return facultySkillList;
	}

}

package com.cg.fms.bean;

public class FacultySkill {

	@Override
	public String toString() {
		return "FacultySkill [facultyId=" + facultyId + ", skillSet="
				+ skillSet + "]";
	}
	private int facultyId;
	private String skillSet;
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	
}

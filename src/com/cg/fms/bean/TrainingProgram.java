package com.cg.fms.bean;

public class TrainingProgram {

	@Override
	public String toString() {
		return "TrainingProgram [TrainingCode=" + TrainingCode
				+ ", courseCode=" + courseCode + ", facultyCode=" + facultyCode
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	private int TrainingCode;
	private int courseCode;
	private int facultyCode;
	private String startDate;
	private String endDate;
	public int getTrainingCode() {
		return TrainingCode;
	}
	public void setTrainingCode(int trainingCode) {
		TrainingCode = trainingCode;
	}
	public int getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	public int getFacultyCode() {
		return facultyCode;
	}
	public void setFacultyCode(int facultyCode) {
		this.facultyCode = facultyCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}

package com.cg.fms.bean;

public class Feedback {

	private int trainingCode;
	private int participantId;
	private String participantName;
	private int FbPrsComm;
	private int FbClrfyDbts;
	private int FbTm;
	private int FbHndOut;
	private int FbHwSwNtwrk;
	private String comments;
	private String suggestions;
	private String feedbackDate;
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public String getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	public int getTrainingCode() {
		return trainingCode;
	}
	public void setTrainingCode(int trainingCode) {
		this.trainingCode = trainingCode;
	}
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	public int getFbPrsComm() {
		return FbPrsComm;
	}
	public void setFbPrsComm(int fbPrsComm) {
		FbPrsComm = fbPrsComm;
	}
	public int getFbClrfyDbts() {
		return FbClrfyDbts;
	}
	public void setFbClrfyDbts(int fbClrfyDbts) {
		FbClrfyDbts = fbClrfyDbts;
	}
	public int getFbTm() {
		return FbTm;
	}
	public void setFbTm(int fbTm) {
		FbTm = fbTm;
	}
	public int getFbHndOut() {
		return FbHndOut;
	}
	public void setFbHndOut(int fbHndOut) {
		FbHndOut = fbHndOut;
	}
	public int getFbHwSwNtwrk() {
		return FbHwSwNtwrk;
	}
	public void setFbHwSwNtwrk(int fbHwSwNtwrk) {
		FbHwSwNtwrk = fbHwSwNtwrk;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	@Override
	public String toString() {
		return "Feedback [trainingCode=" + trainingCode + ", participantId="
				+ participantId + ", participantName=" + participantName
				+ ", FbPrsComm=" + FbPrsComm + ", FbClrfyDbts=" + FbClrfyDbts
				+ ", FbTm=" + FbTm + ", FbHndOut=" + FbHndOut
				+ ", FbHwSwNtwrk=" + FbHwSwNtwrk + ", comments=" + comments
				+ ", suggestions=" + suggestions + ", feedbackDate="
				+ feedbackDate + "]";
	}
	
}

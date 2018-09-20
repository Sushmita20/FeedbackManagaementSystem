package com.cg.fms.bean;

public class ParticipantEnrollment {

	@Override
	public String toString() {
		return "ParticipantEnrollment [trainingCode=" + trainingCode
				+ ", participantId=" + participantId + "]";
	}
	private int trainingCode;
	private int participantId;
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
	
}

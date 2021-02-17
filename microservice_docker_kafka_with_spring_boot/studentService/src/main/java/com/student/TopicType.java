package com.student;

public enum TopicType {

	FEES_PAID("Fees Paid"), DELETE_STUDENT_DETAILS("Delete Student Details");

	private String name;

	private TopicType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

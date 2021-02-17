package com.fees;

public class KafkaMessage {

	private TopicType topicType;

	private String message;

	public KafkaMessage() {
	}

	public KafkaMessage(TopicType topicType, String message) {
		super();
		this.topicType = topicType;
		this.message = message;
	}

	public TopicType getTopicType() {
		return topicType;
	}

	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

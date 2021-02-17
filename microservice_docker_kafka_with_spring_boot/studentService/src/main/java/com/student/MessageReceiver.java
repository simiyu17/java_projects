package com.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.dao.student.StudentDaoImpl;
import com.student.model.FeeStatus;
import com.student.model.Student;

@Component
public class MessageReceiver {

    @Autowired
    private StudentDaoImpl studentdao;

    @StreamListener(target = Sink.INPUT)
    public void receiveMessage(KafkaMessage msg) throws Exception {

    	if(msg.getTopicType() == TopicType.FEES_PAID) {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(msg.getMessage());
            JsonNode stdIdFromMasg = jsonNodeRoot.get("id");
            JsonNode stdpaystatus = jsonNodeRoot.get("fullypaidfee");
            Long stdId = stdIdFromMasg.asLong();
            boolean paidInFull = stdpaystatus.asBoolean();

            if (paidInFull) {
                Student tobeUpdated = studentdao.findById(stdId);
                tobeUpdated.setFeeStatus(FeeStatus.FULLY_PAID);
                studentdao.save(tobeUpdated);
            }
    	}
    }

}
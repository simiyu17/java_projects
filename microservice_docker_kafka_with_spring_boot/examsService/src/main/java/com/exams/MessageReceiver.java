package com.exams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.exams.dao.exam.ExaminationDaoImpl;
import com.exams.model.Examination;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageReceiver {

    @Autowired
    private ExaminationDaoImpl examdao;

    @StreamListener(target = Sink.INPUT)
    public void receiveMessage(KafkaMessage msg) throws Exception {
    	System.out.println("========================================== Message Recieved at EXAMZ ========================================================");
    	if(msg.getTopicType() == TopicType.DELETE_STUDENT_DETAILS) {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(msg.getMessage());
            JsonNode stdIdFromMasg = jsonNodeRoot.get("id");
            JsonNode stdrecstatus = jsonNodeRoot.get("deletestudentrecords");
            Long stdId = stdIdFromMasg.asLong();
            boolean deletestudentrecords = stdrecstatus.asBoolean();

            if (deletestudentrecords) {
                List<Examination> tobeDeleted = examdao.getExaminations(stdId);
                for(Examination ex : tobeDeleted){
                    examdao.delete(ex);
                }
            }
    }
        
    }

}
package com.fees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fees.dao.fees.FeesDaoImpl;
import com.fees.model.Fees;

@Component
public class MessageReceiver {

    @Autowired
    private FeesDaoImpl feedao;

    @StreamListener(target = Sink.INPUT)
    public void receiveMessage(KafkaMessage msg) throws Exception {
    	System.out.println("========================================== Message Recieved at FEES ========================================================");
    	if(msg.getTopicType() == TopicType.DELETE_STUDENT_DETAILS) {
            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(msg.getMessage());
            JsonNode stdIdFromMasg = jsonNodeRoot.get("id");
            JsonNode stdrecstatus = jsonNodeRoot.get("deletestudentrecords");
            Long stdId = stdIdFromMasg.asLong();
            boolean deletestudentrecords = stdrecstatus.asBoolean();

            if (deletestudentrecords) {
                List<Fees> tobeDeleted = feedao.getStudentFees(stdId);
                for(Fees ex : tobeDeleted){
                    feedao.delete(ex);
                }
            }
    	}
    }

}
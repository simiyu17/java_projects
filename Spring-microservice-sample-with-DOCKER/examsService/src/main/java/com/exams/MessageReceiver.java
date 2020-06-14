package com.exams;

import java.util.List;

import com.exams.dao.exam.ExaminationDaoImpl;
import com.exams.model.Examination;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @Autowired
    private ExaminationDaoImpl examdao;

    @JmsListener(destination = "${destination.topic}")
    public void receiveMessage(String msg) throws Exception {

            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(msg);
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
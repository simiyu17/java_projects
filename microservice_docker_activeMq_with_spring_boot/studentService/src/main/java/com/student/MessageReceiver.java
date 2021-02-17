package com.student;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.dao.student.StudentDaoImpl;
import com.student.model.FeeStatus;
import com.student.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class MessageReceiver {

    @Autowired
    private StudentDaoImpl studentdao;

    @JmsListener(destination = "${jms.queue.destination}")
    public void receiveMessage(String msg) throws Exception {

            // create `ObjectMapper` instance
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = mapper.readTree(msg);
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
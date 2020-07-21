package com.fees;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fees.dao.fees.FeesDaoImpl;
import com.fees.model.Fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @Autowired
    private FeesDaoImpl feedao;

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
                List<Fees> tobeDeleted = feedao.getStudentFees(stdId);
                for(Fees ex : tobeDeleted){
                    feedao.delete(ex);
                }
            }
        
    }

}
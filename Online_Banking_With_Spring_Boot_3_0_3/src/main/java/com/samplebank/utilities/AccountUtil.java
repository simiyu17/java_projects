package com.samplebank.utilities;

import org.springframework.stereotype.Component;

@Component
public class AccountUtil {

    private AccountUtil() {}

    public static String generateClientAccountNumber(){
        return "6447453648576";
    }
}

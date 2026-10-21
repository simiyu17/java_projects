package com.samplebank.utilities;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class AccountUtil {
    private AccountUtil() {}

    public static String generateClientAccountNumber(Long clientId, Long clientAccountLength){
        long lengthTobeGenerated = clientAccountLength - (String.valueOf(clientId)).length();
        long randomLong = randomNumber((int) lengthTobeGenerated);
        return String.format("%d%d", clientId, randomLong);
    }

    public static long randomNumber(int length) {
        var min = Long.parseLong("1".repeat(length));
        var max = Long.parseLong("9".repeat(length));
        return new SecureRandom().nextLong(max - min + 1) + min;
    }
}

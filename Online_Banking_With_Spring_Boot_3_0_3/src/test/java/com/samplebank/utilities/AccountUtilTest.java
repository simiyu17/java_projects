package com.samplebank.utilities;

import com.samplebank.UnitTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AccountUtilTest extends UnitTestBase {

private static final Long CLIENT_ACCOUNT_NUMBER_LENGTH = 14L;

    @ParameterizedTest
    @ValueSource(longs = {5, 15, 1000, 55555, 100000001})
    void generateClientAccountNumber(Long clientId) {
        Assertions.assertThat(AccountUtil.generateClientAccountNumber(clientId, CLIENT_ACCOUNT_NUMBER_LENGTH)).startsWith(String.valueOf(clientId)).hasSize(CLIENT_ACCOUNT_NUMBER_LENGTH.intValue());
    }
}
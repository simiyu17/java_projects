package com.samplebank;

import com.samplebank.security.CurrentUserDetails;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser
public abstract class UnitTestBase {

    @MockBean
    protected CurrentUserDetails userDetails;
}

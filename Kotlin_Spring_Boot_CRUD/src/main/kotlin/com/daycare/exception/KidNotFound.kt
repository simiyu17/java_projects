package com.daycare.exception

import java.lang.RuntimeException

class KidNotFound: RuntimeException {
    constructor(kidId: Long): super(String().format("No Kid found by the ID: %d", kidId))
}
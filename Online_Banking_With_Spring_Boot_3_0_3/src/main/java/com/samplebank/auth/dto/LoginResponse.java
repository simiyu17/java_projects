
package com.samplebank.auth.dto;

import java.io.Serializable;

/**
 * @author simiyu
 */
public record LoginResponse(boolean success, String msg, UserDto user, String authToken) implements Serializable {

}

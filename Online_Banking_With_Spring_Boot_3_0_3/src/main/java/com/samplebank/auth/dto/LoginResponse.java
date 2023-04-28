
package com.samplebank.auth.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author simiyu
 */
public record LoginResponse(boolean success, String msg, String authToken) implements Serializable {

}

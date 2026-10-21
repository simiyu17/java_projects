
package com.samplebank.shared.dto;

import java.io.Serializable;

/**
 * @author simiyu
 */
public record ApiResponse(boolean success, String msg) implements Serializable {

}

package com.samplebank.dto;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record ChangePasswordDto(@NotBlank String oldPassword, @NotBlank String newPassword) implements Serializable {

}

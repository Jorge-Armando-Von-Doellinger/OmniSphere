package omnisphere.microsservices.User.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "Username is required!") String username,
        @Email @NotBlank(message = "Email is required!") String email,
        @NotBlank(message = "Password is required!") String password) {
}

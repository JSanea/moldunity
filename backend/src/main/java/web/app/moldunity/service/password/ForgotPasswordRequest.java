package web.app.moldunity.service.password;


import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @NotBlank String email,
        @NotBlank String password
){}

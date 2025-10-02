package web.app.moldunity.service.password;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank String current,
        @NotBlank String fresh
){}

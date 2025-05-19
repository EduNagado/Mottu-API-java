package Mottu.com.GEF.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthDTO(

    @NotBlank
    String username,

    @NotBlank
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
        message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula e um número"
    )
    String password,

    @NotBlank
    @Email
    String email) {


}

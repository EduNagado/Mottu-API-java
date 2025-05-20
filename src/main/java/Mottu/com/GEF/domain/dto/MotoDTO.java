package Mottu.com.GEF.domain.dto;


import Mottu.com.GEF.domain.model.enums.Modelo;
import Mottu.com.GEF.domain.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MotoDTO(
    
    @NotNull(message = "O modelo é obrigatório")
    Modelo modelo,

    @NotBlank
    String cor,
    
    @NotBlank
    @Pattern(
        regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$",
        message = "Placa inválida. Use o padrão ABC1D23."
    )
    String placa,

    @NotNull(message = "O Status não poder ser nulo")
    Status status) {
}

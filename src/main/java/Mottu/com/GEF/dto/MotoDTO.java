package Mottu.com.GEF.dto;


import Mottu.com.GEF.model.enums.Status;
import Mottu.com.GEF.model.enums.Modelo;
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

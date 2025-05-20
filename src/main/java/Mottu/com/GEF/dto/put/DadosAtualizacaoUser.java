package Mottu.com.GEF.dto.put;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUser(

    @NotNull
    UUID id,
    String username,
    String email) {
}

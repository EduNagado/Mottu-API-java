package Mottu.com.GEF.domain.dto.put;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUser(

    @NotNull
    UUID id,
    String username,
    String email) {
}

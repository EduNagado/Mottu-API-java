package Mottu.com.GEF.domain.dto.put;

import java.util.UUID;

import Mottu.com.GEF.domain.model.enums.Modelo;
import Mottu.com.GEF.domain.model.enums.Status;
import jakarta.validation.constraints.NotNull;


public record DadosAtualizacaoMoto(
    
    @NotNull
    UUID id,
    Modelo modelo,
    String placa,
    String cor,
    Status status ) {
    
}

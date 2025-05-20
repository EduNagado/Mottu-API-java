package Mottu.com.GEF.domain.dto.put;

import java.util.UUID;

import Mottu.com.GEF.domain.model.Moto;
import Mottu.com.GEF.domain.model.enums.Modelo;
import Mottu.com.GEF.domain.model.enums.Status;

public record DadosDetalhamentoMoto(UUID id, Modelo modelo, String placa, String cor, Status status) {
    public DadosDetalhamentoMoto(Moto moto){
        this(moto.getId(), moto.getModelo(), moto.getPlaca(), moto.getCor(), moto.getStatus());
    }

    
}

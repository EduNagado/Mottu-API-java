package Mottu.com.GEF.dto.put;

import java.util.UUID;

import Mottu.com.GEF.model.Moto;
import Mottu.com.GEF.model.enums.Modelo;
import Mottu.com.GEF.model.enums.Status;

public record DadosDetalhamentoMoto(UUID id, Modelo modelo, String placa, String cor, Status status) {
    public DadosDetalhamentoMoto(Moto moto){
        this(moto.getId(), moto.getModelo(), moto.getPlaca(), moto.getCor(), moto.getStatus());
    }

    
}

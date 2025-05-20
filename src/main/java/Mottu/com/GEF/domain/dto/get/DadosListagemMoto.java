package Mottu.com.GEF.domain.dto.get;



import Mottu.com.GEF.domain.model.Moto;
import Mottu.com.GEF.domain.model.enums.Modelo;
import Mottu.com.GEF.domain.model.enums.Status;

public record DadosListagemMoto( Modelo modelo, String placa, String cor, Status status ) {
    public DadosListagemMoto(Moto moto){
        this( moto.getModelo(), moto.getPlaca(), moto.getCor(), moto.getStatus());
    }
}

package Mottu.com.GEF.dto.get;



import Mottu.com.GEF.model.Moto;
import Mottu.com.GEF.model.enums.Modelo;
import Mottu.com.GEF.model.enums.Status;

public record DadosListagemMoto( Modelo modelo, String placa, String cor, Status status ) {
    public DadosListagemMoto(Moto moto){
        this( moto.getModelo(), moto.getPlaca(), moto.getCor(), moto.getStatus());
    }
}

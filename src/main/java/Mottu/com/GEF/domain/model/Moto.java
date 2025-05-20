package Mottu.com.GEF.domain.model;

import java.util.UUID;

import Mottu.com.GEF.domain.dto.MotoDTO;
import Mottu.com.GEF.domain.dto.put.DadosAtualizacaoMoto;
import Mottu.com.GEF.domain.model.enums.Modelo;
import Mottu.com.GEF.domain.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name ="motos")
@Entity(name="Motos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Moto {

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Modelo modelo;

    private String cor;
    private String placa;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Moto(MotoDTO dadosMoto, User usuario) {
        this.modelo = dadosMoto.modelo();
        this.cor = dadosMoto.cor();
        this.placa = dadosMoto.placa();
        this.status = dadosMoto.status();
        this.usuario = usuario;
    }

    public void atualizarInformacoes(DadosAtualizacaoMoto dadosMoto) {
    if (dadosMoto.modelo() != null) {
        this.modelo = dadosMoto.modelo();
    }
    if (dadosMoto.placa() != null) {
        this.placa = dadosMoto.placa();
    }
    if (dadosMoto.cor() != null) {
        this.cor = dadosMoto.cor();
    }
    if (dadosMoto.status() != null) {
        this.status = dadosMoto.status();
    }
}
}

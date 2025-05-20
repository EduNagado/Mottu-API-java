package Mottu.com.GEF.model;
import java.util.UUID;

import Mottu.com.GEF.dto.AuthDTO;
import Mottu.com.GEF.dto.put.DadosAtualizacaoUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name ="users")
@Entity(name="User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    
    public User(AuthDTO dados) {
        this.username = dados.username();
        this.password = dados.password();
        this.email = dados.email();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private String email;


    public void atualizarInformacoes (DadosAtualizacaoUser dados){
        if(dados.username() != null ){
            this.username = dados.username();
        }
         if(dados.email() != null ){
            this.email = dados.email();
        }
    }
}

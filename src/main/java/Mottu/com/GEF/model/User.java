package Mottu.com.GEF.model;

import java.util.UUID;

import Mottu.com.GEF.dto.AuthDTO;
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

}

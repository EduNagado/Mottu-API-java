package Mottu.com.GEF.dto.put;

import java.util.UUID;

import Mottu.com.GEF.model.User;

public record DadosDetalhamentoUser(UUID id, String username, String email) {
    
    public DadosDetalhamentoUser(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
    
}

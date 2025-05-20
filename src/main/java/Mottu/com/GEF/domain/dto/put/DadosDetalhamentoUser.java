package Mottu.com.GEF.domain.dto.put;

import java.util.UUID;

import Mottu.com.GEF.domain.model.User;

public record DadosDetalhamentoUser(UUID id, String username, String email) {
    
    public DadosDetalhamentoUser(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
    
}

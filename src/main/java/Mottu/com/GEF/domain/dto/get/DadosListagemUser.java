package Mottu.com.GEF.domain.dto.get;

import Mottu.com.GEF.domain.model.User;

public record DadosListagemUser( String username, String email) {
    public DadosListagemUser(User user) {
        this( user.getUsername(), user.getEmail());
    }
    
}

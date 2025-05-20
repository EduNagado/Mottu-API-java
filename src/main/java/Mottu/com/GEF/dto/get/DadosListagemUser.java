package Mottu.com.GEF.dto.get;

import Mottu.com.GEF.model.User;

public record DadosListagemUser( String username, String email) {
    public DadosListagemUser(User user) {
        this( user.getUsername(), user.getEmail());
    }
    
}

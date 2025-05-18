package Mottu.com.GEF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Mottu.com.GEF.dto.AuthDTO;
import Mottu.com.GEF.model.User;
import Mottu.com.GEF.repository.AuthRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    

    // @PostMapping("/login")
    // public void login() {

        
    // }

    @Autowired
    private AuthRepository repository;

    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid AuthDTO dados) {
        repository.save(new User(dados));
       
    }
}

package Mottu.com.GEF.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Mottu.com.GEF.dto.DadosListagemMoto;
import Mottu.com.GEF.dto.MotoDTO;
import Mottu.com.GEF.model.Moto;
import Mottu.com.GEF.model.User;
import Mottu.com.GEF.repository.AuthRepository;
import Mottu.com.GEF.repository.MotoRepository;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/CadastrarMoto")
public class MotoController {
    
    @Autowired
    private MotoRepository repository;

    @Autowired
    private AuthRepository userRepository;

  @PostMapping
    public void registerMoto(@RequestBody @Valid MotoDTO dadosMoto, @RequestParam String usuarioId) {
    // Converte para UUID com hífens, se necessário
    String uuidComHifens = usuarioId.replaceFirst(
        "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
        "$1-$2-$3-$4-$5"
    );
    UUID uuid = UUID.fromString(uuidComHifens);

    User usuario = userRepository.findById(uuid)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    System.out.println("Usuário " + usuario.getId() +" Cadastrou uma moto com sucesso");
    repository.save(new Moto(dadosMoto, usuario));
    }

    @GetMapping
    public Page<DadosListagemMoto> listar(@PageableDefault(size = 10, sort={"modelo"}) Pageable paginacao){
        return repository.findAll(paginacao)
            .map(DadosListagemMoto::new);
    }
}

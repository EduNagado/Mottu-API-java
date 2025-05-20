package Mottu.com.GEF.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Mottu.com.GEF.dto.MotoDTO;
import Mottu.com.GEF.dto.get.DadosListagemMoto;
import Mottu.com.GEF.dto.put.DadosAtualizacaoMoto;
import Mottu.com.GEF.model.Moto;
import Mottu.com.GEF.model.User;
import Mottu.com.GEF.repository.AuthRepository;
import Mottu.com.GEF.repository.MotoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("Moto")
public class MotoController {
    
    @Autowired
    private MotoRepository repository;

    @Autowired
    private AuthRepository userRepository;

    @PostMapping("/cadastrar")
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

    @GetMapping("/listar")
    public Page<DadosListagemMoto> listar(@PageableDefault(size = 10, sort={"modelo"}) Pageable paginacao){
        return repository.findAll(paginacao)
            .map(DadosListagemMoto::new);
    }

    @PutMapping("/atualizar")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMoto dadosMoto) {
        Moto moto = repository.findById(dadosMoto.id())
            .orElseThrow(() -> new RuntimeException("Moto não encontrada com ID: " + dadosMoto.id()));
        moto.atualizarInformacoes(dadosMoto);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }
}

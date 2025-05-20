package Mottu.com.GEF.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Mottu.com.GEF.dto.AuthDTO;
import Mottu.com.GEF.dto.get.DadosListagemUser;
import Mottu.com.GEF.dto.put.DadosAtualizacaoUser;
import Mottu.com.GEF.model.User;
import Mottu.com.GEF.repository.AuthRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthRepository repository;
    
    
    @PostMapping("/cadastrar")
    @Transactional
    public void register(@RequestBody @Valid AuthDTO dados) {
        repository.save(new User(dados));
       
    }

    @Cacheable(value = "usuarios-pagina-fixa")
    @GetMapping("listar")
    public Page<DadosListagemUser> listar() {
        Pageable paginacao = PageRequest.of(0, 20, Sort.by("username"));
        return repository.findAll(paginacao)
                .map(DadosListagemUser::new);
    }
    
    @PutMapping("/atualizar")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoUser dados) {
        User user = repository.findById(dados.id())
            .orElseThrow(() -> new RuntimeException(" não encontrada com ID: " + dados.id()));
        user.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }
    
}

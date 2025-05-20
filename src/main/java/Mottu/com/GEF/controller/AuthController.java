package Mottu.com.GEF.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import Mottu.com.GEF.dto.AuthDTO;
import Mottu.com.GEF.dto.get.DadosListagemUser;
import Mottu.com.GEF.dto.put.DadosAtualizacaoUser;
import Mottu.com.GEF.dto.put.DadosDetalhamentoUser;
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
    public ResponseEntity<DadosDetalhamentoUser> register(@RequestBody @Valid AuthDTO dados, UriComponentsBuilder uriBuilder) {
        var user = new User(dados);
        repository.save(user);
        var uri = uriBuilder.path("/auth/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUser(user));
    }

    @Cacheable(value = "usuarios-pagina-fixa")
    @GetMapping("listar")
    public ResponseEntity<Page<DadosListagemUser>> listar() {
        Pageable paginacao = PageRequest.of(0, 20, Sort.by("username"));
        Page<DadosListagemUser> page = repository.findAll(paginacao)
                .map(DadosListagemUser::new);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUser> atualizar(@RequestBody @Valid DadosAtualizacaoUser dados) {
        User user = repository.findById(dados.id())
            .orElseThrow(() -> new RuntimeException(" não encontrada com ID: " + dados.id()));
        user.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUser(user));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUser> detalhar(@PathVariable UUID id){
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        return ResponseEntity.ok(new DadosDetalhamentoUser(user));
    }
    
}

package Mottu.com.GEF.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import Mottu.com.GEF.dto.MotoDTO;
import Mottu.com.GEF.dto.get.DadosListagemMoto;
import Mottu.com.GEF.dto.put.DadosAtualizacaoMoto;
import Mottu.com.GEF.dto.put.DadosDetalhamentoMoto;
import Mottu.com.GEF.model.Moto;
import Mottu.com.GEF.model.User;
import Mottu.com.GEF.repository.AuthRepository;
import Mottu.com.GEF.repository.MotoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/moto")
public class MotoController {
    
    @Autowired
    private MotoRepository repository;

    @Autowired
    private AuthRepository userRepository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMoto> registerMoto(@RequestBody @Valid MotoDTO dadosMoto,@RequestParam String usuarioId, UriComponentsBuilder uriBuilder) {
        String uuidComHifens = usuarioId.replaceFirst(
            "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
            "$1-$2-$3-$4-$5"
        );
        UUID uuid = UUID.fromString(uuidComHifens);

        User usuario = userRepository.findById(uuid)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + uuid));

        Moto moto = new Moto(dadosMoto, usuario);
        repository.save(moto);

        var uri = uriBuilder.path("/moto/{id}").buildAndExpand(moto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMoto(moto));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemMoto>> listar(@PageableDefault(size = 10, sort = {"modelo"}) Pageable paginacao) {
        Page<DadosListagemMoto> page = repository.findAll(paginacao)
            .map(DadosListagemMoto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMoto> atualizar(@RequestBody @Valid DadosAtualizacaoMoto dadosMoto) {
        Moto moto = repository.findById(dadosMoto.id())
            .orElseThrow(() -> new RuntimeException("Moto não encontrada com ID: " + dadosMoto.id()));

        moto.atualizarInformacoes(dadosMoto);
        return ResponseEntity.ok(new DadosDetalhamentoMoto(moto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMoto> detalhar(@PathVariable UUID id) {
        Moto moto = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Moto não encontrada com ID: " + id));
        return ResponseEntity.ok(new DadosDetalhamentoMoto(moto));
    }
}

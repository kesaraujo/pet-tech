package br.com.fiap.pettech.dominio.pessoa.controller;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                   @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var pessoas = pessoaService.findAll(pageRequest);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable UUID id) {
        var pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> save(@Valid @PathVariable PessoaDTO dto) {
        var pessoaSaved = pessoaService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((pessoaSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(pessoaSaved);
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable UUID id, @RequestBody PessoaDTO dto) {
        var pessoaUpdated = pessoaService.update(id, dto);
        return ResponseEntity.ok(pessoaUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

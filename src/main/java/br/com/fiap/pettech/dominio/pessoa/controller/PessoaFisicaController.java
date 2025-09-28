package br.com.fiap.pettech.dominio.pessoa.controller;

import br.com.fiap.pettech.dominio.pessoa.entity.PessoaFisica;
import br.com.fiap.pettech.dominio.pessoa.repository.PessoaFisicaCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController()
@RequestMapping("/pf")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaCollectionRepository repository;

    @GetMapping(value = "")
    public ResponseEntity<Collection<PessoaFisica>> findAll() {
        //var pessoas = repository.findAll().stream().map(PessoaFisicaDTO::of).toList();
        var pessoas = repository.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<PessoaFisica>> findById(@PathVariable Long id) {
        var pessoaFisica = this.repository.findById(id);
        return ResponseEntity.ok(pessoaFisica);
    }

    @PostMapping
    public ResponseEntity<PessoaFisica> save(@RequestBody PessoaFisica pessoa) {
        repository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Optional<PessoaFisica>> update(@RequestBody PessoaFisica pessoa) {
        Optional<PessoaFisica> pessoaAdicionada = repository.update(pessoa);
        return ResponseEntity.ok(pessoaAdicionada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.delete(id);
        return ResponseEntity.ok("Delete efetuado com sucesso!");
    }
}

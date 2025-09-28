package br.com.fiap.pettech.dominio.pessoa.controller;

import br.com.fiap.pettech.dominio.pessoa.entity.PessoaFisica;
import br.com.fiap.pettech.dominio.pessoa.repository.PessoaFisicaCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

}

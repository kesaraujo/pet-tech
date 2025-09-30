package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repository;

    public Collection<Produto> findAll() {
        var produtos = repository.findAll();
        return produtos;
    }

    public Optional<Produto> findById(UUID id) {
        return repository.findById(id);
    }

    public Produto save(Produto produto) {
        var produtoSaved = repository.save(produto);
        return produtoSaved;
    }

//    public Optional<Produto> update(UUID id, Produto produto) {
//        Optional<Produto> produtoEncontrado = this.findById(id);
//
//        if (produtoEncontrado.isPresent()) {
//            Produto produtoUpdate = repository.update(id, produto);
//            return Optional.of(produtoUpdate);
//        }
//        return Optional.empty();
//    }

//    public void delete(UUID id) {
//        repository.delete(id);
//    }
}

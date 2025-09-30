package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.exception.DatabaseException;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repository;

    public Collection<Produto> findAll() {
        var produtos = repository.findAll();
        return produtos;
    }

    public Produto findById(UUID id) {
        var produto = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado."));
        return produto;
    }

    public Produto save(Produto produto) {
        var produtoSaved = repository.save(produto);
        return produtoSaved;
    }

    public Produto update(UUID id, Produto produto) {

        try {
            Produto buscaProduto = repository.getReferenceById(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImagem(produto.getUrlImagem());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repository.save(buscaProduto);
            return buscaProduto;
        } catch(EntityNotFoundException e) {
            throw new ControllerNotFoundException("Produto não encontrado." + id);
        }
    }

    public void delete(UUID id) {
        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entidade não encontrada." + id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade de base.");
        }
    }
}

package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.exception.DatabaseException;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repository;

    public Page<ProdutoDTO> findAll(PageRequest pagina) {

        var produtos = repository.findAll(pagina);
        return produtos.map(p -> new ProdutoDTO(p));

    }

    public ProdutoDTO findById(UUID id) {
        var produto = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado."));
        return new ProdutoDTO(produto);
    }

    public ProdutoDTO save(ProdutoDTO dto) {

        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setUrlImagem(dto.getUrlImage());
        entity.setPreco(dto.getPreco());

        var produtoSaved = repository.save(entity);
        return new ProdutoDTO(produtoSaved);
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produto) {

        try {

            Produto buscaProduto = repository.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImagem(produto.getUrlImage());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repository.save(buscaProduto);
            return new ProdutoDTO(buscaProduto);

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

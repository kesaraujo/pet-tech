package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private static Set<Produto> produtos;

    static  {
        produtos = new LinkedHashSet<>();

        Produto produto1 = new Produto("Produto 1", "Descrição Produto1", "Imagem Produto1", 19.99);
        Produto produto2 = new Produto("Produto 2", "Descrição Produto2", "Imagem Produto2", 29.99);

        produtos.add(produto1);
        produtos.add(produto2);
    }

    @Override
    public Optional<Produto> findById(UUID id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Set<Produto> findAll() {
        return produtos;
    }

    @Override
    public Produto save(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto update(UUID id, Produto produto) {
        var produtoEncontrado = produtos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        produtoEncontrado.setNome(produto.getNome());
        produtoEncontrado.setDescricao(produto.getDescricao());
        produtoEncontrado.setUrlImagem(produto.getUrlImagem());
        produtoEncontrado.setPreco(produto.getPreco());
        return produtoEncontrado;
    }

    @Override
    public void delete(UUID id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }
}

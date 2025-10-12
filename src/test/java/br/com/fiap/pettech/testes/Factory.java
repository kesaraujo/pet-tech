package br.com.fiap.pettech.testes;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;

public class Factory {

    public static Produto createProduto() {
        Produto produto = new Produto("IPhone", "Descrição IPhone", "URL IPhone", 800.00);
        return produto;
    }

    public static ProdutoDTO createProdutoDTO() {
        Produto produto = createProduto();
        return new ProdutoDTO(produto);
    }
}

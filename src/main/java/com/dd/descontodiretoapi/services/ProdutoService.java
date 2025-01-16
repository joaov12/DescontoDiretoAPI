package com.dd.descontodiretoapi.services;

import java.util.List;

import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;



    public List<Produto> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado.");
        }
        return produtos;
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe produto com o ID: " + id));
    }

    public Produto addProduto(Produto produto) {
        try {
            return produtoRepository.save(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar produto", e);
        }


    }

    public Produto updateProduto(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new RuntimeException("Produto não encontrada para o ID: " + produto.getId());
        }

        try {
            return produtoRepository.save(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}

package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.services.ClienteService;
import com.dd.descontodiretoapi.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
@Tag(name = "Produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Obter todos os Produtos", description = "Retorna uma lista de todos os produtos cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @Operation(summary = "Obter produto por ID", description = "Retorna um produto com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @Operation(summary = "Adicionar produto", description = "Adiciona um novo produto")
    @PostMapping("/add")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.addProduto(produto));
    }

    @Operation(summary = "Editar produto", description = "Edita um produto existente")
    @PutMapping("/edit")
    public ResponseEntity<Produto> editproduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateProduto(produto));
    }

    @Operation(summary = "Excluir produto", description = "Exclui um produto com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteproduto(@PathVariable("id") Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}

package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Obter todos os Produtos",
            description = "Retorna uma lista de todos os produtos cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de produtos recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @Operation(
            summary = "Obter produto por ID",
            description = "Retorna um produto com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/find/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @Operation(
            summary = "Adicionar produto",
            description = "Adiciona um novo produto",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Produto adicionado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/add")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.addProduto(produto));
    }

    @Operation(
            summary = "Editar produto",
            description = "Edita um produto existente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto editado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PutMapping("/edit")
    public ResponseEntity<Produto> editProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateProduto(produto));
    }

    @Operation(
            summary = "Excluir produto",
            description = "Exclui um produto com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Produto excluído com sucesso",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable("id") Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
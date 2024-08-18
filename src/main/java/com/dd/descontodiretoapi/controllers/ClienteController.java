package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Cliente;
import com.dd.descontodiretoapi.services.ClienteService;
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
@RequestMapping("clientes")
@Tag(name = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
            summary = "Obter todos os Clientes",
            description = "Retorna uma lista de todos os clientes cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de clientes recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @Operation(
            summary = "Obter cliente por ID",
            description = "Retorna um cliente com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
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
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @Operation(
            summary = "Adicionar cliente",
            description = "Adiciona um novo cliente",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Cliente adicionado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
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
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.addCliente(cliente));
    }

    @Operation(
            summary = "Editar cliente",
            description = "Edita um cliente existente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente editado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
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
    public ResponseEntity<Cliente> editCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(cliente));
    }

    @Operation(
            summary = "Excluir cliente",
            description = "Exclui um cliente com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Cliente excluído com sucesso",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado",
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
    public ResponseEntity<?> deleteCliente(@PathVariable("id") Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
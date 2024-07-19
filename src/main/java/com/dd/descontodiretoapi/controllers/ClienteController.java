package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Cliente;
import com.dd.descontodiretoapi.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Obter todos os Clientes", description = "Retorna uma lista de todos os clientes cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @Operation(summary = "Obter cliente por ID", description = "Retorna um cliente com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @Operation(summary = "Adicionar cliente", description = "Adiciona um novo cliente")
    @PostMapping("/add")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.addCliente(cliente));
    }

    @Operation(summary = "Editar cliente", description = "Edita um cliente existente")
    @PutMapping("/edit")
    public ResponseEntity<Cliente> editCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(cliente));
    }

    @Operation(summary = "Excluir cliente", description = "Exclui um cliente com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}

package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.services.ClienteService;
import com.dd.descontodiretoapi.services.ComercioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comercios")
@Tag(name = "Comercios")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    @Operation(summary = "Obter todos os comercios", description = "Retorna uma lista de todos os comercios cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Comercio>> getAllComercios() {
        return ResponseEntity.status(HttpStatus.OK).body(comercioService.findAll());
    }

    @Operation(summary = "Obter Comercio por ID", description = "Retorna um Comercio com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Comercio> getComercioById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(comercioService.findById(id));
    }

    @Operation(summary = "Adicionar Comercio", description = "Adiciona um novo Comercio")
    @PostMapping("/add")
    public ResponseEntity<Comercio> addComercio(@RequestBody Comercio comercio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comercioService.addComercio(comercio));
    }

    @Operation(summary = "Editar Comercio", description = "Edita um Comercio existente")
    @PutMapping("/edit")
    public ResponseEntity<Comercio> editComercio(@RequestBody Comercio comercio) {
        return ResponseEntity.status(HttpStatus.OK).body(comercioService.updateComercio(comercio));
    }

    @Operation(summary = "Excluir comercio", description = "Exclui um comercio com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComercio(@PathVariable("id") Long id) {
        comercioService.deleteComercio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}

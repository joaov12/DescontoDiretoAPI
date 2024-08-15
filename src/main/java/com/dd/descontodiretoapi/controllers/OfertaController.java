package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Oferta;
import com.dd.descontodiretoapi.services.OfertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ofertas")
@Tag(name = "Ofertas")
public class OfertaController {

    @Autowired
    OfertaService ofertaService;

    @Operation(summary = "Obter todos os Ofertas", description = "Retorna uma lista de todos os ofertas cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Oferta>> getAllOfertas() {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.findAll());
    }

    @Operation(summary = "Obter oferta por ID", description = "Retorna um oferta com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Oferta> getOfertaById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.findById(id));
    }

    @Operation(summary = "Adicionar oferta", description = "Adiciona um novo oferta")
    @PostMapping("/add")
    public ResponseEntity<Oferta> addOferta(@RequestBody Oferta oferta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaService.addOferta(oferta));
    }

    @Operation(summary = "Editar oferta", description = "Edita um oferta existente")
    @PutMapping("/edit")
    public ResponseEntity<Oferta> editOferta(@RequestBody Oferta oferta) {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.updateOferta(oferta));
    }

    @Operation(summary = "Excluir oferta", description = "Exclui um oferta com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOferta(@PathVariable("id") Long id) {
        ofertaService.deleteOferta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}

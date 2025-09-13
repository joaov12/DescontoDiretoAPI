package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.dto.AddOfertaDTO;
import com.dd.descontodiretoapi.models.Oferta;
import com.dd.descontodiretoapi.services.OfertaService;
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
@RequestMapping("/ofertas")
@Tag(name = "Ofertas")
public class OfertaController {

    @Autowired
    OfertaService ofertaService;

    @Operation(
            summary = "Obter todos os Ofertas",
            description = "Retorna uma lista de todos os ofertas cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de ofertas recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Oferta.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Oferta>> getAllOfertas() {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.findAll());
    }

    @Operation(
            summary = "Obter oferta por ID",
            description = "Retorna um oferta com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Oferta encontrada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Oferta.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Oferta não encontrada",
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
    public ResponseEntity<Oferta> getOfertaById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.findById(id));
    }

    @Operation(
            summary = "Adicionar oferta",
            description = "Adiciona um novo oferta",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Oferta adicionada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Oferta.class))
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
    @PostMapping
    public ResponseEntity<Oferta> addOferta(@RequestBody AddOfertaDTO addOfertaDTO) {
        Oferta novaOferta = ofertaService.addOferta(addOfertaDTO);
        return new ResponseEntity<>(novaOferta, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Editar oferta",
            description = "Edita um oferta existente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Oferta editada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Oferta.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Oferta não encontrada",
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
    public ResponseEntity<Oferta> editOferta(@RequestBody Oferta oferta) {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.updateOferta(oferta));
    }

    @Operation(
            summary = "Excluir oferta",
            description = "Exclui um oferta com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Oferta excluída com sucesso",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Oferta não encontrada",
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
    public ResponseEntity<?> deleteOferta(@PathVariable("id") Long id) {
        ofertaService.deleteOferta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @Operation(
            summary = "Obter top ofertas por likes",
            description = "Retorna as ofertas com mais likes ordenadas por relevância",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de top ofertas recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Oferta.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/top")
    public ResponseEntity<List<Oferta>> getTopOfertas() {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.findTopOfertas());
    }

    @Operation(
            summary = "Obter ofertas recentes",
            description = "Retorna as ofertas mais recentes ordenadas por data de postagem",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de ofertas recentes recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Oferta.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/recentes")
    public ResponseEntity<List<Oferta>> getOfertasRecentes() {
        return ResponseEntity.status(HttpStatus.OK).body(ofertaService.findOfertasRecentes());
    }

}
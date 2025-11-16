package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.dto.LoginComercioRequest;
import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.repositories.ComercioRepository;
import com.dd.descontodiretoapi.services.ComercioService;
import com.dd.descontodiretoapi.services.aws.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/comercios")
@Tag(name = "Comercios")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    @Autowired
    private ComercioRepository comercioRepository;

    @Autowired
    private S3Service s3Service;

    @Operation(
            summary = "Obter todos os comércios",
            description = "Retorna uma lista de todos os comércios cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de comércios recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comercio.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Comercio>> getAllComercios() {
        return ResponseEntity.status(HttpStatus.OK).body(comercioService.findAll());
    }

    @Operation(
            summary = "Obter Comercio por ID",
            description = "Retorna um Comercio com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comercio encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comercio.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Comercio não encontrado",
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
    public ResponseEntity<Comercio> getComercioById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(comercioService.findById(id));
    }

    @Operation(
            summary = "Adicionar Comercio",
            description = "Adiciona um novo Comercio",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Comercio adicionado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comercio.class))
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
    public ResponseEntity<Comercio> addComercio(@RequestBody Comercio comercio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comercioService.addComercio(comercio));
    }

    @Operation(
            summary = "Editar Comercio",
            description = "Edita um Comercio existente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comercio editado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comercio.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Comercio não encontrado",
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
    public ResponseEntity<Comercio> editComercio(@RequestBody Comercio comercio) {
        return ResponseEntity.status(HttpStatus.OK).body(comercioService.updateComercio(comercio));
    }

    @Operation(
            summary = "Excluir comercio",
            description = "Exclui um comercio com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Comercio excluído com sucesso",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Comercio não encontrado",
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
    public ResponseEntity<?> deleteComercio(@PathVariable("id") Long id) {
        comercioService.deleteComercio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @PostMapping(value = "/upload-foto-comercio/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Comercio uploadFoto(@PathVariable Long id, @RequestPart(value = "photo") MultipartFile file) {
        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comércio não encontrado!"));



        try {
            String fotoUrl = s3Service.uploadFile(
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType()
            );

            comercio.setFotoUrl(fotoUrl);
            comercioRepository.save(comercio);

            return comercio;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da foto: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Login do comércio",
            description = "Realiza o login de um comércio usando email e senha",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comercio.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Credenciais inválidas",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Comercio> login(@RequestBody LoginComercioRequest request) {
        Comercio comercio = comercioService.login(request.getEmail(), request.getSenha());

        return ResponseEntity.status(HttpStatus.OK).body(comercio);
    }


}
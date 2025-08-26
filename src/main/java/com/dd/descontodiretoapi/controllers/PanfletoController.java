package com.dd.descontodiretoapi.controllers;

import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.models.Panfleto;
import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.repositories.PanfletoRepository;
import com.dd.descontodiretoapi.services.PanfletoService;
import com.dd.descontodiretoapi.services.aws.S3Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("panfletos")
@Tag(name = "Panfletos")
public class PanfletoController {
    @Autowired
    private PanfletoService panfletoService;

    @Autowired
    private PanfletoRepository panfletoRepository;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/all")
    public ResponseEntity<List<Panfleto>> getAllPanfletos() {
        return ResponseEntity.status(HttpStatus.OK).body(panfletoService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Panfleto> getPanfletoById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(panfletoService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Panfleto> addPanfleto(@RequestBody Panfleto panfleto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(panfletoService.addPanfleto(panfleto));
    }

    @PutMapping("/edit")
    public ResponseEntity<Panfleto> editPanfleto(@RequestBody Panfleto panfleto) {
        return ResponseEntity.status(HttpStatus.OK).body(panfletoService.updatePanfleto(panfleto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePanfleto(@PathVariable("id") Long id) {
        panfletoService.deletePanfleto(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/upload-foto-comercio/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Panfleto uploadFoto(@PathVariable Long id, @RequestPart(value = "photo") MultipartFile file) {
        Panfleto panfleto = panfletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Panfleto não encontrado!"));

        try {
            // Fazer upload da imagem para o S3
            String fotoUrl = s3Service.uploadFile(
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType()
            );

            // Salvar o link da imagem no banco
            panfleto.setFotoUrl(fotoUrl);
            panfletoRepository.save(panfleto);

            return panfleto;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da foto: " + e.getMessage());
        }
    }
}

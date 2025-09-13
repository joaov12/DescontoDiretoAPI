package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.dto.AddPanfletoDTO;
import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.models.Panfleto;
import com.dd.descontodiretoapi.repositories.ComercioRepository;
import com.dd.descontodiretoapi.repositories.PanfletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanfletoService {

    @Autowired
    private PanfletoRepository panfletoRepository;

    @Autowired
    private ComercioRepository comercioRepository;

    public List<Panfleto> findAll(){
        return panfletoRepository.findAll();
    }

    public Panfleto addPanfleto(Panfleto panfleto) {
        try {
            return panfletoRepository.save(panfleto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar panfleto", e);
        }
    }

    public Panfleto addPanfleto(AddPanfletoDTO panfletoDTO) {
        // Busca Comercio pelo ID
        Comercio comercio = comercioRepository.findById(panfletoDTO.getComercioId())
                .orElseThrow(() -> new RuntimeException("Comercio não encontrado"));

        // Cria novo Panfleto
        Panfleto panfleto = new Panfleto();
        panfleto.setComercio(comercio);
        panfleto.setFotoUrl(panfletoDTO.getFotoUrl());
        panfleto.setDataExpiracao(panfletoDTO.getDataExpiracao());

        try {
            return panfletoRepository.save(panfleto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar panfleto", e);
        }
    }

    public Panfleto findById(Long id){
        return panfletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe panfleto com o ID: " + id));
    }

    public Panfleto updatePanfleto(Panfleto panfleto) {
        if (!panfletoRepository.existsById(panfleto.getId())) {
            throw new RuntimeException("Panfleto não encontrado para o ID: " + panfleto.getId());
        }

        try {
            return panfletoRepository.save(panfleto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar panfleto", e);
        }
    }

    public void deletePanfleto(Long id) {
        panfletoRepository.deleteById(id);
    }
}

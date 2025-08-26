package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Panfleto;
import com.dd.descontodiretoapi.repositories.PanfletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanfletoService {

    @Autowired
    private PanfletoRepository panfletoRepository;

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

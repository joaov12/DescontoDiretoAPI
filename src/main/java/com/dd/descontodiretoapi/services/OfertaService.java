package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Oferta;
import com.dd.descontodiretoapi.repositories.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    public List<Oferta> findAll() {
        List<Oferta> ofertas = ofertaRepository.findAll();
        if (ofertas.isEmpty()) {
            throw new RuntimeException("Nenhum oferta encontrado.");
        }
        return ofertas;
    }

    public Oferta findById(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe oferta com o ID: " + id));
    }

    public Oferta addOferta(Oferta oferta) {
        try {
            return ofertaRepository.save(oferta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar oferta", e);
        }
    }

    public Oferta updateOferta(Oferta oferta) {
        if (!ofertaRepository.existsById(oferta.getId())) {
            throw new RuntimeException("Oferta não encontrada para o ID: " + oferta.getId());
        }

        try {
            return ofertaRepository.save(oferta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar oferta", e);
        }
    }

    public void deleteOferta(Long id) {
        ofertaRepository.deleteById(id);
    }
}

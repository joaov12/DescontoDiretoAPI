package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.repositories.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercioService {

    @Autowired
    private ComercioRepository comercioRepository;

    public List<Comercio> findAll() {
        List<Comercio> comercios = comercioRepository.findAll();
        if (comercios.isEmpty()) {
            throw new RuntimeException("Nenhum comercio encontrado.");
        }
        return comercios;
    }

    public Comercio findById(Long id) {
        return comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe comercio com o ID: " + id));
    }

    public Comercio addComercio(Comercio comercio) {
        try {
            return comercioRepository.save(comercio);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar comercio", e);
        }
    }

    public Comercio updateComercio(Comercio comercio) {
        if (!comercioRepository.existsById(comercio.getId())) {
            throw new RuntimeException("Comercio não encontrada para o ID: " + comercio.getId());
        }

        try {
            return comercioRepository.save(comercio);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar comercio", e);
        }
    }

    public void deleteComercio(Long id) {
        comercioRepository.deleteById(id);
    }
}

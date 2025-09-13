package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.dto.AddOfertaDTO;
import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.models.Oferta;
import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.repositories.ComercioRepository;
import com.dd.descontodiretoapi.repositories.OfertaRepository;
import com.dd.descontodiretoapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private ComercioRepository comercioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

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

    public Oferta addOferta(AddOfertaDTO ofertaDTO) {
        // Busca Comercio e Produto pelos IDs
        Comercio comercio = comercioRepository.findById(ofertaDTO.getComercioId())
                .orElseThrow(() -> new RuntimeException("Comercio não encontrado"));
        Produto produto = produtoRepository.findById(ofertaDTO.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Cria nova Oferta
        Oferta oferta = new Oferta();
        oferta.setComercio(comercio);
        oferta.setProduto(produto);
        oferta.setPreco(ofertaDTO.getPreco());
        oferta.setValidade(ofertaDTO.getValidade());
        oferta.setDataPostagem(LocalDateTime.now());

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

    public List<Oferta> findTopOfertas() {
        return ofertaRepository.findTop10ByOrderByLikesDesc();
    }

    public List<Oferta> findOfertasRecentes() {
        return ofertaRepository.findTop10ByOrderByDataPostagemDesc();
    }


}

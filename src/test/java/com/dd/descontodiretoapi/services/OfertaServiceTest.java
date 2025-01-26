package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.dto.AddOfertaDTO;
import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.models.Oferta;
import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.repositories.ComercioRepository;
import com.dd.descontodiretoapi.repositories.OfertaRepository;
import com.dd.descontodiretoapi.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OfertaServiceTest {

    @InjectMocks
    private OfertaService ofertaService;

    @Mock
    private OfertaRepository ofertaRepository;

    @Mock
    private ComercioRepository comercioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    private Oferta oferta;
    private Comercio comercio;
    private Produto produto;
    private AddOfertaDTO ofertaDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        comercio = new Comercio();
        comercio.setId(1L);
        comercio.setNome("Comercio Teste");

        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");

        oferta = new Oferta();
        oferta.setId(1L);
        oferta.setComercio(comercio);
        oferta.setProduto(produto);
        oferta.setPreco(10.0f);
        oferta.setValidade(new Date());

        ofertaDTO = new AddOfertaDTO();
        ofertaDTO.setComercioId(1L);
        ofertaDTO.setProdutoId(1L);
        ofertaDTO.setPreco(10.0f);
        ofertaDTO.setValidade(new Date());
    }

    @Test
    public void testFindAll_Success() {
        when(ofertaRepository.findAll()).thenReturn(Arrays.asList(oferta));

        var result = ofertaService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(ofertaRepository, times(1)).findAll();
    }

    @Test
    public void testFindAll_Empty() {
        when(ofertaRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ofertaService.findAll();
        });

        assertEquals("Nenhum oferta encontrado.", exception.getMessage());
    }

    @Test
    public void testFindById_Success() {
        when(ofertaRepository.findById(1L)).thenReturn(Optional.of(oferta));

        var result = ofertaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        when(ofertaRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ofertaService.findById(1L);
        });

        assertEquals("Não existe oferta com o ID: 1", exception.getMessage());
    }

    @Test
    public void testAddOferta_Success() {
        when(comercioRepository.findById(1L)).thenReturn(Optional.of(comercio));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(ofertaRepository.save(any(Oferta.class))).thenReturn(oferta);

        var result = ofertaService.addOferta(ofertaDTO);

        assertNotNull(result);
        assertEquals(oferta.getId(), result.getId());
        verify(ofertaRepository, times(1)).save(any(Oferta.class));
    }

    @Test
    public void testAddOferta_ComercioNotFound() {
        when(comercioRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ofertaService.addOferta(ofertaDTO);
        });

        assertEquals("Comercio não encontrado", exception.getMessage());
    }

    @Test
    public void testUpdateOferta_Success() {
        when(ofertaRepository.existsById(1L)).thenReturn(true);
        when(ofertaRepository.save(any(Oferta.class))).thenReturn(oferta);

        var result = ofertaService.updateOferta(oferta);

        assertNotNull(result);
        assertEquals(oferta.getId(), result.getId());
        verify(ofertaRepository, times(1)).save(any(Oferta.class));
    }

    @Test
    public void testUpdateOferta_NotFound() {
        when(ofertaRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ofertaService.updateOferta(oferta);
        });

        assertEquals("Oferta não encontrada para o ID: 1", exception.getMessage());
    }

    @Test
    public void testDeleteOferta_Success() {
        doNothing().when(ofertaRepository).deleteById(1L);

        ofertaService.deleteOferta(1L);

        verify(ofertaRepository, times(1)).deleteById(1L);
    }
} 
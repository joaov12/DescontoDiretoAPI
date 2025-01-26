package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Comercio;
import com.dd.descontodiretoapi.repositories.ComercioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ComercioServiceTest {

    @InjectMocks
    private ComercioService comercioService;

    @Mock
    private ComercioRepository comercioRepository;

    private Comercio comercio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        comercio = new Comercio();
        comercio.setId(1L);
        comercio.setNome("Comercio Teste");
        comercio.setEndereco("Endereço Teste");
        comercio.setTelefone("11999999999");
    }

    @Test
    public void testFindAll_Success() {
        when(comercioRepository.findAll()).thenReturn(Arrays.asList(comercio));

        var result = comercioService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(comercioRepository, times(1)).findAll();
    }

    @Test
    public void testFindAll_Empty() {
        when(comercioRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            comercioService.findAll();
        });

        assertEquals("Nenhum comercio encontrado.", exception.getMessage());
    }

    @Test
    public void testFindById_Success() {
        when(comercioRepository.findById(1L)).thenReturn(Optional.of(comercio));

        var result = comercioService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Comercio Teste", result.getNome());
    }

    @Test
    public void testFindById_NotFound() {
        when(comercioRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            comercioService.findById(1L);
        });

        assertEquals("Não existe comercio com o ID: 1", exception.getMessage());
    }

    @Test
    public void testAddComercio_Success() {
        when(comercioRepository.save(any(Comercio.class))).thenReturn(comercio);

        var result = comercioService.addComercio(comercio);

        assertNotNull(result);
        assertEquals(comercio.getId(), result.getId());
        assertEquals(comercio.getNome(), result.getNome());
        verify(comercioRepository, times(1)).save(any(Comercio.class));
    }

    @Test
    public void testAddComercio_Error() {
        when(comercioRepository.save(any(Comercio.class))).thenThrow(new RuntimeException("Erro DB"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            comercioService.addComercio(comercio);
        });

        assertEquals("Erro ao adicionar comercio", exception.getMessage());
    }

    @Test
    public void testUpdateComercio_Success() {
        when(comercioRepository.existsById(1L)).thenReturn(true);
        when(comercioRepository.save(any(Comercio.class))).thenReturn(comercio);

        var result = comercioService.updateComercio(comercio);

        assertNotNull(result);
        assertEquals(comercio.getId(), result.getId());
        assertEquals(comercio.getNome(), result.getNome());
        verify(comercioRepository, times(1)).save(any(Comercio.class));
    }

    @Test
    public void testUpdateComercio_NotFound() {
        when(comercioRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            comercioService.updateComercio(comercio);
        });

        assertEquals("Comercio não encontrada para o ID: 1", exception.getMessage());
    }

    @Test
    public void testDeleteComercio_Success() {
        doNothing().when(comercioRepository).deleteById(1L);

        comercioService.deleteComercio(1L);

        verify(comercioRepository, times(1)).deleteById(1L);
    }
} 
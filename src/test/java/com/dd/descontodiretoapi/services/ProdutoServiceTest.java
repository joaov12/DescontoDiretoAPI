package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Produto;
import com.dd.descontodiretoapi.repositories.ProdutoRepository;
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

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    private Produto produto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
    }

    @Test
    public void testFindAll_Success() {
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto));
        
        var result = produtoService.findAll();
        
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    public void testFindAll_Empty() {
        when(produtoRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoService.findAll();
        });

        assertEquals("Nenhum produto encontrado.", exception.getMessage());
    }

    @Test
    public void testFindById_Success() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        var result = produtoService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Produto Teste", result.getNome());
    }

    @Test
    public void testFindById_NotFound() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoService.findById(1L);
        });

        assertEquals("Não existe produto com o ID: 1", exception.getMessage());
    }

    @Test
    public void testAddProduto_Success() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        var result = produtoService.addProduto(produto);

        assertNotNull(result);
        assertEquals(produto.getId(), result.getId());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    public void testAddProduto_Error() {
        when(produtoRepository.save(any(Produto.class))).thenThrow(new RuntimeException("Erro DB"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoService.addProduto(produto);
        });

        assertEquals("Erro ao adicionar produto", exception.getMessage());
    }

    @Test
    public void testUpdateProduto_Success() {
        when(produtoRepository.existsById(1L)).thenReturn(true);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        var result = produtoService.updateProduto(produto);

        assertNotNull(result);
        assertEquals(produto.getId(), result.getId());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    public void testUpdateProduto_NotFound() {
        when(produtoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoService.updateProduto(produto);
        });

        assertEquals("Produto não encontrada para o ID: 1", exception.getMessage());
    }

    @Test
    public void testDeleteProduto_Success() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deleteProduto(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
    }
} 
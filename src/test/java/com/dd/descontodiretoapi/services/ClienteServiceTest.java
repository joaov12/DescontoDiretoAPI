package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Cliente;
import com.dd.descontodiretoapi.models.Oferta;
import com.dd.descontodiretoapi.repositories.ClienteRepository;
import com.dd.descontodiretoapi.repositories.OfertaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private OfertaRepository ofertaRepository;

    private Cliente cliente;
    private Oferta oferta;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("teste@email.com");
        cliente.setOfertasPreferidas(new ArrayList<>());

        oferta = new Oferta();
        oferta.setId(1L);
        oferta.setPreco(10.0f);
    }

    @Test
    public void testFindAll_Success() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));

        var result = clienteService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testFindAll_Empty() {
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.findAll();
        });

        assertEquals("Nenhum cliente encontrado.", exception.getMessage());
    }

    @Test
    public void testFindById_Success() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        var result = clienteService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Cliente Teste", result.getNome());
    }

    @Test
    public void testFindById_NotFound() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.findById(1L);
        });

        assertEquals("Não existe cliente com o ID: 1", exception.getMessage());
    }

    @Test
    public void testAddCliente_Success() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        var result = clienteService.addCliente(cliente);

        assertNotNull(result);
        assertEquals(cliente.getId(), result.getId());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testAddCliente_Error() {
        when(clienteRepository.save(any(Cliente.class))).thenThrow(new RuntimeException("Erro DB"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.addCliente(cliente);
        });

        assertEquals("Erro ao adicionar cliente", exception.getMessage());
    }

    @Test
    public void testUpdateCliente_Success() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        var result = clienteService.updateCliente(cliente);

        assertNotNull(result);
        assertEquals(cliente.getId(), result.getId());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testUpdateCliente_NotFound() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.updateCliente(cliente);
        });

        assertEquals("Cliente não encontrada para o ID: 1", exception.getMessage());
    }

    @Test
    public void testDeleteCliente_Success() {
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deleteCliente(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAddOfertaToFavoritos_Success() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(ofertaRepository.findById(1L)).thenReturn(Optional.of(oferta));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        var result = clienteService.addOfertaToFavoritos(1L, 1L);

        assertNotNull(result);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testAddOfertaToFavoritos_OfertaJaExiste() {
        cliente.getOfertasPreferidas().add(oferta);
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(ofertaRepository.findById(1L)).thenReturn(Optional.of(oferta));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.addOfertaToFavoritos(1L, 1L);
        });

        assertEquals("A oferta já está nos favoritos", exception.getMessage());
    }

    @Test
    public void testRemoveOfertaFromFavoritos_Success() {
        cliente.getOfertasPreferidas().add(oferta);
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(ofertaRepository.findById(1L)).thenReturn(Optional.of(oferta));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        var result = clienteService.removeOfertaFromFavoritos(1L, 1L);

        assertNotNull(result);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testRemoveOfertaFromFavoritos_OfertaNaoExiste() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(ofertaRepository.findById(1L)).thenReturn(Optional.of(oferta));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.removeOfertaFromFavoritos(1L, 1L);
        });

        assertEquals("A oferta não está nos favoritos", exception.getMessage());
    }
} 
package com.dd.descontodiretoapi.services;

import com.dd.descontodiretoapi.models.Cliente;
import com.dd.descontodiretoapi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new RuntimeException("Nenhum cliente encontrado.");
        }
        return clientes;
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe cliente com o ID: " + id));
    }

    public Cliente addCliente(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar cliente", e);
        }
    }

    public Cliente updateCliente(Cliente cliente) {
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new RuntimeException("Cliente não encontrada para o ID: " + cliente.getId());
        }

        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
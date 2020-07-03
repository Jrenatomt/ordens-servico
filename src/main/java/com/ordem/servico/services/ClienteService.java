package com.ordem.servico.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ordem.servico.domain.Cliente;
import com.ordem.servico.repositories.ClienteRepository;
import com.ordem.servico.services.exceptions.DatabaseException;
import com.ordem.servico.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new DatabaseException("Já existe um cliente cadastrado com este e-mail.");
		}
		return clienteRepository.save(cliente);
	}

	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar um cliente com ordem de serviço.");
		}
	}

	public Cliente update(Long id, Cliente cliente) {
	    try {
			Cliente clienteUpdate = clienteRepository.getOne(id);
			updateData(clienteUpdate, cliente);
			return clienteRepository.save(clienteUpdate);
	    }catch(LazyInitializationException e) {
	    	throw new ResourceNotFoundException(id);
	    }
	}

	private void updateData(Cliente clienteUpdate, Cliente cliente) {
		clienteUpdate.setNome(cliente.getNome());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefone(cliente.getTelefone());
	}
}

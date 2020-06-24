package com.ordem.servico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordem.servico.domain.Cliente;
import com.ordem.servico.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id){
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.get();
	}
	
	public Cliente insert(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente update(Long id, Cliente cliente) {
			Cliente clienteUpdate = clienteRepository.getOne(id);
			updateData(clienteUpdate, cliente);
			return clienteRepository.save(clienteUpdate);
	
	}

	private void updateData(Cliente clienteUpdate, Cliente cliente) {
		clienteUpdate.setNome(cliente.getNome());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefone(cliente.getTelefone());
	}
}



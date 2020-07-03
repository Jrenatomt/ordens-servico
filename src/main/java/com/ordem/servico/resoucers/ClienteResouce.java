package com.ordem.servico.resoucers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ordem.servico.domain.Cliente;
import com.ordem.servico.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResouce {
	
	@Autowired
	private ClienteService service;
	
	//listar clientes
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> listCliente = service.findAll();
		return ResponseEntity.ok().body(listCliente);
	}
	
	//buscar cliente
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	//criar cliente
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody @Valid Cliente cliente){
		cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	//deletar cliente
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//atualizar cliente
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id,@RequestBody @Valid Cliente cliente){
		cliente = service.update(id, cliente);
		return ResponseEntity.ok().body(cliente);
	}	
}

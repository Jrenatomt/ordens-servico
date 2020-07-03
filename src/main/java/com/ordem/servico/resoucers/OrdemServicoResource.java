package com.ordem.servico.resoucers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ordem.servico.domain.OrdemServico;
import com.ordem.servico.services.OrdemServicoService;

@RestController
@RequestMapping(value = "/ordens-servico")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService ordemServicoService;

	@Transactional
	@GetMapping
	public ResponseEntity<List<OrdemServico>> findAll() {
		List<OrdemServico> list = ordemServicoService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@Transactional
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServico> findById(@PathVariable Long id) {
		OrdemServico obj = ordemServicoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico insert(@Valid @RequestBody OrdemServico ordemServico) {
		return ordemServicoService.insert(ordemServico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		OrdemServico ordemServico = ordemServicoService.findById(id);
		ordemServicoService.delete(ordemServico);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServico> update(@Valid @PathVariable Long id, @RequestBody OrdemServico ordemServico) {
		ordemServico = ordemServicoService.update(id, ordemServico);
		return ResponseEntity.ok().body(ordemServico);
	}

	@PutMapping(value = "/{id}/finalizar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long id) {
		ordemServicoService.finalizar(id);
	}
}

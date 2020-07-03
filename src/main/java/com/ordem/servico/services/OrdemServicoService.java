package com.ordem.servico.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordem.servico.domain.Cliente;
import com.ordem.servico.domain.OrdemServico;
import com.ordem.servico.domain.enums.StatusOrdemServico;
import com.ordem.servico.repositories.ClienteRepository;
import com.ordem.servico.repositories.OrdemServicoRepository;
import com.ordem.servico.services.exceptions.RegraNegocioException;
import com.ordem.servico.services.exceptions.ResourceNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	@Transactional
	public OrdemServico findById(Long id) {
		return ordemServicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Transactional
	public List<OrdemServico> findAll() {
		return ordemServicoRepository.findAll();
	}

	@Transactional
	public OrdemServico insert(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDate.now());
		return ordemServicoRepository.save(ordemServico);
	}

	@Transactional
	public void delete(OrdemServico ordemServico) {
		try {
		    ordemServicoRepository.delete(ordemServico);
		}catch(RegraNegocioException e) {
			throw new ResourceNotFoundException("não foi possivel deletar.");
		}
	}
 
	@Transactional
	public OrdemServico update(Long id, OrdemServico ordemServico) {
		try {
			OrdemServico ordemUpdate = ordemServicoRepository.getOne(id);
			updateData(ordemUpdate, ordemServico);
			return ordemServicoRepository.save(ordemUpdate);
		}catch(RegraNegocioException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(OrdemServico ordemUpdate, OrdemServico ordemServico) {
		ordemUpdate.setDescricao(ordemServico.getDescricao());
		ordemUpdate.setPreco(ordemServico.getPreco());
		ordemUpdate.setStatus(ordemServico.getStatus());	
	}
	
	public void finalizar(Long id) {
		OrdemServico ordemServico = findById(id);
		ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
		ordemServico.setDataFinalizacao(LocalDate.now());
		ordemServicoRepository.save(ordemServico);
	}
}

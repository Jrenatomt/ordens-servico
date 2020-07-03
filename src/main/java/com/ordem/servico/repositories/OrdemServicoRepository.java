package com.ordem.servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordem.servico.domain.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	

}

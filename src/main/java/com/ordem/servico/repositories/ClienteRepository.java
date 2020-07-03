package com.ordem.servico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ordem.servico.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByEmail(String email);

}

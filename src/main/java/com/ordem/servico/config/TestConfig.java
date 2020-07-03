package com.ordem.servico.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ordem.servico.domain.Cliente;
import com.ordem.servico.domain.OrdemServico;
import com.ordem.servico.domain.enums.StatusOrdemServico;
import com.ordem.servico.repositories.ClienteRepository;
import com.ordem.servico.repositories.OrdemServicoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli1 = new Cliente(null, "Renato", "Renato@email.com", "99999");
		Cliente cli2 = new Cliente(null, "Ryan", "ryan@email.com", "9999888");
		Cliente cli3 = new Cliente(null, "biel", "biel@email.com", "99969888");
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3));
		
		OrdemServico ordem1 = new OrdemServico(null, "reparo", new BigDecimal("100"), LocalDate.now(), LocalDate.of(2020, Month.JUNE, 28), cli1, StatusOrdemServico.ABERTA);
		OrdemServico ordem2 = new OrdemServico(null, "concerto", new BigDecimal("200"), LocalDate.now(), LocalDate.of(2020, Month.JUNE, 28), cli2, StatusOrdemServico.ABERTA);
		
		ordemServicoRepository.saveAll(Arrays.asList(ordem1,ordem2));
		
	
	
	}
}

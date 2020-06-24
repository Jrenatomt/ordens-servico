package com.ordem.servico.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ordem.servico.domain.Cliente;
import com.ordem.servico.repositories.ClienteRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		Cliente cli1 = new Cliente(null, "Renato", "Renato@email.com", "99999");
		Cliente cli2 = new Cliente(null, "Ryan", "ryan@email.com", "9999888");
		Cliente cli3 = new Cliente(null, "biel", "biel@email.com", "99969888");
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3));
	}

}

package com.ivogoncalves.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ivogoncalves.helpdesk.domain.Chamado;
import com.ivogoncalves.helpdesk.domain.Cliente;
import com.ivogoncalves.helpdesk.domain.Tecnico;
import com.ivogoncalves.helpdesk.domain.enums.Perfil;
import com.ivogoncalves.helpdesk.domain.enums.Prioridade;
import com.ivogoncalves.helpdesk.domain.enums.Status;
import com.ivogoncalves.helpdesk.repositories.ChamadoRepository;
import com.ivogoncalves.helpdesk.repositories.ClienteRepository;
import com.ivogoncalves.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Ivo José", "32899893807", "ivojose@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "79696990812", "torvalds@mail.com", "123");
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro chamando", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
}

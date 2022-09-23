package com.ivogoncalves.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivogoncalves.helpdesk.domain.Chamado;
import com.ivogoncalves.helpdesk.repositories.ChamadoRepository;
import com.ivogoncalves.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado findByID(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id: " + id));
	}
	
	public List<Chamado> findByAll(){
		return chamadoRepository.findAll();
	}
}

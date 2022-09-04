package com.ivogoncalves.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivogoncalves.helpdesk.domain.Tecnico;
import com.ivogoncalves.helpdesk.domain.dtos.TecnicoDTO;
import com.ivogoncalves.helpdesk.repositories.TecnicoRepository;
import com.ivogoncalves.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + 10));
	}
	
	public List<Tecnico> findAll(){
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj); 
	}	
}

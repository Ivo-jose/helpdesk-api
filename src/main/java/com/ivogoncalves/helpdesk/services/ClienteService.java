package com.ivogoncalves.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivogoncalves.helpdesk.domain.Cliente;
import com.ivogoncalves.helpdesk.domain.Pessoa;
import com.ivogoncalves.helpdesk.domain.dtos.ClienteDTO;
import com.ivogoncalves.helpdesk.repositories.ClienteRepository;
import com.ivogoncalves.helpdesk.repositories.PessoaRepository;
import com.ivogoncalves.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.ivogoncalves.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder enconder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(@Valid ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(enconder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return clienteRepository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
		  objDTO.setSenha(enconder.encode(objDTO.getSenha())); 	
		}
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return clienteRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado !");
		}
		clienteRepository.delete(obj);
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF, já cadastrado no sistema! ");
		}
		// Validando o Email
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-MAIL, já cadastrado no sistema! ");
		}
	}
}

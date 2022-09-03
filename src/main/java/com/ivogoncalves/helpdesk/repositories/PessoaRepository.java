package com.ivogoncalves.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivogoncalves.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
}

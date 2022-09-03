package com.ivogoncalves.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivogoncalves.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
}

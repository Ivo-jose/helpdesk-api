package com.ivogoncalves.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivogoncalves.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
}

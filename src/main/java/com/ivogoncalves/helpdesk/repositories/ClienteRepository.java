package com.ivogoncalves.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivogoncalves.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}

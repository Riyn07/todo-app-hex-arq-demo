package com.example.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioTareaSpringData extends JpaRepository<EntidadTareaJpa, Long> {

}
package com.victor.br.apiRestful.repository;

import com.victor.br.apiRestful.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findByUsername(String username);
}

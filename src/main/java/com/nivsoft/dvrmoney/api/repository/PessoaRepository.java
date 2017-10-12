package com.nivsoft.dvrmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivsoft.dvrmoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

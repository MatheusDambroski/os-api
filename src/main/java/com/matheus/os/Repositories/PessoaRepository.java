package com.matheus.os.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.os.domain.Pessoa;
import com.matheus.os.domain.Tecnico;

@Repository
public interface PessoaRepository extends JpaRepository<Tecnico, Integer> {

	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);

}

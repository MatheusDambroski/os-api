package com.matheus.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.os.Repositories.PessoaRepository;
import com.matheus.os.Repositories.TecnicoRepository;
import com.matheus.os.domain.Pessoa;
import com.matheus.os.domain.Tecnico;
import com.matheus.os.dtos.TecnicoDTO;
import com.matheus.os.services.exception.DataIntegratyViolationException;
import com.matheus.os.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	/*
	 * Busca tecnico pelo ID
	 */
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!" + id + ", Tipo: " + Tecnico.class.getName()));
	}
	/*
	 * Lista todos tecnicos e clientes
	 */
	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	/*
	 * Confere se existe já o CPF e cria
	 */
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	/*
	 * Atualiza dados do Tecnico
	 */
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}
	/*
	 * Deleta um tecnico pelo ID
	 */
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui Ordens de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}
	/*
	 * Busca Tecnico pelo CPF
	 */
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoarepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}


}

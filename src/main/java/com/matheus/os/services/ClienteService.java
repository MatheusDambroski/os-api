package com.matheus.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.os.Repositories.ClienteRepository;
import com.matheus.os.Repositories.PessoaRepository;
import com.matheus.os.domain.Cliente;
import com.matheus.os.domain.Pessoa;
import com.matheus.os.dtos.ClienteDTO;
import com.matheus.os.services.exception.DataIntegratyViolationException;
import com.matheus.os.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoarepository;

	/*
	 * Busca tecnico pelo ID
	 */
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!" + id + ", Tipo: " + Cliente.class.getName()));
	}

	/*
	 * Lista todos tecnicos e clientes
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/*
	 * Confere se existe já o CPF e cria
	 */
	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * Atualiza dados do Cliente
	 */
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

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
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);

	}

	/*
	 * Busca Cliente pelo CPF
	 */
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoarepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}

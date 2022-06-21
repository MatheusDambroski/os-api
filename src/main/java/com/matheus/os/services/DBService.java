package com.matheus.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.os.Repositories.ClienteRepository;
import com.matheus.os.Repositories.OSRepository;
import com.matheus.os.Repositories.TecnicoRepository;
import com.matheus.os.domain.Cliente;
import com.matheus.os.domain.OS;
import com.matheus.os.domain.Tecnico;
import com.matheus.os.domain.enuns.Prioridade;
import com.matheus.os.domain.enuns.Status;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Matheus Dambroski", "034.077.280-85", "(51) 98052-0025");
		Tecnico t2 = new Tecnico(null, "Gustavo Dambroski", "693.952.490-80", "(51) 99543-2011");
		Cliente c1 = new Cliente(null, "Gabriel Dambroski", "034.077.310-35", "(51) 99604-2388");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste MACbook", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}

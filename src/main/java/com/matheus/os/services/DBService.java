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
		Tecnico t3 = new Tecnico(null, "Dulcinea Corrêa Furtunato", "652.278.302-90", "(69) 96782-0533");
		Tecnico t4 = new Tecnico(null, "Roberto Goulart Frotté", "570.826.639-27", "(47) 96769-4213");
		
		Cliente c1 = new Cliente(null, "Gabriel Dambroski", "034.077.310-35", "(51) 99604-2388");
		Cliente c2 = new Cliente(null, "Mirian Elias Duarte", "747.755.244-37", "(84) 96786-5864");
		Cliente c3 = new Cliente(null, "Cristiane Cunha Albuquerque", "388.484.171-83", "(65) 97309-5858");
		Cliente c4 = new Cliente(null, "Jhonata Lopes Salomão", "817.133.009-66", "(48) 98184-8515");
		Cliente c5 = new Cliente(null, "Luciane Montilla Bastida", "195.686.382-61", "(95) 98933-4632");
		
		OS os1 = new OS(null, Prioridade.ALTA, "Teste MACbook", Status.ANDAMENTO, t1, c1);
		OS os2 = new OS(null, Prioridade.BAIXA, "Trocar fonte computador, 5846", Status.ANDAMENTO, t2, c3);
		OS os3 = new OS(null, Prioridade.BAIXA, "Adicionar mais um pente de memória no notebook, 5835", Status.ANDAMENTO, t2, c4);
		OS os4 = new OS(null, Prioridade.MEDIA, "Verificar porque computador não liga, 5840", Status.ABERTO, t2, c4);
		OS os5 = new OS(null, Prioridade.ALTA, "Instalar PacoteOffice no computador, 5838", Status.ANDAMENTO, t4, c5);
		
		
		t1.getList().add(os1);
		t2.getList().add(os2);
		t2.getList().add(os4);
		t4.getList().add(os5);
		c1.getList().add(os1);
		c3.getList().add(os2);
		c4.getList().add(os4);
		c5.getList().add(os5);
		

		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		osRepository.saveAll(Arrays.asList(os1, os2, os3, os4, os5));
	}

}

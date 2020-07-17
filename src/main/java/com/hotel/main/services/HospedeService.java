package com.hotel.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.main.models.Hospede;
import com.hotel.main.repository.HospedeRepository;

@Service
public class HospedeService {
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	public List<Hospede> buscarTodos() {
		return hospedeRepository.findAll();
	}
	
	public List<Hospede> buscarTodos(String nome, String nroDoc, String nroFone, Boolean presenteAgora) {
		return hospedeRepository.buscarTodos(nome, nroDoc, nroFone, presenteAgora);
	}
	
	public Hospede buscarPorId(Long idHospede) {
		return hospedeRepository.findById(idHospede).orElse(null);
	}
	
	public void salvar(Hospede hospede) {
		hospedeRepository.save(hospede);
	}
	
	public void excluir(Long idHospede) {
		hospedeRepository.deleteById(idHospede);
	}
	
//	public List<Hospede> buscarHospedes(String sentencaBuscada) {
//		return hospedeRepository.findHospedeByNomeOrNroDocumentoOrNroTelefone(sentencaBuscada);
//	}

}

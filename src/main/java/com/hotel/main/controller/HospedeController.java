package com.hotel.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.main.models.Hospede;
import com.hotel.main.services.CheckInService;
import com.hotel.main.services.HospedeService;

@RestController
public class HospedeController {

	@Autowired
	private HospedeService hospedeService;
	
	@Autowired
	private CheckInService checkinService;

	@GetMapping(value = "/hospedes")
	public List<Hospede> buscaTodos(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String nroDoc, @RequestParam(required = false) String nroFone,
			@RequestParam(required = false, defaultValue = "false") Boolean presenteAgora) {
		List<Hospede> hospedes = hospedeService.buscarTodos(nome, nroDoc, nroFone, presenteAgora);
		for (Hospede hospede : hospedes) {
			hospede.setTotalCheckin(checkinService.buscarTotalCheckin(hospede.getIdHospede()));
			hospede.setUltimoCheckin(checkinService.buscarUltimoCheckin(hospede.getIdHospede()));
		}
		return hospedes;
	}

	@PostMapping(value = "/hospedes")
	public void salvar(@RequestBody Hospede hospede) {
		System.out.println(hospede);
		hospedeService.salvar(hospede);
	}

	@DeleteMapping(value = "/hospedes/{idHospede}")
	public void excluir(@PathVariable(value = "idHospede") Long idHospede) {
		System.out.println(idHospede);
		hospedeService.excluir(idHospede);
	}

	

}

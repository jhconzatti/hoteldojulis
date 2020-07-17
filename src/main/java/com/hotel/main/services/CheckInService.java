package com.hotel.main.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.main.models.CheckIn;
import com.hotel.main.repository.CheckInRepository;

@Service
public class CheckInService {
	
	@Autowired
	private CheckInRepository checkinRepository;

	public CheckIn salvar(CheckIn novoCheckIn) {
		return checkinRepository.save(novoCheckIn);
	}
	
	public List<CheckIn> buscarTodos() {
		return checkinRepository.findAll();
	}
	
	public CheckIn buscarPorId(Long idCheckin) {
		return checkinRepository.findById(idCheckin).orElse(null);
	}

	public BigDecimal buscarTotalCheckin(Long idHospede) {
		return checkinRepository.buscarValorTotal(idHospede);
	}

	public LocalDateTime buscarUltimoCheckin(Long idHospede) {
		return  checkinRepository.buscarUltimoCheckin(idHospede);
	}
	
	
}

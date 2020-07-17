package com.hotel.main.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.main.models.CheckIn;
import com.hotel.main.models.Hospede;
import com.hotel.main.services.CheckInService;
import com.hotel.main.services.HospedeService;

@RestController
public class CheckInController {

	@Autowired
	private HospedeService hospedeService;

	@Autowired
	private CheckInService checkinService;

	@GetMapping(value = "/checkin")
	public List<CheckIn> buscaTodos() {
		return checkinService.buscarTodos();
	}
	
	/*
	 * consultar hospedes que ja realizaram checkin mas nao estao mais no hotel
	 */
	
	/* 
	 * consultar quem ainda esta no hotel
	 */
	
	/*
	 * Todas consultas devem apresentar o valor total e o valor da ultima hospedagem
	 */

	@PostMapping(value = "/checkin/{idHospede}")
	public CheckIn checkIn(@PathVariable(value = "idHospede") Long idHospede, @RequestBody CheckIn checkIn) {

		Hospede hospede = hospedeService.buscarPorId(idHospede);
		CheckIn novoCheckIn = new CheckIn();
		novoCheckIn.setHospede(hospede);
		LocalDateTime dataEntrada = checkIn.getDataEntrada() == null ? LocalDateTime.now() : checkIn.getDataEntrada();
		novoCheckIn.setDataEntrada(dataEntrada);
		LocalDateTime dataSaida = checkIn.getDataSaida() == null ? novoCheckIn.getDataEntrada().plusHours(8)
				: checkIn.getDataSaida();
		novoCheckIn.setDataSaida(dataSaida);
		novoCheckIn.setAdicionalVeiculo(checkIn.getAdicionalVeiculo());

		BigDecimal valorHospedagem = BigDecimal.ZERO;
		Boolean adicionalVeiculo = novoCheckIn.getAdicionalVeiculo();
		LocalDateTime dataWhileIni = dataEntrada;
		LocalDateTime dataWhileFim = dataSaida;

		LocalDateTime horaLimite = LocalDateTime.of(dataSaida.getYear(), dataSaida.getMonth(),
				dataSaida.getDayOfMonth(), 16, 30);
		if (dataWhileFim.isAfter(horaLimite)) {
			dataWhileFim.plusDays(1);
		}

		// Hospedagem
		// Seg - Sex: 120$
		// Sab - Dom: 150$
		// Garagem:
		// Seg - Sex: 15$
		// Sab - Dom: 20$
		// Saída após 16h30 será cobrada mais uma diária
		while (dataWhileFim.isAfter(dataWhileIni)) {
			if (adicionalVeiculo == true) {
				System.out.println("Entrou Adicional Veiculo");
				switch (dataWhileIni.getDayOfWeek()) {
				case MONDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(135));
					break;
				case TUESDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(135));
					break;
				case WEDNESDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(135));
					break;
				case THURSDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(135));
					break;
				case FRIDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(135));
					break;
				case SATURDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(170));
					break;
				case SUNDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(170));
					break;
				default:
					break;
				}
			} else {
				switch (dataWhileIni.getDayOfWeek()) {
				case MONDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(120));
					break;
				case TUESDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(120));
					break;
				case WEDNESDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(120));
					break;
				case THURSDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(120));
					break;
				case FRIDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(120));
					break;
				case SATURDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(150));
					break;
				case SUNDAY:
					valorHospedagem = valorHospedagem.add(new BigDecimal(150));
					break;
				default:
					break;
				}
			}
			dataWhileIni = dataWhileIni.plusDays(1);
		}
		
		novoCheckIn.setValorCheckin(valorHospedagem);

		return checkinService.salvar(novoCheckIn);
	}

}

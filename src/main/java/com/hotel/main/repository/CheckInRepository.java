package com.hotel.main.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.main.models.CheckIn;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    
	@Query("select sum(c.valorCheckin) "
			+ "from CheckIn c "
			+ "where 1=1 "
			+ "and c.hospede.id = :idHospede")
	BigDecimal buscarValorTotal(@Param(value = "idHospede")Long idHospede);
	
	@Query("select max(c.dataSaida) "
			+ "from CheckIn c "
			+ "where 1=1 "
			+ "and c.hospede.id = :idHospede")
	LocalDateTime buscarUltimoCheckin(@Param(value = "idHospede")Long idHospede);
}

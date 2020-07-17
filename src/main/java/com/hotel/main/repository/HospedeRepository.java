package com.hotel.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.main.models.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

	@Query("select distinct h from Hospede h "
			+ "left join h.checkins c " 
			+ "where 1=1 "
			+ "and ((:nome is null) or (h.nomeHospede like %:nome%) "
			+ "and (:nroDoc is null or h.nroDocumento like %:nroDoc%) "
			+ "and (:nroFone is null or h.nroFone like %:nroFone%)"
			+ "and ((:presenteAgora is false and c = null) or (:presenteAgora is true and c != null and c.dataSaida > CURRENT_TIMESTAMP))) ")
	List<Hospede> buscarTodos(@Param("nome") String nome, @Param("nroDoc") String nroDoc, @Param("nroFone") String nroFone, @Param("presenteAgora") Boolean presenteAgora);

}

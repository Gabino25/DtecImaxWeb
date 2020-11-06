package com.dtecimax.jpa.jdbc.as;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.dtecimax.jpa.dao.as.CitasDao;
import com.dtecimax.jpa.dto.as.CitasDto;

@Stateless 
public class CitasDaoImpl implements CitasDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public void insertCitas(CitasDto pCitasDto) {
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.CITAS_S");
		BigInteger lCitasS = (BigInteger)q.getSingleResult();
		pCitasDto.setNumeroCita(lCitasS.longValue());
        em.persist(pCitasDto);
	}
	
	
	

	@Override
	public List<CitasDto> findSelectItems() {
		return em.createNamedQuery("CitasDto.findAll").getResultList();
	}

	@Override
	public List<CitasDto> findAll() {
		return em.createNamedQuery("CitasDto.findAll").getResultList();
	}

	@Override
	public CitasDto findByNumeroCita(long pNumeroCita) {
		return em.find(CitasDto.class, pNumeroCita);
	}

	@Override
	public List<CitasDto> findAllByNumDoc(long pNumeroDoctor
			                             ,String pSearchTipoEstu
			                             ) {
		String strQuery = "SELECT c FROM CitasDto c where 1=1";
		if(0!=pNumeroDoctor) {
			strQuery = strQuery+" AND c.numeroDoctor = "+pNumeroDoctor;
		}
		if(null!=pSearchTipoEstu&&!"".equals(pSearchTipoEstu)) {
			strQuery = strQuery+" AND c.numeroEstudio in (SELECT e.numeroEstudio FROM EstudiosDto e where e.tipoEstudio = '"+pSearchTipoEstu+"')";
		}
		Query query = em.createQuery(strQuery);
		List<CitasDto> results = query.getResultList();
		return results;
	}

	@Override
	public void actualizaCitas(CitasDto pCitasDto, long pNumeroCita) {
		CitasDto citasDto = em.find(CitasDto.class, pNumeroCita);
		System.out.println("DaoImpl NumeroPaciente:"+citasDto.getNumeroPaciente());
		citasDto.setNumeroPaciente(pCitasDto.getNumeroPaciente());
		citasDto.setNumeroEstudio(pCitasDto.getNumeroEstudio());
		citasDto.setNumeroDoctor(pCitasDto.getNumeroDoctor());
		citasDto.setFechaCita(pCitasDto.getFechaCita());
		citasDto.setHoraInicialCita(pCitasDto.getHoraInicialCita());
		citasDto.setHoraFinalCita(pCitasDto.getHoraFinalCita());
		citasDto.setNumeroDoctorReferente(pCitasDto.getNumeroDoctorReferente());
		citasDto.setComentariosC(pCitasDto.getComentariosC());
	}

	@Override
	public void deleteCita(long pNumeroCita) {
		CitasDto citasDto = em.find(CitasDto.class, pNumeroCita); 
		em.remove(citasDto);
	}




	@Override
	public int validaDuplicados(long pNumeroDoctor
			                  , Timestamp pFechaCita
			                  , Time pHoraInicialCita
			                  , Time pHoraFinalCita) {
		/**
		String strQuery = "SELECT COUNT(1)\r\n" + 
				"  FROM CitasDto c \r\n" + 
				" WHERE c.numeroDoctor ="+pNumeroDoctor+
				"   AND c.fechaCita  = :pFechaCita"+
			    "   AND ( (:pHoraInicialCita BETWEEN c.horaInicialCita AND c.horaFinalCita) OR (:pHoraFinalCita BETWEEN c.horaInicialCita AND c.horaFinalCita) )"; 
		**/
   	    System.out.println(pFechaCita);
	 	System.out.println(pHoraInicialCita);
		System.out.println(pHoraFinalCita);
		String strpFechaCita = pFechaCita.toString(); 
		strpFechaCita = strpFechaCita.substring(0, 10); 
		System.out.println("strpFechaCita:"+strpFechaCita);
		String strQuery = "SELECT COUNT(1)\r" + 
				"  FROM [dbo].[CITAS]\r" + 
				" WHERE NUMERO_DOCTOR = "+pNumeroDoctor+"\r" + 
				" AND FECHA_CITA = :pFechaCita \r" + 
				"  AND (('"+pHoraInicialCita+"' BETWEEN HORA_INICIAL_CITA AND HORA_FINAL_CITA)\r" + 
				"    OR ('"+pHoraFinalCita+"' BETWEEN HORA_INICIAL_CITA AND HORA_FINAL_CITA)\r" + 
				"	  )"; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFechaCita", pFechaCita, TemporalType.TIMESTAMP);
		Integer integer = (Integer)query.getSingleResult(); 
		
		return integer.intValue();
	}

}

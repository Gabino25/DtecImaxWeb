package com.dtecimax.jpa.jdbc.hr;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtecimax.jpa.dao.hr.DoctoresReferentesDao;
import com.dtecimax.jpa.dto.hr.DoctoresReferentesDto;

@Stateless 
public class DoctoresReferentesDaoImpl implements DoctoresReferentesDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	
	  @Override public void insertDoctoresReferentes(DoctoresReferentesDto pDoctoresReferentesDto) 
	  { 
	  Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.DOCTORES_REFERENTES_S");
	  BigInteger lDoctoresReferentesS = (BigInteger)q.getSingleResult();
	  pDoctoresReferentesDto.setNumeroDoctorReferente(lDoctoresReferentesS.longValue());
	  em.persist(pDoctoresReferentesDto); }
	 
	
	  @Override 
	  public List<DoctoresReferentesDto> findSelectItems() { 
		  return em.createNamedQuery("DoctoresReferentesDto.findAll").getResultList(); }
	  
	  @Override 
	  public List<DoctoresReferentesDto> findAll() { 
		  return em.createNamedQuery("DoctoresReferentesDto.findAll").getResultList(); }
	  
	  @Override 
	  public void deleteDoctoresReferentes(long pNumeroDoctorReferente) { 
		  DoctoresReferentesDto doctoresReferentesDto = em.find(DoctoresReferentesDto.class, pNumeroDoctorReferente); 
		  em.remove(doctoresReferentesDto);
		  }
	  
	  
	  @Override public DoctoresReferentesDto findByNumeroDoctorReferente(long pNumeroDoctorReferente) { 
		  return em.find(DoctoresReferentesDto.class, pNumeroDoctorReferente); }


	@Override
	public void updateDoctoresReferentes(long pNumeroDoctorReferente, DoctoresReferentesDto pDoctoresReferentesDto) {
		DoctoresReferentesDto doctoresReferentesDto = em.find(DoctoresReferentesDto.class, pNumeroDoctorReferente); 
		 doctoresReferentesDto.setNombreDoctorReferente(pDoctoresReferentesDto.getNombreDoctorReferente());
		 doctoresReferentesDto.setApellidoMaternoDoctorReferente(pDoctoresReferentesDto.getApellidoMaternoDoctorReferente());
		 doctoresReferentesDto.setApellidoPaternoDoctorReferente(pDoctoresReferentesDto.getApellidoPaternoDoctorReferente());
		 doctoresReferentesDto.setFechaNacimientoDoctorReferente(pDoctoresReferentesDto.getFechaNacimientoDoctorReferente()); 
		 doctoresReferentesDto.setCedulaDoctorReferente(pDoctoresReferentesDto.getCedulaDoctorReferente());
		 doctoresReferentesDto.setCelularDoctorReferente(pDoctoresReferentesDto.getCelularDoctorReferente());
		 doctoresReferentesDto.setCurpDoctorReferente(pDoctoresReferentesDto.getCurpDoctorReferente());
		  doctoresReferentesDto.setDomicilioDoctorReferente(pDoctoresReferentesDto.getDomicilioDoctorReferente());
		  doctoresReferentesDto.setLugarTrabajo(pDoctoresReferentesDto.getLugarTrabajo());
		  doctoresReferentesDto.setAreaDoctorReferente(pDoctoresReferentesDto.getAreaDoctorReferente());
		  doctoresReferentesDto.setCorreoDoctorReferente(pDoctoresReferentesDto.getCorreoDoctorReferente());
		  doctoresReferentesDto.setEstatus(pDoctoresReferentesDto.getEstatus());
		  doctoresReferentesDto.setFechaUltimaActualizacion(pDoctoresReferentesDto.getFechaUltimaActualizacion());
		  doctoresReferentesDto.setComentarios(pDoctoresReferentesDto.getComentarios());
		  
		  }

	@Override
	public List<DoctoresReferentesDto> findBySearch(String pSearchNomDoct
			                             ,String pSearchApellPatDoc
			                             ,String pSearchApellMatDoc
			                             ,String pSearchLugarTrabajo
			                             ,String pSearchAreaDocRef
			                             
			                             ) {
		String query = " SELECT d FROM DoctoresReferentesDto d where 1=1"; 
		if(null!=pSearchNomDoct&&!"".equals(pSearchNomDoct)) {
			query = query+" AND d.nombreDoctorReferente like '%"+pSearchNomDoct+"%' ";
		}
		if(null!=pSearchApellPatDoc&&!"".equals(pSearchApellPatDoc)) {
			query = query+" AND d.apellidoPaternoDoctorReferente like '%"+pSearchApellPatDoc+"%' ";
		}
		if(null!=pSearchApellMatDoc&&!"".equals(pSearchApellMatDoc)) {
			query = query+" AND d.apellidoMaternoDoctorReferente like '%"+pSearchApellMatDoc+"%' ";
		}
		if(null!=pSearchLugarTrabajo&&!"".equals(pSearchLugarTrabajo)) {
			query = query+" AND d.lugarTrabajo like '%"+pSearchLugarTrabajo+"%' ";
		}
		if(null!=pSearchAreaDocRef&&!"".equals(pSearchAreaDocRef)) {
			query = query+" AND d.areaDoctorReferente like '%"+pSearchAreaDocRef+"%' ";
		}
		return em.createQuery(query).getResultList();
	}

		
	}
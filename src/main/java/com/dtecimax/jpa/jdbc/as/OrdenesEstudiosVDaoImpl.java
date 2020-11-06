package com.dtecimax.jpa.jdbc.as;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtecimax.jpa.dao.as.OrdenesEstudiosVDao;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;

@Stateless 
public class OrdenesEstudiosVDaoImpl implements OrdenesEstudiosVDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public List<OrdenesEstudiosVDto> findOneYearOld() {
		String strQuery = "SELECT o FROM OrdenesEstudiosVDto o"; 
		Query query = em.createQuery(strQuery);
		List<OrdenesEstudiosVDto> listOrdenesEstudiosVDto = query.getResultList();
		return listOrdenesEstudiosVDto;
	}
	@Override
	public void insertOrdenesEstudios(OrdenesEstudiosVDto pOrdenesEstudiosVDto) {
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.ORDENES_ESTUDIOS_S");
		BigInteger lOrdenesEstudiosS = (BigInteger)q.getSingleResult();
		pOrdenesEstudiosVDto.setNumeroOrden(lOrdenesEstudiosS.longValue());
		em.persist(pOrdenesEstudiosVDto);
	}
	@Override
	public OrdenesEstudiosVDto findByNumeroOrden(long pNumeroOrden) {
		String strQuery = "SELECT o FROM OrdenesEstudiosVDto o WHERE numeroOrden='"+pNumeroOrden+"'"; 
		Query query = em.createQuery(strQuery);
		OrdenesEstudiosVDto ordenesEstudiosVDto = (OrdenesEstudiosVDto)query.getSingleResult();
		return ordenesEstudiosVDto; 
	}

	@Override
	public List<OrdenesEstudiosVDto> findByEstaus(String pEstatus,int pTipoOrden) {
		String strQuery = "SELECT o FROM OrdenesEstudiosVDto o WHERE estatus='"+pEstatus+"' and o.tipoOrden="+pTipoOrden;
		Query query = em.createQuery(strQuery);
		List<OrdenesEstudiosVDto> listOrdenesEstudiosVDto = query.getResultList();
		return listOrdenesEstudiosVDto;
	}
	@Override
	public List<OrdenesEstudiosVDto> findAll() {
		String strQuery = "SELECT o FROM OrdenesEstudiosVDto o ORDER BY fechaCreacion DESC" ;
		Query query = em.createQuery(strQuery);
		List<OrdenesEstudiosVDto> listOrdenesEstudiosVDto = query.getResultList();
		return listOrdenesEstudiosVDto;
	}
@Override
	
	public List<OrdenesEstudiosVDto> findBySearchHisto( 
			String pSearchNumOrde
            ,String pSearchNomEstu
            ,String pSearchNomPaci
            ,String pSearchNomDocRef
            
            ) {
		System.out.println("pSearchNumOrde:"+pSearchNumOrde);
		System.out.println("pSearchNomEstu:"+pSearchNomEstu);
		System.out.println("pSearchNomPaci:"+pSearchNomPaci);
		System.out.println("pSearchNomPaci:"+pSearchNomDocRef);
		String query = " SELECT o FROM OrdenesEstudiosVDto o WHERE 1=1"; 
		if(null!=pSearchNumOrde&&!"".equals(pSearchNumOrde)) {
			query = query+" AND o.numeroOrden = "+pSearchNumOrde;
		}
		if(null!=pSearchNomEstu&&!"".equals(pSearchNomEstu)) {
			query = query+" AND o.nombreEstudio  like '%"+pSearchNomEstu+"%' ";
		}
		if(null!=pSearchNomPaci&&!"".equals(pSearchNomPaci)) {
			query = query+" AND o.nombrePaciente like '%"+pSearchNomPaci+"%' ";
		
		}
		if(null!=pSearchNomDocRef&&!"".equals(pSearchNomDocRef)) {
			query = query+" AND nombreDoctorReferente like '%"+pSearchNomDocRef+"%' ";
		}
        System.out.println("query:"+query); 
		return em.createQuery(query).getResultList();
	}
}

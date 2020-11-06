package com.dtecimax.jpa.jdbc.as;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtecimax.jpa.dao.as.OrdenesEstudiosDao;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;
import com.dtecimax.jpa.dto.hr.DoctoresReferentesDto;

@Stateless 
public class OrdenesEstudiosDaoImpl implements OrdenesEstudiosDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public void insertOrdenesEstudios(OrdenesEstudiosDto pOrdenesEstudiosDto) {
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.ORDENES_ESTUDIOS_S");
		BigInteger lOrdenesEstudiosS = (BigInteger)q.getSingleResult();
		pOrdenesEstudiosDto.setNumeroOrden(lOrdenesEstudiosS.longValue());
		em.persist(pOrdenesEstudiosDto);
	}

	@Override
	public List<OrdenesEstudiosDto> findSelectItems() {
		return em.createNamedQuery("OrdenesEstudiosDto.findAll").getResultList();
	}

	@Override
	public List<OrdenesEstudiosDto> findAllDesc() {
		return em.createNamedQuery("OrdenesEstudiosDto.findAllDesc").getResultList();
	}

	@Override
	public void deleteOrdenesEstudios(long pNumeroOrdenEstudio) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrdenEstudio);
		em.remove(ordenesEstudiosDto);
	}

	@Override
	public void updateOrdenesEstudios(long pNumeroOrden
			                        , OrdenesEstudiosDto pOrdenesEstudiosDto) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		if(null!=pOrdenesEstudiosDto.getIndicacionesDoctor()) {
			ordenesEstudiosDto.setIndicacionesDoctor(pOrdenesEstudiosDto.getIndicacionesDoctor());
		}
		if(null!=pOrdenesEstudiosDto.getInfoAdicional()) {
			ordenesEstudiosDto.setInfoAdicional(pOrdenesEstudiosDto.getInfoAdicional());
		}
		if(null!=pOrdenesEstudiosDto.getHoraInicialOrden()) {
			ordenesEstudiosDto.setHoraInicialOrden(pOrdenesEstudiosDto.getHoraInicialOrden());
		}
		if(null!=pOrdenesEstudiosDto.getHoraFinalOrden()) {
			ordenesEstudiosDto.setHoraFinalOrden(pOrdenesEstudiosDto.getHoraFinalOrden());
		}
		ordenesEstudiosDto.setNumeroEstudio(pOrdenesEstudiosDto.getNumeroEstudio());
		ordenesEstudiosDto.setNumeroAlergia(pOrdenesEstudiosDto.getNumeroAlergia());
		ordenesEstudiosDto.setRequiereFactura(pOrdenesEstudiosDto.getRequiereFactura());
		ordenesEstudiosDto.setNumeroPaciente(pOrdenesEstudiosDto.getNumeroPaciente());
		ordenesEstudiosDto.setNumeroDoctor(pOrdenesEstudiosDto.getNumeroDoctor());
		
		if(null!=pOrdenesEstudiosDto.getEstatus()) {
			ordenesEstudiosDto.setEstatus(pOrdenesEstudiosDto.getEstatus());
		}
		
	}

	@Override
	public List<OrdenesEstudiosDto> findAllEspeciales() {
		return em.createNamedQuery("OrdenesEstudiosDto.findAllEspeciales").getResultList();
	}

	@Override
	public List<OrdenesEstudiosDto> findAllContrastados() {
		return em.createNamedQuery("OrdenesEstudiosDto.findAllContrastados").getResultList();
	}

	@Override
	public List<OrdenesEstudiosDto> findAllDentales() {
		return em.createNamedQuery("OrdenesEstudiosDto.findAllDentales").getResultList();

	}

	@Override
	public List<OrdenesEstudiosDto> findBySearch(String pSearchTipo 
								                ,String pSeacrhNumEstu
								                ,String pSearchNomPaci
					                            ,String pSearchNomDoct
								                ) {
		String query = " SELECT o FROM OrdenesEstudiosDto o where o.tipoOrden = "+pSearchTipo; 
		if(null!=pSeacrhNumEstu&&!"".equals(pSeacrhNumEstu)) {
			query = query+" AND o.numeroOrden="+pSeacrhNumEstu;
		}
		if(null!=pSearchNomPaci&&!"".equals(pSearchNomPaci)) {
			query = query+" AND o.numeroPaciente in (SELECT p.numeroPaciente FROM PacientesDto p where p.nombrePaciente like '%"+pSearchNomPaci+"%')";
		}
		if(null!=pSearchNomDoct&&!"".equals(pSearchNomDoct)) {
			query = query+" AND o.numeroDoctor in (SELECT d.numeroDoctor FROM DoctoresDto d where d.nombreDoctor like '%"+pSearchNomDoct+"%')";
		}
		return em.createQuery(query).getResultList();
	}
	
	
	@Override
	public void updateFormato(long pNumeroOrden
			                , String pFormato
			                , String pFormatoText) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		ordenesEstudiosDto.setFormato(pFormato);
		ordenesEstudiosDto.setFormatoText(pFormatoText);
	}

	@Override
	public String findFormatoText(long pNumeroOrden) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		String strRetval = ordenesEstudiosDto.getFormatoText(); 
		return strRetval;
	}

	@Override
	public OrdenesEstudiosDto findByNumeroOrdenEstudio(long pNumeroOrden) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		return ordenesEstudiosDto;
	}

	@Override
	public void aplicarDescuento(long pNumeroOrden, BigDecimal pDescuento, String pObservDesc) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		if(null!=pDescuento) {
			ordenesEstudiosDto.setDescuento(pDescuento);
		}
		if(null!=pObservDesc&!"".equals(pObservDesc)) {
			ordenesEstudiosDto.setObservDesc(pObservDesc);
		}
	}

	@Override
	public void aplicarPago(long pNumeroOrden, BigDecimal pPago, String pObservPago) {
		OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		if(null!=pPago) {
			ordenesEstudiosDto.setTotal(pPago);
		}
		if(null!=pObservPago&!"".equals(pObservPago)) {
			ordenesEstudiosDto.setObservPago(pObservPago);
		}
		ordenesEstudiosDto.setEstatusPago("PAGADO");
	}

	 public void aplicarInfoFactura(long pNumeroOrden
						           ,String pInfoFactura
						            ) {
		
		 OrdenesEstudiosDto ordenesEstudiosDto = em.find(OrdenesEstudiosDto.class, pNumeroOrden);
		 ordenesEstudiosDto.setInfoFactura(pInfoFactura);
		 
	 }
	
}

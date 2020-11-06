package com.dtecimax.ejb.services.as;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dtecimax.jpa.dao.as.OrdenesEstudiosDao;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;

@Stateless
public class OrdenesEstudiosLocalImpl implements OrdenesEstudiosLocal {

	@Inject
	OrdenesEstudiosDao ordenesEstudiosDao;
	
	@Override
	public void insertOrdenesEstudios(OrdenesEstudiosDto pOrdenesEstudiosDto) {
		ordenesEstudiosDao.insertOrdenesEstudios(pOrdenesEstudiosDto);
	}

	@Override
	public List<OrdenesEstudiosDto> findSelectItems() {
		return ordenesEstudiosDao.findSelectItems();
	}

	@Override
	public List<OrdenesEstudiosDto> findAllDesc() {
		return ordenesEstudiosDao.findAllDesc();
	}

	@Override
	public void deleteOrdenesEstudios(long pNumeroOrdenEstudio) {
		 ordenesEstudiosDao.deleteOrdenesEstudios(pNumeroOrdenEstudio);
	}

	@Override
	public void updateOrdenesEstudios(long pNumeroOrden, OrdenesEstudiosDto pOrdenesEstudiosDto) {
		ordenesEstudiosDao.updateOrdenesEstudios(pNumeroOrden, pOrdenesEstudiosDto);
	}

	@Override
	public List<OrdenesEstudiosDto> findAllEspeciales() {
		return ordenesEstudiosDao.findAllEspeciales();
	}

	@Override
	public List<OrdenesEstudiosDto> findAllContrastados() {
		return ordenesEstudiosDao.findAllContrastados();
	}

	@Override
	public List<OrdenesEstudiosDto> findAllDentales() {
		return ordenesEstudiosDao.findAllDentales();
	}
	
	@Override
	public List<OrdenesEstudiosDto> findBySearch(String pSearchTipo 
            ,String pSeacrhNumEstu
            ,String pSearchNomPaci
            ,String pSearchNomDoct
            ){
		return ordenesEstudiosDao.findBySearch(pSearchTipo
				                             , pSeacrhNumEstu
				                             , pSearchNomPaci
				                             , pSearchNomDoct
				                             );
	}
	
	
	@Override
	public void updateFormato(long pNumeroOrden
			                 , String pFormato
			                 , String pFormatoText) {
		ordenesEstudiosDao.updateFormato(pNumeroOrden, pFormato, pFormatoText); 
	}

	@Override
	public String findFormatoText(long pNumeroOrden) {
		return ordenesEstudiosDao.findFormatoText(pNumeroOrden);
	}

	@Override
	public OrdenesEstudiosDto findByNumeroOrdenEstudio(long pNumeroOrden) {
		return ordenesEstudiosDao.findByNumeroOrdenEstudio(pNumeroOrden); 
	}

	@Override
	public void aplicarDescuento(long pNumeroOrden
			                   , BigDecimal pDescuento
			                   , String pObservDesc) {
		ordenesEstudiosDao.aplicarDescuento(pNumeroOrden
				                          , pDescuento
				                          , pObservDesc); 	
	}

	@Override
	public void aplicarPago(long pNumeroOrden
			              , BigDecimal pPago
			              , String pObservPago) {
		ordenesEstudiosDao.aplicarPago(pNumeroOrden
				                     , pPago
				                     , pObservPago); 
	}

	@Override
	 public void aplicarInfoFactura(long pNumeroOrden
						           ,String pInfoFactura
						            ) {
		
		ordenesEstudiosDao.aplicarInfoFactura(pNumeroOrden
				                            , pInfoFactura
				                            );
	 
	}
	
}

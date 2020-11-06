package com.dtecimax.jpa.dao.as;

import java.util.List;

import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;

public interface OrdenesEstudiosVDao {
	
	public List<OrdenesEstudiosVDto> findOneYearOld();
	public void insertOrdenesEstudios(OrdenesEstudiosVDto pOrdenesEstudiosVDto);
	public OrdenesEstudiosVDto findByNumeroOrden(long pNumeroOrden);
	public List<OrdenesEstudiosVDto> findByEstaus(String pEstatus,int pTipoOrden);
	public List<OrdenesEstudiosVDto> findAll();
	public List<OrdenesEstudiosVDto> findBySearchHisto(String pSearchNumOrde
            ,String pSearchNomEstu
            ,String pSearchNomPaci
            ,String pSearchNomDocRef);
}

package com.dtecimax.ejb.services.ar;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dtecimax.jpa.dao.ar.AsGastosDao;
import com.dtecimax.jpa.dto.ar.AsGastosDto;

@Stateless
public class AsGastosLocalImpl implements AsGastosLocal {

	@Inject 
	AsGastosDao asGastosDao;
	
	@Override
	public void insertAsGasto(AsGastosDto pAsGastosDto) {
		asGastosDao.insertAsGasto(pAsGastosDto);
	}

	@Override
	public List<AsGastosDto> findByFiltros(Date pFechaDesde, Date pFechaHasta) {
		return asGastosDao.findByFiltros(pFechaDesde, pFechaHasta);
	}

	@Override
	public void deleteAsGasto(long pNumero) {
		 asGastosDao.deleteAsGasto(pNumero);
	}

	@Override
	public void updateAsGasto(long pNumero, AsGastosDto pAsGastosDto) {
		asGastosDao.updateAsGasto(pNumero, pAsGastosDto);
	}

}

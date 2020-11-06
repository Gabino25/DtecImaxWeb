package com.dtecimax.ejb.services.ar;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import com.dtecimax.jpa.dto.ar.AsGastosDto;

@Local
public interface AsGastosLocal {

	public void insertAsGasto(AsGastosDto pAsGastosDto);
	
	public List<AsGastosDto> findByFiltros(Date pFechaDesde
							              ,Date pFechaHasta
							               );
	
	public void deleteAsGasto(long pNumero);
	public void updateAsGasto(long pNumero
                             ,AsGastosDto pAsGastosDto);
}

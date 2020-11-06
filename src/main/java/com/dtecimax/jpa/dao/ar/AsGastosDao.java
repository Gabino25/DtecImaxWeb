package com.dtecimax.jpa.dao.ar;

import java.sql.Date;
import java.util.List;

import com.dtecimax.jpa.dto.ar.AsGastosDto;

public interface AsGastosDao {

	public void insertAsGasto(AsGastosDto pAsGastosDto);
	public List<AsGastosDto> findByFiltros(Date pFechaDesde
                                          ,Date pFechaHasta
                                           );
	public void deleteAsGasto(long pNumero);
	public void updateAsGasto(long pNumero
			                 ,AsGastosDto pAsGastosDto);
}

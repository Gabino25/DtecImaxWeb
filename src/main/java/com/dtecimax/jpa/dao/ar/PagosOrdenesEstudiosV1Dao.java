package com.dtecimax.jpa.dao.ar;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

public interface PagosOrdenesEstudiosV1Dao {

	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
			                                            ,String pApellidoPaterno
			                                            ,String pApellidoMaterno
			                                            ,Date pFechaDesde
			                                            ,Date pFechaHasta
			                                            );
	public List<Object> findPagosConTarjConFact(Date pFechaDesde
                                               ,Date pFechaHasta);
	public List<Object> findPagosConTarjSinFact(Date pFechaDesde
                                               ,Date pFechaHasta);
	public List<Object> findPagosConEfectivo(Date pFechaDesde
                                            ,Date pFechaHasta);
	public List<Object> findPagosConCLIP(Date pFechaDesde
                                         ,Date pFechaHasta);
	
	public BigDecimal sumaPagosConTarjConFact(Date pFecha); 
	public BigDecimal sumaPagosConTarjSinFact(Date pFecha); 
	public BigDecimal sumaPagosConEfectivo(Date pFecha); 
	public BigDecimal sumaPagosConCLIP(Date pFecha); 
	
}

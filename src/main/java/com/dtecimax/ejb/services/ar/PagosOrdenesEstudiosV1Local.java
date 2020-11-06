package com.dtecimax.ejb.services.ar;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import com.dtecimax.ejb.model.ar.AdmonPagOrdEstud;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@Local
public interface PagosOrdenesEstudiosV1Local {

	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
											            ,String pApellidoPaterno
											            ,String pApellidoMaterno
											            ,Date pFechaDesde
											            ,Date pFechaHasta
											            );
	public List<AdmonPagOrdEstud> findPagosConTarjConFact(Date pFechaDesde
                                                         ,Date pFechaHasta);
	public List<AdmonPagOrdEstud> findPagosConTarjSinFact(Date pFechaDesde
	                                                     ,Date pFechaHasta);
	public List<AdmonPagOrdEstud> findPagosConEfectivo(Date pFechaDesde
	                                                  ,Date pFechaHasta);
	public List<AdmonPagOrdEstud> findPagosConCLIP(Date pFechaDesde
	                                              ,Date pFechaHasta);
	public BigDecimal sumaPagosConTarjConFact(Date pFecha); 
	public BigDecimal sumaPagosConTarjSinFact(Date pFecha); 
	public BigDecimal sumaPagosConEfectivo(Date pFecha); 
	public BigDecimal sumaPagosConCLIP(Date pFecha); 
}

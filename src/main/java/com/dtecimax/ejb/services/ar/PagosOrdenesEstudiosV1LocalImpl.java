package com.dtecimax.ejb.services.ar;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dtecimax.ejb.model.ar.AdmonPagOrdEstud;
import com.dtecimax.jpa.dao.ar.PagosOrdenesEstudiosV1Dao;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@Stateless 
public class PagosOrdenesEstudiosV1LocalImpl implements PagosOrdenesEstudiosV1Local {

	@Inject
	PagosOrdenesEstudiosV1Dao pagosOrdenesEstudiosV1Dao;

	@Override
	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
			                                           , String pApellidoPaterno
			                                           , String pApellidoMaterno
			                                           , Date pFechaDesde
			                                           , Date pFechaHasta) {
		return pagosOrdenesEstudiosV1Dao.findByFiltros(pNombrePaciente
				                                     , pApellidoPaterno
				                                     , pApellidoMaterno
				                                     , pFechaDesde
				                                     , pFechaHasta);
	}

	@Override
	public List<AdmonPagOrdEstud> findPagosConTarjConFact(Date pFechaDesde
			                                            , Date pFechaHasta) {
		List<Object> listObjects = pagosOrdenesEstudiosV1Dao.findPagosConTarjConFact(pFechaDesde
				                                                                  , pFechaHasta); 
		System.out.println("findPagosConTarjConFact listObjects:"+listObjects);
		if(null!=listObjects) {
			System.out.println("listObjects.size():"+listObjects.size());
		}
		List<AdmonPagOrdEstud> retval = listObjectsToListAdmonPagOrdEstud(listObjects);
		return retval;
	}

	@Override
	public List<AdmonPagOrdEstud> findPagosConTarjSinFact(Date pFechaDesde
			                                            , Date pFechaHasta) {
		List<Object> listObjects = pagosOrdenesEstudiosV1Dao.findPagosConTarjSinFact(pFechaDesde
				                                                                   , pFechaHasta);
		System.out.println("findPagosConTarjSinFact listObjects:"+listObjects);
		if(null!=listObjects) {
			System.out.println("listObjects.size():"+listObjects.size());
		}
		List<AdmonPagOrdEstud> retval = listObjectsToListAdmonPagOrdEstud(listObjects);
		return retval;
	}

	@Override
	public List<AdmonPagOrdEstud> findPagosConEfectivo(Date pFechaDesde
			                                         , Date pFechaHasta) {
		List<Object> listObjects = pagosOrdenesEstudiosV1Dao.findPagosConEfectivo(pFechaDesde
				                                                                , pFechaHasta); 
		System.out.println("findPagosConEfectivo listObjects:"+listObjects);
		if(null!=listObjects) {
			System.out.println("listObjects.size():"+listObjects.size());
		}
		List<AdmonPagOrdEstud> retval = listObjectsToListAdmonPagOrdEstud(listObjects);
		return retval;
	}

	@Override
	public List<AdmonPagOrdEstud> findPagosConCLIP(Date pFechaDesde
			                                     , Date pFechaHasta) {
		List<Object> listObjects = pagosOrdenesEstudiosV1Dao.findPagosConCLIP(pFechaDesde
				                                                            , pFechaHasta); 
		System.out.println("findPagosConCLIP listObjects:"+listObjects);
		if(null!=listObjects) {
			System.out.println("listObjects.size():"+listObjects.size());
		}
		List<AdmonPagOrdEstud> retval = listObjectsToListAdmonPagOrdEstud(listObjects);
		return retval;
	}
	
	private List<AdmonPagOrdEstud> listObjectsToListAdmonPagOrdEstud(List<Object> pListObject){
		List<AdmonPagOrdEstud> retval = new ArrayList<AdmonPagOrdEstud>(); 
		if(null!=pListObject) {
		for(Object object:pListObject) {
			if(object instanceof Object[]) {
				AdmonPagOrdEstud admonPagOrdEstud = new AdmonPagOrdEstud();
				Object[] row = (Object[]) object;
				if(row[0] instanceof String) { /** [NOMBRE_COMPLETO_PACIENTE] **/
					admonPagOrdEstud.setPaciente((String)row[0]);
				}
				if(row[1] instanceof String) { /** [NOMBRE_ESTUDIO] **/
					admonPagOrdEstud.setEstudioDesc((String)row[1]);
				}
				if(row[2] instanceof BigDecimal) { /** [COSTO_TOTAL] **/
					admonPagOrdEstud.setCostoTotal((BigDecimal)row[2]);
				}
				if(row[3] instanceof String) { /** [DECODE_TIPO_PAGO] **/
					admonPagOrdEstud.setTipoDePagoDesc((String)row[3]);
				}
				if(row[4] instanceof String) { /** [OBSERV_PAGO] **/
					admonPagOrdEstud.setObservacionPago((String)row[4]);
				}
				if(row[5] instanceof String) { /** [INFO_FACTURA] **/
					admonPagOrdEstud.setInfoFactura((String)row[5]);
				}
				if(row[6] instanceof java.sql.Date) { /** [CAST_FECHA_CREACION] **/
					java.sql.Date sqlDate = (java.sql.Date)row[6]; 
					java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
					admonPagOrdEstud.setFechaCreacion(utilDate);
				}
				if(row[7] instanceof String) { /** [DECODE_REQUIERE_FACTURA] **/
					admonPagOrdEstud.setRequireFacturaDesc((String)row[7]);
				}
				retval.add(admonPagOrdEstud); 
			}
		}
		}
		return retval; 
	}

	@Override
	public BigDecimal sumaPagosConTarjConFact(Date pFecha) {
		return pagosOrdenesEstudiosV1Dao.sumaPagosConTarjConFact(pFecha);
	}

	@Override
	public BigDecimal sumaPagosConTarjSinFact(Date pFecha) {
		return pagosOrdenesEstudiosV1Dao.sumaPagosConTarjSinFact(pFecha); 
	}

	@Override
	public BigDecimal sumaPagosConEfectivo(Date pFecha) {
		return pagosOrdenesEstudiosV1Dao.sumaPagosConEfectivo(pFecha);
	}

	@Override
	public BigDecimal sumaPagosConCLIP(Date pFecha) {
		return pagosOrdenesEstudiosV1Dao.sumaPagosConCLIP(pFecha); 
	}
	
}

package com.dtecimax.jpa.jdbc.ar;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.dtecimax.jpa.dao.ar.PagosOrdenesEstudiosV1Dao;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@Stateless 
public class PagosOrdenesEstudiosV1DaoImpl implements PagosOrdenesEstudiosV1Dao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
			                                           , String pApellidoPaterno
			                                           , String pApellidoMaterno
			                                           , Date pFechaDesde
			                                           , Date pFechaHasta) {
		List<PagosOrdenesEstudiosV1Dto> retval = null; 
		String strQuery = "SELECT p FROM PagosOrdenesEstudiosV1Dto p WHERE 1=1";
		if(null!=pNombrePaciente&&!"".equals(pNombrePaciente)) {
			strQuery = strQuery+" AND p.nombrePaciente like '%"+pNombrePaciente+"%'";
		}
		if(null!=pApellidoPaterno&&!"".equals(pApellidoPaterno)) {
			strQuery = strQuery+" AND p.apellidoPaternoPaciente like '%"+pApellidoPaterno+"%'";
		}
		if(null!=pApellidoMaterno&&!"".equals(pApellidoMaterno)) {
			strQuery = strQuery+" AND p.apellidoMaternoPaciente like '%"+pApellidoMaterno+"%'";
		}
		strQuery = strQuery+" AND p.castFechaCreacion BETWEEN :pFechaDesde AND :pFechaHasta";
		Query query = em.createQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		retval = query.getResultList(); 
		return retval;
	}

	@Override
	public List<Object> findPagosConTarjConFact(Date pFechaDesde
			                                  , Date pFechaHasta) {
		List<Object> retval = new ArrayList<Object>(); 
		String strQuery = "select NOMBRE_COMPLETO_PACIENTE\r" + 
						  "      ,NOMBRE_ESTUDIO\r" + 
						  "	  ,ISNULL(TOTAL,0) COSTO_TOTAL\r" + 
						  "	  ,DECODE_TIPO_PAGO\r" + 
						  "	  ,OBSERV_PAGO\r" + 
						  "	  ,INFO_FACTURA\r" + 
						  "	  ,CAST_FECHA_CREACION\r" + 
						  "	  ,DECODE_REQUIERE_FACTURA\r" + 
						  "  from PAGOS_ORDENES_ESTUDIOS_V1\r" + 
						  " WHERE DECODE_TIPO_PAGO in ('Tarjeta Debito','Tarjeta Credito','Tarjeta Bancaria') \r" + 
						  "   AND REQUIERE_FACTURA in (1)\r"+ 
		                  "   AND CAST_FECHA_CREACION BETWEEN :pFechaDesde AND :pFechaHasta "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		retval = query.getResultList();
		return retval;
	}

	@Override
	public List<Object> findPagosConTarjSinFact(Date pFechaDesde
			                                  , Date pFechaHasta) {
		List<Object> retval = new ArrayList<Object>(); 
		String strQuery = "select NOMBRE_COMPLETO_PACIENTE\r" + 
						  "      ,NOMBRE_ESTUDIO\r" + 
						  "	  ,ISNULL(TOTAL,0) COSTO_TOTAL\r" + 
						  "	  ,DECODE_TIPO_PAGO\r" + 
						  "	  ,OBSERV_PAGO\r" + 
						  "	  ,INFO_FACTURA\r" + 
						  "	  ,CAST_FECHA_CREACION\r" + 
						  "	  ,DECODE_REQUIERE_FACTURA\r" + 
						  "  from PAGOS_ORDENES_ESTUDIOS_V1\r" + 
						  " WHERE DECODE_TIPO_PAGO in ('Tarjeta Debito','Tarjeta Credito','Tarjeta Bancaria') \r" + 
						  "   AND REQUIERE_FACTURA in (0)\r"+ 
		                  "   AND CAST_FECHA_CREACION BETWEEN :pFechaDesde AND :pFechaHasta "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		retval = query.getResultList();
		return retval;
	}

	@Override
	public List<Object> findPagosConEfectivo(Date pFechaDesde
			                               , Date pFechaHasta) {
		List<Object> retval = new ArrayList<Object>(); 
		String strQuery = "select NOMBRE_COMPLETO_PACIENTE\r" + 
						  "      ,NOMBRE_ESTUDIO\r" + 
						  "	  ,ISNULL(TOTAL,0) COSTO_TOTAL\r" + 
						  "	  ,DECODE_TIPO_PAGO\r" + 
						  "	  ,OBSERV_PAGO\r" + 
						  "	  ,INFO_FACTURA\r" + 
						  "	  ,CAST_FECHA_CREACION\r" + 
						  "	  ,DECODE_REQUIERE_FACTURA\r" + 
						  "  from PAGOS_ORDENES_ESTUDIOS_V1\r" + 
						  " WHERE DECODE_TIPO_PAGO in ('Efectivo') \r" + 
						  "   AND CAST_FECHA_CREACION BETWEEN :pFechaDesde AND :pFechaHasta "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		retval = query.getResultList();
		return retval;
	}

	@Override
	public List<Object> findPagosConCLIP(Date pFechaDesde
			                           , Date pFechaHasta) {
		List<Object> retval = new ArrayList<Object>(); 
		String strQuery = "select NOMBRE_COMPLETO_PACIENTE\r" + 
						  "      ,NOMBRE_ESTUDIO\r" + 
						  "	  ,ISNULL(TOTAL,0) COSTO_TOTAL\r" + 
						  "	  ,DECODE_TIPO_PAGO\r" + 
						  "	  ,OBSERV_PAGO\r" + 
						  "	  ,INFO_FACTURA\r" + 
						  "	  ,CAST_FECHA_CREACION\r" + 
						  "	  ,DECODE_REQUIERE_FACTURA\r" + 
						  "  from PAGOS_ORDENES_ESTUDIOS_V1\r" + 
						  " WHERE DECODE_TIPO_PAGO in ('CLIP') \r" + 
						  "   AND CAST_FECHA_CREACION BETWEEN :pFechaDesde AND :pFechaHasta "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		retval = query.getResultList();
		return retval;
	}

	@Override
	public BigDecimal sumaPagosConTarjConFact(Date pFecha) {
		BigDecimal retval = new BigDecimal(0);
		String strQuery = "select ISNULL(sum(total),0)\r" + 
				          " from PAGOS_ORDENES_ESTUDIOS_V1\r"+
				          " WHERE DECODE_TIPO_PAGO in ('Tarjeta Debito','Tarjeta Credito','Tarjeta Bancaria') \r" + 
						  "   AND REQUIERE_FACTURA in (1)\r"+ 
			   	          "   AND CAST_FECHA_CREACION = :pFecha "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFecha", pFecha, TemporalType.DATE);
		retval = (BigDecimal)query.getSingleResult(); 
		return retval;
	}

	@Override
	public BigDecimal sumaPagosConTarjSinFact(Date pFecha) {
		BigDecimal retval = new BigDecimal(0);
		String strQuery = "select ISNULL(sum(total),0)\r" + 
				          " from PAGOS_ORDENES_ESTUDIOS_V1\r"+
				          " WHERE DECODE_TIPO_PAGO in ('Tarjeta Debito','Tarjeta Credito','Tarjeta Bancaria') \r" + 
						  "   AND REQUIERE_FACTURA in (0)\r"+ 
			   	          "   AND CAST_FECHA_CREACION = :pFecha "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFecha", pFecha, TemporalType.DATE);
		retval = (BigDecimal)query.getSingleResult(); 
		return retval;
	}

	@Override
	public BigDecimal sumaPagosConEfectivo(Date pFecha) {
		BigDecimal retval = new BigDecimal(0);
		String strQuery = "select ISNULL(sum(total),0)\r" + 
				          " from PAGOS_ORDENES_ESTUDIOS_V1\r"+
				          " WHERE DECODE_TIPO_PAGO in ('Efectivo') \r" + 
						  "   AND CAST_FECHA_CREACION = :pFecha "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFecha", pFecha, TemporalType.DATE);
		retval = (BigDecimal)query.getSingleResult(); 
		return retval;
	}

	@Override
	public BigDecimal sumaPagosConCLIP(Date pFecha) {
		BigDecimal retval = new BigDecimal(0);
		String strQuery = "select ISNULL(sum(total),0)\r" + 
				          " from PAGOS_ORDENES_ESTUDIOS_V1\r"+
				          " WHERE DECODE_TIPO_PAGO in ('CLIP') \r" + 
						  "   AND CAST_FECHA_CREACION = :pFecha "; 
		Query query = em.createNativeQuery(strQuery);
		query.setParameter("pFecha", pFecha, TemporalType.DATE);
		retval = (BigDecimal)query.getSingleResult(); 
		return retval;
	}

}

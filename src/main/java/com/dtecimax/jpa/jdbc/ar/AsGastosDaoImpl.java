package com.dtecimax.jpa.jdbc.ar;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.dtecimax.jpa.dao.ar.AsGastosDao;
import com.dtecimax.jpa.dto.ar.AsGastosDto;

@Stateless 
public class AsGastosDaoImpl implements AsGastosDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public void insertAsGasto(AsGastosDto pAsGastosDto) {
	
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.AS_GASTOS_S");
		BigInteger lAsGastosS = (BigInteger)q.getSingleResult();
		pAsGastosDto.setNumero(lAsGastosS.longValue());
		java.util.Date sysdate = new java.util.Date();
		java.sql.Timestamp sqlsysdate = new java.sql.Timestamp(sysdate.getTime());
		pAsGastosDto.setCreadoPor((short)-1);
		pAsGastosDto.setActualizadoPor((short)-1);
		pAsGastosDto.setFechaCreacion(sqlsysdate);
		pAsGastosDto.setFechaActualizacion(sqlsysdate);
		em.persist(pAsGastosDto);
		
	}

	@Override
	public List<AsGastosDto> findByFiltros(Date pFechaDesde, Date pFechaHasta) {
		String strQuery="SELECT a FROM AsGastosDto a";
		strQuery = strQuery+" WHERE 1=1 ";
		strQuery = strQuery+" AND a.fechaPago BETWEEN :pFechaDesde AND :pFechaHasta";
		Query query = em.createQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		return query.getResultList();
	}

	@Override
	public void deleteAsGasto(long pNumero) {
		AsGastosDto asGastosDto = em.find(AsGastosDto.class, pNumero); 
		em.remove(asGastosDto);
	}

	@Override
	public void updateAsGasto(long pNumero
			                , AsGastosDto pAsGastosDto) {
		AsGastosDto asGastosDto = em.find(AsGastosDto.class, pNumero); 
		asGastosDto.setAutorizo(pAsGastosDto.getAutorizo());
		asGastosDto.setRazon(pAsGastosDto.getRazon());
		asGastosDto.setMonto(pAsGastosDto.getMonto());
	}

}

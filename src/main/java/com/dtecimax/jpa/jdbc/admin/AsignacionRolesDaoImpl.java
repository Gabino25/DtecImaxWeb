package com.dtecimax.jpa.jdbc.admin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtecimax.jpa.dao.admin.AsignacionRolesDao;
import com.dtecimax.jpa.dto.admin.AsignacionRolesDto;
import com.dtecimax.jpa.dto.admin.AsignacionRolesVDto;

@Stateless
public class AsignacionRolesDaoImpl implements AsignacionRolesDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public long insertaAsignacionRol(AsignacionRolesDto pAsignacionRolesDto) {
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.ASIGNACION_ROLES_S");
		BigInteger lNumeroS = (BigInteger)q.getSingleResult();
		pAsignacionRolesDto.setNumero(lNumeroS.longValue());
        em.persist(pAsignacionRolesDto);
        return lNumeroS.longValue(); 
	}

	@Override
	public AsignacionRolesVDto findByNumero(long pNumeroAsignacionRol) {
		AsignacionRolesVDto asignacionRolesVDto = em.find(AsignacionRolesVDto.class, pNumeroAsignacionRol); 
		return asignacionRolesVDto;
	}

	@Override
	public List<AsignacionRolesVDto> findByNombreUsuario(String pNombreUsuario) {
		String strQuery = "SELECT a FROM AsignacionRolesVDto a WHERE a.nombreUsuario='"+pNombreUsuario+"'"; 
		Query query = em.createQuery(strQuery); 
		return query.getResultList();
	}

	@Override
	public AsignacionRolesVDto findByNumeroAndRol(short pNumeroUsuario
			                                    , String pRolCode) {
		String strQuery = "SELECT a FROM AsignacionRolesVDto a WHERE a.numeroUsuario = "+pNumeroUsuario+" AND a.rolCode = '"+pRolCode+"'"; 
		Query query = em.createQuery(strQuery); 
		AsignacionRolesVDto asignacionRolesVDto = null; 
		asignacionRolesVDto = (AsignacionRolesVDto)query.getSingleResult(); 
		return asignacionRolesVDto;
	}

	@Override
	public long actualizaAsignacionRol(long pNumeroAsignacionRol, AsignacionRolesDto pAsignacionRolesDto) {
		AsignacionRolesDto asignacionRolesDto = em.find(AsignacionRolesDto.class, pNumeroAsignacionRol); 
		asignacionRolesDto.setNumeroUsuario(pAsignacionRolesDto.getNumeroUsuario());
		asignacionRolesDto.setRolCode(pAsignacionRolesDto.getRolCode());
		asignacionRolesDto.setFechaEfectivaDesde(pAsignacionRolesDto.getFechaEfectivaDesde());
		asignacionRolesDto.setFechaEfectivaHasta(pAsignacionRolesDto.getFechaEfectivaHasta());
		return asignacionRolesDto.getNumero();
	}

	@Override
	public void borraAsignacionRol(long pNumeroAsignacionRol) {
		AsignacionRolesDto asignacionRolesDto = em.find(AsignacionRolesDto.class, pNumeroAsignacionRol); 
		em.remove(asignacionRolesDto);	
	}

}

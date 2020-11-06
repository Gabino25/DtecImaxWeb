package com.dtecimax.ejb.services.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import com.dtecimax.jpa.dao.admin.AsignacionRolesDao;
import com.dtecimax.jpa.dto.admin.AsignacionRolesDto;
import com.dtecimax.jpa.dto.admin.AsignacionRolesVDto;

@Stateless
public class AsignacionRolesLocalImpl implements AsignacionRolesLocal {

	@Inject
	AsignacionRolesDao asignacionRolesDao;

	@Override
	public long insertaAsignacionRol(AsignacionRolesDto pAsignacionRolesDto) {
		return asignacionRolesDao.insertaAsignacionRol(pAsignacionRolesDto);
	}

	@Override
	public AsignacionRolesVDto findByNumero(long pNumeroAsignacionRol) {
		return asignacionRolesDao.findByNumero(pNumeroAsignacionRol);
	}

	@Override
	public List<AsignacionRolesVDto> findByNombreUsuario(String pNombreUsuario) {
		return asignacionRolesDao.findByNombreUsuario(pNombreUsuario);
	}

	@Override
	public AsignacionRolesVDto findByNumeroAndRol(short pNumeroUsuario, String pRolCode) {
		return asignacionRolesDao.findByNumeroAndRol(pNumeroUsuario, pRolCode);
	}

	@Override
	public long actualizaAsignacionRol(long pNumeroAsignacionRol, AsignacionRolesDto pAsignacionRolesDto) {
		return asignacionRolesDao.actualizaAsignacionRol(pNumeroAsignacionRol, pAsignacionRolesDto);
	}

	@Override
	public void borraAsignacionRol(long pNumeroAsignacionRol) {
		asignacionRolesDao.borraAsignacionRol(pNumeroAsignacionRol);
	} 
	
	
}

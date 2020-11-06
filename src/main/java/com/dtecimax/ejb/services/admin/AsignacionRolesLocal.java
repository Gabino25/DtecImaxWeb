package com.dtecimax.ejb.services.admin;

import java.util.List;

import javax.ejb.Local;

import com.dtecimax.jpa.dto.admin.AsignacionRolesDto;
import com.dtecimax.jpa.dto.admin.AsignacionRolesVDto;

@Local
public interface AsignacionRolesLocal {

	public long insertaAsignacionRol(AsignacionRolesDto pAsignacionRolesDto); 
	public AsignacionRolesVDto findByNumero(long pNumeroAsignacionRol);
	public List<AsignacionRolesVDto> findByNombreUsuario(String pNombreUsuario); 
	public AsignacionRolesVDto findByNumeroAndRol(short pNumeroUsuario
                                                , String pRolCode); 
	public long actualizaAsignacionRol(long pNumeroAsignacionRol,AsignacionRolesDto pAsignacionRolesDto); 
	public void borraAsignacionRol(long pNumeroAsignacionRol); 
}

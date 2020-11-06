package com.dtecimax.jpa.dao.admin;

import java.util.List;

import com.dtecimax.jpa.dto.admin.AsignacionRolesDto;
import com.dtecimax.jpa.dto.admin.AsignacionRolesVDto;

public interface AsignacionRolesDao {

 public long insertaAsignacionRol(AsignacionRolesDto pAsignacionRolesDto);
 public AsignacionRolesVDto findByNumero(long pNumeroAsignacionRol);
 public List<AsignacionRolesVDto> findByNombreUsuario(String pNombreUsuario); 
 public AsignacionRolesVDto findByNumeroAndRol(short pNumeroUsuario, String pRolCode);
 public long actualizaAsignacionRol(long pNumeroAsignacionRol,AsignacionRolesDto pAsignacionRolesDto); 
 public void borraAsignacionRol(long pNumeroAsignacionRol);
}

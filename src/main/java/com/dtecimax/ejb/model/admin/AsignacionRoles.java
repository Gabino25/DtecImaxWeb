package com.dtecimax.ejb.model.admin;

import java.io.Serializable;
import java.util.Date;
public class AsignacionRoles implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long numero;
	private String atributo1;
	private String atributo2;
	private String atributo3;
	private String atributo4;
	private String atributo5;
	private String categoriaAttributo;
	private short numeroUsuario;
	private String rolCode;
	private Date fechaEfectivaDesde;
	private Date fechaEfectivaHasta;
	private String nombreUsuario; 
	private String rolDesc; 
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getAtributo1() {
		return atributo1;
	}
	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	public String getAtributo2() {
		return atributo2;
	}
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}
	public String getAtributo3() {
		return atributo3;
	}
	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}
	public String getAtributo4() {
		return atributo4;
	}
	public void setAtributo4(String atributo4) {
		this.atributo4 = atributo4;
	}
	public String getAtributo5() {
		return atributo5;
	}
	public void setAtributo5(String atributo5) {
		this.atributo5 = atributo5;
	}
	public String getCategoriaAttributo() {
		return categoriaAttributo;
	}
	public void setCategoriaAttributo(String categoriaAttributo) {
		this.categoriaAttributo = categoriaAttributo;
	}
	public short getNumeroUsuario() {
		return numeroUsuario;
	}
	public void setNumeroUsuario(short numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}
	public String getRolCode() {
		return rolCode;
	}
	public void setRolCode(String rolCode) {
		this.rolCode = rolCode;
	}
	public Date getFechaEfectivaDesde() {
		return fechaEfectivaDesde;
	}
	public void setFechaEfectivaDesde(Date fechaEfectivaDesde) {
		this.fechaEfectivaDesde = fechaEfectivaDesde;
	}
	public Date getFechaEfectivaHasta() {
		return fechaEfectivaHasta;
	}
	public void setFechaEfectivaHasta(Date fechaEfectivaHasta) {
		this.fechaEfectivaHasta = fechaEfectivaHasta;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getRolDesc() {
		return rolDesc;
	}
	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}
	

}

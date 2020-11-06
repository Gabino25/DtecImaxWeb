package com.dtecimax.jpa.dto.admin;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the ASIGNACION_ROLES_V database table.
 * 
 */
@Entity
@Table(name="ASIGNACION_ROLES_V")
@NamedQuery(name="AsignacionRolesVDto.findAll", query="SELECT a FROM AsignacionRolesVDto a")
public class AsignacionRolesVDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTUALIZADO_POR")
	private short actualizadoPor;

	@Column(name="ATRIBUTO1")
	private String atributo1;

	@Column(name="ATRIBUTO2")
	private String atributo2;

	@Column(name="ATRIBUTO3")
	private String atributo3;

	@Column(name="ATRIBUTO4")
	private String atributo4;

	@Column(name="ATRIBUTO5")
	private String atributo5;

	@Column(name="CATEGORIA_ATTRIBUTO")
	private String categoriaAttributo;

	@Column(name="CREADO_POR")
	private short creadoPor;

	@Column(name="FECHA_ACTUALIZACION")
	private Timestamp fechaActualizacion;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	@Column(name="FECHA_EFECTIVA_DESDE")
	private Date fechaEfectivaDesde;

	@Column(name="FECHA_EFECTIVA_HASTA")
	private Date fechaEfectivaHasta;

	@Column(name="NOMBRE_USUARIO")
	private String nombreUsuario;

	@Id
	@Column(name="NUMERO")
	private long numero;

	@Column(name="NUMERO_USUARIO")
	private short numeroUsuario;

	@Column(name="ROL_CODE")
	private String rolCode;

	@Column(name="ROL_DESC")
	private String rolDesc;

	public AsignacionRolesVDto() {
	}

	public short getActualizadoPor() {
		return this.actualizadoPor;
	}

	public void setActualizadoPor(short actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	public String getAtributo1() {
		return this.atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return this.atributo2;
	}

	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

	public String getAtributo3() {
		return this.atributo3;
	}

	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}

	public String getAtributo4() {
		return this.atributo4;
	}

	public void setAtributo4(String atributo4) {
		this.atributo4 = atributo4;
	}

	public String getAtributo5() {
		return this.atributo5;
	}

	public void setAtributo5(String atributo5) {
		this.atributo5 = atributo5;
	}

	public String getCategoriaAttributo() {
		return this.categoriaAttributo;
	}

	public void setCategoriaAttributo(String categoriaAttributo) {
		this.categoriaAttributo = categoriaAttributo;
	}

	public short getCreadoPor() {
		return this.creadoPor;
	}

	public void setCreadoPor(short creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEfectivaDesde() {
		return this.fechaEfectivaDesde;
	}

	public void setFechaEfectivaDesde(Date fechaEfectivaDesde) {
		this.fechaEfectivaDesde = fechaEfectivaDesde;
	}

	public Date getFechaEfectivaHasta() {
		return this.fechaEfectivaHasta;
	}

	public void setFechaEfectivaHasta(Date fechaEfectivaHasta) {
		this.fechaEfectivaHasta = fechaEfectivaHasta;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public short getNumeroUsuario() {
		return this.numeroUsuario;
	}

	public void setNumeroUsuario(short numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public String getRolCode() {
		return this.rolCode;
	}

	public void setRolCode(String rolCode) {
		this.rolCode = rolCode;
	}

	public String getRolDesc() {
		return this.rolDesc;
	}

	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}

}
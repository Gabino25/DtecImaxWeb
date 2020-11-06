package com.dtecimax.jpa.dto.hr;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="DOC_REF_VS_DOC")
@NamedQuery(name="DocRefVsDocDto.findAll", query="SELECT d FROM DocRefVsDocDto d")
public class DocRefVsDocDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="NUMERO")
	private long numero; 
	
	@Column(name="NUMERO_UBICACION")
	private long numeroUbicacion;
	
	@Column(name="NUMERO_DOCTOR_REFERENTE")
	private long numeroDoctorReferente;
	
	@Column(name="NUMERO_DOCTOR")
	private long numeroDoctor;
	
	@Column(name="ESTATUS")
	private boolean estatus;
	
	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;
	
	@Column(name="FECHA_ULTIMA_ACTUALIZACION")
	private Timestamp fechaUltimaActualizacion;
	
	@Column(name="USUARIO_ULTIMA_ACTUALIZACION")
	private long usuarioUltimaActualizacion;
	
	@Column(name="COMENTARIOS")
	private String comentarios; 
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public long getNumeroUbicacion() {
		return numeroUbicacion;
	}
	public void setNumeroUbicacion(long numeroUbicacion) {
		this.numeroUbicacion = numeroUbicacion;
	}
	public long getNumeroDoctorReferente() {
		return numeroDoctorReferente;
	}
	public void setNumeroDoctorReferente(long numeroDoctorReferente) {
		this.numeroDoctorReferente = numeroDoctorReferente;
	}
	public long getNumeroDoctor() {
		return numeroDoctor;
	}
	public void setNumeroDoctor(long numeroDoctor) {
		this.numeroDoctor = numeroDoctor;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Timestamp getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	public long getUsuarioUltimaActualizacion() {
		return usuarioUltimaActualizacion;
	}
	public void setUsuarioUltimaActualizacion(long usuarioUltimaActualizacion) {
		this.usuarioUltimaActualizacion = usuarioUltimaActualizacion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}

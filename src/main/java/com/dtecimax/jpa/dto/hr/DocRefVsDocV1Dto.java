package com.dtecimax.jpa.dto.hr;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="DOC_REF_VS_DOC_V1")
@NamedQuery(name="DocRefVsDocV1Dto.findAll", query="SELECT d FROM DocRefVsDocV1Dto d")
public class DocRefVsDocV1Dto implements Serializable{

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
	
	@Column(name="NOMBRE_UBICACION")
	private String nombreUbicacion;
	
	@Column(name="NOMBRE_COMPLETO_DOCTOR_REF")
	private String nombreCompletoDoctorRef; 
	
	@Column(name="NOMBRE_COMPLETO_DOCTOR")
	private String nombreCompletoDoctor; 
	
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
	public String getNombreUbicacion() {
		return nombreUbicacion;
	}
	public void setNombreUbicacion(String nombreUbicacion) {
		this.nombreUbicacion = nombreUbicacion;
	}
	public String getNombreCompletoDoctorRef() {
		return nombreCompletoDoctorRef;
	}
	public void setNombreCompletoDoctorRef(String nombreCompletoDoctorRef) {
		this.nombreCompletoDoctorRef = nombreCompletoDoctorRef;
	}
	public String getNombreCompletoDoctor() {
		return nombreCompletoDoctor;
	}
	public void setNombreCompletoDoctor(String nombreCompletoDoctor) {
		this.nombreCompletoDoctor = nombreCompletoDoctor;
	}
	
}
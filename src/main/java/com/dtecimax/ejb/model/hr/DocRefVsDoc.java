package com.dtecimax.ejb.model.hr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class DocRefVsDoc implements Serializable{

	private static final long serialVersionUID = 1L;
	private long numero; 
	private long numeroUbicacion;
	private long numeroDoctorReferente;
	private long numeroDoctor;
	private boolean estatus;
	private Date fechaCreacion;
	private Date fechaUltimaActualizacion;
	private long usuarioUltimaActualizacion;
	private String comentarios; 
	private String nombreUbicacion;
	private String nombreCompletoDoctorRef; 
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
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

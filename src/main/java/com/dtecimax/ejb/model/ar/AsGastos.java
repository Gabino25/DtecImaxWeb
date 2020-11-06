package com.dtecimax.ejb.model.ar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


public class AsGastos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private short actualizadoPor;
	private String autorizo;
	private short creadoPor;
	private Timestamp fechaActualizacion;
	private Timestamp fechaCreacion;
	private Date fechaPago;
	private BigDecimal monto;
	private long numero;
	private String razon;


	public short getActualizadoPor() {
		return this.actualizadoPor;
	}

	public void setActualizadoPor(short actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	public String getAutorizo() {
		return this.autorizo;
	}

	public void setAutorizo(String autorizo) {
		this.autorizo = autorizo;
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

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getRazon() {
		return this.razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}
	
}

package com.dtecimax.jpa.dto.ar;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;


/**
 * The persistent class for the AS_GASTOS database table.
 * 
 */
@Entity
@Table(name="AS_GASTOS")
@NamedQuery(name="AsGastosDto.findAll", query="SELECT a FROM AsGastosDto a")
public class AsGastosDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTUALIZADO_POR")
	private short actualizadoPor;

	@Column(name="AUTORIZO")
	private String autorizo;

	@Column(name="CREADO_POR")
	private short creadoPor;

	@Column(name="FECHA_ACTUALIZACION")
	private Timestamp fechaActualizacion;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	@Column(name="FECHA_PAGO")
	private Date fechaPago;

	@Column(name="MONTO")
	private BigDecimal monto;
    
	@Id
	@Column(name="NUMERO")
	private long numero;

	@Column(name="RAZON")
	private String razon;

	public AsGastosDto() {
	}

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
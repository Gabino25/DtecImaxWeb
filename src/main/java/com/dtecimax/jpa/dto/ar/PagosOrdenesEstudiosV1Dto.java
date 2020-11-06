package com.dtecimax.jpa.dto.ar;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the PAGOS_ORDENES_ESTUDIOS_V1 database table.
 * 
 */
@Entity
@Table(name="PAGOS_ORDENES_ESTUDIOS_V1")
@NamedQuery(name="PagosOrdenesEstudiosV1Dto.findAll", query="SELECT p FROM PagosOrdenesEstudiosV1Dto p")
public class PagosOrdenesEstudiosV1Dto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="APELLIDO_MATERNO_PACIENTE")
	private String apellidoMaternoPaciente;

	@Column(name="APELLIDO_PATERNO_PACIENTE")
	private String apellidoPaternoPaciente;

	@Column(name="CAST_FECHA_CREACION")
	private Date castFechaCreacion;

	@Column(name="COSTO_ESTUDIO")
	private int costoEstudio;

	@Column(name="DECODE_REQUIERE_FACTURA")
	private String decodeRequiereFactura;

	@Column(name="DECODE_TIPO_ORDEN")
	private String decodeTipoOrden;

	@Column(name="DECODE_TIPO_PAGO")
	private String decodeTipoPago;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	@Column(name="NOMBRE_COMPLETO_DOCTOR")
	private String nombreCompletoDoctor;

	@Column(name="NOMBRE_COMPLETO_PACIENTE")
	private String nombreCompletoPaciente;

	@Column(name="NOMBRE_DOCTOR")
	private String nombreDoctor;

	@Column(name="NOMBRE_ESTUDIO")
	private String nombreEstudio;

	@Column(name="NOMBRE_PACIENTE")
	private String nombrePaciente;

	@Column(name="NUMERO_DOCTOR")
	private long numeroDoctor;

	@Column(name="NUMERO_ESTUDIO")
	private long numeroEstudio;
    
	@Id
	@Column(name="NUMERO_ORDEN")
	private long numeroOrden;

	@Column(name="NUMERO_PACIENTE")
	private long numeroPaciente;

	@Column(name="NUMERO_UBICACION")
	private long numeroUbicacion;

	@Column(name="REQUIERE_FACTURA")
	private boolean requiereFactura;

	@Column(name="TIPO_ORDEN")
	private short tipoOrden;

	@Column(name="TIPO_PAGO")
	private short tipoPago;

	@Column(name="ESTATUS_PAGO")
	private String estatusPago;
	
	@Column(name="COSTO")
	private BigDecimal costo; 
	
	@Column(name="DESCUENTO")
	private BigDecimal descuento; 
	
	@Column(name="TOTAL")
	private BigDecimal total;
	
	@Column(name="OBSERV_PAGO")
	private String observPago;
	
	@Column(name="OBSERV_DESC")
	private String observDesc;
	
	@Column(name="INFO_FACTURA")
	private String infoFactura; 
	
	public PagosOrdenesEstudiosV1Dto() {
	}

	public String getApellidoMaternoPaciente() {
		return this.apellidoMaternoPaciente;
	}

	public void setApellidoMaternoPaciente(String apellidoMaternoPaciente) {
		this.apellidoMaternoPaciente = apellidoMaternoPaciente;
	}

	public String getApellidoPaternoPaciente() {
		return this.apellidoPaternoPaciente;
	}

	public void setApellidoPaternoPaciente(String apellidoPaternoPaciente) {
		this.apellidoPaternoPaciente = apellidoPaternoPaciente;
	}

	public Date getCastFechaCreacion() {
		return this.castFechaCreacion;
	}

	public void setCastFechaCreacion(Date castFechaCreacion) {
		this.castFechaCreacion = castFechaCreacion;
	}

	public int getCostoEstudio() {
		return this.costoEstudio;
	}

	public void setCostoEstudio(int costoEstudio) {
		this.costoEstudio = costoEstudio;
	}

	public String getDecodeRequiereFactura() {
		return this.decodeRequiereFactura;
	}

	public void setDecodeRequiereFactura(String decodeRequiereFactura) {
		this.decodeRequiereFactura = decodeRequiereFactura;
	}

	public String getDecodeTipoOrden() {
		return this.decodeTipoOrden;
	}

	public void setDecodeTipoOrden(String decodeTipoOrden) {
		this.decodeTipoOrden = decodeTipoOrden;
	}

	public String getDecodeTipoPago() {
		return this.decodeTipoPago;
	}

	public void setDecodeTipoPago(String decodeTipoPago) {
		this.decodeTipoPago = decodeTipoPago;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreCompletoDoctor() {
		return this.nombreCompletoDoctor;
	}

	public void setNombreCompletoDoctor(String nombreCompletoDoctor) {
		this.nombreCompletoDoctor = nombreCompletoDoctor;
	}

	public String getNombreCompletoPaciente() {
		return this.nombreCompletoPaciente;
	}

	public void setNombreCompletoPaciente(String nombreCompletoPaciente) {
		this.nombreCompletoPaciente = nombreCompletoPaciente;
	}

	public String getNombreDoctor() {
		return this.nombreDoctor;
	}

	public void setNombreDoctor(String nombreDoctor) {
		this.nombreDoctor = nombreDoctor;
	}

	public String getNombreEstudio() {
		return this.nombreEstudio;
	}

	public void setNombreEstudio(String nombreEstudio) {
		this.nombreEstudio = nombreEstudio;
	}

	public String getNombrePaciente() {
		return this.nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public long getNumeroDoctor() {
		return this.numeroDoctor;
	}

	public void setNumeroDoctor(long numeroDoctor) {
		this.numeroDoctor = numeroDoctor;
	}

	public long getNumeroEstudio() {
		return this.numeroEstudio;
	}

	public void setNumeroEstudio(long numeroEstudio) {
		this.numeroEstudio = numeroEstudio;
	}

	public long getNumeroOrden() {
		return this.numeroOrden;
	}

	public void setNumeroOrden(long numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public long getNumeroPaciente() {
		return this.numeroPaciente;
	}

	public void setNumeroPaciente(long numeroPaciente) {
		this.numeroPaciente = numeroPaciente;
	}

	public long getNumeroUbicacion() {
		return this.numeroUbicacion;
	}

	public void setNumeroUbicacion(long numeroUbicacion) {
		this.numeroUbicacion = numeroUbicacion;
	}

	public boolean getRequiereFactura() {
		return this.requiereFactura;
	}

	public void setRequiereFactura(boolean requiereFactura) {
		this.requiereFactura = requiereFactura;
	}

	public short getTipoOrden() {
		return this.tipoOrden;
	}

	public void setTipoOrden(short tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public short getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(short tipoPago) {
		this.tipoPago = tipoPago;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getObservPago() {
		return observPago;
	}

	public void setObservPago(String observPago) {
		this.observPago = observPago;
	}

	public String getObservDesc() {
		return observDesc;
	}

	public void setObservDesc(String observDesc) {
		this.observDesc = observDesc;
	}

	public String getEstatusPago() {
		return estatusPago;
	}

	public void setEstatusPago(String estatusPago) {
		this.estatusPago = estatusPago;
	}

	public String getInfoFactura() {
		return infoFactura;
	}

	public void setInfoFactura(String infoFactura) {
		this.infoFactura = infoFactura;
	}

}
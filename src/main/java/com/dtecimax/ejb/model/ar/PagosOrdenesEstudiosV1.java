package com.dtecimax.ejb.model.ar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean  
@ViewScoped
public class PagosOrdenesEstudiosV1 implements Serializable {

	private static final long serialVersionUID = -8699375420877101085L;

	private String apellidoMaternoPaciente;
	private String apellidoPaternoPaciente;
	private Date castFechaCreacion;
	private int costoEstudio;
	private String decodeRequiereFactura;
	private String decodeTipoOrden;
	private String decodeTipoPago;
	private Date fechaCreacion;
	private String nombreCompletoDoctor;
	private String nombreCompletoPaciente;
	private String nombreDoctor;
	private String nombreEstudio;
	private String nombrePaciente;
	private long numeroDoctor;
	private long numeroEstudio;
	private long numeroOrden;
	private long numeroPaciente;
	private long numeroUbicacion;
	private boolean requiereFactura;
	private short tipoOrden;
	private short tipoPago;
	private BigDecimal descuento; 
	private BigDecimal total; 
	private String observDesc; 
	private String observPago; 
	private String infoFactura;
	

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

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
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

	public String getObservDesc() {
		return observDesc;
	}

	public void setObservDesc(String observDesc) {
		this.observDesc = observDesc;
	}

	public String getObservPago() {
		return observPago;
	}

	public void setObservPago(String observPago) {
		this.observPago = observPago;
	}

	public String getInfoFactura() {
		return infoFactura;
	}

	public void setInfoFactura(String infoFactura) {
		this.infoFactura = infoFactura;
	}

	
}

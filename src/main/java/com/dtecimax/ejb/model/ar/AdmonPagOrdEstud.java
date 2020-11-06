package com.dtecimax.ejb.model.ar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** Administracion Pagos Ordenes Estudios **/
public class AdmonPagOrdEstud implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String paciente; 
	private String estudioDesc; 
	private BigDecimal costoTotal; 
	private String tipoDePagoDesc; 
    private String observacionPago; 
    private String requireFacturaDesc; 
    private String infoFactura; 
    private Date fechaCreacion;
    
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getEstudioDesc() {
		return estudioDesc;
	}
	public void setEstudioDesc(String estudioDesc) {
		this.estudioDesc = estudioDesc;
	}
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}
	public String getTipoDePagoDesc() {
		return tipoDePagoDesc;
	}
	public void setTipoDePagoDesc(String tipoDePagoDesc) {
		this.tipoDePagoDesc = tipoDePagoDesc;
	}
	public String getObservacionPago() {
		return observacionPago;
	}
	public void setObservacionPago(String observacionPago) {
		this.observacionPago = observacionPago;
	}
	public String getRequireFacturaDesc() {
		return requireFacturaDesc;
	}
	public void setRequireFacturaDesc(String requireFacturaDesc) {
		this.requireFacturaDesc = requireFacturaDesc;
	}
	public String getInfoFactura() {
		return infoFactura;
	}
	public void setInfoFactura(String infoFactura) {
		this.infoFactura = infoFactura;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	} 
	
}

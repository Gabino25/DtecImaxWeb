package com.dtecimax.jpa.dto.as;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;


/**
 * The persistent class for the ORDENES_ESTUDIOS database table.
 * 
 */
@Entity
@Table(name="ORDENES_ESTUDIOS")
@NamedQueries({@NamedQuery(name="OrdenesEstudiosDto.findAllSimples", query="SELECT o FROM OrdenesEstudiosDto o where o.tipoOrden = 1")
              ,@NamedQuery(name="OrdenesEstudiosDto.findAllDesc", query="SELECT o FROM OrdenesEstudiosDto o where o.tipoOrden = 1")
              ,@NamedQuery(name="OrdenesEstudiosDto.findAllEspeciales", query="SELECT o FROM OrdenesEstudiosDto o where o.tipoOrden = 2")
              ,@NamedQuery(name="OrdenesEstudiosDto.findAllContrastados", query="SELECT o FROM OrdenesEstudiosDto o where o.tipoOrden = 3")
              ,@NamedQuery(name="OrdenesEstudiosDto.findAllDentales", query="SELECT o FROM OrdenesEstudiosDto o where o.tipoOrden = 4")
              })


public class OrdenesEstudiosDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUMERO_ORDEN")
	private long numeroOrden;

	@Column(name="ESTATUS")
	private String estatus;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	@Column(name="FECHA_ULTIMA_ACTUALIZACION")
	private Timestamp fechaUltimaActualizacion;

	@Column(name="INDICACIONES_DOCTOR")
	private String indicacionesDoctor;

	@Column(name="INFO_ADICIONAL")
	private String infoAdicional;

	@Column(name="NUMERO_ALERGIA")
	private String numeroAlergia;

	@Column(name="NUMERO_CITA")
	private long numeroCita;

	@Column(name="NUMERO_DOCTOR")
	private long numeroDoctor;
	
	@Column(name="NUMERO_DOCTOR_REFERENTE")
	private long numeroDoctorReferente;


	@Column(name="NUMERO_ESTUDIO")
	private long numeroEstudio;

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

	@Column(name="UBICACION_PACIENTE")
	private short ubicacionPaciente;

	@Column(name="USUARIO_ULTIMA_ACTUALIZACION")
	private long usuarioUltimaActualizacion;
	
	@Column(name="HORA_FINAL_ORDEN")
	private Time horaFinalOrden;

	@Column(name="HORA_INICIAL_ORDEN")
	private Time horaInicialOrden;
	
	@Column(name="FORMATO")
	private String formato; 
	
	@Column(name="FORMATO_TEXT")
	private String formatoText; 

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
	
	public OrdenesEstudiosDto() {
	}
	
	/************************************************************************/
	/***********************************************************************/
	
	/************************************************************************
	 ************************************************************************/
	

	public long getNumeroOrden() {
		return this.numeroOrden;
	}

	public void setNumeroOrden(long numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaUltimaActualizacion() {
		return this.fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public String getIndicacionesDoctor() {
		return this.indicacionesDoctor;
	}

	public void setIndicacionesDoctor(String indicacionesDoctor) {
		this.indicacionesDoctor = indicacionesDoctor;
	}

	public String getInfoAdicional() {
		return this.infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
		public String getNumeroAlergia() {
		return this.numeroAlergia;
	}

	public void setNumeroAlergia(String numeroAlergia) {
		this.numeroAlergia = numeroAlergia;
	}

	public long getNumeroCita() {
		return this.numeroCita;
	}

	public void setNumeroCita(long numeroCita) {
		this.numeroCita = numeroCita;
	}

	public long getNumeroDoctor() {
		return this.numeroDoctor;
	}

	public void setNumeroDoctor(long numeroDoctor) {
		this.numeroDoctor = numeroDoctor;
	}
	public long getNumeroDoctorReferente() {
		return this.numeroDoctorReferente;
	}

	public void setNumeroDoctorReferente(long numeroDoctorReferente) {
		this.numeroDoctorReferente = numeroDoctorReferente;
	}
	public long getNumeroEstudio() {
		return this.numeroEstudio;
	}

	public void setNumeroEstudio(long numeroEstudio) {
		this.numeroEstudio = numeroEstudio;
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

	public short getUbicacionPaciente() {
		return this.ubicacionPaciente;
	}

	public void setUbicacionPaciente(short ubicacionPaciente) {
		this.ubicacionPaciente = ubicacionPaciente;
	}

	public long getUsuarioUltimaActualizacion() {
		return this.usuarioUltimaActualizacion;
	}

	public void setUsuarioUltimaActualizacion(long usuarioUltimaActualizacion) {
		this.usuarioUltimaActualizacion = usuarioUltimaActualizacion;
	}
	
	public Time getHoraFinalOrden() {
		return this.horaFinalOrden;
	}

	public void setHoraFinalOrden(Time horaFinalOrden) {
		this.horaFinalOrden = horaFinalOrden;
	}

	public Time getHoraInicialOrden() {
		return this.horaInicialOrden;
	}

	public void setHoraInicialOrden(Time horaInicialOrden) {
		this.horaInicialOrden = horaInicialOrden;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getFormatoText() {
		return formatoText;
	}

	public void setFormatoText(String formatoText) {
		this.formatoText = formatoText;
	}

	public String getEstatusPago() {
		return estatusPago;
	}

	public void setEstatusPago(String estatusPago) {
		this.estatusPago = estatusPago;
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

	public String getInfoFactura() {
		return infoFactura;
	}

	public void setInfoFactura(String infoFactura) {
		this.infoFactura = infoFactura;
	}

	

}
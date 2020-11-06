package com.dtecimax.ejb.backing.admin.as;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.dtecimax.ejb.services.ar.PacientesLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosVLocal;
import com.dtecimax.jpa.dto.ar.PacientesDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;
import com.dtecimax.utils.Utilitarios;
import com.dtecimax.utils.Utils;

@ManagedBean  
@ViewScoped
public class Formato2Form {

	private String nombreCompletoPaciente;
	private Date fechaDeNacimiento; 
	private String fechaDeNacimientoddMMyyyy; 
	private int anios;
	private Date currentDate=new Date(); 
	private String telefono; 
	private String tipoDePago;
	private String factura; 
	private String estudio; 
	
	@Inject
	OrdenesEstudiosVLocal ordenesEstudiosVLocal; 
	
	@Inject 
	PacientesLocal pacientesLocal; 
	
	@PostConstruct
    public void init() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        Long longNumeroOrden =  (Long)sessionMap.get("svNumeroOrden"); 
        System.out.println("longNumeroOrden:");
        System.out.println(longNumeroOrden);
        OrdenesEstudiosVDto ordenesEstudiosVDto = ordenesEstudiosVLocal.findByNumeroOrden(longNumeroOrden.longValue()); 
        nombreCompletoPaciente = ordenesEstudiosVDto.getNombrePaciente(); 
        PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente()); 
        fechaDeNacimiento = pacientesDto.getFechaNacimientoPaciente(); 
        fechaDeNacimientoddMMyyyy = Utils.getddMMyyyysdfInstanceV2().format(fechaDeNacimiento);
        int difMonths = Utilitarios.differenceInMonths(fechaDeNacimiento, currentDate);
        int intAniosBetween = difMonths/12;
        anios = intAniosBetween; 
       
        telefono = pacientesDto.getCelularPaciente(); 
        tipoDePago = ordenesEstudiosVDto.getDecodeTipoPago();
        factura = ordenesEstudiosVDto.getDecodeRequiereFactura(); 
        setEstudio(ordenesEstudiosVDto.getNombreEstudio());
    }
	
	public String getNombreCompletoPaciente() {
		return nombreCompletoPaciente;
	}
	public void setNombreCompletoPaciente(String nombreCompletoPaciente) {
		this.nombreCompletoPaciente = nombreCompletoPaciente;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public int getAnios() {
		return anios;
	}
	public void setAnios(int anios) {
		this.anios = anios;
	}

	public String getFechaDeNacimientoddMMyyyy() {
		return fechaDeNacimientoddMMyyyy;
	}

	public void setFechaDeNacimientoddMMyyyy(String fechaDeNacimientoddMMyyyy) {
		this.fechaDeNacimientoddMMyyyy = fechaDeNacimientoddMMyyyy;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	} 
	
}

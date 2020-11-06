package com.dtecimax.ejb.backing.as;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

import com.dtecimax.ejb.model.as.OrdenesEstudiosEspeciales;
import com.dtecimax.ejb.services.admin.UbicacionesLocal;
import com.dtecimax.ejb.services.ar.AlergiasPacientesLocal;
import com.dtecimax.ejb.services.ar.PacientesLocal;
import com.dtecimax.ejb.services.as.EstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosVLocal;
import com.dtecimax.ejb.services.email.SessionBeanLocal;
import com.dtecimax.ejb.services.hr.DoctoresLocal;
import com.dtecimax.ejb.services.hr.DoctoresReferentesLocal;
import com.dtecimax.jpa.dto.admin.UbicacionesDto;
import com.dtecimax.jpa.dto.ar.AlergiasPacientesDto;
import com.dtecimax.jpa.dto.ar.PacientesDto;
import com.dtecimax.jpa.dto.as.EstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;
import com.dtecimax.jpa.dto.hr.DoctoresDto;
import com.dtecimax.jpa.dto.hr.DoctoresReferentesDto;

@ManagedBean  
@ViewScoped
public class OrdenesEspecialesForm {
	@ManagedProperty(value="#{ordenesEstudiosEspeciales}") 
	private OrdenesEstudiosEspeciales ordenesEstudiosEspeciales;

	private String edad; 
	private Date fechaNacimiento;
	private long costoEstudio;
	
	private String toemail;
	private String subjectemail; 
	
	private OrdenesEstudiosEspeciales ordenesEstudiosEspecialesSelected = new OrdenesEstudiosEspeciales(); 
	private List<OrdenesEstudiosEspeciales> listOrdenesEstudiosEspeciales = new ArrayList<OrdenesEstudiosEspeciales>(); 
	private List<OrdenesEstudiosEspeciales> listOrdEstEspeEnAten = new ArrayList<OrdenesEstudiosEspeciales>(); 
	private List<OrdenesEstudiosEspeciales> listOrdEstEspeEstRea = new ArrayList<OrdenesEstudiosEspeciales>(); 
	
	private List<SelectItem> alergiasPacientesItems; 
	private String[] selectedAlergiasPacientes;
	
	public String[] getSelectedAlergiasPacientes() {
		return selectedAlergiasPacientes;
	}
	
	public void setSelectedAlergiasPacientes(String[] selectedAlergiasPacientes) {
		this.selectedAlergiasPacientes = selectedAlergiasPacientes;
	}
	
	@Inject
	PacientesLocal pacientesLocal;
	
	@Inject
	DoctoresLocal doctoresLocal;
	@Inject
	DoctoresReferentesLocal doctoresReferentesLocal;
	
	@Inject
	UbicacionesLocal ubicacionesLocal; 
	
	@Inject 
	OrdenesEstudiosLocal ordenesEstudiosLocal; 
	
	@Inject 
	OrdenesEstudiosVLocal ordenesEstudiosVLocal;
	
	@Inject 
	AlergiasPacientesLocal alergiasPacientesLocal; 
	
	@Inject 
	EstudiosLocal estudiosLocal; 
	
	@Inject 
	SessionBeanLocal sessionBeanLocal; 
	
	private String searchNumEstu; 
	private String searchNomPaci; 
	private String searchNomDoct; 
	
	private String localFormato; 
	
	 @PostConstruct
	 public void init() {
		 alergiasPacientesItems = new ArrayList<SelectItem>();
		 List<AlergiasPacientesDto> listAlergiasPacientes = alergiasPacientesLocal.findSelectItems();
		 Iterator<AlergiasPacientesDto> iterAlergiasPacientes = listAlergiasPacientes.iterator();
		 while(iterAlergiasPacientes.hasNext()) {
			 AlergiasPacientesDto  alergiasPacientesDto = iterAlergiasPacientes.next();
			 SelectItem selectItem = new SelectItem(alergiasPacientesDto.getNumeroAlergia(),alergiasPacientesDto.getNombreAlergia());
			 alergiasPacientesItems.add(selectItem);
		 }
		 refreshEntity();
	 }
	
	public OrdenesEstudiosEspeciales getOrdenesEstudiosEspeciales() {
		return ordenesEstudiosEspeciales;
	}

	public void setOrdenesEstudiosEspeciales(OrdenesEstudiosEspeciales ordenesEstudiosEspeciales) {
		this.ordenesEstudiosEspeciales = ordenesEstudiosEspeciales;
	} 
	
	public void handlePacienteChange() {
		PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosEspeciales.getNumeroPaciente());
		if(null!=pacientesDto) {
		if(null!=pacientesDto.getFechaNacimientoPaciente()) {
		fechaNacimiento = new Date(pacientesDto.getFechaNacimientoPaciente().getTime());
		int intMonthsBetween =  differenceInMonths(fechaNacimiento,new Date());
		int intAniosBetween = intMonthsBetween/12;
		edad = intAniosBetween+" anios";
		 }
		}
	}
	
	public void handlePacienteChangeUpd() {
		PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosEspecialesSelected.getNumeroPaciente());
		if(null!=pacientesDto) {
		if(null!=pacientesDto.getFechaNacimientoPaciente()) {
		ordenesEstudiosEspecialesSelected.setFechaNacimientoPaciente(pacientesDto.getFechaNacimientoPaciente());	
		int intMonthsBetween =  differenceInMonths(pacientesDto.getFechaNacimientoPaciente(),new Date());
		int intAniosBetween = intMonthsBetween/12;
		ordenesEstudiosEspecialesSelected.setEdad(intAniosBetween+" anios");
		 }
		}
	}

	public void insertaOrdenSimple() {
	
	  boolean loggedIn = false;
		
	  OrdenesEstudiosDto ordenesEstudiosDto = new OrdenesEstudiosDto();
	  
	  String strselectedAlergiasPacientes = ""; 
	  if(null!=selectedAlergiasPacientes) {
		  for(int i=0;i<selectedAlergiasPacientes.length;i++) {
			  strselectedAlergiasPacientes = strselectedAlergiasPacientes+selectedAlergiasPacientes[i]+",";
		  }
		  strselectedAlergiasPacientes = strselectedAlergiasPacientes.substring(0, strselectedAlergiasPacientes.length()-1);
	  }
	  
	  ordenesEstudiosDto.setNumeroUbicacion(ordenesEstudiosEspeciales.getNumeroUbicacion());
	  ordenesEstudiosDto.setNumeroDoctor(ordenesEstudiosEspeciales.getNumeroDoctor());
	  ordenesEstudiosDto.setNumeroDoctorReferente(ordenesEstudiosEspeciales.getNumeroDoctorReferente());
	  ordenesEstudiosDto.setNumeroPaciente(ordenesEstudiosEspeciales.getNumeroPaciente());
	  ordenesEstudiosDto.setTipoOrden((short)2/*Especial*/);
	  ordenesEstudiosDto.setNumeroEstudio(ordenesEstudiosEspeciales.getNumeroEstudio()/* (long)2 Estudio Dummy*/);
	  ordenesEstudiosDto.setNumeroAlergia(strselectedAlergiasPacientes/**Alergia Dummy**/);
	  ordenesEstudiosDto.setRequiereFactura(ordenesEstudiosEspeciales.getRequiereFactura());
	  ordenesEstudiosDto.setTipoPago(ordenesEstudiosEspeciales.getTipoPago());
	  ordenesEstudiosDto.setEstatus(ordenesEstudiosEspeciales.getEstatus());
	  ordenesEstudiosDto.setFechaCreacion(ordenesEstudiosEspeciales.getFechaCreacion());
	  ordenesEstudiosDto.setUsuarioUltimaActualizacion(ordenesEstudiosEspeciales.getUsuarioUltimaActualizacion());
	  ordenesEstudiosDto.setFechaUltimaActualizacion(ordenesEstudiosEspeciales.getFechaUltimaActualizacion());
	  ordenesEstudiosDto.setIndicacionesDoctor(ordenesEstudiosEspeciales.getIndicacionesDoctor());
	  ordenesEstudiosDto.setInfoAdicional(ordenesEstudiosEspeciales.getInfoAdicional());
	 
	  ordenesEstudiosDto.setHoraInicialOrden(ordenesEstudiosEspeciales.getHoraInicialOrden());
	  ordenesEstudiosDto.setHoraFinalOrden(ordenesEstudiosEspeciales.getHoraFinalOrden());
	  ordenesEstudiosDto.setEstatus("PENDIENTE");
	  
	  ordenesEstudiosLocal.insertOrdenesEstudios(ordenesEstudiosDto);
	  refreshEntity();
	  loggedIn = true;
	  PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		
	}
	
	private void refreshEntity() {
		listOrdenesEstudiosEspeciales = new ArrayList<OrdenesEstudiosEspeciales>(); 
		
		List<OrdenesEstudiosVDto> listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findByEstaus("PENDIENTE", 2/*OrdenesEspeciales*/);
		Iterator<OrdenesEstudiosVDto> iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
		while(iterOrdenesEstudiosVDto.hasNext()) {
			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
			OrdenesEstudiosEspeciales ordenesEstudiosEspeciales = new OrdenesEstudiosEspeciales();
			ordenesEstudiosEspeciales.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
			ordenesEstudiosEspeciales.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosEspeciales.setFechaNacimientoPaciente(ordenesEstudiosVDto.getFechaNacimientoPaciente());
			ordenesEstudiosEspeciales.setNombreDoctor(ordenesEstudiosVDto.getNombreDoctor());
			ordenesEstudiosEspeciales.setApellidoPaternoDoctor(ordenesEstudiosVDto.getApellidoPaternoDoctor());
			ordenesEstudiosEspeciales.setApellidoMaternoDoctor(ordenesEstudiosVDto.getApellidoMaternoDoctor());
			ordenesEstudiosEspeciales.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente());
			ordenesEstudiosEspeciales.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente());
			ordenesEstudiosEspeciales.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente());
			ordenesEstudiosEspeciales.setNombreUbicacion(ordenesEstudiosVDto.getNombreUbicacion());
			ordenesEstudiosEspeciales.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
			ordenesEstudiosEspeciales.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
			ordenesEstudiosEspeciales.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
			ordenesEstudiosEspeciales.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
			ordenesEstudiosEspeciales.setNumeroDoctor(ordenesEstudiosVDto.getNumeroDoctor());
			ordenesEstudiosEspeciales.setRequiereFactura(ordenesEstudiosVDto.getRequiereFactura());
			ordenesEstudiosEspeciales.setInfoAdicional(ordenesEstudiosVDto.getInfoAdicional());
			ordenesEstudiosEspeciales.setTipoPago(ordenesEstudiosVDto.getTipoPago());
			ordenesEstudiosEspeciales.setEstatus(ordenesEstudiosVDto.getEstatus());
			ordenesEstudiosEspeciales.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
			ordenesEstudiosEspeciales.setUsuarioUltimaActualizacion(ordenesEstudiosVDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosEspeciales.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
			ordenesEstudiosEspeciales.setIndicacionesDoctor(ordenesEstudiosVDto.getIndicacionesDoctor());
			ordenesEstudiosEspeciales.setNumeroAlergia(ordenesEstudiosVDto.getNumeroAlergia());
			ordenesEstudiosEspeciales.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
			ordenesEstudiosEspeciales.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
			ordenesEstudiosEspeciales.setDecodeEstatus(ordenesEstudiosVDto.getDecodeEstatus());
			listOrdenesEstudiosEspeciales.add(ordenesEstudiosEspeciales);
	
		}
	
		
        this.listOrdEstEspeEnAten = new ArrayList<OrdenesEstudiosEspeciales>(); 
		
		listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findByEstaus("EN_ATENCION", 2/*OrdenesEspeciales*/);
		iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
		while(iterOrdenesEstudiosVDto.hasNext()) {
			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
			OrdenesEstudiosEspeciales ordenesEstudiosEspeciales = new OrdenesEstudiosEspeciales();
			ordenesEstudiosEspeciales.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
			ordenesEstudiosEspeciales.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosEspeciales.setFechaNacimientoPaciente(ordenesEstudiosVDto.getFechaNacimientoPaciente());
			ordenesEstudiosEspeciales.setNombreDoctor(ordenesEstudiosVDto.getNombreDoctor());
			ordenesEstudiosEspeciales.setApellidoPaternoDoctor(ordenesEstudiosVDto.getApellidoPaternoDoctor());
			ordenesEstudiosEspeciales.setApellidoMaternoDoctor(ordenesEstudiosVDto.getApellidoMaternoDoctor());
			ordenesEstudiosEspeciales.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente());
			ordenesEstudiosEspeciales.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente());
			ordenesEstudiosEspeciales.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente());
		    ordenesEstudiosEspeciales.setNombreUbicacion(ordenesEstudiosVDto.getNombreUbicacion());
			ordenesEstudiosEspeciales.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
			ordenesEstudiosEspeciales.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
			ordenesEstudiosEspeciales.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
			ordenesEstudiosEspeciales.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
			ordenesEstudiosEspeciales.setNumeroDoctor(ordenesEstudiosVDto.getNumeroDoctor());
			ordenesEstudiosEspeciales.setRequiereFactura(ordenesEstudiosVDto.getRequiereFactura());
			ordenesEstudiosEspeciales.setInfoAdicional(ordenesEstudiosVDto.getInfoAdicional());
			ordenesEstudiosEspeciales.setTipoPago(ordenesEstudiosVDto.getTipoPago());
			ordenesEstudiosEspeciales.setEstatus(ordenesEstudiosVDto.getEstatus());
			ordenesEstudiosEspeciales.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
			ordenesEstudiosEspeciales.setUsuarioUltimaActualizacion(ordenesEstudiosVDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosEspeciales.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
			ordenesEstudiosEspeciales.setIndicacionesDoctor(ordenesEstudiosVDto.getIndicacionesDoctor());
			ordenesEstudiosEspeciales.setNumeroAlergia(ordenesEstudiosVDto.getNumeroAlergia());
			ordenesEstudiosEspeciales.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
			ordenesEstudiosEspeciales.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
			ordenesEstudiosEspeciales.setDecodeEstatus(ordenesEstudiosVDto.getDecodeEstatus());
			this.listOrdEstEspeEnAten.add(ordenesEstudiosEspeciales);
		}
		
	       this.listOrdEstEspeEstRea = new ArrayList<OrdenesEstudiosEspeciales>(); 
		
			listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findByEstaus("ESTUDIO_REALIZADO", 2/*OrdenesEspeciales*/);
			iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
			while(iterOrdenesEstudiosVDto.hasNext()) {
				OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
				OrdenesEstudiosEspeciales ordenesEstudiosEspeciales = new OrdenesEstudiosEspeciales();
				ordenesEstudiosEspeciales.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
				ordenesEstudiosEspeciales.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
				ordenesEstudiosEspeciales.setFechaNacimientoPaciente(ordenesEstudiosVDto.getFechaNacimientoPaciente());
				ordenesEstudiosEspeciales.setNombreDoctor(ordenesEstudiosVDto.getNombreDoctor());
				ordenesEstudiosEspeciales.setApellidoPaternoDoctor(ordenesEstudiosVDto.getApellidoPaternoDoctor());
				ordenesEstudiosEspeciales.setApellidoMaternoDoctor(ordenesEstudiosVDto.getApellidoMaternoDoctor());
				ordenesEstudiosEspeciales.setNombreUbicacion(ordenesEstudiosVDto.getNombreUbicacion());
				ordenesEstudiosEspeciales.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente());
				ordenesEstudiosEspeciales.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente());
				ordenesEstudiosEspeciales.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente());
				ordenesEstudiosEspeciales.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
				ordenesEstudiosEspeciales.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
				ordenesEstudiosEspeciales.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
				ordenesEstudiosEspeciales.setNumeroDoctor(ordenesEstudiosVDto.getNumeroDoctor());
				ordenesEstudiosEspeciales.setRequiereFactura(ordenesEstudiosVDto.getRequiereFactura());
				ordenesEstudiosEspeciales.setInfoAdicional(ordenesEstudiosVDto.getInfoAdicional());
				ordenesEstudiosEspeciales.setTipoPago(ordenesEstudiosVDto.getTipoPago());
				ordenesEstudiosEspeciales.setEstatus(ordenesEstudiosVDto.getEstatus());
				ordenesEstudiosEspeciales.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
				ordenesEstudiosEspeciales.setUsuarioUltimaActualizacion(ordenesEstudiosVDto.getUsuarioUltimaActualizacion());
				ordenesEstudiosEspeciales.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
				ordenesEstudiosEspeciales.setIndicacionesDoctor(ordenesEstudiosVDto.getIndicacionesDoctor());
				ordenesEstudiosEspeciales.setNumeroAlergia(ordenesEstudiosVDto.getNumeroAlergia());
				ordenesEstudiosEspeciales.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
				ordenesEstudiosEspeciales.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
				ordenesEstudiosEspeciales.setDecodeEstatus(ordenesEstudiosVDto.getDecodeEstatus());
				this.listOrdEstEspeEstRea.add(ordenesEstudiosEspeciales);
			}
		
		/****************************************************************************************
		List<OrdenesEstudiosDto> listOrdenesEstudiosDto = ordenesEstudiosLocal.findAllEspeciales();
		Iterator<OrdenesEstudiosDto> iterOrdenesEstudiosDto = listOrdenesEstudiosDto.iterator();
		while(iterOrdenesEstudiosDto.hasNext()) {
			OrdenesEstudiosDto ordenesEstudiosDto = iterOrdenesEstudiosDto.next();
			PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
			DoctoresDto doctoresDto = doctoresLocal.findByNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
			UbicacionesDto ubicacionesDto = ubicacionesLocal.findByNumeroUbicacion(ordenesEstudiosDto.getNumeroUbicacion());
			EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
			OrdenesEstudiosEspeciales ordenesEstudiosEspeciales = new OrdenesEstudiosEspeciales();
			ordenesEstudiosEspeciales.setNumeroOrden(ordenesEstudiosDto.getNumeroOrden());
			ordenesEstudiosEspeciales.setNombrePaciente(pacientesDto.getNombrePaciente());
			ordenesEstudiosEspeciales.setFechaNacimientoPaciente(pacientesDto.getFechaNacimientoPaciente());
			ordenesEstudiosEspeciales.setNombreDoctor(doctoresDto.getNombreDoctor());
			ordenesEstudiosEspeciales.setNombreUbicacion(ubicacionesDto.getNombreUbicacion());
			ordenesEstudiosEspeciales.setNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
			ordenesEstudiosEspeciales.setCostoEstudio(estudiosDto.getCostoEstudio());
			ordenesEstudiosEspeciales.setNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
			ordenesEstudiosEspeciales.setNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
			ordenesEstudiosEspeciales.setRequiereFactura(ordenesEstudiosDto.getRequiereFactura());
			ordenesEstudiosEspeciales.setInfoAdicional(ordenesEstudiosDto.getInfoAdicional());
			ordenesEstudiosEspeciales.setTipoPago(ordenesEstudiosDto.getTipoPago());
			ordenesEstudiosEspeciales.setEstatus(ordenesEstudiosDto.getEstatus());
			ordenesEstudiosEspeciales.setFechaCreacion(ordenesEstudiosDto.getFechaCreacion());
			ordenesEstudiosEspeciales.setUsuarioUltimaActualizacion(ordenesEstudiosDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosEspeciales.setFechaUltimaActualizacion(ordenesEstudiosDto.getFechaUltimaActualizacion());
			ordenesEstudiosEspeciales.setIndicacionesDoctor(ordenesEstudiosDto.getIndicacionesDoctor());
			ordenesEstudiosEspeciales.setNumeroAlergia(ordenesEstudiosDto.getNumeroAlergia());
			ordenesEstudiosEspeciales.setHoraInicialOrden(ordenesEstudiosDto.getHoraInicialOrden());
			ordenesEstudiosEspeciales.setHoraFinalOrden(ordenesEstudiosDto.getHoraFinalOrden());
			listOrdenesEstudiosEspeciales.add(ordenesEstudiosEspeciales);
		}
		******************************************************************************************/
	}
	
	public void selectForUpdate(OrdenesEstudiosEspeciales pOrdenesEstudiosEspeciales) {
		
		 ordenesEstudiosEspecialesSelected = new OrdenesEstudiosEspeciales();
		
		 ordenesEstudiosEspecialesSelected.setNumeroOrden(pOrdenesEstudiosEspeciales.getNumeroOrden());
		 ordenesEstudiosEspecialesSelected.setNumeroUbicacion(pOrdenesEstudiosEspeciales.getNumeroUbicacion());
		 ordenesEstudiosEspecialesSelected.setNumeroDoctor(pOrdenesEstudiosEspeciales.getNumeroDoctor());
		 ordenesEstudiosEspecialesSelected.setNumeroDoctorReferente(pOrdenesEstudiosEspeciales.getNumeroDoctorReferente());
		 ordenesEstudiosEspecialesSelected.setNumeroPaciente(pOrdenesEstudiosEspeciales.getNumeroPaciente());
		 ordenesEstudiosEspecialesSelected.setTipoOrden(pOrdenesEstudiosEspeciales.getTipoOrden());
		 ordenesEstudiosEspecialesSelected.setNumeroEstudio(pOrdenesEstudiosEspeciales.getNumeroEstudio());
		 ordenesEstudiosEspecialesSelected.setNumeroAlergia(pOrdenesEstudiosEspeciales.getNumeroAlergia());
		 ordenesEstudiosEspecialesSelected.setRequiereFactura(pOrdenesEstudiosEspeciales.getRequiereFactura());
		 ordenesEstudiosEspecialesSelected.setTipoPago(pOrdenesEstudiosEspeciales.getTipoPago());
		 ordenesEstudiosEspecialesSelected.setEstatus(pOrdenesEstudiosEspeciales.getEstatus());
		 ordenesEstudiosEspecialesSelected.setFechaCreacion(pOrdenesEstudiosEspeciales.getFechaCreacion());
		 ordenesEstudiosEspecialesSelected.setUsuarioUltimaActualizacion(pOrdenesEstudiosEspeciales.getUsuarioUltimaActualizacion());
		 ordenesEstudiosEspecialesSelected.setFechaUltimaActualizacion(pOrdenesEstudiosEspeciales.getFechaUltimaActualizacion());
		 ordenesEstudiosEspecialesSelected.setIndicacionesDoctor(pOrdenesEstudiosEspeciales.getIndicacionesDoctor());
		 ordenesEstudiosEspecialesSelected.setInfoAdicional(pOrdenesEstudiosEspeciales.getInfoAdicional());
		
		 ordenesEstudiosEspecialesSelected.setFechaNacimientoPaciente(pOrdenesEstudiosEspeciales.getFechaNacimientoPaciente());
		 int intMonthsBetween =  differenceInMonths(new Date(pOrdenesEstudiosEspeciales.getFechaNacimientoPaciente().getTime()),new Date());
		 int intAniosBetween = intMonthsBetween/12;
		 ordenesEstudiosEspecialesSelected.setEdad(intAniosBetween+" anios");	
		 ordenesEstudiosEspecialesSelected.setCostoEstudio(pOrdenesEstudiosEspeciales.getCostoEstudio());
	     
		 ordenesEstudiosEspecialesSelected.setHoraInicialOrden(pOrdenesEstudiosEspeciales.getHoraInicialOrden()); 
		 ordenesEstudiosEspecialesSelected.setHoraFinalOrden(pOrdenesEstudiosEspeciales.getHoraFinalOrden());
		 if(null!=pOrdenesEstudiosEspeciales.getHoraInicialOrden()) {
			 ordenesEstudiosEspecialesSelected.setUtilHoraInicialOrden(new Date(pOrdenesEstudiosEspeciales.getHoraInicialOrden().getTime()));
		 }
		 if(null!=pOrdenesEstudiosEspeciales.getHoraFinalOrden()) {
			 ordenesEstudiosEspecialesSelected.setUtilHoraFinalOrden(new Date(pOrdenesEstudiosEspeciales.getHoraFinalOrden().getTime()));
		 }
		 ordenesEstudiosEspecialesSelected.setEstatus(pOrdenesEstudiosEspeciales.getEstatus());
	}
	
   public void selectForDelete(OrdenesEstudiosEspeciales pOrdenesEstudiosEspeciales) {
		
		 ordenesEstudiosEspecialesSelected = new OrdenesEstudiosEspeciales();
		
		 ordenesEstudiosEspecialesSelected.setNumeroOrden(pOrdenesEstudiosEspeciales.getNumeroOrden());
		 ordenesEstudiosEspecialesSelected.setNumeroUbicacion(pOrdenesEstudiosEspeciales.getNumeroUbicacion());
		 ordenesEstudiosEspecialesSelected.setNumeroDoctor(pOrdenesEstudiosEspeciales.getNumeroDoctor());
		 ordenesEstudiosEspecialesSelected.setNumeroPaciente(pOrdenesEstudiosEspeciales.getNumeroPaciente());
		 ordenesEstudiosEspecialesSelected.setTipoOrden(pOrdenesEstudiosEspeciales.getTipoOrden());
		 ordenesEstudiosEspecialesSelected.setNumeroEstudio(pOrdenesEstudiosEspeciales.getNumeroEstudio());
		 ordenesEstudiosEspecialesSelected.setNumeroAlergia(pOrdenesEstudiosEspeciales.getNumeroAlergia());
		 ordenesEstudiosEspecialesSelected.setRequiereFactura(pOrdenesEstudiosEspeciales.getRequiereFactura());
		 ordenesEstudiosEspecialesSelected.setTipoPago(pOrdenesEstudiosEspeciales.getTipoPago());
		 ordenesEstudiosEspecialesSelected.setEstatus(pOrdenesEstudiosEspeciales.getEstatus());
		 ordenesEstudiosEspecialesSelected.setFechaCreacion(pOrdenesEstudiosEspeciales.getFechaCreacion());
		 ordenesEstudiosEspecialesSelected.setUsuarioUltimaActualizacion(pOrdenesEstudiosEspeciales.getUsuarioUltimaActualizacion());
		 ordenesEstudiosEspecialesSelected.setFechaUltimaActualizacion(pOrdenesEstudiosEspeciales.getFechaUltimaActualizacion());
		 ordenesEstudiosEspecialesSelected.setIndicacionesDoctor(pOrdenesEstudiosEspeciales.getIndicacionesDoctor());
		 ordenesEstudiosEspecialesSelected.setInfoAdicional(pOrdenesEstudiosEspeciales.getInfoAdicional());
		
	}
   
   public void selectForEmail(OrdenesEstudiosEspeciales pOrdenesEstudiosEspeciales) {
	   ordenesEstudiosEspecialesSelected = new OrdenesEstudiosEspeciales();
		
	   ordenesEstudiosEspecialesSelected.setNumeroOrden(pOrdenesEstudiosEspeciales.getNumeroOrden());
	   ordenesEstudiosEspecialesSelected.setIndicacionesDoctor(pOrdenesEstudiosEspeciales.getIndicacionesDoctor());
	   ordenesEstudiosEspecialesSelected.setInfoAdicional(pOrdenesEstudiosEspeciales.getInfoAdicional());
		
   }

    public void actualizaOrdenEspecial() {
    	
    	boolean updatedIn = false; 
    	OrdenesEstudiosDto ordenesEstudiosDto = new OrdenesEstudiosDto();
    	
     /**	
      System.out.print("ordenesEstudiosEspecialesSelected.getSelectedAlergiasPacientes()");
  	  System.out.println(ordenesEstudiosEspecialesSelected.getSelectedAlergiasPacientes());
  	  String [] arraySelectedAlergiasPacientes = ordenesEstudiosEspecialesSelected.getSelectedAlergiasPacientes();
  	  String strselectedAlergiasPacientes = null; 
  	  if(null!=arraySelectedAlergiasPacientes) {
  		  System.out.println("Length:"+arraySelectedAlergiasPacientes.length);
  		strselectedAlergiasPacientes = "";
  		  for(int i=0;i<arraySelectedAlergiasPacientes.length;i++) {
  			  strselectedAlergiasPacientes = strselectedAlergiasPacientes+arraySelectedAlergiasPacientes[i]+",";
  		  }
  		  System.out.println(strselectedAlergiasPacientes);
  		  strselectedAlergiasPacientes = strselectedAlergiasPacientes.substring(0, strselectedAlergiasPacientes.length()-1);
  		  System.out.println(strselectedAlergiasPacientes);
  	  }
  	  **/
    	
    	ordenesEstudiosDto.setNumeroPaciente(ordenesEstudiosEspecialesSelected.getNumeroPaciente());
    	ordenesEstudiosDto.setNumeroDoctor(ordenesEstudiosEspecialesSelected.getNumeroDoctor());
    	ordenesEstudiosDto.setNumeroDoctorReferente(ordenesEstudiosEspecialesSelected.getNumeroDoctorReferente());
    	ordenesEstudiosDto.setIndicacionesDoctor(ordenesEstudiosEspecialesSelected.getIndicacionesDoctor());
    	ordenesEstudiosDto.setInfoAdicional(ordenesEstudiosEspecialesSelected.getInfoAdicional());
    	ordenesEstudiosDto.setNumeroAlergia(ordenesEstudiosEspecialesSelected.getNumeroAlergia());
    	ordenesEstudiosDto.setRequiereFactura(ordenesEstudiosEspecialesSelected.getRequiereFactura());
    	ordenesEstudiosDto.setNumeroEstudio(ordenesEstudiosEspecialesSelected.getNumeroEstudio());
    	ordenesEstudiosDto.setHoraInicialOrden(ordenesEstudiosEspecialesSelected.getHoraInicialOrden());
    	ordenesEstudiosDto.setHoraFinalOrden(ordenesEstudiosEspecialesSelected.getHoraFinalOrden());
    	ordenesEstudiosDto.setEstatus(ordenesEstudiosEspecialesSelected.getEstatus());
    	ordenesEstudiosLocal.updateOrdenesEstudios(ordenesEstudiosEspecialesSelected.getNumeroOrden(), ordenesEstudiosDto);
    	refreshEntity();
    	updatedIn = true;
    	PrimeFaces.current().ajax().addCallbackParam("updatedIn", updatedIn);
    }
   
    public void delete() {
    	ordenesEstudiosLocal.deleteOrdenesEstudios(ordenesEstudiosEspecialesSelected.getNumeroOrden());
    	refreshEntity();
    }
    
    public void handleEstudioChange() {
		EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosEspeciales.getNumeroEstudio());
		if(null!=estudiosDto) {
		setCostoEstudio(estudiosDto.getCostoEstudio());
		}
	}
    
    public void handleEstudioChangeUpd() {
		EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosEspecialesSelected.getNumeroEstudio());
		if(null!=estudiosDto) {
			ordenesEstudiosEspecialesSelected.setCostoEstudio(estudiosDto.getCostoEstudio());
		}
	}
    
    
    public void enviarIndicaciones() {
    	System.out.println("Comienza enviarIndicaciones");
    	sessionBeanLocal.sendEmail(this.toemail, this.subjectemail, this.ordenesEstudiosEspecialesSelected.getIndicacionesDoctor());
    	System.out.println("Finaliza enviarIndicaciones");
    }
    
    public void search() {
    	if((null!=this.searchNumEstu&&!"".equals(this.searchNumEstu))
    	  ||(null!=this.searchNomPaci&&!"".equals(this.searchNomPaci))
    	  ||(null!=this.searchNomDoct&&!"".equals(this.searchNomDoct))
    	  ) {
    	
    		listOrdenesEstudiosEspeciales = new ArrayList<OrdenesEstudiosEspeciales>(); 
    		List<OrdenesEstudiosDto> listOrdenesEstudiosDto = ordenesEstudiosLocal.findBySearch("2"
    				                                                                           ,this.searchNumEstu
    				                                                                           ,this.searchNomPaci
    				                                                                           ,this.searchNomDoct
    				                                                                           );
    		Iterator<OrdenesEstudiosDto> iterOrdenesEstudiosDto = listOrdenesEstudiosDto.iterator();
    		while(iterOrdenesEstudiosDto.hasNext()) {
    			OrdenesEstudiosDto ordenesEstudiosDto = iterOrdenesEstudiosDto.next();
    			PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
    			DoctoresDto doctoresDto = doctoresLocal.findByNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
    			DoctoresReferentesDto doctoresReferentesDto = doctoresReferentesLocal.findByNumeroDoctorReferente(ordenesEstudiosDto.getNumeroDoctorReferente());
    			UbicacionesDto ubicacionesDto = ubicacionesLocal.findByNumeroUbicacion(ordenesEstudiosDto.getNumeroUbicacion());
    			OrdenesEstudiosEspeciales ordenesEstudiosEspeciales = new OrdenesEstudiosEspeciales();
    			ordenesEstudiosEspeciales.setNumeroOrden(ordenesEstudiosDto.getNumeroOrden());
    			ordenesEstudiosEspeciales.setNombrePaciente(pacientesDto.getNombrePaciente());
    			ordenesEstudiosEspeciales.setFechaNacimientoPaciente(pacientesDto.getFechaNacimientoPaciente());
    			ordenesEstudiosEspeciales.setNombreDoctor(doctoresDto.getNombreDoctor());
    			ordenesEstudiosEspeciales.setNombreDoctorReferente(doctoresReferentesDto.getNombreDoctorReferente());
    			ordenesEstudiosEspeciales.setNombreUbicacion(ubicacionesDto.getNombreUbicacion());
    			
    			ordenesEstudiosEspeciales.setNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
    			EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
    			ordenesEstudiosEspeciales.setCostoEstudio(estudiosDto.getCostoEstudio());
    			
    			ordenesEstudiosEspeciales.setNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
    			ordenesEstudiosEspeciales.setNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
    			ordenesEstudiosEspeciales.setRequiereFactura(ordenesEstudiosDto.getRequiereFactura());
    			ordenesEstudiosEspeciales.setInfoAdicional(ordenesEstudiosDto.getInfoAdicional());
    			ordenesEstudiosEspeciales.setTipoPago(ordenesEstudiosDto.getTipoPago());
    			ordenesEstudiosEspeciales.setEstatus(ordenesEstudiosDto.getEstatus());
    			ordenesEstudiosEspeciales.setFechaCreacion(ordenesEstudiosDto.getFechaCreacion());
    			ordenesEstudiosEspeciales.setUsuarioUltimaActualizacion(ordenesEstudiosDto.getUsuarioUltimaActualizacion());
    			ordenesEstudiosEspeciales.setFechaUltimaActualizacion(ordenesEstudiosDto.getFechaUltimaActualizacion());
    			ordenesEstudiosEspeciales.setIndicacionesDoctor(ordenesEstudiosDto.getIndicacionesDoctor());
    			ordenesEstudiosEspeciales.setHoraInicialOrden(ordenesEstudiosDto.getHoraInicialOrden());
    			ordenesEstudiosEspeciales.setHoraFinalOrden(ordenesEstudiosDto.getHoraFinalOrden());
    			
    			listOrdenesEstudiosEspeciales.add(ordenesEstudiosEspeciales);
    		}
    		
    	}
    	
    }
    
    public String toFormatoRO(long pNumeroOrden) {
    	
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        sessionMap.remove("svNumeroOrden"); 
        sessionMap.put("svNumeroOrden", pNumeroOrden);
        
        OrdenesEstudiosDto ordenesEstudiosDto = ordenesEstudiosLocal.findByNumeroOrdenEstudio(pNumeroOrden); 
        String strRetval = ""; 
        if("Formato1".equals(ordenesEstudiosDto.getFormato())) {
        	strRetval =  "admin-Formato1RO";
        }else if("Formato2".equals(ordenesEstudiosDto.getFormato())) {
        	strRetval =  "admin-Formato2RO";
        }
    	 return strRetval;
    }
    
    public String executeRcFormato() {
    	System.out.println("Entra executeRcFormato");
    	System.out.println("localFormato:"+localFormato);
    	String strPage = ""; 
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        sessionMap.remove("svNumeroOrden"); 
        sessionMap.put("svNumeroOrden", ordenesEstudiosEspecialesSelected.getNumeroOrden());
    	if("1".equals(localFormato)) {
    		strPage = "admin-Formato1"; 
       }else if("2".equals(localFormato)) {
    	   strPage = "admin-Formato2"; 
       }
        System.out.println("Sale executeRcFormato");
    	return strPage; 
    }
    
	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	private  int differenceInMonths(Date d1, Date d2) {
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(d1);
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(d2);
	    int diff = 0;
	    if (c2.after(c1)) {
	        while (c2.after(c1)) {
	            c1.add(Calendar.MONTH, 1);
	            if (c2.after(c1)) {
	                diff++;
	            }
	        }
	    } else if (c2.before(c1)) {
	        while (c2.before(c1)) {
	            c1.add(Calendar.MONTH, -1);
	            if (c1.before(c2)) {
	                diff--;
	            }
	        }
	    }
	    return diff;
	}

	public List<OrdenesEstudiosEspeciales> getListOrdenesEstudiosEspeciales() {
		return listOrdenesEstudiosEspeciales;
	}

	public void setListOrdenesEstudiosEspeciales(List<OrdenesEstudiosEspeciales> listOrdenesEstudiosEspeciales) {
		this.listOrdenesEstudiosEspeciales = listOrdenesEstudiosEspeciales;
	}

	public OrdenesEstudiosEspeciales getOrdenesEstudiosEspecialesSelected() {
		return ordenesEstudiosEspecialesSelected;
	}

	public void setOrdenesEstudiosEspecialesSelected(OrdenesEstudiosEspeciales ordenesEstudiosEspecialesSelected) {
		this.ordenesEstudiosEspecialesSelected = ordenesEstudiosEspecialesSelected;
	}

	public List<SelectItem> getAlergiasPacientesItems() {
		return alergiasPacientesItems;
	}

	public void setAlergiasPacientesItems(List<SelectItem> alergiasPacientesItems) {
		this.alergiasPacientesItems = alergiasPacientesItems;
	}

	public long getCostoEstudio() {
		return costoEstudio;
	}

	public void setCostoEstudio(long costoEstudio) {
		this.costoEstudio = costoEstudio;
	}

	public String getToemail() {
		return toemail;
	}

	public void setToemail(String toemail) {
		this.toemail = toemail;
	}

	public String getSubjectemail() {
		return subjectemail;
	}

	public void setSubjectemail(String subjectemail) {
		this.subjectemail = subjectemail;
	}

	public String getSearchNumEstu() {
		return searchNumEstu;
	}

	public void setSearchNumEstu(String searchNumEstu) {
		this.searchNumEstu = searchNumEstu;
	}

	public String getSearchNomPaci() {
		return searchNomPaci;
	}

	public void setSearchNomPaci(String searchNomPaci) {
		this.searchNomPaci = searchNomPaci;
	}

	public String getSearchNomDoct() {
		return searchNomDoct;
	}

	public void setSearchNomDoct(String searchNomDoct) {
		this.searchNomDoct = searchNomDoct;
	}

	public String getLocalFormato() {
		return localFormato;
	}

	public void setLocalFormato(String localFormato) {
		this.localFormato = localFormato;
	}

	public List<OrdenesEstudiosEspeciales> getListOrdEstEspeEnAten() {
		return listOrdEstEspeEnAten;
	}

	public void setListOrdEstEspeEnAten(List<OrdenesEstudiosEspeciales> listOrdEstEspeEnAten) {
		this.listOrdEstEspeEnAten = listOrdEstEspeEnAten;
	}

	public List<OrdenesEstudiosEspeciales> getListOrdEstEspeEstRea() {
		return listOrdEstEspeEstRea;
	}

	public void setListOrdEstEspeEstRea(List<OrdenesEstudiosEspeciales> listOrdEstEspeEstRea) {
		this.listOrdEstEspeEstRea = listOrdEstEspeEstRea;
	}

	
}


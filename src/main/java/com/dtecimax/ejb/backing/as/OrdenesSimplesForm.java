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
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import com.dtecimax.ejb.model.as.OrdenesEstudiosSimples;
import com.dtecimax.ejb.services.admin.UbicacionesLocal;
import com.dtecimax.ejb.services.ar.PacientesLocal;
import com.dtecimax.ejb.services.as.EstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;
import com.dtecimax.ejb.services.as.OrdenesEstudiosVLocal;
import com.dtecimax.ejb.services.hr.DoctoresLocal;
import com.dtecimax.ejb.services.hr.DoctoresReferentesLocal;
import com.dtecimax.jpa.dto.admin.UbicacionesDto;
import com.dtecimax.jpa.dto.ar.PacientesDto;
import com.dtecimax.jpa.dto.as.EstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosDto;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;
import com.dtecimax.jpa.dto.hr.DoctoresDto;
import com.dtecimax.jpa.dto.hr.DoctoresReferentesDto;

@ManagedBean  
@ViewScoped
public class OrdenesSimplesForm {

	@ManagedProperty(value="#{ordenesEstudiosSimples}") 
	private OrdenesEstudiosSimples ordenesEstudiosSimples;

	private String edad; 
	private Date fechaNacimiento;
	private long costoEstudio;
	
	private OrdenesEstudiosSimples ordenesEstudiosSimplesSelected = new OrdenesEstudiosSimples(); 
	private List<OrdenesEstudiosSimples> listOrdenesEstudiosSimples = new ArrayList<OrdenesEstudiosSimples>(); 
	private List<OrdenesEstudiosSimples> listOrdEstSimpEnAten = new ArrayList<OrdenesEstudiosSimples>(); 
	private List<OrdenesEstudiosSimples> listOrdEstSimpEstRea = new ArrayList<OrdenesEstudiosSimples>(); 
	
	
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
	EstudiosLocal estudiosLocal;
	
	private String searchNumEstu; 
	private String searchNomPaci; 
	private String searchNomDoct; 
	
	private String localFormato; 
	
	@PostConstruct
	 public void init() {
		 refreshEntity();
	 }
	
	public OrdenesEstudiosSimples getOrdenesEstudiosSimples() {
		return ordenesEstudiosSimples;
	}

	public void setOrdenesEstudiosSimples(OrdenesEstudiosSimples ordenesEstudiosSimples) {
		this.ordenesEstudiosSimples = ordenesEstudiosSimples;
	} 
	
	public void handlePacienteChange() {
		PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosSimples.getNumeroPaciente());
		if(null!=pacientesDto) {
		if(null!=pacientesDto.getFechaNacimientoPaciente()) {
		fechaNacimiento = new Date(pacientesDto.getFechaNacimientoPaciente().getTime());
		int intMonthsBetween =  differenceInMonths(fechaNacimiento,new Date());
		int intAniosBetween = intMonthsBetween/12;
		this.setEdad(intAniosBetween+" anios");
		 }
		}
	}
	
	public void handlePacienteChangeUpd() {
		PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosSimplesSelected.getNumeroPaciente());
		if(null!=pacientesDto) {
		if(null!=pacientesDto.getFechaNacimientoPaciente()) {
		ordenesEstudiosSimplesSelected.setFechaNacimientoPaciente(pacientesDto.getFechaNacimientoPaciente());	
		int intMonthsBetween =  differenceInMonths(pacientesDto.getFechaNacimientoPaciente(),new Date());
		int intAniosBetween = intMonthsBetween/12;
		ordenesEstudiosSimplesSelected.setEdad(intAniosBetween+" anios");
		 }
		}
	}

	public void insertaOrdenSimple() {
	
	  boolean loggedIn = false;
		
	  OrdenesEstudiosDto ordenesEstudiosDto = new OrdenesEstudiosDto();
	  
	  ordenesEstudiosDto.setNumeroUbicacion(ordenesEstudiosSimples.getNumeroUbicacion());
	  ordenesEstudiosDto.setNumeroDoctor(ordenesEstudiosSimples.getNumeroDoctor());
	  ordenesEstudiosDto.setNumeroDoctorReferente(ordenesEstudiosSimples.getNumeroDoctorReferente());
	  ordenesEstudiosDto.setNumeroPaciente(ordenesEstudiosSimples.getNumeroPaciente());
	  ordenesEstudiosDto.setTipoOrden((short)1/*Simple*/);
	  ordenesEstudiosDto.setNumeroEstudio(ordenesEstudiosSimples.getNumeroEstudio()/*(long)2*/);
	  ordenesEstudiosDto.setNumeroAlergia(null/**Alergia Dummy**/);
	  ordenesEstudiosDto.setRequiereFactura(ordenesEstudiosSimples.getRequiereFactura());
	  ordenesEstudiosDto.setTipoPago(ordenesEstudiosSimples.getTipoPago());
	  ordenesEstudiosDto.setEstatus(ordenesEstudiosSimples.getEstatus());
	  ordenesEstudiosDto.setFechaCreacion(ordenesEstudiosSimples.getFechaCreacion());
	  ordenesEstudiosDto.setUsuarioUltimaActualizacion(ordenesEstudiosSimples.getUsuarioUltimaActualizacion());
	  ordenesEstudiosDto.setFechaUltimaActualizacion(ordenesEstudiosSimples.getFechaUltimaActualizacion());
	  ordenesEstudiosDto.setIndicacionesDoctor(ordenesEstudiosSimples.getIndicacionesDoctor());
	  ordenesEstudiosDto.setInfoAdicional(ordenesEstudiosSimples.getInfoAdicional());
	  ordenesEstudiosDto.setHoraInicialOrden(ordenesEstudiosSimples.getHoraInicialOrden());
	  ordenesEstudiosDto.setHoraFinalOrden(ordenesEstudiosSimples.getHoraFinalOrden());
	  ordenesEstudiosDto.setEstatus("PENDIENTE");
	  
	  ordenesEstudiosLocal.insertOrdenesEstudios(ordenesEstudiosDto);
	  refreshEntity();
	  loggedIn = true;
	  PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		
	}
	
	private void refreshEntity() {
		listOrdenesEstudiosSimples = new ArrayList<OrdenesEstudiosSimples>(); 
		List<OrdenesEstudiosVDto> listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findByEstaus("PENDIENTE",1/*OrdenesSimples*/);
		Iterator<OrdenesEstudiosVDto> iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
		while(iterOrdenesEstudiosVDto.hasNext()) {
			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
			OrdenesEstudiosSimples ordenesEstudiosSimples = new OrdenesEstudiosSimples();
			ordenesEstudiosSimples.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
			ordenesEstudiosSimples.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosSimples.setFechaNacimientoPaciente(ordenesEstudiosVDto.getFechaNacimientoPaciente());
			ordenesEstudiosSimples.setNombreDoctor(ordenesEstudiosVDto.getNombreDoctor());
			ordenesEstudiosSimples.setApellidoPaternoDoctor(ordenesEstudiosVDto.getApellidoPaternoDoctor());
			ordenesEstudiosSimples.setApellidoMaternoDoctor(ordenesEstudiosVDto.getApellidoMaternoDoctor());
			ordenesEstudiosSimples.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente());
			ordenesEstudiosSimples.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente());
			ordenesEstudiosSimples.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente());
			ordenesEstudiosSimples.setNombreUbicacion(ordenesEstudiosVDto.getNombreUbicacion());
			ordenesEstudiosSimples.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
			ordenesEstudiosSimples.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
            ordenesEstudiosSimples.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
			ordenesEstudiosSimples.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
			ordenesEstudiosSimples.setNumeroDoctor(ordenesEstudiosVDto.getNumeroDoctor());
			ordenesEstudiosSimples.setNumeroDoctorReferente(ordenesEstudiosVDto.getNumeroDoctorReferente());
			ordenesEstudiosSimples.setRequiereFactura(ordenesEstudiosVDto.getRequiereFactura());
			ordenesEstudiosSimples.setInfoAdicional(ordenesEstudiosVDto.getInfoAdicional());
			ordenesEstudiosSimples.setTipoPago(ordenesEstudiosVDto.getTipoPago());
			ordenesEstudiosSimples.setEstatus(ordenesEstudiosVDto.getEstatus());
			ordenesEstudiosSimples.setDecodeEstatus(ordenesEstudiosVDto.getDecodeEstatus());
			ordenesEstudiosSimples.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
			ordenesEstudiosSimples.setUsuarioUltimaActualizacion(ordenesEstudiosVDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosSimples.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
			ordenesEstudiosSimples.setIndicacionesDoctor(ordenesEstudiosVDto.getIndicacionesDoctor());
			ordenesEstudiosSimples.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
			ordenesEstudiosSimples.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
			listOrdenesEstudiosSimples.add(ordenesEstudiosSimples);
		}
		
		listOrdEstSimpEnAten = new ArrayList<OrdenesEstudiosSimples>(); 
		listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findByEstaus("EN_ATENCION",1/*OrdenesSimples*/);
		iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
		while(iterOrdenesEstudiosVDto.hasNext()) {
			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
			OrdenesEstudiosSimples ordenesEstudiosSimples = new OrdenesEstudiosSimples();
			ordenesEstudiosSimples.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
			ordenesEstudiosSimples.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosSimples.setFechaNacimientoPaciente(ordenesEstudiosVDto.getFechaNacimientoPaciente());
			ordenesEstudiosSimples.setNombreDoctor(ordenesEstudiosVDto.getNombreDoctor());
			ordenesEstudiosSimples.setApellidoPaternoDoctor(ordenesEstudiosVDto.getApellidoPaternoDoctor());
			ordenesEstudiosSimples.setApellidoMaternoDoctor(ordenesEstudiosVDto.getApellidoMaternoDoctor());
			ordenesEstudiosSimples.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente());
			ordenesEstudiosSimples.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente());
			ordenesEstudiosSimples.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente());
			ordenesEstudiosSimples.setNombreUbicacion(ordenesEstudiosVDto.getNombreUbicacion());
			ordenesEstudiosSimples.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
			ordenesEstudiosSimples.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
            ordenesEstudiosSimples.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
			ordenesEstudiosSimples.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
			ordenesEstudiosSimples.setNumeroDoctor(ordenesEstudiosVDto.getNumeroDoctor());
			ordenesEstudiosSimples.setRequiereFactura(ordenesEstudiosVDto.getRequiereFactura());
			ordenesEstudiosSimples.setInfoAdicional(ordenesEstudiosVDto.getInfoAdicional());
			ordenesEstudiosSimples.setTipoPago(ordenesEstudiosVDto.getTipoPago());
			ordenesEstudiosSimples.setEstatus(ordenesEstudiosVDto.getEstatus());
			ordenesEstudiosSimples.setDecodeEstatus(ordenesEstudiosVDto.getDecodeEstatus());
			ordenesEstudiosSimples.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
			ordenesEstudiosSimples.setUsuarioUltimaActualizacion(ordenesEstudiosVDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosSimples.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
			ordenesEstudiosSimples.setIndicacionesDoctor(ordenesEstudiosVDto.getIndicacionesDoctor());
			ordenesEstudiosSimples.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
			ordenesEstudiosSimples.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
			listOrdEstSimpEnAten.add(ordenesEstudiosSimples);
		}
		
		
		listOrdEstSimpEstRea = new ArrayList<OrdenesEstudiosSimples>(); 
		listOrdenesVEstudiosDto = ordenesEstudiosVLocal.findByEstaus("ESTUDIO_REALIZADO",1/*OrdenesSimples*/);
		iterOrdenesEstudiosVDto = listOrdenesVEstudiosDto.iterator(); 
		while(iterOrdenesEstudiosVDto.hasNext()) {
			OrdenesEstudiosVDto ordenesEstudiosVDto = iterOrdenesEstudiosVDto.next(); 
			OrdenesEstudiosSimples ordenesEstudiosSimples = new OrdenesEstudiosSimples();
			ordenesEstudiosSimples.setNumeroOrden(ordenesEstudiosVDto.getNumeroOrden());
			ordenesEstudiosSimples.setNombrePaciente(ordenesEstudiosVDto.getNombrePaciente());
			ordenesEstudiosSimples.setFechaNacimientoPaciente(ordenesEstudiosVDto.getFechaNacimientoPaciente());
			ordenesEstudiosSimples.setNombreDoctor(ordenesEstudiosVDto.getNombreDoctor());
			ordenesEstudiosSimples.setApellidoMaternoDoctor(ordenesEstudiosVDto.getApellidoMaternoDoctor());
			ordenesEstudiosSimples.setApellidoPaternoDoctor(ordenesEstudiosVDto.getApellidoPaternoDoctor());
		    ordenesEstudiosSimples.setNombreDoctorReferente(ordenesEstudiosVDto.getNombreDoctorReferente());
		    ordenesEstudiosSimples.setApellidoPaternoDoctorReferente(ordenesEstudiosVDto.getApellidoPaternoDoctorReferente());
		    ordenesEstudiosSimples.setApellidoMaternoDoctorReferente(ordenesEstudiosVDto.getApellidoMaternoDoctorReferente());
			ordenesEstudiosSimples.setNombreUbicacion(ordenesEstudiosVDto.getNombreUbicacion());
			ordenesEstudiosSimples.setNumeroEstudio(ordenesEstudiosVDto.getNumeroEstudio());
			ordenesEstudiosSimples.setNombreEstudio(ordenesEstudiosVDto.getNombreEstudio());
            ordenesEstudiosSimples.setCostoEstudio(ordenesEstudiosVDto.getCostoEstudio());
			ordenesEstudiosSimples.setNumeroPaciente(ordenesEstudiosVDto.getNumeroPaciente());
			ordenesEstudiosSimples.setNumeroDoctor(ordenesEstudiosVDto.getNumeroDoctor());
			ordenesEstudiosSimples.setRequiereFactura(ordenesEstudiosVDto.getRequiereFactura());
			ordenesEstudiosSimples.setInfoAdicional(ordenesEstudiosVDto.getInfoAdicional());
			ordenesEstudiosSimples.setTipoPago(ordenesEstudiosVDto.getTipoPago());
			ordenesEstudiosSimples.setEstatus(ordenesEstudiosVDto.getEstatus());
			ordenesEstudiosSimples.setDecodeEstatus(ordenesEstudiosVDto.getDecodeEstatus());
			ordenesEstudiosSimples.setFechaCreacion(ordenesEstudiosVDto.getFechaCreacion());
			ordenesEstudiosSimples.setUsuarioUltimaActualizacion(ordenesEstudiosVDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosSimples.setFechaUltimaActualizacion(ordenesEstudiosVDto.getFechaUltimaActualizacion());
			ordenesEstudiosSimples.setIndicacionesDoctor(ordenesEstudiosVDto.getIndicacionesDoctor());
			ordenesEstudiosSimples.setHoraInicialOrden(ordenesEstudiosVDto.getHoraInicialOrden());
			ordenesEstudiosSimples.setHoraFinalOrden(ordenesEstudiosVDto.getHoraFinalOrden());
			listOrdEstSimpEstRea.add(ordenesEstudiosSimples);
		}
		
		/***
		List<OrdenesEstudiosDto> listOrdenesEstudiosDto = ordenesEstudiosLocal.findAllDesc();
		Iterator<OrdenesEstudiosDto> iterOrdenesEstudiosDto = listOrdenesEstudiosDto.iterator();
		while(iterOrdenesEstudiosDto.hasNext()) {
			OrdenesEstudiosDto ordenesEstudiosDto = iterOrdenesEstudiosDto.next();
			PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
			DoctoresDto doctoresDto = doctoresLocal.findByNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
			UbicacionesDto ubicacionesDto = ubicacionesLocal.findByNumeroUbicacion(ordenesEstudiosDto.getNumeroUbicacion());
			OrdenesEstudiosSimples ordenesEstudiosSimples = new OrdenesEstudiosSimples();
			ordenesEstudiosSimples.setNumeroOrden(ordenesEstudiosDto.getNumeroOrden());
			ordenesEstudiosSimples.setNombrePaciente(pacientesDto.getNombrePaciente());
			ordenesEstudiosSimples.setFechaNacimientoPaciente(pacientesDto.getFechaNacimientoPaciente());
			ordenesEstudiosSimples.setNombreDoctor(doctoresDto.getNombreDoctor());
			ordenesEstudiosSimples.setNombreUbicacion(ubicacionesDto.getNombreUbicacion());
			
			ordenesEstudiosSimples.setNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
			EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
			ordenesEstudiosSimples.setCostoEstudio(estudiosDto.getCostoEstudio());
			
			ordenesEstudiosSimples.setNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
			ordenesEstudiosSimples.setNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
			ordenesEstudiosSimples.setRequiereFactura(ordenesEstudiosDto.getRequiereFactura());
			ordenesEstudiosSimples.setInfoAdicional(ordenesEstudiosDto.getInfoAdicional());
			ordenesEstudiosSimples.setTipoPago(ordenesEstudiosDto.getTipoPago());
			ordenesEstudiosSimples.setEstatus(ordenesEstudiosDto.getEstatus());
			ordenesEstudiosSimples.setFechaCreacion(ordenesEstudiosDto.getFechaCreacion());
			ordenesEstudiosSimples.setUsuarioUltimaActualizacion(ordenesEstudiosDto.getUsuarioUltimaActualizacion());
			ordenesEstudiosSimples.setFechaUltimaActualizacion(ordenesEstudiosDto.getFechaUltimaActualizacion());
			ordenesEstudiosSimples.setIndicacionesDoctor(ordenesEstudiosDto.getIndicacionesDoctor());
			ordenesEstudiosSimples.setHoraInicialOrden(ordenesEstudiosDto.getHoraInicialOrden());
			ordenesEstudiosSimples.setHoraFinalOrden(ordenesEstudiosDto.getHoraFinalOrden());
			
			listOrdenesEstudiosSimples.add(ordenesEstudiosSimples);
		}
		***/
	}
	
	public void selectForUpdate(OrdenesEstudiosSimples pOrdenesEstudiosSimples) {
		
		ordenesEstudiosSimplesSelected = new OrdenesEstudiosSimples();
		
		 ordenesEstudiosSimplesSelected.setNumeroOrden(pOrdenesEstudiosSimples.getNumeroOrden());
		 ordenesEstudiosSimplesSelected.setNumeroUbicacion(pOrdenesEstudiosSimples.getNumeroUbicacion());
		 ordenesEstudiosSimplesSelected.setNumeroDoctor(pOrdenesEstudiosSimples.getNumeroDoctor());
		 ordenesEstudiosSimplesSelected.setNumeroPaciente(pOrdenesEstudiosSimples.getNumeroPaciente());
		 ordenesEstudiosSimplesSelected.setTipoOrden(pOrdenesEstudiosSimples.getTipoOrden());
		 ordenesEstudiosSimplesSelected.setNumeroEstudio(pOrdenesEstudiosSimples.getNumeroEstudio());
		 ordenesEstudiosSimplesSelected.setNumeroAlergia(pOrdenesEstudiosSimples.getNumeroAlergia());
		 ordenesEstudiosSimplesSelected.setRequiereFactura(pOrdenesEstudiosSimples.getRequiereFactura());
		 ordenesEstudiosSimplesSelected.setTipoPago(pOrdenesEstudiosSimples.getTipoPago());
		 ordenesEstudiosSimplesSelected.setEstatus(pOrdenesEstudiosSimples.getEstatus());
		 ordenesEstudiosSimplesSelected.setFechaCreacion(pOrdenesEstudiosSimples.getFechaCreacion());
		 ordenesEstudiosSimplesSelected.setUsuarioUltimaActualizacion(pOrdenesEstudiosSimples.getUsuarioUltimaActualizacion());
		 ordenesEstudiosSimplesSelected.setFechaUltimaActualizacion(pOrdenesEstudiosSimples.getFechaUltimaActualizacion());
		 ordenesEstudiosSimplesSelected.setIndicacionesDoctor(pOrdenesEstudiosSimples.getIndicacionesDoctor());
		 ordenesEstudiosSimplesSelected.setInfoAdicional(pOrdenesEstudiosSimples.getInfoAdicional());
		 ordenesEstudiosSimplesSelected.setNumeroDoctorReferente(pOrdenesEstudiosSimples.getNumeroDoctorReferente());
		 
		 ordenesEstudiosSimplesSelected.setFechaNacimientoPaciente(pOrdenesEstudiosSimples.getFechaNacimientoPaciente());
		 int intMonthsBetween =  differenceInMonths(new Date(pOrdenesEstudiosSimples.getFechaNacimientoPaciente().getTime()),new Date());
		 int intAniosBetween = intMonthsBetween/12;
		 ordenesEstudiosSimplesSelected.setEdad(intAniosBetween+" anios");	
		 
		 ordenesEstudiosSimplesSelected.setCostoEstudio(pOrdenesEstudiosSimples.getCostoEstudio());
		 ordenesEstudiosSimplesSelected.setHoraInicialOrden(pOrdenesEstudiosSimples.getHoraInicialOrden()); 
		 ordenesEstudiosSimplesSelected.setHoraFinalOrden(pOrdenesEstudiosSimples.getHoraFinalOrden());
		 if(null!=pOrdenesEstudiosSimples.getHoraInicialOrden()) {
			 ordenesEstudiosSimplesSelected.setUtilHoraInicialOrden(new Date(pOrdenesEstudiosSimples.getHoraInicialOrden().getTime()));
		 }
		 if(null!=pOrdenesEstudiosSimples.getHoraFinalOrden()) {
			 ordenesEstudiosSimplesSelected.setUtilHoraFinalOrden(new Date(pOrdenesEstudiosSimples.getHoraFinalOrden().getTime()));
		 }
		 
		 System.out.println("pOrdenesEstudiosSimples.getEstatus():"+pOrdenesEstudiosSimples.getEstatus());
		 
		 ordenesEstudiosSimplesSelected.setEstatus(pOrdenesEstudiosSimples.getEstatus());
	    
	}
	
   public void selectForDelete(OrdenesEstudiosSimples pOrdenesEstudiosSimples) {
		
		 ordenesEstudiosSimplesSelected = new OrdenesEstudiosSimples();
		
		 ordenesEstudiosSimplesSelected.setNumeroOrden(pOrdenesEstudiosSimples.getNumeroOrden());
		 ordenesEstudiosSimplesSelected.setNumeroUbicacion(pOrdenesEstudiosSimples.getNumeroUbicacion());
		 ordenesEstudiosSimplesSelected.setNumeroDoctor(pOrdenesEstudiosSimples.getNumeroDoctor());
		 ordenesEstudiosSimplesSelected.setNumeroPaciente(pOrdenesEstudiosSimples.getNumeroPaciente());
		 ordenesEstudiosSimplesSelected.setTipoOrden(pOrdenesEstudiosSimples.getTipoOrden());
		 ordenesEstudiosSimplesSelected.setNumeroEstudio(pOrdenesEstudiosSimples.getNumeroEstudio());
		 ordenesEstudiosSimplesSelected.setNumeroAlergia(pOrdenesEstudiosSimples.getNumeroAlergia());
		 ordenesEstudiosSimplesSelected.setRequiereFactura(pOrdenesEstudiosSimples.getRequiereFactura());
		 ordenesEstudiosSimplesSelected.setTipoPago(pOrdenesEstudiosSimples.getTipoPago());
		 ordenesEstudiosSimplesSelected.setEstatus(pOrdenesEstudiosSimples.getEstatus());
		 ordenesEstudiosSimplesSelected.setFechaCreacion(pOrdenesEstudiosSimples.getFechaCreacion());
		 ordenesEstudiosSimplesSelected.setUsuarioUltimaActualizacion(pOrdenesEstudiosSimples.getUsuarioUltimaActualizacion());
		 ordenesEstudiosSimplesSelected.setFechaUltimaActualizacion(pOrdenesEstudiosSimples.getFechaUltimaActualizacion());
		 ordenesEstudiosSimplesSelected.setIndicacionesDoctor(pOrdenesEstudiosSimples.getIndicacionesDoctor());
		 ordenesEstudiosSimplesSelected.setInfoAdicional(pOrdenesEstudiosSimples.getInfoAdicional());
		
	}

    public void actualizaOrdenSimple() {
    	
    	boolean updatedIn = false; 
    	OrdenesEstudiosDto ordenesEstudiosDto = new OrdenesEstudiosDto();
    	ordenesEstudiosDto.setNumeroPaciente(ordenesEstudiosSimplesSelected.getNumeroPaciente());
    	ordenesEstudiosDto.setNumeroDoctor(ordenesEstudiosSimplesSelected.getNumeroDoctor());
    	ordenesEstudiosDto.setNumeroDoctorReferente(ordenesEstudiosSimplesSelected.getNumeroDoctorReferente());
    	ordenesEstudiosDto.setNumeroEstudio(ordenesEstudiosSimplesSelected.getNumeroEstudio());
    	ordenesEstudiosDto.setHoraInicialOrden(ordenesEstudiosSimplesSelected.getHoraInicialOrden());
    	ordenesEstudiosDto.setHoraFinalOrden(ordenesEstudiosSimplesSelected.getHoraFinalOrden());
    	ordenesEstudiosDto.setInfoAdicional(ordenesEstudiosSimplesSelected.getInfoAdicional());
    	ordenesEstudiosDto.setEstatus(ordenesEstudiosSimplesSelected.getEstatus());
    	ordenesEstudiosDto.setNumeroUbicacion(ordenesEstudiosSimplesSelected.getNumeroUbicacion());
    	ordenesEstudiosLocal.updateOrdenesEstudios(ordenesEstudiosSimplesSelected.getNumeroOrden(), ordenesEstudiosDto);
    	refreshEntity();
    	updatedIn = true;
    	PrimeFaces.current().ajax().addCallbackParam("updatedIn", updatedIn);
    }
   
    public void delete() {
    	ordenesEstudiosLocal.deleteOrdenesEstudios(ordenesEstudiosSimplesSelected.getNumeroOrden());
    	refreshEntity();
    }
    
    public void handleEstudioChange() {
		EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosSimples.getNumeroEstudio());
		if(null!=estudiosDto) {
		setCostoEstudio(estudiosDto.getCostoEstudio());
		}
	}
    
    public void handleEstudioChangeUpd() {
		EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosSimplesSelected.getNumeroEstudio());
		if(null!=estudiosDto) {
			ordenesEstudiosSimplesSelected.setCostoEstudio(estudiosDto.getCostoEstudio());
		}
	}
    
    public void search() {
    	if((null!=this.searchNumEstu&&!"".equals(this.searchNumEstu))
    	  ||(null!=this.searchNomPaci&&!"".equals(this.searchNomPaci))
    	  ||(null!=this.searchNomDoct&&!"".equals(this.searchNomDoct))
    	  ) {
    	
    		listOrdenesEstudiosSimples = new ArrayList<OrdenesEstudiosSimples>(); 
    		List<OrdenesEstudiosDto> listOrdenesEstudiosDto = ordenesEstudiosLocal.findBySearch("1"
    				                                                                           ,this.searchNumEstu
    				                                                                           ,this.searchNomPaci
    				                                                                           ,this.searchNomDoct
    				                                                                           );
    		Iterator<OrdenesEstudiosDto> iterOrdenesEstudiosDto = listOrdenesEstudiosDto.iterator();
    		while(iterOrdenesEstudiosDto.hasNext()) {
    			OrdenesEstudiosDto ordenesEstudiosDto = iterOrdenesEstudiosDto.next();
    			PacientesDto pacientesDto = pacientesLocal.findByNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
    			DoctoresDto doctoresDto = doctoresLocal.findByNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
    			//DoctoresReferentesDto doctoresReferentesDto = doctoresReferentesLocal.findByNumeroDoctorReferente(ordenesEstudiosDto.getNumeroDoctorReferente());
    			UbicacionesDto ubicacionesDto = ubicacionesLocal.findByNumeroUbicacion(ordenesEstudiosDto.getNumeroUbicacion());
    			OrdenesEstudiosSimples ordenesEstudiosSimples = new OrdenesEstudiosSimples();
    			ordenesEstudiosSimples.setNumeroOrden(ordenesEstudiosDto.getNumeroOrden());
    			ordenesEstudiosSimples.setNombrePaciente(pacientesDto.getNombrePaciente());
    			ordenesEstudiosSimples.setFechaNacimientoPaciente(pacientesDto.getFechaNacimientoPaciente());
    			ordenesEstudiosSimples.setNombreDoctor(doctoresDto.getNombreDoctor());
    			//ordenesEstudiosSimples.setNombreDoctorReferente(doctoresReferentesDto.getNombreDoctorReferente());
    			ordenesEstudiosSimples.setNombreUbicacion(ubicacionesDto.getNombreUbicacion());
    			
    			ordenesEstudiosSimples.setNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
    			EstudiosDto estudiosDto = estudiosLocal.findByNumeroEstudio(ordenesEstudiosDto.getNumeroEstudio());
    			ordenesEstudiosSimples.setCostoEstudio(estudiosDto.getCostoEstudio());
    			
    			ordenesEstudiosSimples.setNumeroPaciente(ordenesEstudiosDto.getNumeroPaciente());
    			ordenesEstudiosSimples.setNumeroDoctor(ordenesEstudiosDto.getNumeroDoctor());
    			ordenesEstudiosSimples.setRequiereFactura(ordenesEstudiosDto.getRequiereFactura());
    			ordenesEstudiosSimples.setInfoAdicional(ordenesEstudiosDto.getInfoAdicional());
    			ordenesEstudiosSimples.setTipoPago(ordenesEstudiosDto.getTipoPago());
    			ordenesEstudiosSimples.setEstatus(ordenesEstudiosDto.getEstatus());
    			ordenesEstudiosSimples.setFechaCreacion(ordenesEstudiosDto.getFechaCreacion());
    			ordenesEstudiosSimples.setUsuarioUltimaActualizacion(ordenesEstudiosDto.getUsuarioUltimaActualizacion());
    			ordenesEstudiosSimples.setFechaUltimaActualizacion(ordenesEstudiosDto.getFechaUltimaActualizacion());
    			ordenesEstudiosSimples.setIndicacionesDoctor(ordenesEstudiosDto.getIndicacionesDoctor());
    			ordenesEstudiosSimples.setHoraInicialOrden(ordenesEstudiosDto.getHoraInicialOrden());
    			ordenesEstudiosSimples.setHoraFinalOrden(ordenesEstudiosDto.getHoraFinalOrden());
    			
    			listOrdenesEstudiosSimples.add(ordenesEstudiosSimples);
    		}
    		
    	}
    	
    }
    
    public String toFormatoOrdenSimple() {
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        sessionMap.remove("svNumeroOrden"); 
        sessionMap.put("svNumeroOrden", ordenesEstudiosSimplesSelected.getNumeroOrden());
        return "admin-Formato1";
    }
    
    public String toFormatoRO(long pNumeroOrden) {
    	System.out.println("Entra toFormatoRO");
    	System.out.println("pNumeroOrden:"+pNumeroOrden);
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
    
    public void handleFormatoChange() {
    	System.out.println("Entra handleFormatoChange");
    	System.out.println("Sale handleFormatoChange");
    }
    
    public String executeRcFormato() {
    	System.out.println("Entra executeRcFormato");
    	System.out.println("localFormato:"+localFormato);
    	String strPage = ""; 
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        sessionMap.remove("svNumeroOrden"); 
        sessionMap.put("svNumeroOrden", ordenesEstudiosSimplesSelected.getNumeroOrden());
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

	public List<OrdenesEstudiosSimples> getListOrdenesEstudiosSimples() {
		return listOrdenesEstudiosSimples;
	}

	public void setListOrdenesEstudiosSimples(List<OrdenesEstudiosSimples> listOrdenesEstudiosSimples) {
		this.listOrdenesEstudiosSimples = listOrdenesEstudiosSimples;
	}

	public OrdenesEstudiosSimples getOrdenesEstudiosSimplesSelected() {
		return ordenesEstudiosSimplesSelected;
	}

	public void setOrdenesEstudiosSimplesSelected(OrdenesEstudiosSimples ordenesEstudiosSimplesSelected) {
		this.ordenesEstudiosSimplesSelected = ordenesEstudiosSimplesSelected;
	}

	public long getCostoEstudio() {
		return costoEstudio;
	}

	public void setCostoEstudio(long costoEstudio) {
		this.costoEstudio = costoEstudio;
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

	public String getSearchNumEstu() {
		return searchNumEstu;
	}

	public void setSearchNumEstu(String searchNumEstu) {
		this.searchNumEstu = searchNumEstu;
	}

	public String getLocalFormato() {
		return localFormato;
	}

	public void setLocalFormato(String localFormato) {
		this.localFormato = localFormato;
	}

	public List<OrdenesEstudiosSimples> getListOrdEstSimpEnAten() {
		return listOrdEstSimpEnAten;
	}

	public void setListOrdEstSimpEnAten(List<OrdenesEstudiosSimples> listOrdEstSimpEnAten) {
		this.listOrdEstSimpEnAten = listOrdEstSimpEnAten;
	}

	public List<OrdenesEstudiosSimples> getListOrdEstSimpEstRea() {
		return listOrdEstSimpEstRea;
	}

	public void setListOrdEstSimpEstRea(List<OrdenesEstudiosSimples> listOrdEstSimpEstRea) {
		this.listOrdEstSimpEstRea = listOrdEstSimpEstRea;
	}
	
}

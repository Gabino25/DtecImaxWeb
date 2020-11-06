package com.dtecimax.ejb.backing.admin;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import com.dtecimax.ejb.model.admin.AsignacionRoles;
import com.dtecimax.ejb.services.admin.AsignacionRolesLocal;
import com.dtecimax.jpa.dto.admin.AsignacionRolesDto;
import com.dtecimax.jpa.dto.admin.AsignacionRolesVDto;

@ManagedBean  
@ViewScoped
public class AsignacionRolesForm {

	private short numeroUsuario;
    private Date fechaEfectivaDesde; 
    private Date fechaEfectivaHasta; 
    private String rolCode; 
    private short buscarNumeroUsuario; 
    private String buscarRolCode; 
    
    private AsignacionRoles selectedAsignacionRoles= new AsignacionRoles();
    
    private java.sql.Date endOfTime = java.sql.Date.valueOf("9999-12-31");
    
    private String message; 
    
    @Inject
    AsignacionRolesLocal asignacionRolesLocal; 
    
    
    public void insert() {
      boolean insertIn = false;
    	
      AsignacionRolesDto asignacionRolesDto = new AsignacionRolesDto(); 
      java.util.Date sysDate = new java.util.Date(); 
	  java.sql.Timestamp sqlSysDate = new java.sql.Timestamp(sysDate.getTime());
	  asignacionRolesDto.setNumeroUsuario(numeroUsuario);
	  asignacionRolesDto.setRolCode(rolCode);
	  asignacionRolesDto.setFechaEfectivaDesde(new java.sql.Date(fechaEfectivaDesde.getTime()));
		if(null!=fechaEfectivaHasta) {
			asignacionRolesDto.setFechaEfectivaHasta(new java.sql.Date(fechaEfectivaHasta.getTime()));
		}else {
			asignacionRolesDto.setFechaEfectivaHasta(endOfTime);
		}
		
		asignacionRolesDto.setFechaCreacion(sqlSysDate);
		asignacionRolesDto.setFechaActualizacion(sqlSysDate); 
		asignacionRolesDto.setCreadoPor((short)-1);
		asignacionRolesDto.setActualizadoPor((short)-1);
		
		try {
			
		long numeroAsignacionRol = asignacionRolesLocal.insertaAsignacionRol(asignacionRolesDto);
		insertIn = true;
		PrimeFaces.current().ajax().addCallbackParam("insertIn", insertIn);
		
		AsignacionRolesVDto asignacionRolesVDto = asignacionRolesLocal.findByNumero(numeroAsignacionRol);
		
		selectedAsignacionRoles.setNumero(asignacionRolesVDto.getNumero());
		selectedAsignacionRoles.setNumeroUsuario(asignacionRolesVDto.getNumeroUsuario());
		selectedAsignacionRoles.setNombreUsuario(asignacionRolesVDto.getNombreUsuario());
		selectedAsignacionRoles.setRolCode(asignacionRolesVDto.getRolCode());
		selectedAsignacionRoles.setRolDesc(asignacionRolesVDto.getRolDesc());
		selectedAsignacionRoles.setFechaEfectivaDesde(asignacionRolesVDto.getFechaEfectivaDesde());
		if(this.endOfTime.compareTo(asignacionRolesVDto.getFechaEfectivaHasta())==0) {
			selectedAsignacionRoles.setFechaEfectivaHasta(null);
		}else {
			selectedAsignacionRoles.setFechaEfectivaHasta(asignacionRolesVDto.getFechaEfectivaHasta()); 
		}
	
		this.setNumeroUsuario((short)0);
		this.setRolCode(null);
		this.setFechaEfectivaDesde(null);
		this.setFechaEfectivaHasta(null);
		
		
		}catch(Exception e) {
		 Throwable throwable = e.getCause();
		 while(null!=throwable) {
			 throwable = throwable.getCause();
			 if(null!=throwable) {
				 if(throwable.toString().contains("ASIGNACION_ROLES_UK")) {
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede duplicar el Rol."));
					 break;
				 }
			 }
		 }
		
	   } /** END try { **/
		
	} /** END  public void insert() **/
    
    public void buscar() {
    	 boolean searchIn = false;
    	try {
    	AsignacionRolesVDto asignacionRolesVDto = asignacionRolesLocal.findByNumeroAndRol(buscarNumeroUsuario, buscarRolCode); 
    	selectedAsignacionRoles.setNumero(asignacionRolesVDto.getNumero());
		selectedAsignacionRoles.setNumeroUsuario(asignacionRolesVDto.getNumeroUsuario());
		selectedAsignacionRoles.setNombreUsuario(asignacionRolesVDto.getNombreUsuario());
		selectedAsignacionRoles.setRolCode(asignacionRolesVDto.getRolCode());
		selectedAsignacionRoles.setRolDesc(asignacionRolesVDto.getRolDesc());
		selectedAsignacionRoles.setFechaEfectivaDesde(asignacionRolesVDto.getFechaEfectivaDesde());
		
		if(endOfTime.compareTo(asignacionRolesVDto.getFechaEfectivaHasta())==0) {
			selectedAsignacionRoles.setFechaEfectivaHasta(null);
		}else {
		  selectedAsignacionRoles.setFechaEfectivaHasta(asignacionRolesVDto.getFechaEfectivaHasta());
		}
		
		this.setBuscarNumeroUsuario((short)0);
		this.setBuscarRolCode(null);
		searchIn = true; 
	    }catch(Exception e) {
			 Throwable throwable = e.getCause();
			 int count = 3; 
			 while(null!=throwable) {
				 if(throwable.toString().contains("No entity found for query")) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede encontrar el registro."));
					 break;
				 }
				 count = count-1; 
				 if(count==0) {
					 break; 
				 }
			 }
			
		   } /** END try { **/
    	PrimeFaces.current().ajax().addCallbackParam("searchIn", searchIn);	
    } /** EN public void buscar() **/
    
    public void actualizar(){
    	 boolean updateIn = false; 
    	 AsignacionRolesDto asignacionRolesDto = new AsignacionRolesDto(); 
    	 asignacionRolesDto.setNumeroUsuario(selectedAsignacionRoles.getNumeroUsuario());
    	 asignacionRolesDto.setRolCode(selectedAsignacionRoles.getRolCode());
    	 java.sql.Date sqlFechaEffectivaDesde = new java.sql.Date(selectedAsignacionRoles.getFechaEfectivaDesde().getTime()); 
    	 asignacionRolesDto.setFechaEfectivaDesde(sqlFechaEffectivaDesde);
    	 java.sql.Date sqlFechaEffectivaHasta = null;
    	 if(null!=selectedAsignacionRoles.getFechaEfectivaHasta()) {
    		 sqlFechaEffectivaHasta = new java.sql.Date(selectedAsignacionRoles.getFechaEfectivaHasta().getTime()); 
    	 }else {
    		 sqlFechaEffectivaHasta = this.endOfTime; 
    	 }
    	 asignacionRolesDto.setFechaEfectivaHasta(sqlFechaEffectivaHasta);
    	 try {
         long numeroAsignacionRol = asignacionRolesLocal.actualizaAsignacionRol(selectedAsignacionRoles.getNumero()
        		                                                              , asignacionRolesDto);  
         updateIn = true; 
    	 }catch(Exception e) {
    		 Throwable throwable = e.getCause();
    		 while(null!=throwable) {
    			 throwable = throwable.getCause();
    			 if(null!=throwable) {
    				 if(throwable.toString().contains("ASIGNACION_ROLES_UK")) {
    					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede duplicar el Rol."));
    					 break;
    				 }
    			 }
    		 }
    		
    	  } /** END try { **/
    	 
         PrimeFaces.current().ajax().addCallbackParam("updateIn", updateIn);	
    }
   
    public void borrar() {
    	boolean deleteIn = false; 
    	asignacionRolesLocal.borraAsignacionRol(selectedAsignacionRoles.getNumero());
    	deleteIn = true; 
    	selectedAsignacionRoles= new AsignacionRoles(); 
    	PrimeFaces.current().ajax().addCallbackParam("deleteIn", deleteIn);
    }
    
	public short getNumeroUsuario() {
		return numeroUsuario;
	}

	public void setNumeroUsuario(short numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public Date getFechaEfectivaDesde() {
		return fechaEfectivaDesde;
	}

	public void setFechaEfectivaDesde(Date fechaEfectivaDesde) {
		this.fechaEfectivaDesde = fechaEfectivaDesde;
	}

	public Date getFechaEfectivaHasta() {
		return fechaEfectivaHasta;
	}

	public void setFechaEfectivaHasta(Date fechaEfectivaHasta) {
		this.fechaEfectivaHasta = fechaEfectivaHasta;
	}

	public String getRolCode() {
		return rolCode;
	}

	public void setRolCode(String rolCode) {
		this.rolCode = rolCode;
	}

	public AsignacionRoles getSelectedAsignacionRoles() {
		return selectedAsignacionRoles;
	}

	public void setSelectedAsignacionRoles(AsignacionRoles selectedAsignacionRoles) {
		this.selectedAsignacionRoles = selectedAsignacionRoles;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public short getBuscarNumeroUsuario() {
		return buscarNumeroUsuario;
	}

	public void setBuscarNumeroUsuario(short buscarNumeroUsuario) {
		this.buscarNumeroUsuario = buscarNumeroUsuario;
	}

	public String getBuscarRolCode() {
		return buscarRolCode;
	}

	public void setBuscarRolCode(String buscarRolCode) {
		this.buscarRolCode = buscarRolCode;
	} 
	
}

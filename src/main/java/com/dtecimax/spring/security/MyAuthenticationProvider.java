package com.dtecimax.spring.security;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dtecimax.ejb.services.admin.AsignacionRolesLocal;
import com.dtecimax.ejb.services.admin.UsuariosLocal;
import com.dtecimax.jpa.dto.admin.AsignacionRolesVDto;
import com.dtecimax.jpa.dto.admin.UsuariosDto;

public class MyAuthenticationProvider implements AuthenticationProvider {

	private UsuariosLocal usuariosLocal;
	private AsignacionRolesLocal asignacionRolesLocal; 
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("Comienza MyAuthenticationProvider authenticate");
		String strUser = authentication.getPrincipal().toString();
		String strPassword = authentication.getCredentials().toString();
		System.out.println("strUser:"+strUser);
		System.out.println("strPassword:"+strPassword);
		
		/**
		  java:global/DtecImaxWeb/UsuariosLocalImpl!com.dtecimax.ejb.services.admin.UsuariosLocal
		  java:app/DtecImaxWeb/UsuariosLocalImpl!com.dtecimax.ejb.services.admin.UsuariosLocal
		  java:module/UsuariosLocalImpl!com.dtecimax.ejb.services.admin.UsuariosLocal
		  java:global/DtecImaxWeb/UsuariosLocalImpl
		  java:app/DtecImaxWeb/UsuariosLocalImpl
		  java:module/UsuariosLocalImpl
		  
		  JNDI Lookup of local EJB 
		**/
		try {
			InitialContext context = new InitialContext();
			usuariosLocal = (UsuariosLocal) context.lookup("java:module/UsuariosLocalImpl");
			asignacionRolesLocal = (AsignacionRolesLocal)context.lookup("java:module/AsignacionRolesLocalImpl");
		} catch (NamingException e1) {
		    System.out.println("Exception:"+e1.getMessage());
			e1.printStackTrace();
		}

		System.out.println(usuariosLocal);
		if(null!=usuariosLocal) {
			UsuariosDto usuariosDto = null; 
			try {
			 usuariosDto = usuariosLocal.findByNombrePassword(strUser, strPassword);
			 List<AsignacionRolesVDto> listAsignacionRolesVDto = asignacionRolesLocal.findByNombreUsuario(strUser); 
			 Iterator<AsignacionRolesVDto> iterAsignacionRolesVDto = listAsignacionRolesVDto.iterator(); 
			 Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			 while(iterAsignacionRolesVDto.hasNext()) {
				 AsignacionRolesVDto asignacionRolesVDto = iterAsignacionRolesVDto.next();
				 System.out.println("asignacionRolesVDto.getRolDesc():"+asignacionRolesVDto.getRolDesc());
				 SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(asignacionRolesVDto.getRolDesc()); 
				 authorities.add(simpleGrantedAuthority); 
			 }
			 
			  
			 /*authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));*/
			 
			 final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
		      result.setDetails(authentication.getDetails());
			  return result; 
			  
			 }catch(Exception e) {
				 System.out.println("*##*");
				 System.out.println(e);
				 System.out.println(e.getMessage());
				 Throwable throwable = e.getCause();
				 System.out.println(throwable);
				 while(null!=throwable) {
						 System.out.println("*#*");
						 System.out.println(throwable.toString());
						 if(throwable.toString().contains("javax.persistence.NoResultException")) {
							 System.out.println("*****");
							 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puede eliminar un paciente con una cita."));
							 break;
						 }
				   throwable = throwable.getCause();
				 }
				
			 }
			System.out.println("usuariosDto:"+usuariosDto);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("Comienza MyAuthenticationProvider supports");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

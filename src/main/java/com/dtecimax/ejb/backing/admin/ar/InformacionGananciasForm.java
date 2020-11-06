package com.dtecimax.ejb.backing.admin.ar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.dtecimax.ejb.model.ar.AdmonPagOrdEstud;
import com.dtecimax.ejb.services.ar.PagosOrdenesEstudiosV1Local;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

@ManagedBean  
@ViewScoped
public class InformacionGananciasForm {

	/** Pagos Con Tarjetas Con Facturas **/
	private List<AdmonPagOrdEstud> listPagosConTarjConFact = new ArrayList<AdmonPagOrdEstud>(); 
	private List<AdmonPagOrdEstud> listPagosConTarjSinFact = new ArrayList<AdmonPagOrdEstud>(); 
	private List<AdmonPagOrdEstud> listPagosConEfectivo = new ArrayList<AdmonPagOrdEstud>(); 
	private List<AdmonPagOrdEstud> listPagosConCLIP = new ArrayList<AdmonPagOrdEstud>(); 
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date searchFechaDesde;
	private Date searchFechaHasta;
	private BigDecimal totalPagosConTarjConFact = new BigDecimal(0); 
	private BigDecimal totalPagosConTarjSinFact = new BigDecimal(0); 
	private BigDecimal totalPagosConEfectivo = new BigDecimal(0); 
	private BigDecimal totalPagosConCLIP = new BigDecimal(0); 
	
	@Inject 
	PagosOrdenesEstudiosV1Local pagosOrdenesEstudiosV1Local;
	
	 @PostConstruct
	 public void init() {
		 try {
			searchFechaDesde = sdf.parse("08/08/2019");
			searchFechaHasta = sdf.parse("08/08/2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 }
	 
	public void filtraPorFechas() {
		System.out.println("Entra "+this.getClass()+" filtraPorFechas");
		java.sql.Date sqlFechaDesde = new java.sql.Date(searchFechaDesde.getTime());
		java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaDesde.getTime());
		listPagosConTarjConFact = pagosOrdenesEstudiosV1Local.findPagosConTarjConFact(sqlFechaDesde, sqlFechaHasta);
		listPagosConTarjSinFact = pagosOrdenesEstudiosV1Local.findPagosConTarjSinFact(sqlFechaDesde, sqlFechaHasta); 
		listPagosConEfectivo = pagosOrdenesEstudiosV1Local.findPagosConEfectivo(sqlFechaDesde, sqlFechaHasta); 
		listPagosConCLIP = pagosOrdenesEstudiosV1Local.findPagosConCLIP(sqlFechaDesde, sqlFechaHasta); 
		
		totalPagosConTarjConFact = new BigDecimal(0); 
		totalPagosConTarjSinFact = new BigDecimal(0); 
		totalPagosConEfectivo = new BigDecimal(0); 
		totalPagosConCLIP = new BigDecimal(0); 
		 
		for(AdmonPagOrdEstud admonPagOrdEstud:listPagosConTarjConFact) {
			totalPagosConTarjConFact.add(admonPagOrdEstud.getCostoTotal());
		}
		for(AdmonPagOrdEstud admonPagOrdEstud:listPagosConTarjSinFact) {
			totalPagosConTarjSinFact.add(admonPagOrdEstud.getCostoTotal());
		}
		for(AdmonPagOrdEstud admonPagOrdEstud:listPagosConEfectivo) {
			totalPagosConEfectivo.add(admonPagOrdEstud.getCostoTotal());
		}
		for(AdmonPagOrdEstud admonPagOrdEstud:listPagosConCLIP) {
			totalPagosConCLIP.add(admonPagOrdEstud.getCostoTotal());
		}
		
		System.out.println("Sale "+this.getClass()+" filtraPorFechas");
	}
	
	public List<AdmonPagOrdEstud> getListPagosConTarjConFact() {
		return listPagosConTarjConFact;
	}
	public void setListPagosConTarjConFact(List<AdmonPagOrdEstud> listPagosConTarjConFact) {
		this.listPagosConTarjConFact = listPagosConTarjConFact;
	}
	public List<AdmonPagOrdEstud> getListPagosConTarjSinFact() {
		return listPagosConTarjSinFact;
	}
	public void setListPagosConTarjSinFact(List<AdmonPagOrdEstud> listPagosConTarjSinFact) {
		this.listPagosConTarjSinFact = listPagosConTarjSinFact;
	}
	public List<AdmonPagOrdEstud> getListPagosConEfectivo() {
		return listPagosConEfectivo;
	}
	public void setListPagosConEfectivo(List<AdmonPagOrdEstud> listPagosConEfectivo) {
		this.listPagosConEfectivo = listPagosConEfectivo;
	}
	public List<AdmonPagOrdEstud> getListPagosConCLIP() {
		return listPagosConCLIP;
	}
	public void setListPagosConCLIP(List<AdmonPagOrdEstud> listPagosConCLIP) {
		this.listPagosConCLIP = listPagosConCLIP;
	}
	public Date getSearchFechaDesde() {
		return searchFechaDesde;
	}
	public void setSearchFechaDesde(Date searchFechaDesde) {
		this.searchFechaDesde = searchFechaDesde;
	}
	public Date getSearchFechaHasta() {
		return searchFechaHasta;
	}
	public void setSearchFechaHasta(Date searchFechaHasta) {
		this.searchFechaHasta = searchFechaHasta;
	}
	public BigDecimal getTotalPagosConTarjConFact() {
		return totalPagosConTarjConFact;
	}
	public void setTotalPagosConTarjConFact(BigDecimal totalPagosConTarjConFact) {
		this.totalPagosConTarjConFact = totalPagosConTarjConFact;
	}
	public BigDecimal getTotalPagosConTarjSinFact() {
		return totalPagosConTarjSinFact;
	}
	public void setTotalPagosConTarjSinFact(BigDecimal totalPagosConTarjSinFact) {
		this.totalPagosConTarjSinFact = totalPagosConTarjSinFact;
	}
	public BigDecimal getTotalPagosConEfectivo() {
		return totalPagosConEfectivo;
	}
	public void setTotalPagosConEfectivo(BigDecimal totalPagosConEfectivo) {
		this.totalPagosConEfectivo = totalPagosConEfectivo;
	}
	public BigDecimal getTotalPagosConCLIP() {
		return totalPagosConCLIP;
	}
	public void setTotalPagosConCLIP(BigDecimal totalPagosConCLIP) {
		this.totalPagosConCLIP = totalPagosConCLIP;
	}
	
}

package com.dtecimax.ejb.backing.ar;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.dtecimax.ejb.model.ar.PagosOrdenesEstudiosV1;
import com.dtecimax.ejb.services.ar.PagosOrdenesEstudiosV1Local;
import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@ManagedBean  
@ViewScoped
public class HistorialDePagosForm {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date searchFechaDesde;
	private Date searchFechaHasta;
	private BigDecimal gananciaTotal; 
	private float totalPorCobrar; 
	private List<PagosOrdenesEstudiosV1> listPagosOrdenesEstudiosV1 = new ArrayList<PagosOrdenesEstudiosV1>(); 
	private PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1Selected = new PagosOrdenesEstudiosV1(); 
	
	@Inject 
	PagosOrdenesEstudiosV1Local pagosOrdenesEstudiosV1Local;
	
	@Inject 
	OrdenesEstudiosLocal ordenesEstudiosLocal; 
	
	
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
		System.out.println("Entra filtraPorFechas");
		
		java.sql.Date sqlFechaDesde = new java.sql.Date(searchFechaDesde.getTime());
		/** java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaHasta.getTime()); 16012020 **/
		java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaDesde.getTime());
				
		List<PagosOrdenesEstudiosV1Dto> listPagosEstudiosV1Dto = pagosOrdenesEstudiosV1Local.findByFiltros(null /*searchNomPaci*/
																		                                 , null /*searchApellPatPaci*/
																		                                 , null /*searchApellMatPaci*/
																		                                 , sqlFechaDesde
																		                                 , sqlFechaHasta
																		                                 );
		listPagosOrdenesEstudiosV1 = new ArrayList<PagosOrdenesEstudiosV1>(); 
		totalPorCobrar = 0f; 
		Iterator<PagosOrdenesEstudiosV1Dto> iterPagosEstudiosV1Dto = listPagosEstudiosV1Dto.iterator();
		while(iterPagosEstudiosV1Dto.hasNext()) {
			PagosOrdenesEstudiosV1Dto pagosOrdenesEstudiosV1Dto = iterPagosEstudiosV1Dto.next(); 
			if(null!=pagosOrdenesEstudiosV1Dto.getEstatusPago()&&!"".equals(pagosOrdenesEstudiosV1Dto.getEstatusPago())) {
			PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1 = new PagosOrdenesEstudiosV1();
			pagosOrdenesEstudiosV1.setNumeroOrden(pagosOrdenesEstudiosV1Dto.getNumeroOrden());
			pagosOrdenesEstudiosV1.setNombreCompletoPaciente(pagosOrdenesEstudiosV1Dto.getNombreCompletoPaciente());
			pagosOrdenesEstudiosV1.setNombreEstudio(pagosOrdenesEstudiosV1Dto.getNombreEstudio());
			pagosOrdenesEstudiosV1.setCostoEstudio(pagosOrdenesEstudiosV1Dto.getCostoEstudio());
			pagosOrdenesEstudiosV1.setCastFechaCreacion(pagosOrdenesEstudiosV1Dto.getCastFechaCreacion());
			pagosOrdenesEstudiosV1.setDescuento(pagosOrdenesEstudiosV1Dto.getDescuento());
			pagosOrdenesEstudiosV1.setDecodeTipoPago(pagosOrdenesEstudiosV1Dto.getDecodeTipoPago());
			float fTotal =0f; 
			if(null!=pagosOrdenesEstudiosV1Dto.getDescuento()) {
				fTotal = pagosOrdenesEstudiosV1Dto.getCostoEstudio()-pagosOrdenesEstudiosV1Dto.getDescuento().floatValue();
			}else {
				fTotal = pagosOrdenesEstudiosV1Dto.getCostoEstudio(); 
			}
			pagosOrdenesEstudiosV1.setTotal(new BigDecimal(fTotal));
			pagosOrdenesEstudiosV1.setObservDesc(pagosOrdenesEstudiosV1Dto.getObservDesc());
			pagosOrdenesEstudiosV1.setObservPago(pagosOrdenesEstudiosV1Dto.getObservPago());
			listPagosOrdenesEstudiosV1.add(pagosOrdenesEstudiosV1); 
			totalPorCobrar = totalPorCobrar + (float)pagosOrdenesEstudiosV1Dto.getCostoEstudio();  
			}
		}
		
		System.out.println("Sale filtraPorFechas");
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

	public List<PagosOrdenesEstudiosV1> getListPagosOrdenesEstudiosV1() {
		return listPagosOrdenesEstudiosV1;
	}

	public void setListPagosOrdenesEstudiosV1(List<PagosOrdenesEstudiosV1> listPagosOrdenesEstudiosV1) {
		this.listPagosOrdenesEstudiosV1 = listPagosOrdenesEstudiosV1;
	}

	public PagosOrdenesEstudiosV1 getPagosOrdenesEstudiosV1Selected() {
		return pagosOrdenesEstudiosV1Selected;
	}

	public void setPagosOrdenesEstudiosV1Selected(PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1Selected) {
		this.pagosOrdenesEstudiosV1Selected = pagosOrdenesEstudiosV1Selected;
	}

	public BigDecimal getGananciaTotal() {
		return gananciaTotal;
	}

	public void setGananciaTotal(BigDecimal gananciaTotal) {
		this.gananciaTotal = gananciaTotal;
	}
	
	public float getTotalPorCobrar() {
		return totalPorCobrar;
	}

	public void setTotalPorCobrar(float totalPorCobrar) {
		this.totalPorCobrar = totalPorCobrar;
	}
	
}

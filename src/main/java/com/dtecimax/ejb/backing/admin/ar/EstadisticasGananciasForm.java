package com.dtecimax.ejb.backing.admin.ar;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.dtecimax.ejb.model.ar.AdmonPagOrdEstud;
import com.dtecimax.ejb.services.ar.PagosOrdenesEstudiosV1Local;

@ManagedBean  
@ViewScoped
public class EstadisticasGananciasForm {

	/** Pagos Con Tarjetas Con Facturas **/
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date searchFechaDesde;
	private Date searchFechaHasta;
	private BarChartModel barModelPagosConTarjConFact;
	private BarChartModel barModelPagosConTarjSinFact;
	private BarChartModel barModelPagosConEfectivo;
	private BarChartModel barModelPagosConCLIP;
	
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
		 barModelPagosConTarjConFact  = initModelPagosConTarjConFact();
		 barModelPagosConTarjSinFact = initModelPagosConTarjSinFact();
		 barModelPagosConEfectivo = initModelPagosConEfectivo();
		 barModelPagosConCLIP = initModelPagosConCLIP();
	 }
	
	 private BarChartModel initModelPagosConTarjConFact() {
		    BarChartModel model = new BarChartModel();	   	 
	        ChartSeries numeroOrdenes = new ChartSeries();
	        numeroOrdenes.setLabel("Ganancias");
	        numeroOrdenes.set(0, 0);
	        model.addSeries(numeroOrdenes);
	        
	        model.setTitle("Ganancias con tarjeta facturado");
	        model.setLegendPosition("ne");
	 
	        Axis xAxis = model.getAxis(AxisType.X);
	        xAxis.setLabel("Fechas");
	 
	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setLabel("Ganancias");
	        yAxis.setMin(0);
	        yAxis.setMax(10);
	        
	        return model; 
	 }
	 
	 private BarChartModel initModelPagosConTarjSinFact() {
		    BarChartModel model = new BarChartModel();	   	 
	        ChartSeries numeroOrdenes = new ChartSeries();
	        numeroOrdenes.setLabel("Ganancias");
	        numeroOrdenes.set(0, 0);
	        model.addSeries(numeroOrdenes);
	        
	        model.setTitle("Ganancias con tarjeta no facturado");
	        model.setLegendPosition("ne");
	 
	        Axis xAxis = model.getAxis(AxisType.X);
	        xAxis.setLabel("Fechas");
	 
	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setLabel("Ganancias");
	        yAxis.setMin(0);
	        yAxis.setMax(10);
	        
	        return model; 
	 }
	 
	 private BarChartModel initModelPagosConEfectivo() {
		    BarChartModel model = new BarChartModel();	   	 
	        ChartSeries numeroOrdenes = new ChartSeries();
	        numeroOrdenes.setLabel("Ganancias");
	        numeroOrdenes.set(0, 0);
	        model.addSeries(numeroOrdenes);
	        
	        model.setTitle("Ganancias con efectivo");
	        model.setLegendPosition("ne");
	 
	        Axis xAxis = model.getAxis(AxisType.X);
	        xAxis.setLabel("Fechas");
	 
	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setLabel("Ganancias");
	        yAxis.setMin(0);
	        yAxis.setMax(10);
	        
	        return model; 
	 }
	 
	 private BarChartModel initModelPagosConCLIP() {
		    BarChartModel model = new BarChartModel();	   	 
	        ChartSeries numeroOrdenes = new ChartSeries();
	        numeroOrdenes.setLabel("Ganancias");
	        numeroOrdenes.set(0, 0);
	        model.addSeries(numeroOrdenes);
	        
	        model.setTitle("Ganancias con CLIP");
	        model.setLegendPosition("ne");
	 
	        Axis xAxis = model.getAxis(AxisType.X);
	        xAxis.setLabel("Fechas");
	 
	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setLabel("Ganancias");
	        yAxis.setMin(0);
	        yAxis.setMax(10);
	        
	        return model; 
	 }
	 
	public void filtraPorFechas() {
		
		
		       if(0==searchFechaDesde.compareTo(searchFechaHasta)
			    ||1==searchFechaDesde.compareTo(searchFechaHasta)) {
			     return; 
			    }
		
		System.out.println("Entra "+this.getClass()+" filtraPorFechas");
		java.sql.Date sqlFechaDesde = new java.sql.Date(searchFechaDesde.getTime());
		java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaHasta.getTime());
		java.util.Date utilTmpDate = searchFechaDesde; 
		
		/**************************************************************************/
		BarChartModel modelPagosConTarjConFact = new BarChartModel();	   	 
        ChartSeries seriesPagosConTarjConFact = new ChartSeries();
        seriesPagosConTarjConFact.setLabel("Ganancias");
        modelPagosConTarjConFact.setTitle("Ganancias con tarjeta facturado");
        modelPagosConTarjConFact.setLegendPosition("ne");
        Axis xAxisPagosConTarjConFact = modelPagosConTarjConFact.getAxis(AxisType.X);
        xAxisPagosConTarjConFact.setLabel("Fechas");
        Axis yAxisPagosConTarjConFact = modelPagosConTarjConFact.getAxis(AxisType.Y);
        yAxisPagosConTarjConFact.setLabel("Numero Ordenes");
        
        BigDecimal maxGanancias = new BigDecimal(10); 
        while(utilTmpDate.compareTo(searchFechaHasta)!=1) {
        	java.sql.Date sqlTmpDate = new java.sql.Date(utilTmpDate.getTime());
        	BigDecimal bigDecimal = pagosOrdenesEstudiosV1Local.sumaPagosConTarjConFact(sqlTmpDate); 
        	if(maxGanancias.compareTo(bigDecimal)==-1) {
        		maxGanancias = bigDecimal; 
        	}
        	seriesPagosConTarjConFact.set(sdf.format(utilTmpDate), bigDecimal);
         	utilTmpDate = new java.util.Date(utilTmpDate.getTime()+(1000 * 60 * 60 * 24)); 
	    }
        modelPagosConTarjConFact.addSeries(seriesPagosConTarjConFact);
	    
        yAxisPagosConTarjConFact.setMin(0);
        yAxisPagosConTarjConFact.setMax(maxGanancias);
        barModelPagosConTarjConFact = modelPagosConTarjConFact; 
        /**********************************************************************/
		
        /**************************************************************************/
        utilTmpDate = searchFechaDesde; 
		BarChartModel modelPagosConTarjSinFact = new BarChartModel();	   	 
        ChartSeries seriesPagosConTarjSinFact = new ChartSeries();
        seriesPagosConTarjSinFact.setLabel("Ganancias");
        modelPagosConTarjSinFact.setTitle("Ganancias con tarjeta no facturado");
        modelPagosConTarjSinFact.setLegendPosition("ne");
        Axis xAxisPagosConTarjSinFact = modelPagosConTarjSinFact.getAxis(AxisType.X);
        xAxisPagosConTarjSinFact.setLabel("Fechas");
        Axis yAxisPagosConTarjSinFact = modelPagosConTarjSinFact.getAxis(AxisType.Y);
        yAxisPagosConTarjSinFact.setLabel("Numero Ordenes");
        
        maxGanancias = new BigDecimal(10); 
        while(utilTmpDate.compareTo(searchFechaHasta)!=1) {
        	java.sql.Date sqlTmpDate = new java.sql.Date(utilTmpDate.getTime());
        	BigDecimal bigDecimal = pagosOrdenesEstudiosV1Local.sumaPagosConTarjSinFact(sqlTmpDate); 
        	if(maxGanancias.compareTo(bigDecimal)==-1) {
        		maxGanancias = bigDecimal; 
        	}
        	seriesPagosConTarjSinFact.set(sdf.format(utilTmpDate), bigDecimal);
         	utilTmpDate = new java.util.Date(utilTmpDate.getTime()+(1000 * 60 * 60 * 24)); 
	    }
        modelPagosConTarjSinFact.addSeries(seriesPagosConTarjSinFact);
	    
        yAxisPagosConTarjSinFact.setMin(0);
        yAxisPagosConTarjSinFact.setMax(maxGanancias);
        barModelPagosConTarjSinFact = modelPagosConTarjSinFact; 
        /**********************************************************************/
        
        /**************************************************************************/
        utilTmpDate = searchFechaDesde; 
		BarChartModel modelPagosConEfectivo = new BarChartModel();	   	 
        ChartSeries seriesPagosConEfectivo = new ChartSeries();
        seriesPagosConEfectivo.setLabel("Ganancias");
        modelPagosConEfectivo.setTitle("Ganancias con efectivo");
        modelPagosConEfectivo.setLegendPosition("ne");
        Axis xAxisPagosConEfectivo = modelPagosConEfectivo.getAxis(AxisType.X);
        xAxisPagosConEfectivo.setLabel("Fechas");
        Axis yAxisPagosConEfectivo = modelPagosConEfectivo.getAxis(AxisType.Y);
        yAxisPagosConEfectivo.setLabel("Numero Ordenes");
        
        maxGanancias = new BigDecimal(10); 
        while(utilTmpDate.compareTo(searchFechaHasta)!=1) {
        	java.sql.Date sqlTmpDate = new java.sql.Date(utilTmpDate.getTime());
        	BigDecimal bigDecimal = pagosOrdenesEstudiosV1Local.sumaPagosConEfectivo(sqlTmpDate); 
        	if(maxGanancias.compareTo(bigDecimal)==-1) {
        		maxGanancias = bigDecimal; 
        	}
        	seriesPagosConEfectivo.set(sdf.format(utilTmpDate), bigDecimal);
         	utilTmpDate = new java.util.Date(utilTmpDate.getTime()+(1000 * 60 * 60 * 24)); 
	    }
        modelPagosConEfectivo.addSeries(seriesPagosConEfectivo);
	    
        yAxisPagosConEfectivo.setMin(0);
        yAxisPagosConEfectivo.setMax(maxGanancias);
        barModelPagosConEfectivo = modelPagosConEfectivo; 
        /**********************************************************************/
        
        /**************************************************************************/
        utilTmpDate = searchFechaDesde; 
		BarChartModel modelPagosConCLIP = new BarChartModel();	   	 
        ChartSeries seriesPagosConCLIP = new ChartSeries();
        seriesPagosConCLIP.setLabel("Ganancias");
        modelPagosConCLIP.setTitle("Ganancias con CLIP");
        modelPagosConCLIP.setLegendPosition("ne");
        Axis xAxisPagosConCLIP = modelPagosConCLIP.getAxis(AxisType.X);
        xAxisPagosConCLIP.setLabel("Fechas");
        Axis yAxisPagosConCLIP = modelPagosConCLIP.getAxis(AxisType.Y);
        yAxisPagosConCLIP.setLabel("Numero Ordenes");
        
        maxGanancias = new BigDecimal(10); 
        while(utilTmpDate.compareTo(searchFechaHasta)!=1) {
        	java.sql.Date sqlTmpDate = new java.sql.Date(utilTmpDate.getTime());
        	BigDecimal bigDecimal = pagosOrdenesEstudiosV1Local.sumaPagosConCLIP(sqlTmpDate); 
        	if(maxGanancias.compareTo(bigDecimal)==-1) {
        		maxGanancias = bigDecimal; 
        	}
        	seriesPagosConCLIP.set(sdf.format(utilTmpDate), bigDecimal);
         	utilTmpDate = new java.util.Date(utilTmpDate.getTime()+(1000 * 60 * 60 * 24)); 
	    }
        modelPagosConCLIP.addSeries(seriesPagosConCLIP);
	    
        yAxisPagosConCLIP.setMin(0);
        yAxisPagosConCLIP.setMax(maxGanancias);
        barModelPagosConCLIP = modelPagosConCLIP; 
        /**********************************************************************/
        
        
        
		System.out.println("Sale "+this.getClass()+" filtraPorFechas");
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
	
	public BarChartModel getBarModelPagosConTarjConFact() {
		return barModelPagosConTarjConFact;
	}

	public void setBarModelPagosConTarjConFact(BarChartModel barModelPagosConTarjConFact) {
		this.barModelPagosConTarjConFact = barModelPagosConTarjConFact;
	}

	public BarChartModel getBarModelPagosConTarjSinFact() {
		return barModelPagosConTarjSinFact;
	}

	public void setBarModelPagosConTarjSinFact(BarChartModel barModelPagosConTarjSinFact) {
		this.barModelPagosConTarjSinFact = barModelPagosConTarjSinFact;
	}

	public BarChartModel getBarModelPagosConEfectivo() {
		return barModelPagosConEfectivo;
	}

	public void setBarModelPagosConEfectivo(BarChartModel barModelPagosConEfectivo) {
		this.barModelPagosConEfectivo = barModelPagosConEfectivo;
	}

	public BarChartModel getBarModelPagosConCLIP() {
		return barModelPagosConCLIP;
	}

	public void setBarModelPagosConCLIP(BarChartModel barModelPagosConCLIP) {
		this.barModelPagosConCLIP = barModelPagosConCLIP;
	}
	
}

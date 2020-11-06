package com.dtecimax.ejb.backing.admin.as;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;

/**
 * Servlet implementation class Formato1CO
 */
@WebServlet(name="/Formato1CO", urlPatterns= {"/Formato1CO"})
public class Formato1CO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formato1CO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Inject 
    OrdenesEstudiosLocal ordenesEstudiosLocal; 
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("Entra doPost Formato1CO");
      String strAccionFormato1CO = request.getParameter("accionFormato1CO"); 
      System.out.println("strAccionFormato1CO:"+strAccionFormato1CO);
      if("postFormatoText".equals(strAccionFormato1CO)) {
    	  String strJsNumeroOrden = request.getParameter("jsNumeroOrden"); 
    	  Long longNumeroOrden = new Long(strJsNumeroOrden);
    	  String strFormatoText = ordenesEstudiosLocal.findFormatoText(longNumeroOrden); 
    	  System.out.println("Formato1CO doPost postFormatoText");
    	  System.out.println(strFormatoText);
    	  response.setContentType( "text/html; charset=UTF-8" );
          PrintWriter out = response.getWriter();
          out.println(strFormatoText);
          out.close();
        	
      }else {
    	  Long longSvNumeroOrden = (Long) request.getSession().getAttribute("svNumeroOrden"); 
          System.out.println("strsvNumeroOrden:"+longSvNumeroOrden);
          BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
          String json = "";
          if(br != null){
        	 json = br.readLine();
          }
          ordenesEstudiosLocal.updateFormato(longSvNumeroOrden, "Formato1", json);	  
      }
     
      System.out.println("Sale doPost Formato1CO ");
	}
   
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException  {
		 System.out.println("Entra doGet Formato1CO");
	      System.out.println("Sale doGet Formato1CO ");
		
       }
}
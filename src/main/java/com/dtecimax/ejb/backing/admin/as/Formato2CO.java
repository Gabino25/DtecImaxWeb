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
 * Servlet implementation class Formato2CO
 */
@WebServlet(name="/Formato2CO", urlPatterns= {"/Formato2CO"})
public class Formato2CO extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  @Inject 
	  OrdenesEstudiosLocal ordenesEstudiosLocal; 
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formato2CO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("Entra Formato2CO doPost");
       String strAccionFormato2CO = request.getParameter("accionFormato2CO"); 
       System.out.println("strAccionFormato2CO:"+strAccionFormato2CO);
       if("postFormatoText".equals(strAccionFormato2CO)) {
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
           ordenesEstudiosLocal.updateFormato(longSvNumeroOrden, "Formato2", json);	 
       }  
       System.out.println("Sale Formato2CO doPost");
	}

}

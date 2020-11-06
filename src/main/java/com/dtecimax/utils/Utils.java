package com.dtecimax.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {
	
	 /*Utilizamos el patron singleton, solo existen un objeto de tipo UsuarioService en Memoria */
	 private static String ddMMyyyyPattern = "dd-MM-yyyy";
	 private static String ddMMyyyyPatternV2 = "dd/MM/yyyy";
	 private static String ddMMyyyyPatternV3 = "dd/MM/yyyy HH:mm:ss";
	 private static String ddMMyyyyPatternV4 = "dd'-'MMMM'-'yyyy"; 
	 private static String yyyyMMddPattern = "yyyy-MM-dd"; 
	 private static String ddMMMyyyyPattern = "dd-MMM-yyyy";
	 private static String hhmmssaPatter="hh:mm:ss a";
	 private static String hhmmPatter="hh:mm";
	 private static SimpleDateFormat ddMMyyyysdfInstance;  
	 private static SimpleDateFormat ddMMyyyysdfInstanceV2;  
	 private static SimpleDateFormat ddMMyyyysdfInstanceV3;  
	 private static SimpleDateFormat ddMMyyyysdfInstanceV4;  
	 private static SimpleDateFormat yyyyMMddsdfInstance; 
	 private static SimpleDateFormat hhmmssasdfInstance;
	 private static SimpleDateFormat hhmmsdfInstance;
	 private static SimpleDateFormat ddMMMyyyysdfInstance;
	
	 
	 private static Locale enUSLocaleInstance;
	 private static NumberFormat enUSNumberFormatInstance; 
	 private final static String enUSPattern = "###,###.00";
	 private final static String enUSPatternV2 = "###,###.000";
	 private final static String enUSPatternV3 = "###,##0,000.00";
	 private final static String enUSPatternV4 = "###,###,000.00";
	
     private static DecimalFormat enUSDecimalFormatInstance; 
     private static DecimalFormat enUSDecimalFormatInstanceV2; 
     private static DecimalFormat enUSDecimalFormatInstanceV3; 
     private static DecimalFormat enUSDecimalFormatInstanceV4;
     
     
     
     public static DecimalFormat getenUSDecimalFormatInstance() {
    	 if(null==enUSDecimalFormatInstance) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstance) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstance = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstance.applyPattern(enUSPattern);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstance; 
     }
     
     public static DecimalFormat getenUSDecimalFormatInstanceV2() {
    	 if(null==enUSDecimalFormatInstanceV2) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstanceV2) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstanceV2 = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstanceV2.applyPattern(enUSPatternV2);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstanceV2; 
     }
     
     public static DecimalFormat getenUSDecimalFormatInstanceV3() {
    	 if(null==enUSDecimalFormatInstanceV3) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstanceV3) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstanceV3 = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstanceV3.applyPattern(enUSPatternV3);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstanceV3; 
     }
	 
     public static DecimalFormat getenUSDecimalFormatInstanceV4() {
    	 if(null==enUSDecimalFormatInstanceV4) {
			  synchronized(Utils.class) {
			  if(null==enUSDecimalFormatInstanceV4) {
				  NumberFormat numberFormat = getenUSFormatInstance(); 
				  enUSDecimalFormatInstanceV4 = (DecimalFormat)numberFormat;
				  enUSDecimalFormatInstanceV4.applyPattern(enUSPatternV4);
			  }
			 }
		 }
    	 return enUSDecimalFormatInstanceV4; 
     }
     
	 public static NumberFormat getenUSFormatInstance() {
		 if(null==enUSNumberFormatInstance) {
			  synchronized(Utils.class) {
			  if(null==enUSNumberFormatInstance) {
				  
				  if(null==enUSLocaleInstance) {
					  synchronized(Utils.class) {
					  if(null==enUSLocaleInstance) {
						  enUSLocaleInstance = new Locale("en", "US");
					  }
					 }
				   }
				  
				  enUSNumberFormatInstance = NumberFormat.getInstance(enUSLocaleInstance); 
			  }
			 }
		 }
		 return enUSNumberFormatInstance; 
	 }
	 
	 
	 public static SimpleDateFormat getddMMyyyysdfInstance() {
		 if(null==ddMMyyyysdfInstance) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstance) {
				  ddMMyyyysdfInstance = new SimpleDateFormat(ddMMyyyyPattern);
			  }
			 }
		 }
		 return ddMMyyyysdfInstance; 
	 }
	
	 public static SimpleDateFormat getddMMyyyysdfInstanceV2() {
		 if(null==ddMMyyyysdfInstanceV2) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstanceV2) {
				  ddMMyyyysdfInstanceV2 = new SimpleDateFormat(ddMMyyyyPatternV2);
			  }
			 }
		 }
		 return ddMMyyyysdfInstanceV2; 
	 }
	 
	 public static SimpleDateFormat getddMMyyyysdfInstanceV3() {
		 if(null==ddMMyyyysdfInstanceV3) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstanceV3) {
				  ddMMyyyysdfInstanceV3 = new SimpleDateFormat(ddMMyyyyPatternV3);
			  }
			 }
		 }
		 return ddMMyyyysdfInstanceV3; 
	 }
	 
	 public static SimpleDateFormat getddMMyyyysdfInstanceV4() {
		 if(null==ddMMyyyysdfInstanceV4) {
			  synchronized(Utils.class) {
			  if(null==ddMMyyyysdfInstanceV4) {
				  ddMMyyyysdfInstanceV4 = new SimpleDateFormat(ddMMyyyyPatternV4, new Locale("ES"));
			  }
			 }
		 }
		 return ddMMyyyysdfInstanceV4; 
	 }
	 
	 public static SimpleDateFormat getyyyyMMddsdfInstance() {
		 if(null==yyyyMMddsdfInstance) {
			  synchronized(Utils.class) {
			  if(null==yyyyMMddsdfInstance) {
				  yyyyMMddsdfInstance = new SimpleDateFormat(yyyyMMddPattern);
			  }
			 }
		 }
		 return yyyyMMddsdfInstance; 
	 }
	 public static SimpleDateFormat gethhmmssasdfInstance() {
		 if(null==hhmmssasdfInstance) {
			  synchronized(Utils.class) {
			  if(null==hhmmssasdfInstance) {
				  hhmmssasdfInstance = new SimpleDateFormat(hhmmssaPatter);
			  }
			 }
		 }
		 return hhmmssasdfInstance; 
	 }
	 public static SimpleDateFormat gethhmmsdfInstance() {
		 if(null==hhmmsdfInstance) {
			  synchronized(Utils.class) {
			  if(null==hhmmsdfInstance) {
				  hhmmsdfInstance = new SimpleDateFormat(hhmmPatter);
			  }
			 }
		 }
		 return hhmmsdfInstance; 
	 }
	 public static SimpleDateFormat getddMMMyyyysdfInstance() {
		 if(null==ddMMMyyyysdfInstance) {
			  synchronized(Utils.class) {
			  if(null==ddMMMyyyysdfInstance) {
				  ddMMMyyyysdfInstance = new SimpleDateFormat(ddMMMyyyyPattern);
			  }
			 }
		 }
		 return ddMMMyyyysdfInstance; 
	 }
	 
    
 	public static String getMes(int pIntMes) {
 		String strRetval = ""; 
 		if(1==pIntMes) {
 			strRetval = "Ene"; 
 		}else if(2==pIntMes) {
 			strRetval = "Feb"; 
 		}else if(3==pIntMes) {
 			strRetval = "Mar"; 
 		}else if(4==pIntMes) {
 			strRetval = "Abr"; 
 		}else if(5==pIntMes) {
 			strRetval = "May"; 
 		}else if(6==pIntMes) {
 			strRetval = "Jun"; 
 		}else if(7==pIntMes) {
 			strRetval = "Jul"; 
 		}else if(8==pIntMes) {
 			strRetval = "Ago"; 
 		}else if(9==pIntMes) {
 			strRetval = "Sep"; 
 		}else if(10==pIntMes) {
 			strRetval = "Oct"; 
 		}else if(11==pIntMes) {
 			strRetval = "Nov"; 
 		}else if(12==pIntMes) {
 			strRetval = "Dic"; 
 		}
 		return strRetval; 
 	}
 	
}

package com.vgmsistemas.vgminterface.servicesrest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public abstract class GenericWs {
   
   protected static final String AUTORIZATION = "Basic " + "dmdtcHJldmVudGE6cHJldmVudGEq";
   protected String token = "";
   protected int expiraEn = 0;
   protected int timeOut = 200000; /*En milisegundos*/
   
   protected Integer metodo;
   protected static final String VOLUMEN_DE_DATOS_ALTO = "ALTO";
   protected static final String VOLUMEN_DE_DATOS_MEDIO = "MEDIO";
   protected static final String VOLUMEN_DE_DATOS_BAJO = "BAJO";

   protected static final int NO_REINTENTAR = 0;
   protected final String tag = "genericRequest";
   protected final String tagToken = "token";
   public static final String KEY_TOKEN = "token";
   protected static final String TOKEN_INVALIDO = "401";
   public static final String PREFERENCIA = "preferencia_token";
   
   protected String url;
  
   /*
   @Autowired
	PropertiesService propertyService;
     
   public GenericWs(){
    url = propertyService.getUrlServicio();
   }
   
  public synchronized void refreshToken()throws Exception {
       
       try {      
			HttpResponse<JsonNode> response = Unirest.post(url + RutasServicios.SERVICE_AUTORIZATION)
					.header("Authorization", AUTORIZATION).header("Accept", "application/json")
					.field("grant_type", "password").field("username", "vgm").field("password", "abc123").asJson();
			token = response.getBody().getObject().getString("access_token").toString();
			expiraEn = Integer.parseInt(response.getBody().getObject().getString("expires_in").toString());

			
			propertyService.setKeyToken(token);
			propertyService.setExpiresIn(expiraEn);

		} catch (Exception ex) {
			ex.getStackTrace();
		}
   }*/
 
	  public String callWebService(String url,String client_id, String client_secret, String jsonClienteString) throws Exception {
	      
	      try {  Unirest.setTimeouts(0, 0); 
	    	  HttpResponse<String> response  = Unirest.post(url)
						.header("Content-Type", "application/json")
			    		.header("client_id", client_id)
			    		.header("client_secret", client_secret)
			    		.body(jsonClienteString)
			    		.asString();
	    	  	
			} catch (Exception ex) {
				ex.getStackTrace();
				throw ex;
			}
	      
 	     return "0";
	  }
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiraEn() {
		return expiraEn;
	}

	public void setExpiraEn(int expiraEn) {
		this.expiraEn = expiraEn;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public Integer getMetodo() {
		return metodo;
	}

	public void setMetodo(Integer metodo) {
		this.metodo = metodo;
	}

	/*public PropertiesService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(PropertiesService propertyService) {
		this.propertyService = propertyService;
	}*/

	public String getTag() {
		return tag;
	}

	public String getTagToken() {
		return tagToken;
	}

			  
}

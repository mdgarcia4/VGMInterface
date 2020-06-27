package com.vgmsistemas.vgminterface.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	@Value("${contact.apikey}")
	private String apiKeyGoogle;
	
	@Value("${servicios.url}")
	private String UrlServicio;
	
	private String keyToken;
	
	private Integer ExpiresIn;
	
	public String getApiKeyGoogle() {
		return apiKeyGoogle;
	}

	public void setApiKeyGoogle(String apiKeyGoogle) {
		this.apiKeyGoogle = apiKeyGoogle;
	}

	public String getUrlServicio() {
		return UrlServicio;
	}

	public void setUrlServicio(String urlServicio) {
		UrlServicio = urlServicio;
	}

	public String getKeyToken() {
		return keyToken;
	}

	public void setKeyToken(String keyToken) {
		this.keyToken = keyToken;
	}

	public Integer getExpiresIn() {
		return ExpiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		ExpiresIn = expiresIn;
	}
	
}

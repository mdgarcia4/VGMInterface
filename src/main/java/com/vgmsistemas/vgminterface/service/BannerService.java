package com.vgmsistemas.vgminterface.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Banner;
import com.vgmsistemas.vgminterface.repository.BannerRepo;

@Service
public class BannerService {

	@Autowired
	BannerRepo bannerRepo;

	public List<Banner> getByDePaginaAndSnActivo(String dePagina, String snActivo){
		List<Banner> banners;
		banners = bannerRepo.findByDePaginaAndSnActivo(dePagina, snActivo);
		return banners;
	}
	
}
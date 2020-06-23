package com.vgmsistemas.vgminterface.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Marca;
import com.vgmsistemas.vgminterface.repository.MarcaRepo;

@Service
public class MarcaService {

	@Autowired
	MarcaRepo marcaRepo;

	public List<Marca> getAll()  {

		List<Marca> marcas ;
		marcas = marcaRepo.findAll();
		return marcas;
	}
	
	public List<Marca> getBySnWeb(String sn){
		List<Marca> marcas;
		marcas = marcaRepo.findBySnWeb(sn);
		return marcas;
	}

	public MarcaRepo getMarcaRepo() {
		return marcaRepo;
	}

	public void setMarcaRepo(MarcaRepo marcaRepo) {
		this.marcaRepo = marcaRepo;
	}
	
	
	

	
}
package com.vgmsistemas.vgminterface.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Sucursal;
import com.vgmsistemas.vgminterface.repository.SucursalRepo;

@Service
public class SucursalService {

	@Autowired
	SucursalRepo sucursalRepo;

	public List<Sucursal> getAll(){
		List<Sucursal> sucursales;
		sucursales = sucursalRepo.findAll();
		for(Sucursal suc: sucursales) 
		{
			if(suc.getLatitud()== null) 
			{
				suc.setLatitud(0F);
			}
			if(suc.getLongitud()== null) 
			{
				suc.setLongitud(0F);
			}
		}
		return sucursales;
	}
	
}
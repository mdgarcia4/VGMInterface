package com.vgmsistemas.vgminterface.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Proveedor;
import com.vgmsistemas.vgminterface.repository.ProveedorRepo;


@Service
public class ProveedorService {

	@Autowired
	ProveedorRepo proveedorRepo;

	public List<Proveedor> getAll()  {

		List<Proveedor> proveedores ;
		proveedores = proveedorRepo.findAll();
		return proveedores;
	}
	
	public List<Proveedor> getBySnWeb(String sn){
		List<Proveedor> marcas;
		marcas = proveedorRepo.findBySnWeb(sn);
		return marcas;
	}

	public ProveedorRepo getProveedorRepo() {
		return proveedorRepo;
	}

	public void setProveedorRepo(ProveedorRepo proveedorRepo) {
		this.proveedorRepo = proveedorRepo;
	}

	
	
}
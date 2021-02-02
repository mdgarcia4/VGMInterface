package com.vgmsistemas.vgminterface.service.unilever;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonView;
import com.vgmsistemas.vgminterface.entity.Empresa;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.Proveedor;
import com.vgmsistemas.vgminterface.entity.ProveedorSucursal;
import com.vgmsistemas.vgminterface.entity.Sucursal;
import com.vgmsistemas.vgminterface.entity.unilever.Inventories;
import com.vgmsistemas.vgminterface.entity.unilever.Inventory;
import com.vgmsistemas.vgminterface.entity.unilever.Views;
import com.vgmsistemas.vgminterface.repository.EmpresaRepo;
import com.vgmsistemas.vgminterface.repository.MarcaRepo;
import com.vgmsistemas.vgminterface.repository.ParametroInterfaceRepo;
import com.vgmsistemas.vgminterface.repository.ProveedorRepo;
import com.vgmsistemas.vgminterface.repository.ProveedorSucursalRepo;
import com.vgmsistemas.vgminterface.repository.SucursalRepo;
import com.vgmsistemas.vgminterface.repository.unilever.InventoryRepo;
import com.vgmsistemas.vgminterface.repository.unilever.ProductRepo;
import com.vgmsistemas.vgminterface.servicesrest.InventoryWs;

@Service
public class InventoryService {
	@Value("${tamanioPagina}")
	private int tamanioPagina ;

	@Autowired
	ProductRepo productoRepo;
	
	@Autowired
	ParametroInterfaceRepo parametroInterfaceRepo;
	
	@Autowired
	ProveedorRepo proveedorRepo;
	
	@Autowired
	MarcaRepo marcaRepo;
	
	@Autowired
	SucursalRepo sucursalRepo;
	
	@Autowired
	InventoryRepo inventoryRepo;
	
	@Autowired
	EmpresaRepo empresaRepo;	
	
	@Autowired
	ProveedorSucursalRepo proveedorSucursalRepo;
	
	@Value("${lotesEsperaSegundos}")
	Integer lotesEsperaSegundos;
	
	@Value("${depositoDefault}")
	long idDeposito;
	
	@Value("${sucursalDefault}")
	long idSucursal;
	
	private static Logger LOG = LoggerFactory.getLogger(InventoryService.class)	;
	
	
	@JsonView(Views.Public.class)
	public Integer enviar(String tiEnvio) throws Exception {
		InventoryWs inventoryWs = new InventoryWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById(idSucursal);
		
		Optional<Empresa> empresa = empresaRepo.findBySnActivada("S");
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param ;
		
		// Optengo los parametros de la interface
		if (empresa.get().getTiImplementacionInterfaz().equals("1")   ) {
			param = parametroInterfaceRepo.findByIdEmpresaAndIdSucursal(empresa.get().getId(),idSucursal);
		} else { 
			// Optengo los parametros de la interface
			param = parametroInterfaceRepo.findByIdEmpresa(empresa.get().getId());
		}
		
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Inventory> listaInventory;
		if (tiEnvio == "TODOS") {
			listaInventory = inventoryRepo.findStock(idDeposito, "S","S");
		} else {
			listaInventory = inventoryRepo.findStock(idDeposito, "S","S",suc.get().getFechaSistema());
		}
		
		// Actualizo los campos en null y los agrego a la lista de productos
		Inventories stocks = new Inventories(listaInventory);
		
		//stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		if (empresa.get().getTiImplementacionInterfaz().equals("1")  ) {
			Optional<ProveedorSucursal> proveedorSucursal = proveedorSucursalRepo.findByIdSucursalAndIdProveedor(idSucursal, prov.get().getIdProveedor());
			if (proveedorSucursal.isPresent()) {
				stocks.setDistributor_code(String.valueOf(proveedorSucursal.get().getIdClienteProv()));
			} else {
				stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			}
			
		} else {
			stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		}
		
		// Envio al Web Service
	    return inventoryWs.callWebService(stocks, param);
	}

	@JsonView(Views.Public.class)
	public Integer enviarxLote(String tiEnvio) throws Exception {
		int totalPaginas;
		
		int result;
		Pageable pagina = PageRequest.of(0, tamanioPagina);
		InventoryWs inventoryWs = new InventoryWs();
		
		// Sucursales
		//Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		// Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		
		Optional<Empresa> empresa = empresaRepo.findBySnActivada("S");
		
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param ;
		
		// Optengo los parametros de la interface
		if (empresa.get().getTiImplementacionInterfaz().equals("1")   ) {
			param = parametroInterfaceRepo.findByIdEmpresaAndIdSucursal(empresa.get().getId(),idSucursal);
		} else { 
			// Optengo los parametros de la interface
			param = parametroInterfaceRepo.findByIdEmpresa(empresa.get().getId());
		}
		
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		Page<Inventory> paginaInventory;
		List<Inventory> listaInventory;
		
		paginaInventory = inventoryRepo.findStockByPagina(idDeposito,"S","S", pagina);
		totalPaginas = paginaInventory.getTotalPages();
		for ( int pag = 0; pag < totalPaginas; pag++ ) {
			
			// Indico pagina a leer
			pagina = PageRequest.of(pag, tamanioPagina);
			
			// Recupero la pagina
			if (tiEnvio == "TODOS") {
				paginaInventory = inventoryRepo.findStockByPagina(idDeposito,"S","S", pagina);
			} else {
				paginaInventory = inventoryRepo.findStockByPagina(idDeposito,"S","S", pagina);
			}
			
			listaInventory = paginaInventory.toList();
		
			// Actualizo los campos en null y los agrego a la lista de productos
			Inventories stocks = new Inventories(listaInventory);
		
			//stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			if (empresa.get().getTiImplementacionInterfaz().equals("1")  ) {
				Optional<ProveedorSucursal> proveedorSucursal = proveedorSucursalRepo.findByIdSucursalAndIdProveedor(idSucursal, prov.get().getIdProveedor());
				if (proveedorSucursal.isPresent()) {
					stocks.setDistributor_code(String.valueOf(proveedorSucursal.get().getIdClienteProv()));
				} else {
					stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
				}
				
			} else {
				stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			}
			
		
			// Envio al Web Service
		    result = inventoryWs.callWebService(stocks, param);
		    
		    Thread.sleep (lotesEsperaSegundos * 1000);
		}
		return 0;
	}
	
	
	public Integer enviarEAN(String ean) throws Exception {
		InventoryWs inventoryWs = new InventoryWs();
		
		// Sucursales
		//Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		// Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Optional<Empresa> empresa = empresaRepo.findBySnActivada("S");
		
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param ;
		
		// Optengo los parametros de la interface
		if (empresa.get().getTiImplementacionInterfaz().equals("1")   ) {
			param = parametroInterfaceRepo.findByIdEmpresaAndIdSucursal(empresa.get().getId(),idSucursal);
		} else { 
			// Optengo los parametros de la interface
			param = parametroInterfaceRepo.findByIdEmpresa(empresa.get().getId());
		}
		
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Inventory> listaInventory;

		if (ean==null) {
			listaInventory = inventoryRepo.findStockByEAN(idDeposito);			
		} else {
			listaInventory = inventoryRepo.findStockByEAN(idDeposito,ean);	
		}
		
		// Actualizo los campos en null y los agrego a la lista de productos
		Inventories stocks = new Inventories(listaInventory);
		
		//stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		if (empresa.get().getTiImplementacionInterfaz().equals("1")  ) {
			Optional<ProveedorSucursal> proveedorSucursal = proveedorSucursalRepo.findByIdSucursalAndIdProveedor(idSucursal, prov.get().getIdProveedor());
			if (proveedorSucursal.isPresent()) {
				stocks.setDistributor_code(String.valueOf(proveedorSucursal.get().getIdClienteProv()));
			} else {
				stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			}
			
		} else {
			stocks.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		}
		
		// Envio al Web Service
	    return inventoryWs.callWebService(stocks, param);
	}
	
	
}
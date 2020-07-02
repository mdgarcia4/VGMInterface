package com.vgmsistemas.vgminterface.service.unilever;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Marca;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.Proveedor;
import com.vgmsistemas.vgminterface.entity.unilever.Product;
import com.vgmsistemas.vgminterface.entity.unilever.Products;
import com.vgmsistemas.vgminterface.repository.MarcaRepo;
import com.vgmsistemas.vgminterface.repository.ParametroInterfaceRepo;
import com.vgmsistemas.vgminterface.repository.ProveedorRepo;
import com.vgmsistemas.vgminterface.repository.unilever.ProductRepo;
import com.vgmsistemas.vgminterface.servicesrest.ProductsWs;

@Service
public class ProductsService {

	@Autowired
	ProductRepo productoRepo;
	
	@Autowired
	ParametroInterfaceRepo parametroInterfaceRepo;
	
	@Autowired
	ProveedorRepo proveedorRepo;
	
	@Autowired
	MarcaRepo marcaRepo;
	//private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRestController.class)	;
	
	public Products leerProductos() throws Exception {
		
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		List<Product> listaProduct = productoRepo.findByIdProveedor(idProveedor);
		Products productos = new Products(listaProduct);
		
		return productos;
	}
	
	public Integer enviar() throws Exception {
		ProductsWs productsWs = new ProductsWs();
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Product> listaProduct = productoRepo.findByIdProveedor(idProveedor);
		
		// Actualizo los campos en null y los agrego a la lista de productos
		for (Product prod: listaProduct) {
			prod.setBrand(marcaRepo.findById(prod.getIdMarca()).get().getDescripcion());
			prod.setIndustry_name(prov.get().getDeProveedor());
			if (prod.getName_prod()== null) {
				prod.setName_prod(prod.getShort_description());
			}
			if (prod.getSnActivo().equals("S")) {
				prod.setStatus_prod("1");
			} else {
				prod.setStatus_prod("0");
			}
				
		}
		Products productos = new Products(listaProduct);
		
		productos.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		
		// Envio al Web Service
	    return productsWs.callWebService(productos, param);
	}
}
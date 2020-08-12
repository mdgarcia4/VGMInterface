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
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.Proveedor;
import com.vgmsistemas.vgminterface.entity.Sucursal;
import com.vgmsistemas.vgminterface.entity.unilever.Prices;
import com.vgmsistemas.vgminterface.entity.unilever.Product;
import com.vgmsistemas.vgminterface.entity.unilever.Products;
import com.vgmsistemas.vgminterface.entity.unilever.Views;
import com.vgmsistemas.vgminterface.repository.MarcaRepo;
import com.vgmsistemas.vgminterface.repository.ParametroInterfaceRepo;
import com.vgmsistemas.vgminterface.repository.ProveedorRepo;
import com.vgmsistemas.vgminterface.repository.SucursalRepo;
import com.vgmsistemas.vgminterface.repository.unilever.PriceRepo;
import com.vgmsistemas.vgminterface.repository.unilever.ProductRepo;
import com.vgmsistemas.vgminterface.servicesrest.PricesWs;
import com.vgmsistemas.vgminterface.servicesrest.ProductsWs;

@Service
public class ProductsService {
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
	PriceRepo precioRepo;
	
	private static Logger LOG = LoggerFactory.getLogger(ProductsService.class)	;
	
	public Products leerProductos() throws Exception {
		
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		List<Product> listaProduct = productoRepo.findByIdProveedor(idProveedor);
		Products productos = new Products(listaProduct);
		
		return productos;
	}
	
	@JsonView(Views.Public.class)
	public Integer enviarProductosxLote(String tiEnvio) throws Exception {
		int totalPaginas;
		int result;
		Pageable pagina = PageRequest.of(1, tamanioPagina);
		
		ProductsWs productsWs = new ProductsWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		Page<Product> paginaProduct;
		List<Product> listaProduct;
		
		if (tiEnvio == "TODOS") {
			paginaProduct = productoRepo.findProdByPagina(idProveedor,"S", pagina);
		} else {
			paginaProduct = productoRepo.findProdByActualizaPagina(idProveedor, "S", suc.get().getFechaSistema(), pagina);
		}
			
		totalPaginas = paginaProduct.getTotalPages();
		for ( int pag = 1; pag <= totalPaginas; pag++ ) {
			// Indico pagina a leer
			pagina = PageRequest.of(pag, tamanioPagina);
			// Recupero la pagina
			paginaProduct = productoRepo.findProdByPagina(idProveedor,"S", pagina); 
		
			listaProduct = paginaProduct.toList();
			
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
				LOG.info("EAN: " + prod.getEAN() + " Nombre Corto: " + prod.getShort_description() + " Nombre: " + prod.getName_prod());
				if (prod.getName_prod()==null)	{
					prod.setName_prod(prod.getShort_description());
				}
				LOG.info("EAN: " + prod.getEAN() + " Nombre Corto: " + prod.getShort_description() + " Nombre: " + prod.getName_prod());
				
			}
			Products productos = new Products(listaProduct);
			
			productos.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			
			// Envio al Web Service
		    result = productsWs.callWebService(productos, param);
		    if (result != 0) {
		    	LOG.info("El resultado del envio fue incorrecto.");
		    }
		}
		return 0;
	}
	
	@JsonView(Views.Public.class)
	public Integer enviar(String tiEnvio) throws Exception {
		ProductsWs productsWs = new ProductsWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Product> listaProduct;
		if (tiEnvio == "TODOS") {
			listaProduct = productoRepo.findProdByIdProveedor(idProveedor,"S");
		} else {
			listaProduct = productoRepo.findProdModByProveedorFeActualiza(idProveedor,"S",suc.get().getFechaSistema());
		}
		
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
			LOG.info("EAN: " + prod.getEAN() + " Nombre Corto: " + prod.getShort_description() + " Nombre: " + prod.getName_prod());
			if (prod.getName_prod()==null)	{
				prod.setName_prod(prod.getShort_description());
			}
			LOG.info("EAN: " + prod.getEAN() + " Nombre Corto: " + prod.getShort_description() + " Nombre: " + prod.getName_prod());
			
		}
		Products productos = new Products(listaProduct);
		
		productos.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		
		// Envio al Web Service
	    return productsWs.callWebService(productos, param);
	}
	
	@JsonView(Views.Public.class)
	public Integer enviarEAN(String ean) throws Exception {
		ProductsWs productsWs = new ProductsWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Product> listaProduct;
		
		listaProduct = productoRepo.findProdByEAN(ean);
				
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
			LOG.info("EAN: " + prod.getEAN() + " Nombre Corto: " + prod.getShort_description() + " Nombre: " + prod.getName_prod());
			if (prod.getName_prod()==null)	{
				prod.setName_prod(prod.getShort_description());
			}
			LOG.info("EAN: " + prod.getEAN() + " Nombre Corto: " + prod.getShort_description() + " Nombre: " + prod.getName_prod());
			
		}
		Products productos = new Products(listaProduct);
		
		productos.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		
		// Envio al Web Service
	    return productsWs.callWebService(productos, param);
	}
	
	@JsonView(Views.Public.class)
	public Integer enviarPrecios(String tiEnvio) throws Exception {
		PricesWs pricesWs = new PricesWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Product> listaProduct;
		if (tiEnvio == "TODOS") {
			listaProduct = productoRepo.findProdByIdProveedor(idProveedor,"S");
		} else {
			listaProduct = productoRepo.findProdModByProveedorFeActPrecioInterfaz(idProveedor,"S",suc.get().getFechaSistema());
		}
		Long id;
		
		// Actualizo los campos en null y los agrego a la lista de productos
		/*for (Product prod: listaProduct) {
			id = prod.getId();
			List<Price> precios = precioRepo.findPrecioByIdArticulo(id);
			prod.setPrices(precios);
		}*/
		Prices precios = new Prices(listaProduct);
		
		precios.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		
		// Envio al Web Service
	    return pricesWs.callWebService(precios, param);
	}
	
	@JsonView(Views.Public.class)
	public Integer enviarPreciosxLote(String tiEnvio) throws Exception {
		int totalPaginas;
		int result;
		Pageable pagina = PageRequest.of(1, tamanioPagina);
		
		PricesWs pricesWs = new PricesWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		Page<Product> paginaProduct;
		List<Product> listaProduct;
		
		if (tiEnvio == "TODOS") {
			paginaProduct = productoRepo.findProdByPagina(idProveedor,"S", pagina);
		} else {
			paginaProduct = productoRepo.findProdByActPrecioInterfazPagina(idProveedor, "S", suc.get().getFechaSistema(), pagina);
		}
		totalPaginas = paginaProduct.getTotalPages();
		for ( int pag = 1; pag <= totalPaginas; pag++ ) {
			// Indico pagina a leer
			pagina = PageRequest.of(pag, tamanioPagina);
			// Recupero la pagina
			paginaProduct = productoRepo.findProdByPagina(idProveedor,"S", pagina); 
		
			listaProduct = paginaProduct.toList();
		
			Prices precios = new Prices(listaProduct);
			
			precios.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			
			// Envio al Web Service
		    result = pricesWs.callWebService(precios, param);
		}
		return 0;
	}
	
	@JsonView(Views.Public.class)
	public Integer enviarPreciosProducto(String ean) throws Exception {
		PricesWs pricesWs = new PricesWs();
		
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById((long) 1);
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
		
		// Recupero la lista de productos
		List<Product> listaProduct;
		
		listaProduct = productoRepo.findProdByEAN(ean);
		
		Prices precios = new Prices(listaProduct);
		
		precios.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		
		// Envio al Web Service
	    return pricesWs.callWebService(precios, param);
	}

	public Optional<Product> leerProductos(Long id) {
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Long idProveedor = param.get().getIdProveedor();
		
		Optional<Product> product = productoRepo.findById(id);
		//Products productos = new Products(Product);
		
		return product;
	}
}
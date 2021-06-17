package com.ec.altioracorp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ec.altioracorp.entidades.Articulo;
import com.ec.altioracorp.entidades.DetalleOrden;
import com.ec.altioracorp.entidades.Orden;
import com.ec.altioracorp.repository.DetalleOrdenRepository;
import com.ec.altioracorp.repository.OrdenRepository;
import com.ec.altioracorp.utilitario.OrdenCrear;
import com.ec.altioracorp.utilitario.Respuesta;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class OrdenController {

	@Autowired
	private OrdenRepository ordenRepository;
	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;
	
	
	@RequestMapping(value = "/ordenes", method = RequestMethod.GET)
	public ResponseEntity<List<Orden>> ordenes() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Orden> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (List<Orden>) ordenRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<Orden>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<Orden>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/ordenesforcli/{dni}", method = RequestMethod.GET)
	public ResponseEntity<List<Orden>> ordenesforcli(@PathVariable String dni) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Orden> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (List<Orden>) ordenRepository.findByIdClienteCliCedulaLike("%" + dni + "%");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<Orden>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<Orden>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/crearorden", method = RequestMethod.POST)
	public ResponseEntity<?> crearorden(@RequestBody OrdenCrear valor) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		Respuesta respuesta = new Respuesta();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			Gson gson = new Gson();
			String JSON = gson.toJson(valor);
			System.out.println(" JSON ENVIO Cliente " + JSON);
			Orden orden = new Orden();
			orden.setIdCliente(valor.getIdCliente());
			orden.setOrdFecha(new Date());
			orden.setOrdDescripcion("S/N");
			ordenRepository.save(orden);
			
			DetalleOrden detalle= new DetalleOrden();
			
			for (Articulo item: valor.getIdrticulos()) {
				detalle= new DetalleOrden();
				detalle.setDetCantidad(new BigDecimal(1));
				detalle.setIdArticulo(item);
				detalle.setIdOrden(orden);
				detalleOrdenRepository.save(detalle);
			}
			
			
			respuesta = new Respuesta();
			respuesta.setCodigo(HttpStatus.OK.toString());
			respuesta.setDescripcion("CORRECTO");
		} catch (Exception e) {
			// TODO: handle exception
			respuesta = new Respuesta();
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			respuesta.setDescripcion("ERROR");
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<Respuesta>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<Respuesta>(respuesta, httpHeaders, HttpStatus.OK);
	}

}

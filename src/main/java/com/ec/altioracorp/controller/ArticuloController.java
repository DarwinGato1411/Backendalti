package com.ec.altioracorp.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
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
import com.ec.altioracorp.entidades.Cliente;
import com.ec.altioracorp.repository.ArticuloRepository;
import com.ec.altioracorp.repository.ClienteRepository;
import com.ec.altioracorp.utilitario.Respuesta;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ArticuloController {

	@Autowired
	private ArticuloRepository articuloRepository;

	@RequestMapping(value = "/articulos", method = RequestMethod.GET)
	public ResponseEntity<List<Articulo>> articulos() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Articulo> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (List<Articulo>) articuloRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<Articulo>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<Articulo>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/articuloslike/{valor}", method = RequestMethod.GET)
	public ResponseEntity<List<Articulo>> clientednilike(@PathVariable String valor) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Articulo> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (List<Articulo>) articuloRepository.findLikeCodigoNombre("%" + valor + "%", "%" + valor + "%");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<Articulo>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<Articulo>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/nuevoart", method = RequestMethod.POST)
	public ResponseEntity<?> nuevoart(@RequestBody Articulo valor) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		Respuesta respuesta = new Respuesta();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {
			if (valor.getIdArticulo() == 0) {
				articuloRepository.save(valor);
			}else {
				Articulo artRecup=articuloRepository.findByIdArticulo(valor.getIdArticulo());
				artRecup.setArtCodigo(valor.getArtCodigo());
				artRecup.setArtNombre(valor.getArtNombre());
				artRecup.setArtPrecio(valor.getArtPrecio());
				articuloRepository.save(valor);
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

	@RequestMapping(value = "/eliminarart", method = RequestMethod.POST)
	public ResponseEntity<?> eliminarart(@RequestBody Articulo valor) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		Respuesta respuesta = new Respuesta();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			articuloRepository.delete(valor);
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

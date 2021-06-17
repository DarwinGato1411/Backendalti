package com.ec.altioracorp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ec.altioracorp.entidades.DetalleOrden;
import com.ec.altioracorp.repository.DetalleOrdenRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class DetalleOrdenController {

	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;

	@RequestMapping(value = "/detorden/{idOrden}", method = RequestMethod.GET)
	public ResponseEntity<List<DetalleOrden>> ordenes(@PathVariable Integer idOrden) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<DetalleOrden> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {
			respuesta = (List<DetalleOrden>) detalleOrdenRepository.findByIdOrdenIdOrden(idOrden);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<DetalleOrden>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<DetalleOrden>>(respuesta, httpHeaders, HttpStatus.OK);
	}

}

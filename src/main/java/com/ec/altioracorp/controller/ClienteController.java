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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ec.altioracorp.entidades.Cliente;
import com.ec.altioracorp.repository.ClienteRepository;
import com.ec.altioracorp.utilitario.Respuesta;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> clientes() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Cliente> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (List<Cliente>) clienteRepository.findByCliNombreLike("%%");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<Cliente>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<Cliente>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/clientedni/{dni}", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> clientednilike(@PathVariable String dni) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Cliente> respuesta = new ArrayList();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (List<Cliente>) clienteRepository.findByCliCedulaLike("%" + dni + "%");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<List<Cliente>>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<List<Cliente>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/nuevocli", method = RequestMethod.POST)
	public ResponseEntity<?> nuevocli(@RequestBody Cliente valor) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		Respuesta respuesta = new Respuesta();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			Gson gson = new Gson();
			String JSON = gson.toJson(valor);
			System.out.println(" JSON ENVIO Cliente " + JSON);

			if (valor.getIdCliente() == 0) {
				clienteRepository.save(valor);
			} else {

				Cliente recup = clienteRepository.findByCliCedula(valor.getCliCedula());
				recup.setCliApellido(valor.getCliApellido());
				recup.setCliNombre(valor.getCliNombre());
				clienteRepository.save(recup);
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

	@RequestMapping(value = "/eliminarcli", method = RequestMethod.POST)
	public ResponseEntity<?> eliminarcli(@RequestBody Cliente valor) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		Respuesta respuesta = new Respuesta();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			clienteRepository.delete(valor);
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

	@RequestMapping(value = "/clienteporcedula/{dni}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> clienteforcedula(@PathVariable String dni) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		Cliente respuesta = new Cliente();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache, no-store, max-age=120, must-revalidate");
		try {

			respuesta = (Cliente) clienteRepository.findByCliCedula(dni);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR catalogues " + e.getMessage());
			httpHeaders.add("STATUS", "0");
			return new ResponseEntity<Cliente>(respuesta, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpHeaders.add("STATUS", "1");
		return new ResponseEntity<Cliente>(respuesta, httpHeaders, HttpStatus.OK);
	}

}

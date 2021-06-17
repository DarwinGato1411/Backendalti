package com.ec.altioracorp.utilitario;

import java.util.List;

import com.ec.altioracorp.entidades.Articulo;
import com.ec.altioracorp.entidades.Cliente;

public class OrdenCrear {

	private Cliente idCliente;
	private List<Articulo> idrticulos;
	public OrdenCrear(Cliente idCliente, List<Articulo> idrticulos) {
		super();
		this.idCliente = idCliente;
		this.idrticulos = idrticulos;
	}
	public Cliente getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	public List<Articulo> getIdrticulos() {
		return idrticulos;
	}
	public void setIdrticulos(List<Articulo> idrticulos) {
		this.idrticulos = idrticulos;
	}
	
	
}

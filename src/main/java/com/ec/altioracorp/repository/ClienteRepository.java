package com.ec.altioracorp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ec.altioracorp.entidades.Cliente;

/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	List<Cliente> findByCliNombreLike(String nombre);
	Cliente findByCliCedula(String dni);
	
	List<Cliente> findByCliCedulaLike(String dni);
}

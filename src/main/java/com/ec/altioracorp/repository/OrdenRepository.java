package com.ec.altioracorp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ec.altioracorp.entidades.Cliente;
import com.ec.altioracorp.entidades.Orden;

/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdenRepository extends CrudRepository<Orden, Long> {

	List<Orden> findByOrdNumero(Integer numero);
	List<Orden> findByIdClienteCliCedulaLike(String cedula);
}

package com.ec.altioracorp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ec.altioracorp.entidades.Cliente;
import com.ec.altioracorp.entidades.DetalleOrden;
import com.ec.altioracorp.entidades.Orden;

/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetalleOrdenRepository extends CrudRepository<DetalleOrden, Long> {

	List<DetalleOrden> findByIdOrdenIdOrden(Integer idOrden);
}

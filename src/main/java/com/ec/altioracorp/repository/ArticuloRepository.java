package com.ec.altioracorp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ec.altioracorp.entidades.Articulo;

/**
 * Spring Data JPA repository for the Products entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {

	List<Articulo> findByArtNombre(String nombre);
	Articulo findByIdArticulo(Integer idArticulo);
	@Query(value = "SELECT a FROM Articulo a WHERE  a.artCodigo LIKE :artCodigo OR  a.artNombre LIKE :artNombre ")
	List<Articulo> findLikeCodigoNombre(@Param("artCodigo") String turEstado,@Param("artNombre") String turNombre);

	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.altioracorp.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_articulo")
	private Integer idArticulo;
	@Column(name = "art_codigo")
	private String artCodigo;
	@Column(name = "art_nombre")
	private String artNombre;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "art_precio")
	private BigDecimal artPrecio;
	@JsonIgnore
	@OneToMany(mappedBy = "idArticulo")
	private Collection<DetalleOrden> detalleOrdenCollection;

	public Articulo() {
	}

	public Articulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getArtCodigo() {
		return artCodigo;
	}

	public void setArtCodigo(String artCodigo) {
		this.artCodigo = artCodigo;
	}

	public String getArtNombre() {
		return artNombre;
	}

	public void setArtNombre(String artNombre) {
		this.artNombre = artNombre;
	}

	public BigDecimal getArtPrecio() {
		return artPrecio;
	}

	public void setArtPrecio(BigDecimal artPrecio) {
		this.artPrecio = artPrecio;
	}

	@XmlTransient
	public Collection<DetalleOrden> getDetalleOrdenCollection() {
		return detalleOrdenCollection;
	}

	public void setDetalleOrdenCollection(Collection<DetalleOrden> detalleOrdenCollection) {
		this.detalleOrdenCollection = detalleOrdenCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idArticulo != null ? idArticulo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Articulo)) {
			return false;
		}
		Articulo other = (Articulo) object;
		if ((this.idArticulo == null && other.idArticulo != null)
				|| (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ec.entidades.Articulo[ idArticulo=" + idArticulo + " ]";
	}

}

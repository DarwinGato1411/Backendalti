/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.altioracorp.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "orden")
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden")
    private Integer idOrden;
    @Column(name = "ord_numero")
    private Integer ordNumero;
    @Column(name = "ord_fecha")
    @Temporal(TemporalType.DATE)
    private Date ordFecha;
    @Column(name = "ord_descripcion")
    private String ordDescripcion;
    @Column(name = "ord_total")
    private BigDecimal ordTotal;
    
    @OneToMany(mappedBy = "idOrden")
    @JsonIgnore
    private Collection<DetalleOrden> detalleOrdenCollection;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente idCliente;

    public Orden() {
    }

    public Orden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getOrdNumero() {
        return ordNumero;
    }

    public void setOrdNumero(Integer ordNumero) {
        this.ordNumero = ordNumero;
    }

    public Date getOrdFecha() {
        return ordFecha;
    }

    public void setOrdFecha(Date ordFecha) {
        this.ordFecha = ordFecha;
    }

    public String getOrdDescripcion() {
        return ordDescripcion;
    }

    public void setOrdDescripcion(String ordDescripcion) {
        this.ordDescripcion = ordDescripcion;
    }

    @XmlTransient
    public Collection<DetalleOrden> getDetalleOrdenCollection() {
        return detalleOrdenCollection;
    }

    public void setDetalleOrdenCollection(Collection<DetalleOrden> detalleOrdenCollection) {
        this.detalleOrdenCollection = detalleOrdenCollection;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getOrdTotal() {
		return ordTotal;
	}

	public void setOrdTotal(BigDecimal ordTotal) {
		this.ordTotal = ordTotal;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidades.Orden[ idOrden=" + idOrden + " ]";
    }
    
}

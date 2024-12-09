/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "detalle_reservacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleReservacion.findAll", query = "SELECT d FROM DetalleReservacion d"),
    @NamedQuery(name = "DetalleReservacion.findByNumDetalle", query = "SELECT d FROM DetalleReservacion d WHERE d.numDetalle = :numDetalle")})
public class DetalleReservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numDetalle")
    private Integer numDetalle;
    @JoinColumn(name = "numHab", referencedColumnName = "numHab")
    @ManyToOne(optional = false)
    private Habitacion numHab;
    @JoinColumn(name = "numReserva", referencedColumnName = "numReserva")
    @ManyToOne(optional = false)
    private Reservacion numReserva;

    public DetalleReservacion() {
    }

    public DetalleReservacion(Integer numDetalle) {
        this.numDetalle = numDetalle;
    }

    public Integer getNumDetalle() {
        return numDetalle;
    }

    public void setNumDetalle(Integer numDetalle) {
        this.numDetalle = numDetalle;
    }

    public Habitacion getNumHab() {
        return numHab;
    }

    public void setNumHab(Habitacion numHab) {
        this.numHab = numHab;
    }

    public Reservacion getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(Reservacion numReserva) {
        this.numReserva = numReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDetalle != null ? numDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleReservacion)) {
            return false;
        }
        DetalleReservacion other = (DetalleReservacion) object;
        if ((this.numDetalle == null && other.numDetalle != null) || (this.numDetalle != null && !this.numDetalle.equals(other.numDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleReservacion[ numDetalle=" + numDetalle + " ]";
    }
    
}

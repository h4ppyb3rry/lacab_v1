/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "reporteinci")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporteinci.findAll", query = "SELECT r FROM Reporteinci r"),
    @NamedQuery(name = "Reporteinci.findByNumReporte", query = "SELECT r FROM Reporteinci r WHERE r.numReporte = :numReporte"),
    @NamedQuery(name = "Reporteinci.findByDescripcion", query = "SELECT r FROM Reporteinci r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Reporteinci.findByFecha", query = "SELECT r FROM Reporteinci r WHERE r.fecha = :fecha")})
public class Reporteinci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numReporte")
    private Integer numReporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "numReserva", referencedColumnName = "numReserva")
    @ManyToOne(optional = false)
    private Reservacion numReserva;

    public Reporteinci() {
    }

    public Reporteinci(Integer numReporte) {
        this.numReporte = numReporte;
    }

    public Reporteinci(Integer numReporte, String descripcion, Date fecha) {
        this.numReporte = numReporte;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getNumReporte() {
        return numReporte;
    }

    public void setNumReporte(Integer numReporte) {
        this.numReporte = numReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (numReporte != null ? numReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporteinci)) {
            return false;
        }
        Reporteinci other = (Reporteinci) object;
        if ((this.numReporte == null && other.numReporte != null) || (this.numReporte != null && !this.numReporte.equals(other.numReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reporteinci[ numReporte=" + numReporte + " ]";
    }
    
}

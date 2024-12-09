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
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m"),
    @NamedQuery(name = "Mantenimiento.findByNumReporte", query = "SELECT m FROM Mantenimiento m WHERE m.numReporte = :numReporte"),
    @NamedQuery(name = "Mantenimiento.findByDescripcion", query = "SELECT m FROM Mantenimiento m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Mantenimiento.findByFecha", query = "SELECT m FROM Mantenimiento m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mantenimiento.findByResponsable", query = "SELECT m FROM Mantenimiento m WHERE m.responsable = :responsable")})
public class Mantenimiento implements Serializable {

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
    @Size(max = 255)
    @Column(name = "responsable")
    private String responsable;
    @JoinColumn(name = "numHab", referencedColumnName = "numHab")
    @ManyToOne(optional = false)
    private Habitacion numHab;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer numReporte) {
        this.numReporte = numReporte;
    }

    public Mantenimiento(Integer numReporte, String descripcion, Date fecha) {
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Habitacion getNumHab() {
        return numHab;
    }

    public void setNumHab(Habitacion numHab) {
        this.numHab = numHab;
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
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.numReporte == null && other.numReporte != null) || (this.numReporte != null && !this.numReporte.equals(other.numReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Mantenimiento[ numReporte=" + numReporte + " ]";
    }
    
}

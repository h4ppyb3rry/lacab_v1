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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n"),
    @NamedQuery(name = "Nota.findByNumNota", query = "SELECT n FROM Nota n WHERE n.numNota = :numNota"),
    @NamedQuery(name = "Nota.findByFechaEmision", query = "SELECT n FROM Nota n WHERE n.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Nota.findByImporteTotal", query = "SELECT n FROM Nota n WHERE n.importeTotal = :importeTotal")})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numNota")
    private Integer numNota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_total")
    private long importeTotal;
    @JoinColumn(name = "numPersonal", referencedColumnName = "numPersonal")
    @ManyToOne
    private Personal numPersonal;
    @JoinColumn(name = "numReserva", referencedColumnName = "numReserva")
    @ManyToOne(optional = false)
    private Reservacion numReserva;

    public Nota() {
    }

    public Nota(Integer numNota) {
        this.numNota = numNota;
    }

    public Nota(Integer numNota, Date fechaEmision, long importeTotal) {
        this.numNota = numNota;
        this.fechaEmision = fechaEmision;
        this.importeTotal = importeTotal;
    }

    public Integer getNumNota() {
        return numNota;
    }

    public void setNumNota(Integer numNota) {
        this.numNota = numNota;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public long getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(long importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Personal getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(Personal numPersonal) {
        this.numPersonal = numPersonal;
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
        hash += (numNota != null ? numNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.numNota == null && other.numNota != null) || (this.numNota != null && !this.numNota.equals(other.numNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Nota[ numNota=" + numNota + " ]";
    }
    
}

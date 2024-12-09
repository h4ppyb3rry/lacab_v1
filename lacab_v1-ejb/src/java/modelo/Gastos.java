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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByNumGasto", query = "SELECT g FROM Gastos g WHERE g.numGasto = :numGasto"),
    @NamedQuery(name = "Gastos.findByDetalles", query = "SELECT g FROM Gastos g WHERE g.detalles = :detalles"),
    @NamedQuery(name = "Gastos.findByMonto", query = "SELECT g FROM Gastos g WHERE g.monto = :monto"),
    @NamedQuery(name = "Gastos.findByTipoGasto", query = "SELECT g FROM Gastos g WHERE g.tipoGasto = :tipoGasto")})
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numGasto")
    private Integer numGasto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "detalles")
    private String detalles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_gasto")
    private String tipoGasto;
    @JoinColumn(name = "numPersonal", referencedColumnName = "numPersonal")
    @ManyToOne
    private Personal numPersonal;

    public Gastos() {
    }

    public Gastos(Integer numGasto) {
        this.numGasto = numGasto;
    }

    public Gastos(Integer numGasto, String detalles, float monto, String tipoGasto) {
        this.numGasto = numGasto;
        this.detalles = detalles;
        this.monto = monto;
        this.tipoGasto = tipoGasto;
    }

    public Integer getNumGasto() {
        return numGasto;
    }

    public void setNumGasto(Integer numGasto) {
        this.numGasto = numGasto;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Personal getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(Personal numPersonal) {
        this.numPersonal = numPersonal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numGasto != null ? numGasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.numGasto == null && other.numGasto != null) || (this.numGasto != null && !this.numGasto.equals(other.numGasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Gastos[ numGasto=" + numGasto + " ]";
    }
    
}

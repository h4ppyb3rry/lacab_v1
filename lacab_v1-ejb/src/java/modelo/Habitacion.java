/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "habitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h"),
    @NamedQuery(name = "Habitacion.findByNumHab", query = "SELECT h FROM Habitacion h WHERE h.numHab = :numHab"),
    @NamedQuery(name = "Habitacion.findByTarifa", query = "SELECT h FROM Habitacion h WHERE h.tarifa = :tarifa"),
    @NamedQuery(name = "Habitacion.findByCapacidad", query = "SELECT h FROM Habitacion h WHERE h.capacidad = :capacidad"),
    @NamedQuery(name = "Habitacion.findByNumCamas", query = "SELECT h FROM Habitacion h WHERE h.numCamas = :numCamas"),
    @NamedQuery(name = "Habitacion.findByPiso", query = "SELECT h FROM Habitacion h WHERE h.piso = :piso"),
    @NamedQuery(name = "Habitacion.findByEstado", query = "SELECT h FROM Habitacion h WHERE h.estado = :estado"),
    @NamedQuery(name = "Habitacion.findByTv", query = "SELECT h FROM Habitacion h WHERE h.tv = :tv")})
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numHab")
    private Integer numHab;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarifa")
    private long tarifa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_camas")
    private int numCamas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "piso")
    private int piso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tv")
    private boolean tv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numHab")
    private List<DetalleReservacion> detalleReservacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numHab")
    private List<Mantenimiento> mantenimientoList;

    public Habitacion() {
    }

    public Habitacion(Integer numHab) {
        this.numHab = numHab;
    }

    public Habitacion(Integer numHab, long tarifa, int capacidad, int numCamas, int piso, String estado, boolean tv) {
        this.numHab = numHab;
        this.tarifa = tarifa;
        this.capacidad = capacidad;
        this.numCamas = numCamas;
        this.piso = piso;
        this.estado = estado;
        this.tv = tv;
    }

    public Integer getNumHab() {
        return numHab;
    }

    public void setNumHab(Integer numHab) {
        this.numHab = numHab;
    }

    public long getTarifa() {
        return tarifa;
    }

    public void setTarifa(long tarifa) {
        this.tarifa = tarifa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    @XmlTransient
    public List<DetalleReservacion> getDetalleReservacionList() {
        return detalleReservacionList;
    }

    public void setDetalleReservacionList(List<DetalleReservacion> detalleReservacionList) {
        this.detalleReservacionList = detalleReservacionList;
    }

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numHab != null ? numHab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.numHab == null && other.numHab != null) || (this.numHab != null && !this.numHab.equals(other.numHab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Habitacion[ numHab=" + numHab + " ]";
    }
    
}

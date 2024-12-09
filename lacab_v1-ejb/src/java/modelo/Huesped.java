/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "huesped")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Huesped.findAll", query = "SELECT h FROM Huesped h"),
    @NamedQuery(name = "Huesped.findByNumHuesped", query = "SELECT h FROM Huesped h WHERE h.numHuesped = :numHuesped"),
    @NamedQuery(name = "Huesped.findByNombre", query = "SELECT h FROM Huesped h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Huesped.findByOcupacion", query = "SELECT h FROM Huesped h WHERE h.ocupacion = :ocupacion"),
    @NamedQuery(name = "Huesped.findByResidencia", query = "SELECT h FROM Huesped h WHERE h.residencia = :residencia"),
    @NamedQuery(name = "Huesped.findByDomicilio", query = "SELECT h FROM Huesped h WHERE h.domicilio = :domicilio"),
    @NamedQuery(name = "Huesped.findByNacionalidad", query = "SELECT h FROM Huesped h WHERE h.nacionalidad = :nacionalidad")})
public class Huesped implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numHuesped")
    private Integer numHuesped;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 60)
    @Column(name = "ocupacion")
    private String ocupacion;
    @Size(max = 255)
    @Column(name = "residencia")
    private String residencia;
    @Size(max = 255)
    @Column(name = "domicilio")
    private String domicilio;
    @Size(max = 255)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @OneToMany(mappedBy = "numHuesped")
    private List<Reservacion> reservacionList;

    public Huesped() {
    }

    public Huesped(Integer numHuesped) {
        this.numHuesped = numHuesped;
    }

    public Integer getNumHuesped() {
        return numHuesped;
    }

    public void setNumHuesped(Integer numHuesped) {
        this.numHuesped = numHuesped;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public List<Reservacion> getReservacionList() {
        return reservacionList;
    }

    public void setReservacionList(List<Reservacion> reservacionList) {
        this.reservacionList = reservacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numHuesped != null ? numHuesped.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Huesped)) {
            return false;
        }
        Huesped other = (Huesped) object;
        if ((this.numHuesped == null && other.numHuesped != null) || (this.numHuesped != null && !this.numHuesped.equals(other.numHuesped))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Huesped[ numHuesped=" + numHuesped + " ]";
    }
    
}

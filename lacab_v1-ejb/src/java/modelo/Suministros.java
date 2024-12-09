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
@Table(name = "suministros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suministros.findAll", query = "SELECT s FROM Suministros s"),
    @NamedQuery(name = "Suministros.findByNumSuministro", query = "SELECT s FROM Suministros s WHERE s.numSuministro = :numSuministro"),
    @NamedQuery(name = "Suministros.findByNombre", query = "SELECT s FROM Suministros s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Suministros.findByExistencia", query = "SELECT s FROM Suministros s WHERE s.existencia = :existencia"),
    @NamedQuery(name = "Suministros.findByFechaUpdt", query = "SELECT s FROM Suministros s WHERE s.fechaUpdt = :fechaUpdt"),
    @NamedQuery(name = "Suministros.findByCategoria", query = "SELECT s FROM Suministros s WHERE s.categoria = :categoria")})
public class Suministros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numSuministro")
    private Integer numSuministro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencia")
    private int existencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_updt")
    @Temporal(TemporalType.DATE)
    private Date fechaUpdt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categoria")
    private String categoria;

    public Suministros() {
    }

    public Suministros(Integer numSuministro) {
        this.numSuministro = numSuministro;
    }

    public Suministros(Integer numSuministro, String nombre, int existencia, Date fechaUpdt, String categoria) {
        this.numSuministro = numSuministro;
        this.nombre = nombre;
        this.existencia = existencia;
        this.fechaUpdt = fechaUpdt;
        this.categoria = categoria;
    }

    public Integer getNumSuministro() {
        return numSuministro;
    }

    public void setNumSuministro(Integer numSuministro) {
        this.numSuministro = numSuministro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public Date getFechaUpdt() {
        return fechaUpdt;
    }

    public void setFechaUpdt(Date fechaUpdt) {
        this.fechaUpdt = fechaUpdt;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSuministro != null ? numSuministro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suministros)) {
            return false;
        }
        Suministros other = (Suministros) object;
        if ((this.numSuministro == null && other.numSuministro != null) || (this.numSuministro != null && !this.numSuministro.equals(other.numSuministro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Suministros[ numSuministro=" + numSuministro + " ]";
    }
    
}

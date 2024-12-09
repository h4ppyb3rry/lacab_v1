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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByNumPersonal", query = "SELECT p FROM Personal p WHERE p.numPersonal = :numPersonal"),
    @NamedQuery(name = "Personal.findByUsuario", query = "SELECT p FROM Personal p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "Personal.findByPassword", query = "SELECT p FROM Personal p WHERE p.password = :password"),
    @NamedQuery(name = "Personal.findByNombre", query = "SELECT p FROM Personal p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personal.findByCelular", query = "SELECT p FROM Personal p WHERE p.celular = :celular"),
    @NamedQuery(name = "Personal.findByDireccion", query = "SELECT p FROM Personal p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Personal.findByAnioIngreso", query = "SELECT p FROM Personal p WHERE p.anioIngreso = :anioIngreso"),
    @NamedQuery(name = "Personal.findByTurno", query = "SELECT p FROM Personal p WHERE p.turno = :turno"),
    @NamedQuery(name = "Personal.findByCedula", query = "SELECT p FROM Personal p WHERE p.cedula = :cedula")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numPersonal")
    private Integer numPersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_ingreso")
    private int anioIngreso;
    @Size(max = 50)
    @Column(name = "turno")
    private String turno;
    @Size(max = 8)
    @Column(name = "cedula")
    private String cedula;
    @OneToMany(mappedBy = "numPersonal")
    private List<Nota> notaList;
    @OneToMany(mappedBy = "numPersonal")
    private List<Gastos> gastosList;

    public Personal() {
    }

    public Personal(Integer numPersonal) {
        this.numPersonal = numPersonal;
    }

    public Personal(Integer numPersonal, String usuario, String password, String nombre, String celular, String direccion, int anioIngreso) {
        this.numPersonal = numPersonal;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
        this.anioIngreso = anioIngreso;
    }

    public Integer getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(Integer numPersonal) {
        this.numPersonal = numPersonal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @XmlTransient
    public List<Gastos> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<Gastos> gastosList) {
        this.gastosList = gastosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numPersonal != null ? numPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.numPersonal == null && other.numPersonal != null) || (this.numPersonal != null && !this.numPersonal.equals(other.numPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Personal[ numPersonal=" + numPersonal + " ]";
    }
    
}

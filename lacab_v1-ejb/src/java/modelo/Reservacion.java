/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "reservacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservacion.findAll", query = "SELECT r FROM Reservacion r"),
    @NamedQuery(name = "Reservacion.findByNumReserva", query = "SELECT r FROM Reservacion r WHERE r.numReserva = :numReserva"),
    @NamedQuery(name = "Reservacion.findByNumHabitaciones", query = "SELECT r FROM Reservacion r WHERE r.numHabitaciones = :numHabitaciones"),
    @NamedQuery(name = "Reservacion.findByNumPersonas", query = "SELECT r FROM Reservacion r WHERE r.numPersonas = :numPersonas"),
    @NamedQuery(name = "Reservacion.findByFechaLlegada", query = "SELECT r FROM Reservacion r WHERE r.fechaLlegada = :fechaLlegada"),
    @NamedQuery(name = "Reservacion.findByFechaSalida", query = "SELECT r FROM Reservacion r WHERE r.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Reservacion.findByDeposito", query = "SELECT r FROM Reservacion r WHERE r.deposito = :deposito"),
    @NamedQuery(name = "Reservacion.findByEstado", query = "SELECT r FROM Reservacion r WHERE r.estado = :estado"),
    @NamedQuery(name = "Reservacion.findByNecesEsp", query = "SELECT r FROM Reservacion r WHERE r.necesEsp = :necesEsp")})
public class Reservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numReserva")
    private Integer numReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_habitaciones")
    private int numHabitaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_personas")
    private int numPersonas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_llegada")
    @Temporal(TemporalType.DATE)
    private Date fechaLlegada;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deposito")
    private boolean deposito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "neces_esp")
    private boolean necesEsp;
    @JoinColumn(name = "numHuesped", referencedColumnName = "numHuesped")
    @ManyToOne
    private Huesped numHuesped;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numReserva")
    private List<Reporteinci> reporteinciList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numReserva")
    private List<DetalleReservacion> detalleReservacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numReserva")
    private List<Nota> notaList;

    public Reservacion() {
    }

    public Reservacion(Integer numReserva) {
        this.numReserva = numReserva;
    }

    public Reservacion(Integer numReserva, int numHabitaciones, int numPersonas, Date fechaLlegada, boolean deposito, String estado, boolean necesEsp) {
        this.numReserva = numReserva;
        this.numHabitaciones = numHabitaciones;
        this.numPersonas = numPersonas;
        this.fechaLlegada = fechaLlegada;
        this.deposito = deposito;
        this.estado = estado;
        this.necesEsp = necesEsp;
    }

    public Integer getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(Integer numReserva) {
        this.numReserva = numReserva;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean getDeposito() {
        return deposito;
    }

    public void setDeposito(boolean deposito) {
        this.deposito = deposito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getNecesEsp() {
        return necesEsp;
    }

    public void setNecesEsp(boolean necesEsp) {
        this.necesEsp = necesEsp;
    }

    public Huesped getNumHuesped() {
        return numHuesped;
    }

    public void setNumHuesped(Huesped numHuesped) {
        this.numHuesped = numHuesped;
    }

    @XmlTransient
    public List<Reporteinci> getReporteinciList() {
        return reporteinciList;
    }

    public void setReporteinciList(List<Reporteinci> reporteinciList) {
        this.reporteinciList = reporteinciList;
    }

    @XmlTransient
    public List<DetalleReservacion> getDetalleReservacionList() {
        return detalleReservacionList;
    }

    public void setDetalleReservacionList(List<DetalleReservacion> detalleReservacionList) {
        this.detalleReservacionList = detalleReservacionList;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numReserva != null ? numReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservacion)) {
            return false;
        }
        Reservacion other = (Reservacion) object;
        if ((this.numReserva == null && other.numReserva != null) || (this.numReserva != null && !this.numReserva.equals(other.numReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reservacion[ numReserva=" + numReserva + " ]";
    }
    
}

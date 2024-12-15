/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manipuladatos.MDSuministros;
import modelo.Suministros;

/**
 *
 * @author Paula
 */
@Named(value = "aDSuministros")
@SessionScoped
public class ADSuministros implements Serializable {

    @EJB
    private MDSuministros mDSuministros;

    private Suministros suministro;

    public Suministros getSuministro() {
        return suministro;
    }

    public void setReporteI(Suministros suministro) {
        this.suministro = suministro;
    }

    public List<Suministros> getSuministros() {
        return mDSuministros.suministros();
    }

    public void creaSuministro() {
        suministro = new Suministros();
    }

    public String nuevoSuministro() {

        suministro.setFechaUpdt(new Date());

        mDSuministros.agregarSuministro(suministro);
        return "inventario.xhtml?faces-redirect=true";
    }
    
    public String editarSuministro() {

        suministro.setFechaUpdt(new Date());

        mDSuministros.editarSuministro(suministro);
        return "inventario.xhtml?faces-redirect=true";
    }

    public String nSuministro() {
        creaSuministro();
        return "suministro_nuevo.xhtml?faces-redirect=true";
    }
    
    
    public String buscarSumPorId(int i) {
        suministro = mDSuministros.buscarSumPorId(i);
        if (suministro == null) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "error", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        return "editar_sum.xhtml?faces-redirect=true"; 

    }

    public ADSuministros() {
        creaSuministro();
    }

}

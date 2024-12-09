/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDHuesped;
import modelo.Huesped;

/**
 *
 * @author Paula
 */
@Named(value = "aDHuesped")
@SessionScoped
public class ADHuesped implements Serializable {

    @EJB
    private MDHuesped mDHuesped;
    
    private Huesped huesped;

    public void creaHuesped() {
        huesped = new Huesped();
    }

    public List<Huesped> getHuespedes() {
        return mDHuesped.huespedes();
    }

    public void registroHuesped() {
        mDHuesped.insertarHuesped(huesped);
        creaHuesped();
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
    public ADHuesped() {
        creaHuesped();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    private static final Pattern LETRAS_PATTERN = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$");

    public void creaHuesped() {
        huesped = new Huesped();
    }

    public List<Huesped> getHuespedes() {
        return mDHuesped.huespedes();
    }

    public void registroHuesped() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        mDHuesped.insertarHuesped(huesped);
         FacesMessage msj = new FacesMessage("Registro exitoso.");
            contexto.addMessage(null, msj);
        creaHuesped();
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
    public String nuevoHuesped() {
        creaHuesped();
        return "registroh.xhtml?faces-redirect=true";
    }
    
    
    //validador
    public void vLetras(FacesContext contexto, UIComponent obp, Object value){
        String input = (String) value;
        if (input != null && !LETRAS_PATTERN.matcher(input).matches()) {
            FacesMessage msj = new FacesMessage("Solo puedes ingresar letras.");
            contexto.addMessage(obp.getClientId(contexto), msj);
        }
    }
    
    
    public ADHuesped() {
        creaHuesped();
    }
    
}

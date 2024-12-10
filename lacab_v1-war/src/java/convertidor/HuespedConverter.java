/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidor;

/**
 *
 * @author Paula
 */

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import modelo.Huesped;
import manipuladatos.MDHuesped;
import javax.inject.Inject;

@Named("huespedConverter")
@RequestScoped
public class HuespedConverter implements Converter {

    @Inject
    private MDHuesped mDHuesped;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.valueOf(value);
            return mDHuesped.buscarHuespedPorId(id); // Implementa este método en MDHuesped
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID del huésped no es válido: " + value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Huesped)) {
            return "";
        }
        return String.valueOf(((Huesped) value).getNumHuesped());
    }
}

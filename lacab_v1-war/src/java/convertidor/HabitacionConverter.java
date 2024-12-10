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
import javax.inject.Inject;
import manipuladatos.MDHabitacion;
import modelo.Habitacion;

@Named("habitacionConverter")
@RequestScoped
public class HabitacionConverter implements Converter {

    @Inject
    private MDHabitacion mDHabitacion;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.valueOf(value);
            return mDHabitacion.buscarHabitacion(id); 
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID del huésped no es válido: " + value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Habitacion)) {
            return "";
        }
        return String.valueOf(((Habitacion) value).getNumHab());
    }
}

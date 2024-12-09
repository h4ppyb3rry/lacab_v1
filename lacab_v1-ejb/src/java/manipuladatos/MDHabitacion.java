/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.HabitacionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Habitacion;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDHabitacion {

    @EJB
    private HabitacionFacade habitacionF;
    
    public void editarHabitacion(Habitacion h){
        habitacionF.edit(h);
    }

     public List<Habitacion> habitaciones(){
       return habitacionF.findAll();  
     }
    

    
}

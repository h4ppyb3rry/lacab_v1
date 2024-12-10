/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.ReservacionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDReserva {

    @EJB
    private ReservacionFacade reservacionF;
    
    public void insertarReserva(Reservacion r){
        reservacionF.create(r);
    }

     public List<Reservacion> reservaciones(){
       return reservacionF.findAll();  
     }
    
     public void actualizarReserva(Reservacion r){
        reservacionF.edit(r);
    }

  
}

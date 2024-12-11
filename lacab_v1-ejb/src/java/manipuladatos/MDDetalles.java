/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.DetalleReservacionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.DetalleReservacion;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDDetalles {

    @EJB
    private DetalleReservacionFacade detalleF;
    public void insertarDetalle(DetalleReservacion dr){
        detalleF.create(dr);
    }

     public List<DetalleReservacion> reservaciones(){
       return detalleF.findAll();  
     }
    
     public List<DetalleReservacion> detallesReserva(Reservacion r){
       return detalleF.obtenerDetalles(r);  
     }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

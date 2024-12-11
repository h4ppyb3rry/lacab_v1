/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.NotaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Nota;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDNota {

    @EJB
    private NotaFacade notaF;
    
     public void generarNota(Nota n) {
        notaF.create(n);
    }

    public List<Nota> notas() {
        return notaF.findAll();
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

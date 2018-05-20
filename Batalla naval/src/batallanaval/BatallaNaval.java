/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batallanaval;

import presentacion.Modelo;

/**
 *
 * @author Daniel
 */
public class BatallaNaval {

    /**
     * @param args the command line arguments
     */
    private Modelo aplicacion;
    
    public BatallaNaval() throws Exception {
        aplicacion = new Modelo();
        aplicacion.iniciar();
       
    }
    public static void main(String[] args) throws Exception {
          new BatallaNaval();
    }
    
}

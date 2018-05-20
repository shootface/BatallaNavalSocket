
package presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author JuanGuaba 20151020047
 */
public class ControladorPrincipal implements MouseListener{
    private final VentanaPrincipal ventanaInicial;
    private JLabel barco;
    
    public ControladorPrincipal(VentanaPrincipal aThis) {
            ventanaInicial = aThis;
            barco = new JLabel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        barco = (JLabel)e.getSource();
        ventanaInicial.getModelo().clickbarco(barco);
        try {
            ventanaInicial.getModelo().controlDisparosSalida(barco);
        } catch (IOException ex) {
            System.out.println("salido DAÑO");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        barco = (JLabel)e.getSource();
        ventanaInicial.getModelo().resaltarBarco(barco);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        barco = (JLabel)e.getSource();
        ventanaInicial.getModelo().desrealtarBarco(barco);
    }
    
}

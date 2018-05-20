/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanGuaba
 */
public class ControladorSelecion implements MouseListener{
    private VentanaSelecion ventanaselecion;
    private JButton botonfinal;
    
    public ControladorSelecion(VentanaSelecion aThis) {
        ventanaselecion = aThis;
        botonfinal = new JButton();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        botonfinal = (JButton) e.getSource();
        System.out.println("BOTON : "+botonfinal.getName());
        try {
            ventanaselecion.getModelo().sistema(botonfinal);
        } catch (Exception ex) {
            System.out.println(" DAÑO control server o cliente "+ex);
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
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}

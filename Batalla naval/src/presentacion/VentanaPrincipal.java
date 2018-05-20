/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author JuanGuaba
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    private JLabel[][] Cubo;
    private JLabel[][] Cubo2;
    private ControladorPrincipal control;
    private JLabel[]lateral;
    private JLabel[]superior;
    private JLabel [] submarino;
    private JLabel [][] destructor;
    private JLabel [][]crucero;
    private JLabel [][] acorazado;
    private final Modelo modelo;
    private final Border border;
    private boolean entrosubmarino = true;
    
    public VentanaPrincipal(Modelo aThis) {
        border = LineBorder.createBlackLineBorder();
        modelo = aThis;
        initComponents();
        initCajones(0,0);
        initCajones2(0,0);
        lateral();
        lateral2();
        superior();
        superior2();
        barcos();
    }
    
    public ControladorPrincipal getControl(){
        if(control == null){
            control = new ControladorPrincipal (this);
        }
        return control;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1053, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void lateral(){
        lateral = new JLabel[10];
        int posy = 0;
        for(int i=0;i<10;i++){
            lateral[i]=new JLabel();
            lateral[i].setSize(40,40);
        }
        lateral[0].setText(" A ");
        lateral[1].setText(" B ");
        lateral[2].setText(" C ");
        lateral[3].setText(" D ");
        lateral[4].setText(" E ");
        lateral[5].setText(" F ");
        lateral[6].setText(" G ");
        lateral[7].setText(" H ");
        lateral[8].setText(" I ");
        lateral[9].setText(" J ");
        for(int i=0;i<10;i++){
            posy=100+(i*(40));
            lateral[i].setLocation(35,posy);
            lateral[i].setOpaque(true);
            lateral[i].setBorder(border);
            lateral[i].setBackground(Color.LIGHT_GRAY);
            this.getContentPane().add(lateral[i]);
        }
    }
    
    private void lateral2(){
        lateral = new JLabel[10];
        int posy = 0;
        for(int i=0;i<10;i++){
            lateral[i]=new JLabel();
            lateral[i].setSize(40,40);
        }
        lateral[0].setText(" A ");
        lateral[1].setText(" B ");
        lateral[2].setText(" C ");
        lateral[3].setText(" D ");
        lateral[4].setText(" E ");
        lateral[5].setText(" F ");
        lateral[6].setText(" G ");
        lateral[7].setText(" H ");
        lateral[8].setText(" I ");
        lateral[9].setText(" J ");
        for(int i=0;i<10;i++){
            posy=100+(i*(40));
            lateral[i].setLocation(653,posy);
            lateral[i].setOpaque(true);
            lateral[i].setBorder(border);
            lateral[i].setBackground(Color.LIGHT_GRAY);
            this.getContentPane().add(lateral[i]);
        }
    }
    
    private void superior(){
        superior = new JLabel[10];
        int posy = 0;
        for(int i=0;i<10;i++){
            superior[i]=new JLabel();
            superior[i].setSize(40,40);
            superior[i].setText(Integer.toString(i+1));
            posy=80+(i*(40));
            superior[i].setLocation(posy,53);
            superior[i].setOpaque(true);
            superior[i].setBorder(border);
            superior[i].setBackground(Color.LIGHT_GRAY);
            this.getContentPane().add(superior[i]);
        }
    }
    
    private void superior2(){
        superior = new JLabel[10];
        int posy = 0;
        for(int i=0;i<10;i++){
            superior[i]=new JLabel();
            superior[i].setSize(40,40);
            superior[i].setText(Integer.toString(i+1));
            posy=700+(i*(40));
            superior[i].setLocation(posy,53);
            superior[i].setOpaque(true);
            superior[i].setBorder(border);
            superior[i].setBackground(Color.LIGHT_GRAY);
            this.getContentPane().add(superior[i]);
        }
    }
    
    private void initCajones(int posx ,int posy) {
        Cubo = new JLabel[10][10];
        int contx = 0;
        int conty = 0;
        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
            Cubo[i][j]= new JLabel();
            Cubo[i][j].setSize(40,40);
            Cubo[i][j].setText(" ");
            Cubo[i][j].setName("mar");
            contx = contx +1; 
            conty = conty +1;
            posx=80+(j*(40)); 
            posy=100+(i*(40));
            Cubo[i][j].setLocation(posx, posy);
            Cubo[i][j].setOpaque(true);
            Cubo[i][j].setBackground(new Color(0,128,255));
            Cubo[i][j].setBorder(border);
            Cubo[i][j].addMouseListener(getControl());
            this.getContentPane().add(Cubo[i][j]);
        }
      }
    }
    
    private void initCajones2(int posx ,int posy) {
        Cubo2 = new JLabel[10][10];
        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
            Cubo2[i][j]= new JLabel();
            Cubo2[i][j].setSize(40,40);
            Cubo2[i][j].setText(" ");
            Cubo2[i][j].setName("mar2");
            posx=700+(j*(40)); 
            posy=100+(i*(40));
            Cubo2[i][j].setLocation(posx, posy);
            Cubo2[i][j].setOpaque(true);
            Cubo2[i][j].setBackground(new Color(0,128,255));
            Cubo2[i][j].addMouseListener(getControl());
            Cubo2[i][j].setBorder(border);
            
            this.getContentPane().add(Cubo2[i][j]);
        }
      }
    }
    
    private void barcos(){ 
        //Creacion de los submarinos
        submarino = new JLabel[4];
        int posx = 0;
        int cont = 0;
        for(int i=0;i<4;i++){
            submarino[i] = new JLabel();
            submarino[i].setSize(40,40);
            posx=80+(i*(40+20));
            submarino[i].setLocation(posx,550);
            submarino[i].setName("submarino"+cont);
            cont = cont +1 ;
            System.out.println(submarino[i].getName());
            submarino[i].setOpaque(true);
            submarino[i].setBackground(Color.black);
            submarino[i].addMouseListener(getControl());
            this.getContentPane().add(submarino[i]);
        }
        //creacion de los barcos destructores
        destructor = new JLabel[3][2];
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                destructor[i][j] = new JLabel();
                destructor[i][j].setSize(40,40);
                destructor[i][j].setOpaque(true);
                destructor[i][j].setBackground(Color.black);
                destructor[i][j].addMouseListener(getControl());
                this.getContentPane().add(destructor[i][j]);
            }
        }
        destructor[0][0].setLocation(80,600);
        destructor[0][0].setName("destructor10");
        destructor[0][1].setLocation(120,600);
        destructor[0][1].setName("destructor11");
        destructor[1][0].setLocation(200, 600);
        destructor[1][0].setName("destructor20");
        destructor[1][1].setLocation(240, 600);
        destructor[1][1].setName("destructor21");
        destructor[2][0].setLocation(300, 600);
        destructor[2][0].setName("destructor30");
        destructor[2][1].setLocation(340, 600);
        destructor[2][1].setName("destructor31");
        //creacion de los cruceros de guerra
        crucero = new JLabel[2][3];
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                crucero[i][j] = new JLabel();
                crucero[i][j].setSize(40, 40);
                crucero[i][j].setOpaque(true);
                crucero[i][j].setBackground(Color.black);
                crucero[i][j].addMouseListener(getControl());
                this.getContentPane().add(crucero[i][j]);
            }
        }
        crucero[0][0].setLocation(80, 660);
        crucero[0][0].setName("crucero10");
        crucero[0][1].setLocation(120,660);
        crucero[0][1].setName("crucero11");
        crucero[0][2].setLocation(160, 660);
        crucero[0][2].setName("crucero12");
        crucero[1][0].setLocation(240, 660);
        crucero[1][0].setName("crucero20");
        crucero[1][1].setLocation(280, 660);
        crucero[1][1].setName("crucero21");
        crucero[1][2].setLocation(320, 660);
        crucero[1][2].setName("crucero22");
        acorazado = new JLabel[4][1];
    }
    
    public JLabel[][] getCubo(){
        return Cubo;
    }
    public JLabel[][] getCubo2() {
        return Cubo2;
    }
    public JLabel[] getSubmarino() {
        return submarino;
    }
    public JLabel[][] getDestructor() {
        return destructor;
    }
    public JLabel[][] getCrucero() {
        return crucero;
    }
    public JLabel[][] getAcorazado() {
        return acorazado;
    }
    public Border getBorder() {
        return border;
    }
    public Modelo getModelo() {
        return modelo;
    }
    
    
}

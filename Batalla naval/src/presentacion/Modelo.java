
package presentacion;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import logica.Sistema;

/**
 *
 * @author JuanGuaba
 */

public class Modelo {
    
    private String soy;
    private VentanaPrincipal ventanaInicial;
    private VentanaSelecion ventanaselecion;
    private VentanaJugadores ventanaJugadores;
    private Sistema sistema;
    private boolean creado=false;
    private Color color;
    private String barcoS;
    private int conteo;
    private int pdes1=0;
    private int pdes2=0;
    private int pdes3=0;
    private int cont = 0;
    private int pcru1 = 0;
    private int pcru2 = 0;
    private int posX1=0;
    private int posY1=0;
    private String barco_nom = "";
    private boolean sub1 = false;
    private boolean sub2 = false;
    private boolean sub3 = false;
    private boolean sub4 = false;
    private boolean []submarino = new boolean [4] ;
    
    public Modelo() {
        for(int i=0;i<4;i++){
            submarino[i] = true; 
        }
    }
    
    public void iniciar(){
        getVentanaSelecion().setSize(300,300);
        getVentanaSelecion().setVisible(true);
    }
    
    public void iniciarjuego() throws Exception {
        getVentanaInicial().setSize(1200,1300);
        getVentanaInicial().setVisible(true);  
    }
    
    public void inciarservidorJuego(){
        try {
            if(creado==false){
            System.out.println("SOY SERVIDOR");
            getSistema().getServidor().crearServidor();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ventanaInicial,"No se pudo crear el servidor");
        }
    }
    
    //Metodos de creacion de objetos
    public VentanaPrincipal getVentanaInicial(){ 
        // Se llama a  la ventana principal y si no la hay, la crea
        if (ventanaInicial == null) {
            ventanaInicial = new VentanaPrincipal(this);
        }
        return ventanaInicial;
    }
    public VentanaSelecion getVentanaSelecion(){
        if(ventanaselecion==null){
            ventanaselecion = new VentanaSelecion(this);
        }
        return ventanaselecion;
    }
    public VentanaJugadores getVentanaJugadores(){
        if (ventanaJugadores==null){
            ventanaJugadores = new VentanaJugadores();
        }
        return ventanaJugadores;
    }
    public Sistema getSistema(){
        if(sistema==null){
            sistema = new Sistema(this);
        }
        return sistema;
    }
    public String getSoy() {
        return soy;
    }
    
    //Metodos de gestion de jugadores
    public void sistemaJugadores(){
        
    }
    
    //Metodos del juego
    public void sistema(JButton boton) throws Exception{
        if(boton.getName().equals("crear")){
            System.out.println("entro a crear");
            soy="server";
            inciarservidorJuego();
            iniciarjuego();
            System.out.println("inicio hilo");
            getSistema().getServidor().getHilo().start();
            System.out.println("inciado");
        }
        if(boton.getName().equals("unir")){
            System.out.println("entro a unir");
            soy="cliente";
            iniciarjuego();
            System.out.println("inicio hilo");
            getSistema().getCliente().getHilo().start();
            System.out.println("inciado");
        }
    }     
    public void controlDisparosSalida(JLabel label) throws IOException{
        getSistema().disparosSalida(label,soy,getVentanaInicial().getCubo2());
        //if(cont==16){
           getSistema().getDisparos().recorrer(getVentanaInicial().getCubo());
        //}
    }
    public void controlDisparosLlegaPos(int x , int y){
        getVentanaInicial().getCubo2()[x][y].setBackground(Color.RED);
    }
    public void controlDisparosLlegaNeg(int x , int y){
        getVentanaInicial().getCubo2()[x][y].setBackground(Color.WHITE);
    }
    public void ShootingShip(int x,int y){
        getVentanaInicial().getCubo()[x][y].setBackground(Color.RED);
    }
    public void finJuego(String S) throws IOException{
        if(S.equals("win")){
            JOptionPane.showMessageDialog(ventanaInicial,"GANO EL JUEGO,Felicitaciones");
            if(soy.equals("server")){
                getSistema().getServidor().getDatosEntrada().close();
                getSistema().getServidor().getDatosSalida().close();
                getVentanaInicial().dispose();
            }
            if(soy.equals("cliente")){
                getSistema().getCliente().getDatosEntrada().close();
                getSistema().getCliente().getDatosSalida().close();
                getVentanaInicial().dispose();
            }
        }
        if(S.equals("lose")){
            JOptionPane.showMessageDialog(ventanaInicial,"Usted es muy mal, sera la proxima");
            if(soy.equals("server")){
                getSistema().getServidor().getDatosEntrada().close();
                getSistema().getServidor().getDatosSalida().close();
                getVentanaInicial().dispose();
            }
            if(soy.equals("cliente")){
                getSistema().getCliente().getDatosEntrada().close();
                getSistema().getCliente().getDatosSalida().close();
                getVentanaInicial().dispose();
            }
        }
    }
    public void clickbarco(JLabel barco) {
        if (barco.getName() != "mar") {
            barcoS = barco.getName();
            if (barcoS.substring(0, barcoS.length() - 1).equals("submarino")) {
                barco_nom = "submarino";
            }
            if (barcoS.substring(0, barcoS.length() - 2).equals("destructor")) {
                if (barco.getName().equals("destructor10")) {
                    barco_nom = "destructor10";
                } else if (barco.getName().equals("destructor11")) {
                    barco_nom = "destructor11";
                } else if (barco.getName().equals("destructor20")) {
                    barco_nom = "destructor20";
                } else if (barco.getName().equals("destructor21")) {
                    barco_nom = "destructor21";
                } else if (barco.getName().equals("destructor30")) {
                    barco_nom = "destructor30";
                } else if (barco.getName().equals("destructor31")) {
                    barco_nom = "destructor31";
                }
            }
            if (barcoS.substring(0, barcoS.length() - 2).equals("crucero")) {
                if (barco.getName().equals("crucero10")) {
                    barco_nom = "crucero10";
                } else if (barco.getName().equals("crucero11")) {
                    barco_nom = "crucero11";
                } else if (barco.getName().equals("crucero12")) {
                    barco_nom = "crucero12";
                } else if (barco.getName().equals("crucero20")) {
                    barco_nom = "crucero20";
                } else if (barco.getName().equals("crucero21")) {
                    barco_nom = "crucero21";
                } else if (barco.getName().equals("crucero22")) {
                    barco_nom = "crucero22";
                }
            }
        }

        if (barco_nom.equals("submarino")) {
            asignarSubmarino(barco);
        }
        if (barco_nom.equals("destructor10") || barco_nom.equals("destructor11") || barco_nom.equals("destructor20") || barco_nom.equals("destructor21") || barco_nom.equals("destructor30") || barco_nom.equals("destructor31")) {
            asignardestructor(barco);
        }
        if (barco_nom.equals("crucero10") || barco_nom.equals("crucero11") || barco_nom.equals("crucero12") || barco_nom.equals("crucero20") || barco_nom.equals("crucero21") || barco_nom.equals("crucero22")) {
            asignarcrucero(barco);
        }

    }
    public void asignarSubmarino(JLabel barco){
        if(barco.getName().equals("mar")){
            if(barcoS.equals("submarino0")){
                if(barco.getBackground()!=Color.BLACK){
                    barco.setBackground(Color.BLACK);
                    getVentanaInicial().getSubmarino()[0].setVisible(false);
                    sub1 = true;
                    barcoS = "";
                    cont=cont+1;
                }else{
                    JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                }
            }
            if(barcoS.equals("submarino1")){
                if(barco.getBackground()!=Color.BLACK){
                    barco.setBackground(Color.BLACK);
                    getVentanaInicial().getSubmarino()[1].setVisible(false);
                    sub2 = true;
                    barcoS = "";
                    cont=cont+1;
                }else{
                    JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                }
            }
            if(barcoS.equals("submarino2")){
                if(barco.getBackground()!=Color.BLACK){
                    barco.setBackground(Color.BLACK);
                    getVentanaInicial().getSubmarino()[2].setVisible(false);
                    sub3 = true;
                    barcoS = "";
                    cont=cont+1;
                }else{
                    JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                }
            }
            if(barcoS.equals("submarino3")){
                if(barco.getBackground()!=Color.BLACK){
                    barco.setBackground(Color.BLACK);
                    getVentanaInicial().getSubmarino()[3].setVisible(false);
                    barco.setName("submarino3");
                    sub4 = true;
                    barcoS = "";
                    cont=cont+1;
                }else{
                    JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                }
            }
        }
    }
    public void asignardestructor(JLabel barco){
        int posX=0;
        int posY=0;
        if(barco.getName().equals("mar")){
        if(barcoS.equals("destructor10")){
            if(barco.getBackground()!=Color.BLACK){
                barco.setBackground(Color.BLACK);
                barco.setName("destructor10");
                barcoS="";
                pdes1=pdes1+1;
                getVentanaInicial().getDestructor()[0][0].setVisible(false);
                cont=cont+1;
            }
        }else if(barcoS.equals("destructor11")){
                barco.setBackground(Color.BLACK);
                barco.setName("destructor11");
                barcoS="";
                pdes1=pdes1+1;
                getVentanaInicial().getDestructor()[0][1].setVisible(false);
                cont=cont+1;

        }else if(barcoS.equals("destructor20")){
                barco.setBackground(Color.BLACK);
                barco.setName("destructor20");
                barcoS="";
                pdes2=pdes2+1;
                getVentanaInicial().getDestructor()[1][0].setVisible(false);
                cont=cont+1;
                
        }else if(barcoS.equals("destructor21")){
                barco.setBackground(Color.BLACK);
                barco.setName("destructor21");
                barcoS="";
                pdes2=pdes2+1;
                getVentanaInicial().getDestructor()[1][1].setVisible(false);
                cont=cont+1;
                
        }else if(barcoS.equals("destructor30")){
                barco.setBackground(Color.BLACK);
                barco.setName("destructor30");
                barcoS="";
                pdes3=pdes3+1;
                getVentanaInicial().getDestructor()[2][0].setVisible(false);
                cont=cont+1;
                
        }else if(barcoS.equals("destructor31")){
                barco.setBackground(Color.BLACK);
                barco.setName("destructor31");
                barcoS="";
                pdes3=pdes3+1;
                getVentanaInicial().getDestructor()[2][1].setVisible(false);
                cont=cont+1;
                
        }

        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
                if(getVentanaInicial().getCubo()[i][j].getName().equals("destructor10")&&pdes1==2){
                    posX=i;
                    posY=j;
                }
                if(getVentanaInicial().getCubo()[i][j].getName().equals("destructor20")&&pdes2==2){
                    posX=i;
                    posY=j;
                }
                if(getVentanaInicial().getCubo()[i][j].getName().equals("destructor30")&&pdes3==2){
                    posX=i;
                    posY=j;
                }
            }
        }
                
        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
                if(getVentanaInicial().getCubo()[i][j].getName().equals("destructor11")&&pdes1==2){
                    if( (posX == i && (posY-1==j || posY+1==j) )|| (posY == j && (posX-1==i || posX+1 == i))){
                        pdes1=3;                       
                    }else{
                        getVentanaInicial().getCubo()[i][j].setName("mar");
                        JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                        barco.setBackground(new Color(0,128,255));
                        getVentanaInicial().getDestructor()[0][1].setVisible(true);
                        barcoS="destructor11";
                        pdes1=pdes1-1; 
                        cont=cont-1;
                    }
                }
                if(getVentanaInicial().getCubo()[i][j].getName().equals("destructor21")&&pdes2==2){
                    if( (posX == i && (posY-1==j || posY+1==j) )|| (posY == j && (posX-1==i || posX+1 == i))){
                        pdes2=3;                       
                    }else{
                        getVentanaInicial().getCubo()[i][j].setName("mar");
                        JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                        barco.setBackground(new Color(0,128,255));
                        getVentanaInicial().getDestructor()[1][1].setVisible(true);
                        barcoS="destructor21";
                        pdes2=pdes2-1;  
                        cont=cont-1;
                    }
                }
                if(getVentanaInicial().getCubo()[i][j].getName().equals("destructor31")&&pdes3==2){
                    if( (posX == i && (posY-1==j || posY+1==j) )|| (posY == j && (posX-1==i || posX+1 == i))){
                        pdes3=3;                       
                    }else{
                        getVentanaInicial().getCubo()[i][j].setName("mar");
                        JOptionPane.showMessageDialog(ventanaInicial,"no se JUAN DIEGO");
                        barco.setBackground(new Color(0,128,255));
                        getVentanaInicial().getDestructor()[2][1].setVisible(true);
                        barcoS="destructor31";
                        pdes3=pdes3-1; 
                        cont=cont-1;
                    }
                }
                
            }
        
         }
        }
      
    }
    private void asignarcrucero(JLabel barco) {
        int posX = 0;
        int posY = 0;
        if (barco.getName().equals("mar")) {
            if (barcoS.equals("crucero10")) {
                 if (barco.getBackground() != Color.BLACK) {
                    barco.setBackground(Color.BLACK);
                    barco.setName("crucero10");
                    barcoS = "";
                    pcru1 = pcru1 + 1;
                    cont=cont+1;
                    getVentanaInicial().getCrucero()[0][0].setVisible(false);
                }
            }else if (barcoS.equals("crucero11")) {
                 if (barco.getBackground() != Color.BLACK) {
                    barco.setBackground(Color.BLACK);
                    barco.setName("crucero11");
                    barcoS = "";
                    pcru1 = pcru1 + 1;
                    getVentanaInicial().getCrucero()[0][1].setVisible(false);
                }
            }else if (barcoS.equals("crucero12")) {
                 if (barco.getBackground() != Color.BLACK) {
                    barco.setBackground(Color.BLACK);
                    barco.setName("crucero12");
                    barcoS = "";
                    pcru1 = pcru1 + 1;
                    getVentanaInicial().getCrucero()[0][2].setVisible(false);
                }
            }else  if (barcoS.equals("crucero20")) {
                 if (barco.getBackground() != Color.BLACK) {
                    barco.setBackground(Color.BLACK);
                    barco.setName("crucero20");
                    barcoS = "";
                    pcru2 = pcru2 + 1;
                    cont=cont+1;
                    getVentanaInicial().getCrucero()[1][0].setVisible(false);
                }
            }else if (barcoS.equals("crucero21")) {
                 if (barco.getBackground() != Color.BLACK) {
                    barco.setBackground(Color.BLACK);
                    barco.setName("crucero21");
                    barcoS = "";
                    pcru2 = pcru2 + 1;
                    getVentanaInicial().getCrucero()[1][1].setVisible(false);
                }
            }else if (barcoS.equals("crucero22")) {
                 if (barco.getBackground() != Color.BLACK) {
                    barco.setBackground(Color.BLACK);
                    barco.setName("crucero22");
                    barcoS = "";
                    pcru2 = pcru2 + 1;
                    getVentanaInicial().getCrucero()[1][2].setVisible(false);
                }
            }
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero10") && pcru1 == 2) {
                        posX1 = i;
                        posY1 = j;
                    }
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero20") && pcru2 == 2) {
                        posX1 = i;
                        posY1 = j;
                    }
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero11") && pcru1 == 4) {
                        posX = i;
                        posY = j;
                    }
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero21") && pcru2 == 4) {
                        posX = i;
                        posY = j;
                    }
                }
            }
       
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero11") && pcru1 == 2) {
                        if ((posX1 == i && (posY1 - 1 == j || posY1 + 1 == j)) || (posY1 == j && (posX1 - 1 == i || posX1 + 1 == i))) {
                            System.out.println("LISTO POS");
                            cont=cont+1;
                            pcru1=3;
                        } else {
                            getVentanaInicial().getCubo()[i][j].setName("mar");
                            JOptionPane.showMessageDialog(ventanaInicial, "no se JUAN DIEGO");
                            barco.setBackground(new Color(0, 128, 255));
                            getVentanaInicial().getCrucero()[0][1].setVisible(true);
                            barcoS = "crucero11";
                            pcru1 = pcru1 - 1;
                        }
                    }
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero12") && pcru1 == 4) {
                        if (((posX == i && (posY - 1 == j || posY + 1 == j)) || (posY == j && (posX - 1 == i || posX + 1 == i)))&&( j==posY1 ||i==posX1)) {
                            System.out.println("LISTO POS2");
                            cont=cont+1;
                            pcru1 = 5;
                        } else {
                            getVentanaInicial().getCubo()[i][j].setName("mar");
                            System.out.println("posx1= "+posX1+"  i="+i);
                            JOptionPane.showMessageDialog(ventanaInicial, "no se JUAN DIEGO");
                            barco.setBackground(new Color(0, 128, 255));
                            getVentanaInicial().getCrucero()[0][2].setVisible(true);
                            barcoS = "crucero12";
                            pcru1 = pcru1 - 1;
                        }
                    }
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero21") && pcru2 == 2) {
                        if ((posX1 == i && (posY1 - 1 == j || posY1 + 1 == j)) || (posY1 == j && (posX1 - 1 == i || posX1 + 1 == i))) {
                            System.out.println("LISTO POS");
                            pcru2=3;
                            cont=cont+1;
                        } else {
                            getVentanaInicial().getCubo()[i][j].setName("mar");
                            JOptionPane.showMessageDialog(ventanaInicial, "no se JUAN DIEGO");
                            barco.setBackground(new Color(0, 128, 255));
                            getVentanaInicial().getCrucero()[1][1].setVisible(true);
                            barcoS = "crucero21";
                            pcru2 = pcru2 - 1;
                        }
                    }
                    if (getVentanaInicial().getCubo()[i][j].getName().equals("crucero22") && pcru2 == 4) {
                        if (((posX == i && (posY - 1 == j || posY + 1 == j)) || (posY == j && (posX - 1 == i || posX + 1 == i)))&&( j==posY1 ||i==posX1)) {
                            System.out.println("LISTO POS2");
                            pcru2 = 5;
                            cont=cont+1;
                        } else {
                            getVentanaInicial().getCubo()[i][j].setName("mar");
                            System.out.println("posx1= "+posX1+"  i="+i);
                            JOptionPane.showMessageDialog(ventanaInicial, "no se JUAN DIEGO");
                            barco.setBackground(new Color(0, 128, 255));
                            getVentanaInicial().getCrucero()[1][2].setVisible(true);
                            barcoS = "crucero22";
                            pcru2 = pcru2 - 1;
                        }
                    }
                }
            }
        }
    }
    public void resaltarBarco(JLabel barco) {
        color = barco.getBackground();
        if(barco.getName().equals("submarino0")&&sub1==false){
            barco.setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("submarino1")&&sub2==false){
            barco.setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("submarino2")&&sub3==false){
            barco.setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("submarino3")&&sub4==false){
            barco.setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("destructor10")||barco.getName().equals("destructor11")){
            getVentanaInicial().getDestructor()[0][0].setBackground(new Color(161,34,34));
            getVentanaInicial().getDestructor()[0][1].setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("destructor20")||barco.getName().equals("destructor21")){
            getVentanaInicial().getDestructor()[1][0].setBackground(new Color(161,34,34));
            getVentanaInicial().getDestructor()[1][1].setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("destructor30")||barco.getName().equals("destructor31")){
            getVentanaInicial().getDestructor()[2][0].setBackground(new Color(161,34,34));
            getVentanaInicial().getDestructor()[2][1].setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("crucero10")||barco.getName().equals("crucero11")||barco.getName().equals("crucero12")){
            getVentanaInicial().getCrucero()[0][0].setBackground(new Color(161,34,34));
            getVentanaInicial().getCrucero()[0][1].setBackground(new Color(161,34,34));
            getVentanaInicial().getCrucero()[0][2].setBackground(new Color(161,34,34));
        }
        if(barco.getName().equals("crucero20")||barco.getName().equals("crucero21")||barco.getName().equals("crucero22")){
            getVentanaInicial().getCrucero()[1][0].setBackground(new Color(161,34,34));
            getVentanaInicial().getCrucero()[1][1].setBackground(new Color(161,34,34));
            getVentanaInicial().getCrucero()[1][2].setBackground(new Color(161,34,34));
        }
    }
    public void desrealtarBarco(JLabel barco) {
        if(submarino[0] & barco.getName().equals("submarino0") && sub1 == false){
            barco.setBackground(color);
            }
        if(submarino[1] & barco.getName().equals("submarino1") && sub2 == false){
                barco.setBackground(color);
            }
        if(submarino[2] & barco.getName().equals("submarino2") && sub3 == false){
                barco.setBackground(color);
            } 
        if(submarino[3] & barco.getName().equals("submarino3") && sub4 == false){
                barco.setBackground(color);
            }
        if(barco.getName().equals("destructor10")||barco.getName().equals("destructor11")){
            getVentanaInicial().getDestructor()[0][0].setBackground(color);
            getVentanaInicial().getDestructor()[0][1].setBackground(color);
        }
        if(barco.getName().equals("destructor20")||barco.getName().equals("destructor21")){
            getVentanaInicial().getDestructor()[1][0].setBackground(color);
            getVentanaInicial().getDestructor()[1][1].setBackground(color);
        }
        if(barco.getName().equals("destructor30")||barco.getName().equals("destructor31")){
            getVentanaInicial().getDestructor()[2][0].setBackground(color);
            getVentanaInicial().getDestructor()[2][1].setBackground(color);
        }
        if(barco.getName().equals("crucero10")||barco.getName().equals("crucero11")||barco.getName().equals("crucero12")){
            getVentanaInicial().getCrucero()[0][0].setBackground(color);
            getVentanaInicial().getCrucero()[0][1].setBackground(color);
            getVentanaInicial().getCrucero()[0][2].setBackground(color);
        }
        if(barco.getName().equals("crucero20")||barco.getName().equals("crucero21")||barco.getName().equals("crucero22")){
            getVentanaInicial().getCrucero()[1][0].setBackground(color);
            getVentanaInicial().getCrucero()[1][1].setBackground(color);
            getVentanaInicial().getCrucero()[1][2].setBackground(color);
        }
//        if(barco.getName().equals("mar2")){
//            if(barco.getBackground().equals(Color.RED)){
//                System.out.println("YA LLEGO PAPU");
//            }else{
//                getVentanaInicial().getCubo2()[poscx][poscy].setBackground(new Color(0,128,255)); 
//            }
//        }
    }

}

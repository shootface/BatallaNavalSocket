package logica;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import presentacion.Modelo;

/**
 *
 * @author Daniel
 */
public class Sistema {

    private SistemaCliente cliente;
    private SistemaServidor servidor;
    private UDPServer chatServer;
    private UDPClient chatCliente;
    private Disparos disparos;
    private final Modelo modelo;
    private String turno = "";

    public Sistema(Modelo aThis) {
        modelo = aThis;
    }

    public UDPClient getChatClient() throws SocketException, UnknownHostException{
        if(chatCliente==null){
            chatCliente = new UDPClient(this);
        }
        return chatCliente;
    }
    public UDPServer getChatServer(){
        if(chatServer==null){
            chatServer=new UDPServer(this);
        }
        return chatServer;
    }
    public SistemaCliente getCliente() throws IOException {
        if (cliente == null) {
            cliente = new SistemaCliente(this);
        }
        return cliente;
    }

    public SistemaServidor getServidor() {
        if (servidor == null) {
            servidor = new SistemaServidor(this);
        }
        return servidor;
    }

    public Disparos getDisparos() {
        if (disparos == null) {
            disparos = new Disparos(this);
        }
        return disparos;
    }

    public void disparosSalida(JLabel label, String soy, JLabel[][] cubo2,boolean b) {//Verifica los diparos de salida, 
        if (label.getName().equals("mar2") && b==true) {
            System.out.println("inicio el control de salida");
            int tempx = 0;
            int tempy = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (cubo2[i][j] == label) {
                        tempx = i;
                        tempy = j;
                    }
                }
            }
            if (soy.equals("server")) {
                try {
                    System.out.println("envio como server");
                    getServidor().enviar(Integer.toString(tempx) + "/" + Integer.toString(tempy) + "/" + "N" + "/" + "C");
                } catch (IOException ex) {
                    System.out.println("fallo en control de disparos SISTEMA SERVIDOR");
                }
            }
            if (soy.equals("cliente")) {
                try {
                    System.out.println("envio como cliente");
                    getCliente().enviar(Integer.toString(tempx) + "/" + Integer.toString(tempy) + "/" + "N" + "/" + "S");
                } catch (IOException ex) {
                    System.out.println("fallo en control de disparos SISTEMA CLIENTE");
                }
            }
        }else{
            System.out.println("No puede disparar");
        }
    }

    public void disparosLLega(String mensaje) {
        int tempx = 0;
        int tempy = 0;
        String pos = new String();
        pos = mensaje;
        System.out.println("split");
        String[] post = new String[4];
        post = pos.split("/");
        tempx = Integer.parseInt(post[0]);
        tempy = Integer.parseInt(post[1]);
        System.out.println("x : " + tempx + " y: " + tempy + " estado: " + post[2] + "turno" + post[3]);
        if (post[2].equals("N")) {
            if (getDisparos().disparoLlega(tempx, tempy)) {//AQUI
                System.out.println("verifico si el disparo llego");
                if (modelo.getSoy().equals("server")) {
                    try {
                        System.out.println("ACERTO server");
                        getServidor().enviar(Integer.toString(tempx) + "/" + Integer.toString(tempy) + "/" + "T" + "/" + "C");
                        modelo.ShootingShip(tempx, tempy);
                    } catch (IOException ex) {
                        System.out.println("DAÑO ENVIAR VERIFICACION DE DISPARO disparosllega sistema server");
                    }
                }
                if (modelo.getSoy().equals("cliente")) {
                    try {
                        System.out.println("ACERTO cliente");
                        getCliente().enviar(Integer.toString(tempx) + "/" + Integer.toString(tempy) + "/" + "T" + "/" + "S");
                        modelo.ShootingShip(tempx, tempy);
                    } catch (IOException ex) {
                        System.out.println("DAÑO ENVIAR VERIFICACIOM DE DISPARO disparosllega sistema cliente");
                    }
                }
            } else {
                if (modelo.getSoy().equals("server")) {
                    try {
                        System.out.println("FALLO Server");
                        getServidor().enviar(Integer.toString(tempx) + "/" + Integer.toString(tempy) + "/" + "F" + "/" + "C");
                    } catch (IOException ex) {
                        System.out.println("DAÑO ENVIAR VERIFICACION DE DISPARO disparosllega sistema server envio negativo");
                    }
                }
                if (modelo.getSoy().equals("cliente")) {
                    try {
                        System.out.println("FALLO cliente");
                        getCliente().enviar(Integer.toString(tempx) + "/" + Integer.toString(tempy) + "/" + "F" + "/" + "S");
                    } catch (IOException ex) {
                        System.out.println("DAÑO ENVIAR VERIFICACIOM DE DISPARO disparosllega sistema cliente envio negativo");
                    }
                }
            }
        }
        if (post[2].equals("T")) {
            modelo.controlDisparosLlegaPos(tempx, tempy);
        }
        if (post[2].equals("F")) {
            modelo.controlDisparosLlegaNeg(tempx, tempy);
        }
        
        if (getDisparos().ganar(modelo.getVentanaInicial().getCubo2())) {
            if (modelo.getSoy().equals("server")) {
                try {
                    getServidor().enviar("0/0/P/P");
                    System.out.println("mensaje trato");
                    modelo.finJuego("win");
                    System.out.println("GANEEEEE SERVER");
                    System.out.println("mensaje hecho");
                } catch (IOException ex) {
                    System.out.println("DAÑO ENVIAR PERDIO EN server");
                }
            }
            if (modelo.getSoy().equals("cliente")) {
                try {
                    getCliente().enviar("0/0/P/P");
                    System.out.println("mensaje trato");
                    modelo.finJuego("win");
                    System.out.println("GANEEEEEEE CLIENTE");
                    System.out.println("mensaje hecho");
                } catch (IOException ex) {
                    System.out.println("DAÑO ENVIAR PERDIO EN cliente");
                }
            }
        }
        if (post[2].equals("P") || getDisparos().isFlag()){
            if (modelo.getSoy().equals("server")) {
                try {
                    getServidor().enviar("0/0/W/W");
                    System.out.println("mensaje trato");
                    modelo.finJuego("lose");
                    System.out.println("mensaje hecho");
                } catch (IOException ex) {
                    System.out.println("DAÑO ENVIAR PERDIO EN server");
                }
            }
            if (modelo.getSoy().equals("cliente")) {
                try {
                    getCliente().enviar("0/0/W/W");
                    System.out.println("mensaje trato");
                    modelo.finJuego("lose");
                    System.out.println("mensaje hecho");
                } catch (IOException ex) {
                    System.out.println("DAÑO ENVIAR PERDIO EN cliente");
                }
            }
        }
        if (post[2].equals("W")){
            if (modelo.getSoy().equals("server")) {
                try {
                    getServidor().enviar("0/0/P/P");
                    System.out.println("mensaje trato");
                    modelo.finJuego("win");
                    System.out.println("mensaje hecho");
                } catch (IOException ex) {
                    System.out.println("DAÑO ENVIAR PERDIO EN server");
                }
            }
            if (modelo.getSoy().equals("cliente")) {
                try {
                    getCliente().enviar("0/0/P/P");
                    System.out.println("mensaje trato");
                    modelo.finJuego("win");
                    System.out.println("mensaje hecho");
                } catch (IOException ex) {
                    System.out.println("DAÑO ENVIAR PERDIO EN cliente");
                }
            }
        }
        modelo.setPuntaje(getDisparos().getConteo());
    }
    
    public void mensajesSalida(String mensaje,String soy){
        if(soy.equals("server")){
            try{
                System.out.println("ENVIANDO EL MENSAJE");
                getChatServer().enviar(mensaje);
            }catch(IOException ex){
                System.out.println("ERROR enviando el mensaje : "+ex);
            }
        }
        if(soy.equals("cliente")){
            try{
                System.out.println("ENVIANDO EL MENSAJE");
                getChatClient().enviar(mensaje);
            }catch(IOException ex){
                System.out.println("ERROR enviando el mensaje : "+ex);
            }
        }
    }
    
    public void mensajesEntrada(String mensaje){
        modelo.escribir(mensaje);
    }
}

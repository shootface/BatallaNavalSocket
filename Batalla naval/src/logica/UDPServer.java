package logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author gumo0
 */
/*
Servidor UDP que recibirá datagramas de clientes, en la parte de datos vendrá un string.
Se responderá con el String transformado a mayúsculas al cliente que lo haya mandadado
Atenderá a todos los clientes que manden un string en el orden que los vaya recibiendo
La aplicación escucha datagramas en el puerto 5000
 */
import java.io.*;
import java.net.*;

public class UDPServer implements Runnable{

    private static DatagramSocket serversocket;
    private static DatagramPacket dp;
    private static BufferedReader dis;
    private Sistema sistema;
    private Thread correr;
    private String mensaje;
    public static InetAddress ia;
    public static byte buf[] = new byte[1024];
    public static int cport = 791, sport = 792;
    public boolean detener=false;
    
    public UDPServer(Sistema aThis) {
        sistema = aThis;
    }

    public void crearServidor() throws IOException{
        serversocket = new DatagramSocket(sport);
        dp = new DatagramPacket(buf, buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getByName("10.20.151.15");//IP del cliente
        //ia = InetAddress.getLocalHost();
        System.out.println("Server is Running...");
    }
    
    public void leer() throws IOException{
        System.out.println("Leyendo desde servidor");
        serversocket.receive(dp);
        mensaje = new String(dp.getData(), 0,dp.getLength());
        sistema.mensajesEntrada(mensaje);
    }
    
    public void enviar(String mensaje) throws IOException{
        System.out.println("ENVIANDO.....");
        serversocket.send(new DatagramPacket(mensaje.getBytes(), mensaje.length(), ia, cport));
    }
    
    public Thread getHilo(){
        if(correr==null){
            correr = new Thread(this);
        }
        return correr;
    }
    
    public void detener(){
        detener = true;
    }

    @Override
    public void run() {
        do{
            try{
            leer();
        }catch(IOException ex){
            System.out.println("ERROR HILO LEER SERVIDOR CHAT "+ex);
        }
        }while(detener==false);
    }
}

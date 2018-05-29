/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Juan guaba
 */
public class SistemaServidor implements Runnable{

    public static DatagramSocket serversocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static int cport = 789, sport = 790;
    public boolean detener=false;
    /*private ServerSocket server;
    private Socket cliente;
    private DataOutputStream datosSalida;
    private DataInputStream datosEntrada;*/
    private byte buffer[]=new byte[7];
    private String mensaje;
    private Thread correr;
    private Sistema sistema;

    public SistemaServidor(Sistema aThis) {
        sistema = aThis;
    }
    
    public void crearServidor() throws IOException {
        serversocket = new DatagramSocket(sport);
        dp = new DatagramPacket(buffer, buffer.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();
        //ia = InetAddress.getByName(ip);
        //ia = InetAddress.getByName("192.168.0.5");
        System.out.println("Server is Running...");
        /*server = new ServerSocket(5700);
        System.out.println("espero cliente");  
        cliente = server.accept();
        getDatosEntrada();
        getDatosSalida();*/
    }
    
    public void leer() throws IOException{
        System.out.println("llego en el servidor");
        serversocket.receive(dp);
        mensaje = new String(dp.getData(), 0,dp.getLength());
        sistema.disparosLLega(mensaje);
        /*datosEntrada.read(buffer);        
        System.out.println("LLEGO: "+new String(buffer));
        mensaje = new String(buffer);
        */
    }
    
    public void enviar(String mensaje) throws IOException{
        System.out.println("mensaje : "+mensaje);
        //System.out.println("inicio el cliente");
        serversocket.send(new DatagramPacket(mensaje.getBytes(), mensaje.length(), ia, cport));
        //datosSalida.write(mensaje.getBytes());
//        datosSalida.close();
//        cliente.close();
    }
    
    public Thread getHilo(){
        if(correr==null){
            correr=new Thread(this);
        }
        return correr;
    }
    
    public void detener(){
        detener = true;
    }
//    public DataInputStream getDatosEntrada(){
//        if(datosEntrada==null){
//            try {
//                datosEntrada= new DataInputStream(cliente.getInputStream());
//            } catch (IOException ex) {
//                System.out.println("no sep udo crear datos entrada  en el get");
//            }
//        }
//        return datosEntrada;
//    }
//    
//    public DataOutputStream getDatosSalida(){
//        if(datosSalida==null){
//            try {
//                datosSalida = new DataOutputStream(cliente.getOutputStream());
//            } catch (IOException ex) {
//                System.out.println("no se pudo crear datos salida en el get");
//            }
//        }
//        return datosSalida;
//    }

    @Override
    public void run() {
        //System.out.println("inicio hilo del servidor");
        do{
            try {
                //System.out.println("intento leer");
                leer();
                //System.out.println("si pudo leer");
            } catch (IOException ex) {
                System.out.println("ERROR HILO LEER SERVIDOR "+ex);
            }
        }while(detener==false);
    }
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.*;
import java.net.*;
/**
 *
 * @author Estudiantes
 */
public class SistemaCliente implements Runnable{

    public static DatagramSocket clientsocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static int cport = 789, sport = 790;
    public boolean detener=false;
    /*private Socket host;
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;*/
    byte buffer[] = new byte[7];
    private String mensaje;
    private Thread correrCliente;
    private Sistema sistema;
    
    public SistemaCliente(Sistema aThis) throws IOException {
        sistema = aThis;
        System.out.println("Intentanto la conexion");
        clientsocket = new DatagramSocket(cport);
        dp = new DatagramPacket(buffer, buffer.length);
        //dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();
        //ia = InetAddress.getByName("192.168.0.5");
        System.out.println("Cliente is Running...");
        //host = new Socket("localhost",5700); //Conexion
        //System.out.println("COnectado con "+clientsocket.getInetAddress().getHostAddress());
    }
    
//    public DataInputStream getDatosEntrada(){
//        if(datosEntrada==null){
//            try {
//                datosEntrada = new DataInputStream(host.getInputStream());
//            } catch (IOException ex) {
//                System.out.println("se daño el cliente datos entrada constructor");
//            }
//        }
//        return datosEntrada;
//    }
//    
//    public DataOutputStream getDatosSalida(){
//        if(datosSalida==null){
//            try {
//                datosSalida = new DataOutputStream(host.getOutputStream());
//            } catch (IOException ex) {
//                System.out.println("se daño el cliente datos de salida constructor");
//            }
//        }
//        return datosSalida;
//    }
    
    public void leer() throws Exception {
        clientsocket.receive(dp);
        mensaje = new String(dp.getData(), 0,dp.getLength());
        //datosEntrada.read(buffer);
        //System.out.println("LLEGO: "+new String(buffer));
        //mensaje = new String(buffer);
        System.out.println("MENSAJE : "+mensaje);
        sistema.disparosLLega(mensaje);
//        datosEntrada.close();
//        host.close();
    }

    public void enviar(String mensaje) throws IOException{
        System.out.println("Mensaje :"+mensaje);
        clientsocket.send(new DatagramPacket(mensaje.getBytes(),mensaje.length(), ia, sport));
        //datosSalida.write(mensaje.getBytes());
        System.out.println("enviado");
//        datosSalida.close();
//        host.close();
    }
    
    public Thread getHilo(){
        if(correrCliente==null){
            correrCliente = new Thread(this);
        }
        return correrCliente;
    } 

    public void detener(){
        detener = true;
    }
    @Override
    public void run() {
        System.out.println("entro al hilo del cliente");
        do{
            try {
                System.out.println("intento leer");
                leer();
                System.out.println("si pudo leer");
            } catch (Exception ex) {
                System.out.println("DAÑO HILO LEER CLIENTE "+ex);
            }
        }while(detener==false);
    }
}


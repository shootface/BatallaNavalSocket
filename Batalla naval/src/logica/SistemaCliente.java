/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 *
 * @author Estudiantes
 */
public class SistemaCliente implements Runnable{

    private Socket host;
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;
    byte buffer[] = new byte[7];
    private String mensaje;
    private Thread correrCliente;
    private Sistema sistema;
    
    public SistemaCliente(Sistema aThis) throws IOException {
        sistema = aThis;
        System.out.println("Intentanto la conexion");
        host = new Socket("localhost",5700); //Conexion
        System.out.println("COnectado con "+host.getInetAddress().getHostAddress());
        getDatosEntrada();
        getDatosSalida();
    }
    
    public DataInputStream getDatosEntrada(){
        if(datosEntrada==null){
            try {
                datosEntrada = new DataInputStream(host.getInputStream());
            } catch (IOException ex) {
                System.out.println("se daño el cliente datos entrada constructor");
            }
        }
        return datosEntrada;
    }
    
    public DataOutputStream getDatosSalida(){
        if(datosSalida==null){
            try {
                datosSalida = new DataOutputStream(host.getOutputStream());
            } catch (IOException ex) {
                System.out.println("se daño el cliente datos de salida constructor");
            }
        }
        return datosSalida;
    }
    
    public void leer() throws Exception {
        datosEntrada.read(buffer);
        System.out.println("LLEGO: "+new String(buffer));
        mensaje = new String(buffer);
        System.out.println("MENSAJE : "+mensaje);
        sistema.disparosLLega(mensaje);
//        datosEntrada.close();
//        host.close();
    }

    public void enviar(String mensaje) throws IOException{
        System.out.println("Mensaje :"+mensaje);
        datosSalida.write(mensaje.getBytes());
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
        }while(true);
    }
}


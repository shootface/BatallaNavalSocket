/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author Juan guaba
 */
public class SistemaServidor implements Runnable{

    private ServerSocket server;
    private Socket cliente;
    private DataOutputStream datosSalida;
    private DataInputStream datosEntrada;
    private byte buffer[]=new byte[7];
    private String mensaje;
    private Thread correr;
    private Sistema sistema;

    public SistemaServidor(Sistema aThis) {
        sistema = aThis;
    }
    
    
    
    public void crearServidor() throws IOException {
        server = new ServerSocket(5700);
        System.out.println("espero cliente");  
        cliente = server.accept();
        getDatosEntrada();
        getDatosSalida();
    }
    
    public void leer() throws IOException{
        
        System.out.println("llego en el srvidor");
        datosEntrada.read(buffer);        
        System.out.println("LLEGO: "+new String(buffer));
        mensaje = new String(buffer);
        sistema.disparosLLega(mensaje);
    }
    
    public void enviar(String mensaje) throws IOException{
        System.out.println("mensaje : "+mensaje);
        System.out.println("inicio el cliente");
        datosSalida.write(mensaje.getBytes());
//        datosSalida.close();
//        cliente.close();
    }
    
    public Thread getHilo(){
        if(correr==null){
            correr=new Thread(this);
        }
        return correr;
    }
    
    public DataInputStream getDatosEntrada(){
        if(datosEntrada==null){
            try {
                datosEntrada= new DataInputStream(cliente.getInputStream());
            } catch (IOException ex) {
                System.out.println("no sep udo crear datos entrada  en el get");
            }
        }
        return datosEntrada;
    }
    
    public DataOutputStream getDatosSalida(){
        if(datosSalida==null){
            try {
                datosSalida = new DataOutputStream(cliente.getOutputStream());
            } catch (IOException ex) {
                System.out.println("no se pudo crear datos salida en el get");
            }
        }
        return datosSalida;
    }

    @Override
    public void run() {
        System.out.println("inicio hilo del servidor");
        do{
            try {
                System.out.println("intento leer");
                leer();
                System.out.println("si pudo leer");
            } catch (IOException ex) {
                System.out.println("DAÑO HILO LEER SERVIDOR "+ex);
            }
        }while(true);
    }
    
    
}


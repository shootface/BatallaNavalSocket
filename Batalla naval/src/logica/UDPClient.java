package logica;


import java.io.*;
import java.net.*;

public class UDPClient implements Runnable{

    private static DatagramSocket clientsocket;
    private static DatagramPacket dp;
    private static BufferedReader dis;
    private Sistema sistema;
    private Thread correrCliente;
    private String mensaje;
    public static InetAddress ia;
    public static byte buf[] = new byte[1024];
    public static int cport = 791, sport = 792;
    public boolean detener=false;

    public UDPClient(Sistema aThis) throws SocketException, UnknownHostException {
        sistema = aThis;
        System.out.println("IntentandoConexion");
        clientsocket = new DatagramSocket(cport);
        dp = new DatagramPacket(buf, buf.length);
        ia = InetAddress.getByName("198.168.0.6");//IP del servidor
        //ia = InetAddress.getLocalHost();
        System.out.println("Client is running ...");
    }
    
    public void leer() throws IOException{
        clientsocket.receive(dp);
        mensaje = new String(dp.getData(), 0,dp.getLength());
        System.out.println("MENSAJE : "+mensaje);
        sistema.mensajesEntrada(mensaje);
    }
    
    public void enviar(String mensaje) throws IOException{
        System.out.println("Mensaje :"+mensaje);
        clientsocket.send(new DatagramPacket(mensaje.getBytes(),mensaje.length(), ia, sport));
        System.out.println("enviado");
    }
    
    public void detener(){
        detener = true;
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
        }while(detener==false);
    }
}

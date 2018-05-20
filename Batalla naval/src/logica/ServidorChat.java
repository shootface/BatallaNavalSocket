/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.sun.istack.internal.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author Juan Camilo Guaba
 */
public class ServidorChat {

    private Socket miServicio;
    private ServerSocket socketServicio;
    private OutputStream outputStream;
    private InputStream inputStream;
    private DataOutputStream salidaDatos;
    private DataInputStream entradaDatos;

    private boolean opcion = true;

    private Scanner scanner;
    private String esctribir;

    //APERTURA DE SOCKET
    public void conexion(int numeroPuerto) {
        try {
            socketServicio = new ServerSocket(numeroPuerto);
            System.out.println("El servidor se esta escuchando en el puerto: " + numeroPuerto);
            miServicio = socketServicio.accept();
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (opcion) {
                        System.out.print("SERVIDOR: ");
                        recibirDatos();

                    }
                }
            });
            hilo.start();
            while (opcion) {
                scanner = new Scanner(System.in);
                esctribir = scanner.nextLine();
                if (!esctribir.equals("CLIENTE: fin")) {
                    enviarDatos("SERVIDOR: " + esctribir);
                } else {
                    opcion = false;
                }
            }
            miServicio.close();
        } catch (Exception ex) {
            System.out.println("Error al abrir los sockets");
        }
    }

    public void recibirDatos() {
        try {
            inputStream = miServicio.getInputStream();
            entradaDatos = new DataInputStream(inputStream);
            System.out.println(entradaDatos.readUTF());
        } catch (IOException ex) {
            System.out.println("Error al recibir los datos");
        }
    }

    public void cerrarTodo() {
        try {
            salidaDatos.close();
            entradaDatos.close();
            socketServicio.close();
            miServicio.close();

        } catch (IOException ex) {
            System.out.println("Error al cerrar el socket");
        }
    }
     public void enviarDatos(String datos) {
        try {
            outputStream = miServicio.getOutputStream();
            salidaDatos = new DataOutputStream(outputStream);
            salidaDatos.writeUTF(datos);
            salidaDatos.flush();
        } catch (IOException ex) {
            System.out.println("Error en enviar datos");
        }
    }
     
    public static void main(String[] args) {
        ServidorChat serv = new ServidorChat();
        serv.conexion(5555);
    }
}

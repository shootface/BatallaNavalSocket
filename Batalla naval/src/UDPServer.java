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
import java.util.*;

public class UDPServer {

    public static DatagramSocket serversocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;

    public static InetAddress ia;
    public static byte buf[] = new byte[1024];
    public static int cport = 789, sport = 790;

    public static void main(String[] a) throws IOException {
        serversocket = new DatagramSocket(sport);
        dp = new DatagramPacket(buf, buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();
        System.out.println("Server is Running...");
        while (true) {
            serversocket.receive(dp);
            String str = new String(dp.getData(), 0,
                    dp.getLength());
            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                break;
            }
            System.out.println("Client: " + str);
            String str1 = new String(dis.readLine());
            buf = str1.getBytes();
            serversocket.send(new DatagramPacket(buf, str1.length(), ia, cport));
        }
    }
}

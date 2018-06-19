/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.IOException;

/**
 *
 * @author shan
 */
public class SocketClient {

    /**
     * Iniciar Cliente para conexão com servidor
     *
     * @param host
     * @param port
     */
    public static void executarCliente(String host, int port) {
        ClienteSocket cliente = new ClienteSocket(host, port);

        try {
            cliente.executar();
        } catch (IOException ioe) {
            System.out.println("Problemas ao executar cliente: " + ioe.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //iniciando cliente
        executarCliente("192.168.1.15", 12345);

    }

}

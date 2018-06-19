/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author shan
 */
public class ServidorSocket {

    //private static final Logger log = Logger.getLogger(LocalShell.class.getName());
    int PORT;

    ServerSocket servidor;
    Socket cliente;
    Scanner scaner;
    List<PrintStream> saidasClientes;

    public ServidorSocket(int port) {

        this.PORT = port;
        saidasClientes = new ArrayList<>();

    }

    public void execute() throws IOException {

        String ip;

        //Abrindo Porta Servidor
        servidor = new java.net.ServerSocket(PORT);
        System.out.println("Porta 12345 aberta!");

        while (true) {

            //Aceitando conexão cliente            
            cliente = servidor.accept();
            //ip cliente
            ip = cliente.getInetAddress().getHostAddress();
            //aviso conexão
            System.out.println("Nova conexão com o cliente " + ip);

            //adicionar saida do cliente a lista de clientes
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            saidasClientes.add(ps);

            //tratamento de clientes em uma nova Thread
            TrataCliente tc = new TrataCliente(ip, cliente.getInputStream(), this);
            new Thread(tc).start();

        }

    }

    public void distribuiMensagem(String msg) {

        for (PrintStream cli : saidasClientes) {
            cli.println(msg);
            System.out.println(msg);
        }
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            System.out.println("Erro = " + ex.getMessage());
        }

    }

    public void encerrar() {
        secureClose(servidor);
        secureClose(cliente);
        secureClose(scaner);
        System.exit(2);
    }

}

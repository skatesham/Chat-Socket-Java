/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author shan
 */
public class ChatSocketServidor {

    public ChatSocketServidor() {

        ServerSocket servidor;
        Socket cliente;
        Scanner scaner;

        try {
            //Abrindo Porta Servidor
            servidor = new ServerSocket(12345);
            System.out.println("Porta 12345 aberta!");

            //Aceitando conexão cliente            
            cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            //Criando Scanner
            scaner = new Scanner(cliente.getInputStream());

            //Mostrar stream recebida
            String saida;
            while (scaner.hasNext()) {
                saida = scaner.nextLine();

                if (saida.equalsIgnoreCase("sair")) {
                    scaner.close();
                    cliente.close();
                    servidor.close();
                    System.exit(2);
                } else {
                    System.out.println(saida);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

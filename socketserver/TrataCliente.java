/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author shan
 */
public class TrataCliente implements Runnable {

    InputStream cliente;
    ServidorSocket servidor;

    public TrataCliente(String host, InputStream cliente, ServidorSocket servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }
    
    @Override
    public void run() {
        Scanner scan = new Scanner(this.cliente);
        while(scan.hasNextLine()){
            servidor.distribuiMensagem(scan.nextLine());
            //System.out.println(host+": "+scan.nextLine());
        }
        
    }

}

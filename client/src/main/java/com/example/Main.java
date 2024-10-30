package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 3000);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String opzione = "";
        String nbiglietti = "";
        String user = "";
        String pippo = "";
        String stringaRiC= "";
        boolean cond = false;


        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Inserire il proprio username");
            user = sc.nextLine();
            out.writeBytes(user + "\n");
            pippo = in.readLine();
            if(pippo.equals("p"))
            {
                cond = false;
            }
            else
                cond = true;
        } while (!cond);

        

        System.out.println("Scegliere un'opzione (da 1 a 3)");

     do {
            System.out.println("1_Vedere disponibilita' biglietti");
            System.out.println("2_Comprare biglietti");
            System.out.println("3_Esci dal programma");
            opzione = sc.nextLine();
            if(opzione.equals("1")){
                out.writeBytes("N" + "\n");
                stringaRiC = in.readLine();
                System.out.println("Numero di biglietti : " + stringaRiC);
            }
            else if(opzione.equals("2")){
                out.writeBytes("BUY" + "\n");
                System.out.println("Inserire quantità dei biglietti");
                nbiglietti = sc.nextLine();
                out.writeBytes(nbiglietti + "\n");

                stringaRiC = in.readLine();

                if(stringaRiC.equals("!"))
                    System.out.println("problema con l'acquisto");
                else if(stringaRiC.equals("KO"))
                    System.out.println("biglietti sold out");
                else
                    System.out.println("Acquisto avvenuto con successo");

            }
            else if(opzione.equals("3")){
                out.writeBytes("QUIT" + "\n");
            }
            else
            {
                System.out.println("inserire un'opzione valida");
            }

        } while (!opzione.equals("3"));

        socket.close();
        sc.close();
    }
}
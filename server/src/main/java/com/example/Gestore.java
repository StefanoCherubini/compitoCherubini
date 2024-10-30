package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Gestore extends Thread {
    Socket socket;
    Biglietti b = new Biglietti(100);
    ArrayList <String> array = new ArrayList <>();

    Gestore(Socket socket,Biglietti b){
        this.socket = socket;
        this.b = b;
    }


    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String pippo ="";
            int intNum = 0;
            boolean presente = true;
            do {
                pippo = in.readLine();
                if(array.contains(pippo)){
                        out.writeBytes("p\n");
                        presente=true;
                }
                else{
                        array.add(pippo);
                        out.writeBytes("np\n");
                        presente = false;
                }
            }while(!presente);

            String stringaRicOpz = "";

            do {
                stringaRicOpz = in.readLine();
                if (!stringaRicOpz.equals("QUIT")) {
                    if(stringaRicOpz.equals("N"))
                    {
                        out.writeBytes(b.getB()+"\n");
                    }
                    else if(stringaRicOpz.equals("BUY")) 
                    {
                        String quantBiglietti = in.readLine();
                        intNum = Integer.parseInt(quantBiglietti);

                        if(b.getB() > 0)
                        {                       
                            if(intNum <= 100){
                            b.setB(b.getB()-intNum);
                            out.writeBytes("i\n");
                            }
                            else
                             out.writeBytes("!\n");
                        } 
                        else 
                             out.writeBytes("KO\n");
                    }   
                }                
            } while (!stringaRicOpz.equals("QUIT"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.example.networktestmarkomrsic;

import android.widget.TextView;
import java.io.*;
import java.net.Socket;

public class SimpleThread extends Thread{
    private String inputMatNr;
    private TextView serverAnswer;
    private String host = "se2-isys.aau.at";
    private int port = 53212;
    private Socket socket;
    String serverAntwort;

    public SimpleThread(String matNr, TextView sa){
        inputMatNr = matNr;
        serverAnswer = sa;
    }
    public void run(){
        try {
            socket = new Socket(host, port);
            DataOutputStream outServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            outServer.writeBytes(inputMatNr + "\n");
            serverAntwort = inServer.readLine();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String get(){
        return serverAntwort;
    }
}

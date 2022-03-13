package com.example.networktestmarkomrsic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMatNr;
    private TextView textViewServer;
    private TextView serverAnswer;
    private EditText inputMatNr;
    private Button buttonSend;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMatNr = (TextView) findViewById(R.id.textViewMatNr);
        textViewServer = (TextView) findViewById(R.id.textViewServer);
        inputMatNr = (EditText) findViewById(R.id.inputMatNr);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        serverAnswer = (TextView) findViewById(R.id.serverAnswer);
        calculate = (Button) findViewById(R.id.calc);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matNr = inputMatNr.getText().toString();
                SimpleThread t = new SimpleThread(matNr, serverAnswer);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                serverAnswer.setText(t.get());
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matNrString = inputMatNr.getText().toString();
                char[] cArr = matNrString.toCharArray();

                Random random = new Random();
                int n = random.nextInt(8);
                int zwischen = random.nextInt(8);
                while(zwischen == n){
                    zwischen = random.nextInt(8);
                }
                int m = zwischen;
                int zahl1 = matNrString.charAt(n) - '0';
                int zahl2 = matNrString.charAt(m) - '0';
                if(ggt(zahl1, zahl2) > 1){
                    serverAnswer.setText(m+" "+n);
                }else{
                    serverAnswer.setText("Kein gemeinsamer Teiler >1 gefunden");
                }
            }
        });
    }
    public int ggt(int a, int b) {

        if (a == 0)
            return b;
        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }
}
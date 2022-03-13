package com.example.networktestmarkomrsic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMatNr;
    private TextView textViewServer;
    private TextView serverAnswer;
    private EditText inputMatNr;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMatNr = (TextView) findViewById(R.id.textViewMatNr);
        textViewServer = (TextView) findViewById(R.id.textViewServer);
        inputMatNr = (EditText) findViewById(R.id.inputMatNr);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        serverAnswer = (TextView) findViewById(R.id.serverAnswer);

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
    }
}
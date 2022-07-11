package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RegistrationActivity extends AppCompatActivity {
    ConstraintLayout registrationLayout;
    Button regBtn;
    private String regData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        registrationLayout = findViewById(R.id.registrationLayout);
        regBtn = findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readUserData();

            }
        });
    }

    protected void readUserData() {

        EditText name = findViewById(R.id.inputUsername);
        EditText lastname = findViewById(R.id.inputLastname);
        EditText phone = findViewById(R.id.inputPhone);
        EditText password = findViewById(R.id.inputPassword);


        this.regData = "reg//" + name.getText().toString() + "//" + lastname.getText().toString() + "//"
                + phone.getText().toString() + "//" + password.getText().toString();
        System.out.println(regData);
    }

    protected void sendDataToServer() {
        Socket socket = null;
        try {
            socket = new Socket(MainActivity.getHost(), MainActivity.getPort());
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(regData);
            String response = is.readUTF();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
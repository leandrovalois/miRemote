package com.example.miremote;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String serverIP = "192.168.0.182"; // Substitua pelo IP do computador
    private int serverPort = 8080; // Porta do servidor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botões
        ImageButton btnPower = findViewById(R.id.btnPower);
        ImageButton btnUp = findViewById(R.id.btnUp);
        ImageButton btnDown = findViewById(R.id.btnDown);
        ImageButton btnLeft = findViewById(R.id.btnLeft);
        ImageButton btnRight = findViewById(R.id.btnRight);
        ImageButton btnOK = findViewById(R.id.btnOK);
        ImageButton btnVolumeDown = findViewById(R.id.btnVolumeDown);
        ImageButton btnVolumeUp = findViewById(R.id.btnVolumeUp);
        ImageButton btnNetflix = findViewById(R.id.btnNetflix);
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnHome = findViewById(R.id.btnHome);

        // Listeners
        btnPower.setOnClickListener(v -> sendCommand("power"));
        btnUp.setOnClickListener(v -> sendCommand("up"));
        btnDown.setOnClickListener(v -> sendCommand("down"));
        btnLeft.setOnClickListener(v -> sendCommand("left"));
        btnRight.setOnClickListener(v -> sendCommand("right"));
        btnOK.setOnClickListener(v -> sendCommand("ok"));
        btnVolumeDown.setOnClickListener(v -> sendCommand("volumedown"));
        btnVolumeUp.setOnClickListener(v -> sendCommand("volumeup"));
        btnNetflix.setOnClickListener(v -> sendCommand("netflix"));
        btnBack.setOnClickListener(v -> sendCommand("back"));
        btnHome.setOnClickListener(v -> sendCommand("home"));
    }

    private void sendCommand(String command) {
        new Thread(() -> {
            try {
                String url = "http://" + serverIP + ":" + serverPort + "/" + command;
                Log.d("MiRemote", "Tentando conectar a: " + url); // Log da URL
                URL serverUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                Log.d("MiRemote", "Código de resposta: " + responseCode);
                connection.disconnect();
            } catch (IOException e) {
                Log.e("MiRemote", "Erro ao enviar comando", e);
            }
        }).start();
    }
}
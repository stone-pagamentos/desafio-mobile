package com.example.leonardo.desafiomobile.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.leonardo.desafiomobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void abrirLista(View view){
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);

    }
    public void abrirHistorico(View view){
        Intent intent = new Intent(this, Historico.class);
        startActivity(intent);
    }
}

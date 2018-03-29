package com.andreatta.mateus.keepnote;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarNota extends AppCompatActivity {

    EditText titulo,corpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_nota);

        titulo = findViewById(R.id.txtTitulo);
        corpo = findViewById(R.id.txtNota);
        Button btn = findViewById(R.id.btnSalvar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());

                String tituloString = titulo.getText().toString();
                String notaString = corpo.getText().toString();
                String resultado;

                resultado = crud.inserir(tituloString,notaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }

}

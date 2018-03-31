package com.andreatta.mateus.keepnote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarNota extends AppCompatActivity {

    EditText titulo,corpo;

    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nota);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        titulo  = findViewById(R.id.txtTitulo);
        corpo   = findViewById(R.id.txtNota);
        alterar = findViewById(R.id.btnSalvar);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        corpo.setText(cursor.getString(cursor.getColumnIndexOrThrow("conteudo")));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo),
                        titulo.getText().toString(),
                        corpo.getText().toString());
                Intent intent = new Intent(EditarNota.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void VoltarTela(){
        Intent Tela = new Intent(this, MainActivity.class);
        startActivity(Tela);
    }

}

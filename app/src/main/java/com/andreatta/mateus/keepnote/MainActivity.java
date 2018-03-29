package com.andreatta.mateus.keepnote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// https://www.devmedia.com.br/criando-um-crud-com-android-studio-e-sqlite/32815

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     /*   BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {"titulo","conteudo"};
        int[] idViews = new int[] {R.id.tituloNota, R.id.txtNota};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_item,cursor,nomeCampos,idViews, 0);
        lista = findViewById(R.id.listView);
        lista.setAdapter(adaptador);*/
    }

    public void IrCad(View view) {
        Intent intent = new Intent(this, CadastrarNota.class);
        startActivity(intent);
    }
}

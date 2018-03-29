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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// https://www.devmedia.com.br/criando-um-crud-com-android-studio-e-sqlite/32815

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            BancoController crud = new BancoController(getBaseContext());
            Cursor cursor = crud.carregaDados();

            String[] nomeCampos = new String[] {"idNota","titulo","conteudo"};
            int[] idViews = new int[] {R.id.tituloNota, R.id.conteudoNota};

            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                    R.layout.list_item,cursor,nomeCampos,idViews, 0);
            lista = findViewById(R.id.listView);
            lista.setAdapter(adaptador);
        }catch (Exception ex){
            System.out.print(ex.getMessage());
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void IrCad(View view) {
        Intent intent = new Intent(this, CadastrarNota.class);
        startActivity(intent);
    }
}

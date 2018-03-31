package com.andreatta.mateus.keepnote;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    AlertDialog alerta;
    //retorno do alert
    boolean retorno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarregarLista();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                //Alert de confirmação do excluir
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Opções");
                builder.setMessage("Edite ou exclua essa nota, ou simplesmente clique fora dessa caixa para cancelar");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        editar(position);
                    }
                });
                builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        excluir(position);
                    }
                });

                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();

            }
        });
    }

    public void IrCad(View view) {
        Intent intent = new Intent(this, CadastrarNota.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarLista();
    }

    public void CarregarLista(){

        try {
            BancoController crud = new BancoController(getBaseContext());
            Cursor cursor = crud.carregaDados();

            String[] nomeCampos = new String[]{"titulo", "conteudo"};
            int[] idViews = new int[]{R.id.tituloNota, R.id.conteudoNota};

            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                    R.layout.list_item, cursor, nomeCampos, idViews, 0);
            lista = findViewById(R.id.listView);
            lista.setAdapter(adaptador);

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void editar(int position){
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        try {
            String codigo;
            cursor.moveToPosition(position);
            codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            Intent intent = new Intent(MainActivity.this, EditarNota.class);
            intent.putExtra("codigo", codigo);
            startActivity(intent);
            finish();
        }catch (Exception ex){
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(ex.getMessage());
        }
    }

    private void excluir(int position){
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        try {
            String codigo;
            cursor.moveToPosition(position);
            codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            crud.deletaRegistro(Integer.parseInt(codigo));
        }catch (Exception ex){
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(ex.getMessage());
        }

        CarregarLista();
    }
}


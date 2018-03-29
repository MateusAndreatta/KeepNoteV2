package com.andreatta.mateus.keepnote;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Andreatta on 29/03/2018.
 */

public class BancoController {

    private SQLiteDatabase bd;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String inserir(String titulo, String corpo){
        ContentValues valores;
        long resultado;

        bd = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", corpo);

        resultado = bd.insert("notas_tb", null, valores);
        bd.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void update(int id,String titulo, String corpo){
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("conteudo",corpo);

        bd.update("notas_tb",values,"idNota = "+id,null);
    }

    public void delete(int id){
        bd.delete("notas_tb","idNota = "+id,null);
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {"titulo","conteudo"};
        bd = banco.getReadableDatabase();
        cursor = bd.query("notas_tb", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        bd.close();
        return cursor;
    }
}

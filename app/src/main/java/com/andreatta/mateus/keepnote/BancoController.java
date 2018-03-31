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

    public void deletaRegistro(int id){
        String where = "_id" + "=" + id;
        bd = banco.getReadableDatabase();
        bd.delete("notas_tb",where,null);
        bd.close();
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {"_id","titulo","conteudo"};
        bd = banco.getReadableDatabase();
        cursor = bd.query("notas_tb", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        bd.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {"_id","titulo","conteudo"};
        String where = "_id" + "=" + id;
        bd = banco.getReadableDatabase();
        cursor = bd.query("notas_tb",campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        bd.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String conteudo){
        ContentValues valores;
        String where;

        bd = banco.getWritableDatabase();

        where = "_id" + "=" + id;

        valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        bd.update("notas_tb",valores,where,null);
        bd.close();
    }
}

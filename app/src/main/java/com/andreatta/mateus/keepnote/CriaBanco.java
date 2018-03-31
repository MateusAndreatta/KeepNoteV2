package com.andreatta.mateus.keepnote;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andreatta on 29/03/2018.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String nomeBD = "KeepNote_BD";
    private static final int versaoBD = 1;

    public CriaBanco(Context ctx){
        super(ctx,nomeBD,null,versaoBD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notas_tb(_id integer primary key autoincrement,titulo text not null, conteudo text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE notas_tb");
        onCreate(sqLiteDatabase);
    }
}

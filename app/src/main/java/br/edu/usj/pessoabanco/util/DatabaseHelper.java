package br.edu.usj.pessoabanco.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.usj.pessoabanco.dao.PessoaDAO;

/**
 * Created by rafael on 22/11/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BancoPessoa";
    private static int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                PessoaDAO.NOME_TABELA +"(_id INTEGER PRIMARY KEY, idade INTEGER, nome TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    // DAO (Data Access Object)

    // CRUD (Create, Read, Update, Delete)
}

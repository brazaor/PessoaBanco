package br.edu.usj.pessoabanco.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.usj.pessoabanco.entity.Pessoa;
import br.edu.usj.pessoabanco.util.DatabaseHelper;

/**
 * Created by rafael on 22/11/17.
 */

public class PessoaDAO implements DAO<Pessoa> {

    private DatabaseHelper helper;

    public static final String NOME_TABELA = "pessoa";

    public PessoaDAO(Context contexto){
        helper = new DatabaseHelper(contexto);
    }

    @Override
    public Boolean salvar(Pessoa p) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", p.getNome());
        values.put("idade", p.getIdade());

        long inseriu = db.insert(NOME_TABELA, null,values);

        if(inseriu > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean remover(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String idString = String.valueOf(id);
        int removeu = db.delete(NOME_TABELA, "id = ?", new String[]{idString});

        if (removeu > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean atualizar(Pessoa p) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("_id", p.getId());
        values.put("nome", p.getNome());
        values.put("idade", p.getIdade());

        int atualizou = db.update(NOME_TABELA, values, "id = ?", new String[p.getId()]);

        if (atualizou > 0){
            return true;
        }

        return false;
    }

    @Override
    public List<Pessoa> listar() {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id, nome, idade FROM "+ NOME_TABELA, null);

        cursor.moveToFirst();

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        for(int i = 0; i < cursor.getCount(); i++){
            Pessoa p = new Pessoa();
            p.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            p.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));

            pessoas.add(p);
        }

        return pessoas;
    }
}

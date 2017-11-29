package br.edu.usj.pessoabanco.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import br.edu.usj.pessoabanco.dao.PessoaDAO;

/**
 * Created by rafael on 29/11/17.
 */

public class CadastroPessoa extends Activity {

    PessoaDAO dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dao = new PessoaDAO(this);
    }
}

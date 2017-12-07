package br.edu.usj.pessoabanco.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.usj.pessoabanco.R;
import br.edu.usj.pessoabanco.dao.PessoaDAO;
import br.edu.usj.pessoabanco.entity.Pessoa;

/**
 * Created by rafael on 29/11/17.
 */

public class CadastroPessoa extends Activity {

    private PessoaDAO dao;
    private EditText nomeEdit, idadeEdit;
    private int idPessoa = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);

        this.nomeEdit = (EditText) findViewById(R.id.id_nome);
        this.idadeEdit = (EditText) findViewById(R.id.id_idade);

        dao = new PessoaDAO(this);

        this.nomeEdit.setText("");
        this.idadeEdit.setText("");

        Intent intent = getIntent();
        idPessoa = intent.getIntExtra("id", 0);
        if (idPessoa >0){
            Pessoa p = dao.obterPorId(idPessoa);
            this.nomeEdit.setText(p.getNome());
            this.idadeEdit.setText(String.valueOf(p.getIdade()));
        }
    }

    public void cadastrar(View view) {

        String nome = this.nomeEdit.getText().toString();
        String idadeString = this.idadeEdit.getText().toString();
        int idade = Integer.valueOf(idadeString);

        Pessoa p = new Pessoa();
        p.setIdade(idade);
        p.setNome(nome);

        boolean sucesso = false;

        if(idPessoa > 0){
            p.setId(idPessoa);
            sucesso = dao.atualizar(p);

        }
        else{
            sucesso = dao.salvar(p);
        }

        if (sucesso){
            Toast.makeText(this,
                    "Pessoa cadastrada com sucesso", Toast.LENGTH_LONG).show();
            idPessoa = 0;
            this.nomeEdit.setText("");
            this.idadeEdit.setText("");
        }
        else {
            Toast.makeText(this,
                    "Erro ao cadastrar pessoa", Toast.LENGTH_LONG).show();
        }

    }

    public void listar(View view) {
        Intent intent = new Intent(this, ListarPessoa.class);
        startActivity(intent);
    }
}

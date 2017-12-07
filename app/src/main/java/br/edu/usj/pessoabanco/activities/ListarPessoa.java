package br.edu.usj.pessoabanco.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.usj.pessoabanco.R;
import br.edu.usj.pessoabanco.dao.PessoaDAO;
import br.edu.usj.pessoabanco.entity.Pessoa;

/**
 * Created by rafael on 06/12/2017.
 */

public class ListarPessoa extends Activity {

    private PessoaDAO dao;
    private ListView listView;

    private AlertDialog alertDialog, dialogConfirmacao;
    private int pessoaSelecionada;
    private List<Map<String, Object>> pessoas;

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(
                AdapterView<?> adapterView,
                View view, int position, long id) {
            pessoaSelecionada = position;
            alertDialog.show();
        }
    };

    private DialogInterface.OnClickListener listener2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int item) {
            Integer id = (Integer) pessoas.get(pessoaSelecionada).get("id");
            switch (item){
                case 0:
                    Intent intent = new Intent(getApplicationContext(),
                            CadastroPessoa.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    break;
                case 1:
                    dialogConfirmacao.show();
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    boolean sucesso = dao.remover(id);
                    if (sucesso){
                        pessoas.remove(pessoaSelecionada);
                        Toast.makeText(getApplicationContext(), R.string.remover_sucesso,
                                Toast.LENGTH_LONG).show();
                        dialogConfirmacao.dismiss();
                    }
                    listView.invalidateViews();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialogConfirmacao.dismiss();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listar);

        listView = (ListView) findViewById(R.id.id_lista);

        dao = new PessoaDAO(this);

        String[] de = {"nome", "idade"};
        int[] para = {R.id.id_lista_nome, R.id.id_lista_idade};

        SimpleAdapter adapter = new SimpleAdapter(this,
                listaPessoas(), R.layout.layout_pessoa,
                de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        this.alertDialog = criaAlertDialog();
        this.dialogConfirmacao = criaDialogConfirmacao();
    }

    private List<Map<String, Object>> listaPessoas(){

        pessoas = new ArrayList<Map<String, Object>>();

        List<Pessoa> listaPessoas = dao.listar();

        for(Pessoa p: listaPessoas){
            Map<String, Object> item =
                    new HashMap<String, Object>();
            item.put("id", p.getId());
            item.put("nome", p.getNome());
            item.put("idade", p.getIdade());
            pessoas.add(item);
        }

        return pessoas;
    }

    private AlertDialog criaAlertDialog(){
        final CharSequence[] items = {
                getString(R.string.editar),
                getString(R.string.remover)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, listener2);

        return builder.create();
    }

    private AlertDialog criaDialogConfirmacao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao_pessoa);
        builder.setPositiveButton(getString(R.string.sim), listener2);
        builder.setNegativeButton(getString(R.string.nao), listener2);

        return builder.create();
    }

    public void voltar(View view) {

        Intent intent = new Intent(getApplicationContext(),
                CadastroPessoa.class);
        startActivity(intent);
    }
}

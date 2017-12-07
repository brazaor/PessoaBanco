package br.edu.usj.pessoabanco.dao;

import java.util.List;

/**
 * Created by rafael on 22/11/17.
 */

public interface DAO<T> {

    Boolean salvar(T objeto);

    Boolean remover(int id);

    Boolean atualizar(T objeto);

    List<T> listar();

    T obterPorId(int id);
    
}

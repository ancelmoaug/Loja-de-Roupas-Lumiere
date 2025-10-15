package dao;

import model.Tamanho;

public interface TamanhoDAO {

    Tamanho buscarPorId(int id);

    int buscarPorTamanho(String nomeTamanho);

}

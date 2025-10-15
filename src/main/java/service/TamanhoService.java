package service;

import db.DB;
import impl.TamanhoDAOImpl;
import model.Tamanho;

public class TamanhoService {

    private TamanhoDAOImpl tamanhoDAOImpl;

    public TamanhoService() {
        tamanhoDAOImpl = new TamanhoDAOImpl(DB.getConnection());
    }

    public Tamanho buscarPorId(int id) {
        return tamanhoDAOImpl.buscarPorId(id);
    }

    public int buscarPorTamanho(String tamanho) {
        int idTamanho = tamanhoDAOImpl.buscarPorTamanho(tamanho);
        return idTamanho;
    }

}

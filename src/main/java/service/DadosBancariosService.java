package service;

import db.DB;
import impl.DadosBancariosDAOImpl;
import model.DadosBancarios;
import java.util.List;

public class DadosBancariosService {

    private DadosBancariosDAOImpl dadosBancariosDAOImpl;

    
    public DadosBancariosService() {
        dadosBancariosDAOImpl = new DadosBancariosDAOImpl(DB.getConnection());
    }

    public DadosBancariosDAOImpl getDadosBancariosDAOImpl() {
        return dadosBancariosDAOImpl;
    }

    public DadosBancarios inserirDadosBancarios(DadosBancarios dados) {
        try {
            return dadosBancariosDAOImpl.inserir(dados);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(DadosBancarios dados) {
        try {
            return dadosBancariosDAOImpl.atualizar(dados);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deletar(int id) {
        try {
            if (!(dadosBancariosDAOImpl.buscarPorId(id) instanceof DadosBancarios)) {
                return false; 
            }
            dadosBancariosDAOImpl.deletar(id); 
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public DadosBancarios buscarPorId(int id) {
        return dadosBancariosDAOImpl.buscarPorId(id);
    }

    public List<DadosBancarios> listarTodos() {
        return dadosBancariosDAOImpl.listarTodos();
    }
}

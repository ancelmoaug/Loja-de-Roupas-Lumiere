package service;

import java.util.List;

import db.DB;
import impl.TelefoneDAOImpl;
import model.Telefone;

public class TelefoneService {

    private TelefoneDAOImpl telefoneDAOImpl;

    public TelefoneService() { // Service instancia um DAOImpl mandando DB.getConnection
        telefoneDAOImpl = new TelefoneDAOImpl(DB.getConnection());
    }

    public TelefoneDAOImpl getTelefoneDAOImpl() {
        return telefoneDAOImpl;
    }

    public Telefone inserirTelefone(Telefone telefone) {
        try {
            return telefoneDAOImpl.inserir(telefone);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Telefone telefone) {
        try {
            if (!(telefoneDAOImpl.buscarPorId(telefone.getId()) instanceof Telefone)) {
                return false; // Se não existir, operação não foi sucesso
            }
            return telefoneDAOImpl.atualizar(telefone);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deletar(int id) {
        try {
            if (!(telefoneDAOImpl.buscarPorId(id) instanceof Telefone)) {
                return false; // Se não existir, operação não foi sucesso
            }
            telefoneDAOImpl.deletar(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Telefone buscarPorId(int id) {
        return telefoneDAOImpl.buscarPorId(id);
    }

    public List<Telefone> listarTodos() {
        return telefoneDAOImpl.listarTodos();
    }

    // Específicos
    public Telefone buscarPorNumero(String numero) {
        return telefoneDAOImpl.buscarPorNumero(numero);
    }
}



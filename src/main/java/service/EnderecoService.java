package service;

import java.util.List;

import db.DB;
import impl.EnderecoDAOImpl;
import model.Endereco;

public class EnderecoService {

    private EnderecoDAOImpl enderecoDAOImpl;

    public EnderecoService() { 
        enderecoDAOImpl = new EnderecoDAOImpl(DB.getConnection());
    }

    public EnderecoDAOImpl getEnderecoDAOImpl() {
        return enderecoDAOImpl;
    }

    // Inserir endereço
    public Endereco inserirEndereco(Endereco endereco) {
        return enderecoDAOImpl.inserir(endereco);
    }

    // Atualizar endereço
    public boolean atualizarEndereco(Endereco endereco) {
        return enderecoDAOImpl.atualizar(endereco);
    }

    // Deletar endereço
    public boolean deletarEndereco(int id) {
        try {
            if (enderecoDAOImpl.buscarPorId(id) == null) { 
                return false;
            }
            enderecoDAOImpl.deletar(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    // Buscar por ID
    public Endereco buscarPorId(int id) {
        return enderecoDAOImpl.buscarPorId(id);
    }

    // Buscar por município
    public List<Endereco> buscarPorCidade(String cidade) {
        return enderecoDAOImpl.buscarPorMunicipio(cidade);
    }

    // Buscar por estado
    public List<Endereco> buscarPorEstado(String estado) {
        return enderecoDAOImpl.buscarPorEstado(estado);
    }

    // Buscar por CEP
    public List<Endereco> buscarPorCep(String cep) {
        return enderecoDAOImpl.buscarPorCep(cep);
    }

    // Listar todos
    public List<Endereco> listarTodosEnderecos() {
        return enderecoDAOImpl.listarTodos();
    }
}

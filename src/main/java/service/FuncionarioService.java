package service;

import java.util.List;

import db.DB;
import impl.FuncionarioDAOImpl;
import model.Funcionario;

public class FuncionarioService {
    
    private FuncionarioDAOImpl funcionarioDAOImpl;

    
    public FuncionarioService() {//Service instancia um DAOImpl mandando um DB.getConnection no construtor
        funcionarioDAOImpl = new FuncionarioDAOImpl(DB.getConnection());
    }

    public FuncionarioDAOImpl getVariacaoDAOImpl() {
        return funcionarioDAOImpl;
    }
    
    // esse metodo deve chamar a funcao inserirUsuario de UsuarioService
    public Funcionario inserirFuncionario(Funcionario funcionario) {
        // código do CRUD com o BD
    }

    // esse metodo deve chamar a funcao atualizar de UsuarioService
    boolean atualizar(Funcionario funcionario) {
        // código do CRUD com o BD
    }

    boolean deletar(int id) {
        // código do CRUD com o BD
    }

    Funcionario buscarPorId(int id) {
        // código do CRUD com o BD
    }

    List<Funcionario> listarTodos() {
        // código do CRUD com o BD
    }


    // Específicos
    List<Funcionario> buscarPorCargo(String cargo) {
        // código do CRUD com o BD
    }

    List<Funcionario> buscarPorNome(String nome) {
        // código do CRUD com o BD
    }

    
}

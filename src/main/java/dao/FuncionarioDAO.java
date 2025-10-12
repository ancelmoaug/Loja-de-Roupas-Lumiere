package dao;

import java.util.List;

import model.Funcionario;

public interface FuncionarioDAO {
    
    Funcionario inserir(Funcionario funcionario);
    boolean atualizar(Funcionario funcionario);
    boolean deletar(int id);
    Funcionario buscarPorId(int id);
    List<Funcionario> listarTodos();

    // Específicos
    List<Funcionario> buscarPorCargo(String cargo);
    List<Funcionario> buscarPorNome(String nome);
    
}

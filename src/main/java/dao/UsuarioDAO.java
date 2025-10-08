package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {

    void inserir(Usuario usuario);
    void atualizar(Usuario usuario);
    void deletar(int id);
    Usuario buscarPorId(int id);
    List<Usuario> listarTodos();
    Usuario buscarPorEmail(String email);
    Usuario buscarPorNome(String nome);
    Usuario buscarPorCpf(String cpf);

}

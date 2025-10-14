package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
    // Métodos CRUD (Create, Read, Update, Delete)
    Usuario inserir(Usuario usuario);
    boolean atualizar(Usuario usuario);
    boolean deletar(int id);
    Usuario buscarPorId(int id);

    // Métodos específicos de consulta
    List<Usuario> listarTodos();
    Usuario buscarPorEmail(String email);
    Usuario buscarPorNome(String nome);
    Usuario buscarPorCpf(String cpf);

}

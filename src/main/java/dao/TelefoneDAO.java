package dao;

import java.util.List;
import model.Telefone;

public interface TelefoneDAO {

    Telefone inserir(Telefone telefone);
    boolean atualizar(Telefone telefone);
    boolean deletar(int id);
    Telefone buscarPorId(int id);
    List<Telefone> listarTodos();

    // Específicos
    Telefone buscarPorNumero(String numero);
}

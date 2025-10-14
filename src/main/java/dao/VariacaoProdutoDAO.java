package dao;

import java.util.List;
import model.DadosBancarios;

public interface DadosBancariosDAO {

    DadosBancarios inserir(DadosBancarios dados);
    boolean atualizar(DadosBancarios dados);
    boolean deletar(int id);
    DadosBancarios buscarPorId(int id);

    // Específicos (adicione mais se necessário futuramente)
    List<DadosBancarios> listarTodos();
}
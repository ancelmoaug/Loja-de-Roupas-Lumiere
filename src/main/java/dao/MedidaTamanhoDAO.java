package dao;

import java.util.List;
import model.MedidaTamanho;

public interface MedidaTamanhoDAO {

    MedidaTamanho inserir(MedidaTamanho medida);
    boolean atualizar(MedidaTamanho medida);
    boolean deletar(int id);
    MedidaTamanho buscarPorId(int id);
    List<MedidaTamanho> listarTodos();

    List<MedidaTamanho> buscarPorProduto(int idProduto);
    List<MedidaTamanho> buscarPorTamanho(String tamanho);
}

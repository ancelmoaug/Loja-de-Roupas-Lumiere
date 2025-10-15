package dao;

import java.util.List;
import model.ProdutoBase;

public interface ProdutoBaseDAO {
    

    ProdutoBase inserir(ProdutoBase produto);
    boolean atualizar(ProdutoBase produto);
    boolean deletar(int id);
    ProdutoBase buscarPorId(int id);
    List<ProdutoBase> listarTodos();

   
    List<ProdutoBase> buscarPorNome(String nome);
    List<ProdutoBase> buscarPorCategoria(String categoria);
    List<ProdutoBase> buscarPorFornecedor(int idFornecedor);
}

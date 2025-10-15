package dao;

import java.util.List;
import model.VariacaoProduto;

public interface VariacaoProdutoDAO {

    VariacaoProduto inserir(VariacaoProduto variacao);
    boolean atualizar(VariacaoProduto variacao);
    boolean deletar(int id);
    VariacaoProduto buscarPorId(int id);

    // Específicos
    List<VariacaoProduto> buscarPorProdutoBase(int idProdutoBase);
}
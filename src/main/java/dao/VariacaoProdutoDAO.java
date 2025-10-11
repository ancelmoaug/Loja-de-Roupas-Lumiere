package dao;

import java.util.List;

import model.VariacaoProduto;

public interface VariacaoProdutoDAO {
    
    VariacaoProduto inserir(VariacaoProduto variacao);
    boolean atualizar(VariacaoProduto variacao);
    boolean deletar(int id);
    VariacaoProduto buscarPorId(int id);
    List<VariacaoProduto> listarTodas();

    // Específicos
    List<VariacaoProduto> buscarPorProdutoBase(int idProdutoBase);
    List<VariacaoProduto> buscarPorCor(String cor);
    List<VariacaoProduto> buscarPorTamanho(String tamanho);
    List<VariacaoProduto> buscarPorDisponibilidade(boolean emEstoque);
    
}

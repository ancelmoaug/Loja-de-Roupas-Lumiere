package dao;

import java.util.List;

import model.Fornecedor;
import model.ProdutoBase;

public interface FornecedorDAO {
    
    Fornecedor inserir(Fornecedor fornecedor);
    boolean atualizar(Fornecedor fornecedor);
    boolean deletar(int id);
    Fornecedor buscarPorId(int id);
    List<Fornecedor> listarTodos();

    // Específicos
    Fornecedor buscarPorCnpj(String cnpj);
    List<Fornecedor> buscarPorNomeComercial(String nomeComercial);
    List<ProdutoBase> listarProdutosDoFornecedor(int idFornecedor);
    
}

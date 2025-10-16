package controller;

import java.util.List;

import model.Categoria;
import model.Fornecedor;
import model.MedidaTamanho;
import model.ProdutoBase;
import model.VariacaoProduto;
import service.ProdutoBaseService;

public class ProdutoBaseController {

    private ProdutoBaseService produtoBaseService;


    public ProdutoBaseController() {
        produtoBaseService = new ProdutoBaseService();
    }

    public ProdutoBase inserirProduto(String nome, String descricao, double precoBase, 
                                  Categoria categoria, List<VariacaoProduto> variacoes, 
                                  List<MedidaTamanho> medidasPorTamanho, Fornecedor fornecedor) {

        ProdutoBase produto = new ProdutoBase(nome, descricao, precoBase, categoria, fornecedor);
        return produtoBaseService.inserir(produto);
    }

    public boolean atualizar(String nome, String descricao, double precoBase, 
                             Categoria categoria, List<VariacaoProduto> variacoes, 
                             List<MedidaTamanho> medidasPorTamanho, Fornecedor fornecedor) {

        ProdutoBase produto = new ProdutoBase(nome, descricao, precoBase, categoria, fornecedor);
        return produtoBaseService.atualizar(produto);
    }

    public boolean deletar(int id) {
        return produtoBaseService.deletar(id);
    }

    public ProdutoBase buscarPorId(int id) {
        return produtoBaseService.buscarPorId(id);
    }

    public List<ProdutoBase> listarTodos() {
        return produtoBaseService.listarTodos();
    }

    // Métodos específicos
    public List<ProdutoBase> buscarPorCategoria(String categoria) {
        return produtoBaseService.buscarPorCategoria(categoria);
    }

    public List<ProdutoBase> buscarPorFornecedor(int idFornecedor) {
        return produtoBaseService.buscarPorFornecedor(idFornecedor);
    }
}


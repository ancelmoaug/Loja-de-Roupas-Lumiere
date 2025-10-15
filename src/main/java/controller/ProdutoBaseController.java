package controller;

import java.util.List;

import model.Categoria;
import model.Fornecedor;
import model.MedidaTamanho;
import model.Produto;
import model.VariacaoProduto;
import service.ProdutoService;

public class ProdutoBaseController {

   

    private ProdutoService produtoService;

    public ProdutoController() {
        produtoService = new ProdutoService();
    }

    public Produto inserirProduto(String nome, String descricao, double precoBase, 
                                  Categoria categoria, List<VariacaoProduto> variacoes, 
                                  List<MedidaTamanho> medidasPorTamanho, Fornecedor fornecedor) {

        Produto produto = new Produto(nome, descricao, precoBase, categoria, variacoes, medidasPorTamanho, fornecedor);
        return produtoService.inserir(produto);
    }

    public boolean atualizar(int id, String nome, String descricao, double precoBase, 
                             Categoria categoria, List<VariacaoProduto> variacoes, 
                             List<MedidaTamanho> medidasPorTamanho, Fornecedor fornecedor) {

        Produto produto = new Produto(id, nome, descricao, precoBase, categoria, variacoes, medidasPorTamanho, fornecedor);
        return produtoService.atualizar(produto);
    }

    public boolean deletar(int id) {
        return produtoService.deletar(id);
    }

    public Produto buscarPorId(int id) {
        return produtoService.buscarPorId(id);
    }

    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    // Métodos específicos
    public List<Produto> buscarPorCategoria(int idCategoria) {
        return produtoService.buscarPorCategoria(idCategoria);
    }

    public List<Produto> buscarPorFornecedor(int idFornecedor) {
        return produtoService.buscarPorFornecedor(idFornecedor);
    }
}


package controller;

import java.util.List;

import model.VariacaoProduto;
import service.VariacaoProdutoService;

public class VariacaoProdutoController {

    private VariacaoProdutoService variacaoProdutoService;

    
    public VariacaoProdutoController() {
        variacaoProdutoService = new VariacaoProdutoService();
    }
     
    public VariacaoProduto inserirVariacaoProduto(int idProdutoBase, String nomeCor, String hexadecimalCor,
                                            int idTamanho, int quantidadeEmEstoque, String urlImagem) {
        VariacaoProduto variacao = new VariacaoProduto(nomeCor, hexadecimalCor, null, null, quantidadeEmEstoque, urlImagem);
        return variacaoProdutoService.inserirVariacaoProduto(variacao, idProdutoBase, idTamanho);
    }

    public boolean atualizar(int id, int idProdutoBase, String nomeCor, String hexadecimalCor,
                        int idTamanho, int quantidadeEmEstoque, String urlImagem) {
        VariacaoProduto variacao = new VariacaoProduto(id, nomeCor, hexadecimalCor, null, null, quantidadeEmEstoque, urlImagem);
        return variacaoProdutoService.atualizar(variacao, idProdutoBase, idTamanho);
    }

    public boolean deletar(int id) {
        return variacaoProdutoService.deletar(id);
    }

    public VariacaoProduto buscarPorId(int id) {
        return variacaoProdutoService.buscarPorId(id);
    }


    // Específicos
    public List<VariacaoProduto> buscarPorProdutoBase(int idProdutoBase) {
        return variacaoProdutoService.buscarPorProdutoBase(idProdutoBase);
    }
        
}

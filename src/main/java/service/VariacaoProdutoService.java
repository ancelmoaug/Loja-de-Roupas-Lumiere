package service;

import java.util.List;

import db.DB;
import impl.VariacaoProdutoDAOImpl;
import model.ProdutoBase;
import model.Tamanho;
import model.VariacaoProduto;

public class VariacaoProdutoService {

    private VariacaoProdutoDAOImpl variacaoDAOImpl;

    
    public VariacaoProdutoService() {//Service instancia um DAOImpl mandando um DB.getConnection no construtor
        variacaoDAOImpl = new VariacaoProdutoDAOImpl(DB.getConnection());
    }

    public VariacaoProdutoDAOImpl getVariacaoDAOImpl() {
        return variacaoDAOImpl;
    }

    public VariacaoProduto inserirVariacaoProduto(VariacaoProduto variacao, int idProdutoBase, int idTamanho) {
        //verificar se tem o produto base e o tamanho
        try {
            ProdutoBaseService produtoBaseService = new ProdutoBaseService();
            ProdutoBase produtoBase = produtoBaseService.buscarPorId(idProdutoBase);

            TamanhoService tamanhoService = new TamanhoService();
            Tamanho tamanho = tamanhoService.buscarPorId(idTamanho);

            variacao.setProdutoBase(produtoBase);
            variacao.setTamanho(tamanho);

            return variacaoDAOImpl.inserir(variacao);
        } catch (Exception e) {
            throw e;
        }
    }


    public boolean atualizar(VariacaoProduto variacao, int idProdutoBase, int idTamanho) {
        //verificar se tem o produto base e o tamanho
        try {
            ProdutoBaseService produtoBaseService = new ProdutoBaseService();
            ProdutoBase produtoBase = produtoBaseService.buscarPorId(idProdutoBase);

            TamanhoService tamanhoService = new TamanhoService();
            Tamanho tamanho = tamanhoService.buscarPorId(idTamanho);

            variacao.setProdutoBase(produtoBase);
            variacao.setTamanho(tamanho);

            return variacaoDAOImpl.atualizar(variacao);
        } catch (Exception e) {
            throw e;
        }
    }


    public boolean deletar(int id) {
        try {
            if(!(variacaoDAOImpl.buscarPorId(id) instanceof VariacaoProduto)) { //verifica se tem esse id
                return false; //se não tiver, retorna false, operação n foi sucesso
            }
            variacaoDAOImpl.deletar(id); //se tiver, ele deleta e retorna true
            return true;
        } catch (Exception e) {
            throw e;
        }
    }


    public VariacaoProduto buscarPorId(int id) {
        return variacaoDAOImpl.buscarPorId(id);
    }


    // Específicos
    public List<VariacaoProduto> buscarPorProdutoBase(int idProdutoBase) {
        List<VariacaoProduto> variacoesProdutos = variacaoDAOImpl.buscarPorProdutoBase(idProdutoBase);
        return variacoesProdutos;
    }

}

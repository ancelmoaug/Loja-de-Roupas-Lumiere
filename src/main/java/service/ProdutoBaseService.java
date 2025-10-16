package service;

import java.util.List;

import db.DB;
import impl.ProdutoBaseDAOImpl;
import model.ProdutoBase;

public class ProdutoBaseService {

    private ProdutoBaseDAOImpl produtoBaseDAOImpl;

    public ProdutoBaseService() {
        produtoBaseDAOImpl = new ProdutoBaseDAOImpl(DB.getConnection());
    }

    // CRUD básico

    public ProdutoBase inserir(ProdutoBase produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            System.out.println("Produto inválido!");
            return null;
        }
        return produtoBaseDAOImpl.inserir(produto);
    }

    public boolean atualizar(ProdutoBase produto) {
        if (produto == null || produto.getId() <= 0) {
            System.out.println("Produto inválido!");
            return false;
        }
        return produtoBaseDAOImpl.atualizar(produto);
    }

    public boolean deletar(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return false;
        }
        return produtoBaseDAOImpl.deletar(id);
    }

    public ProdutoBase buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }
        return produtoBaseDAOImpl.buscarPorId(id);
    }

    public List<ProdutoBase> listarTodos() {
        return produtoBaseDAOImpl.listarTodos();
    }

    // Métodos específicos

    public List<ProdutoBase> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido!");
            return List.of();
        }
        return produtoBaseDAOImpl.buscarPorNome(nome);
    }

    public List<ProdutoBase> buscarPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            System.out.println("Categoria inválida!");
            return List.of();
        }
        return produtoBaseDAOImpl.buscarPorCategoria(categoria);
    }

    public List<ProdutoBase> buscarPorFornecedor(int idFornecedor) {
        if (idFornecedor <= 0) {
            System.out.println("ID do fornecedor inválido!");
            return List.of();
        }
        return produtoBaseDAOImpl.buscarPorFornecedor(idFornecedor);
    }
}

  
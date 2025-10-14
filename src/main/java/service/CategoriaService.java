package service;

import db.DB;
import impl.CategoriaDAOImpl;
import model.Categoria;

public class CategoriaService {

    private CategoriaDAOImpl categoriaDAOImpl;

    public CategoriaService() {
        categoriaDAOImpl = new CategoriaDAOImpl(DB.getConnection());
    }

    public Categoria buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido!");
            return null;
        }
        return categoriaDAOImpl.buscarPorId(id);
    }

    public int buscarPorCategoria(String nomeCategoria) {
        if (nomeCategoria == null || nomeCategoria.trim().isEmpty()) {
            System.out.println("O nome da categoria não pode ser vazio.");
            return -1;
        }
        return categoriaDAOImpl.buscarPorCategoria(nomeCategoria);
    }
}

   
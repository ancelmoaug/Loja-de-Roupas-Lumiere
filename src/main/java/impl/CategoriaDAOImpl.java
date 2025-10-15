package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CategoriaDAO;
import model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

    private Connection conn;

    public CategoriaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(
                    rs.getInt("id"),
                    rs.getString("nome_categoria")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int buscarPorCategoria(String nomeCategoria) {
        String sql = "SELECT id FROM categorias WHERE nome_categoria = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeCategoria);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria por nome: " + e.getMessage());
        }
        return -1; // retorna -1 caso não encontre
    }
}



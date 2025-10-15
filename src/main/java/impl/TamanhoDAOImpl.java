package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TamanhoDAO;
import db.DB;
import db.DbException;
import model.Tamanho;

public class TamanhoDAOImpl implements TamanhoDAO {

    private Connection conn;

    public TamanhoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Tamanho buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM tamanhos WHERE id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                // Criar objeto Tamanho
                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                return tamanho;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public int buscarPorTamanho(String tamanho) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT id FROM tamanhos WHERE nome_tamanho = ?");

            st.setString(1, tamanho);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

            return -1; // caso não encontre o tamanho

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

}

package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.TelefoneDAO;
import db.DB;
import db.DbException;
import model.Telefone;

public class TelefoneDAOImpl implements TelefoneDAO {

    private Connection conn;

    public TelefoneDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Telefone inserir(Telefone telefone) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO telefones (numero) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, telefone.getNumero());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    telefone.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
        return telefone;
    }

    @Override
    public boolean atualizar(Telefone telefone) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE telefones SET numero = ? WHERE id = ?"
            );
            st.setString(1, telefone.getNumero());
            st.setInt(2, telefone.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
        return true;
    }

    @Override
    public boolean deletar(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM telefones WHERE id = ?"
            );
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
        return true;
    }

    @Override
    public Telefone buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM telefones WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                return telefone;
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
    public List<Telefone> listarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Telefone> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT * FROM telefones");
            rs = st.executeQuery();
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                lista.add(telefone);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Telefone buscarPorNumero(String numero) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM telefones WHERE numero = ?");
            st.setString(1, numero);
            rs = st.executeQuery();
            if (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                return telefone;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }
}

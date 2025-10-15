package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.EnderecoDAO;
import db.DB;
import db.DbException;
import model.Endereco;

public class EnderecoDAOImpl implements EnderecoDAO {

    private Connection conn;

    public EnderecoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Endereco inserir(Endereco endereco) { // inserir
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO enderecos "
                            + "(estado, municipio, cep, bairro, rua, numero, complemento) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, endereco.getEstado());
            st.setString(2, endereco.getMunicipio());
            st.setString(3, endereco.getCep());
            st.setString(4, endereco.getBairro());
            st.setString(5, endereco.getRua());
            st.setString(6, endereco.getNumero());
            st.setString(7, endereco.getComplemento());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    endereco.setId(id);
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
        return endereco;
    }

    @Override
    public boolean atualizar(Endereco endereco) { // atualizar
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE enderecos "
                            + "SET estado = ?, municipio = ?, cep = ?, bairro = ?, rua = ?, numero = ?, complemento = ? "
                            + "WHERE id = ?");

            st.setString(1, endereco.getEstado());
            st.setString(2, endereco.getMunicipio());
            st.setString(3, endereco.getCep());
            st.setString(4, endereco.getBairro());
            st.setString(5, endereco.getRua());
            st.setString(6, endereco.getNumero());
            st.setString(7, endereco.getComplemento());
            st.setInt(8, endereco.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
        return true;
    }

    @Override
    public boolean deletar(int id) { // deletar
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM enderecos WHERE id = ?");
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
    public Endereco buscarPorId(int id) { // busca por id
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM enderecos WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));

                return endereco;
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
    public List<Endereco> buscarPorMunicipio(String municipio) { 
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Endereco> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM enderecos WHERE municipio LIKE ?");
            st.setString(1, "%" + municipio + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                lista.add(endereco);
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
    public List<Endereco> buscarPorEstado(String estado) { 
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Endereco> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM enderecos WHERE estado LIKE ?");
            st.setString(1, "%" + estado + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                lista.add(endereco);
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
    public List<Endereco> buscarPorCep(String cep) { 
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Endereco> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM enderecos WHERE cep LIKE ?");
            st.setString(1, "%" + cep + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                lista.add(endereco);
            }

            return lista;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

}

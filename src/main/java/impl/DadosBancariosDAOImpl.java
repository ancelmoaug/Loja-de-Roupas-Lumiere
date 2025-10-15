package impl;

import model.DadosBancarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DadosBancariosDAO;

public class DadosBancariosDAOImpl implements DadosBancariosDAO {

    private Connection conn;

    public DadosBancariosDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public DadosBancarios inserir(DadosBancarios dados) {
        String sql = "INSERT INTO dados_bancarios (codigo_agencia, numero_conta, codigo_banco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dados.getCodigoAgencia());
            stmt.setString(2, dados.getNumeroConta());
            stmt.setString(3, dados.getCodigoBanco());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dados.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados bancários: " + e.getMessage(), e);
        }
        return dados;
    }

    @Override
    public boolean atualizar(DadosBancarios dados) {
        String sql = "UPDATE dados_bancarios SET codigo_agencia = ?, numero_conta = ?, codigo_banco = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dados.getCodigoAgencia());
            stmt.setString(2, dados.getNumeroConta());
            stmt.setString(3, dados.getCodigoBanco());
            stmt.setInt(4, dados.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados bancários: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deletar(int id) {
        String sql = "DELETE FROM dados_bancarios WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar dados bancários: " + e.getMessage(), e);
        }
    }

    @Override
    public DadosBancarios buscarPorId(int id) {
        String sql = "SELECT * FROM dados_bancarios WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DadosBancarios(
                        rs.getInt("id"),
                        rs.getString("codigo_agencia"),
                        rs.getString("numero_conta"),
                        rs.getString("codigo_banco")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar dados bancários por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<DadosBancarios> listarTodos() {
        List<DadosBancarios> lista = new ArrayList<>();
        String sql = "SELECT * FROM dados_bancarios";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DadosBancarios dados = new DadosBancarios(
                    rs.getInt("id"),
                    rs.getString("codigo_agencia"),
                    rs.getString("numero_conta"),
                    rs.getString("codigo_banco")
                );
                lista.add(dados);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar dados bancários: " + e.getMessage(), e);
        }
        return lista;
    }
}


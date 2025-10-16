package impl;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Usuario inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, sobrenome, senha, data_nascimento, cpf, telefone, endereco, email, dados_bancarios) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getSenha());
            stmt.setDate(4, Date.valueOf(usuario.getDataDeNascimento()));
            stmt.setString(5, usuario.getCpf());
            stmt.setString(6, usuario.getTelefone().toString()); // Supondo que Telefone tem um método toString()
            stmt.setString(7, usuario.getEndereco().toString()); // Supondo que Endereco tem um método toString()
            stmt.setString(8, usuario.getEmail());
            stmt.setString(9, usuario.getDadosBancarios().toString()); // Supondo que DadosBancarios tem um método toString()
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, sobrenome = ?, senha = ?, data_nascimento = ?, cpf = ?, telefone = ?, endereco = ?, email = ?, dados_bancarios = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getSenha());
            stmt.setDate(4, Date.valueOf(usuario.getDataDeNascimento()));
            stmt.setString(5, usuario.getCpf());
            stmt.setString(6, usuario.getTelefone().toString());
            stmt.setString(7, usuario.getEndereco().toString());
            stmt.setString(8, usuario.getEmail());
            stmt.setString(9, usuario.getDadosBancarios().toString());
            stmt.setInt(10, usuario.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(rs.getString("cpf"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        Usuario usuario = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        Usuario usuario = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}

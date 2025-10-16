package impl;

import dao.ClienteDAO;
import db.DB;
import db.DbException;
import model.Cliente;
import model.DadosBancarios;
import model.Endereco;
import model.Telefone;
import model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    
    private Connection conn;


    public ClienteDAOImpl(Connection conn){
        this.conn = conn;
    }



    @Override
    public Cliente inserir(Cliente cliente){
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO clientes (id) VALUES (?)"
            );

            // O ID do cliente é o mesmo do usuário já inserido
            st.setInt(1, cliente.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada na tabela cliente!");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao inserir Cliente: " + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }

        return cliente;
    }
    
    @Override
    public boolean atualizar(Cliente cliente) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE clientes SET id = ? WHERE id = ?"
            );

            // Aqui, por enquanto, estamos apenas mantendo o mesmo id.
            // Essa linha só faz sentido se você pretende atualizar o id (raro),
            // mas mantemos a estrutura para compatibilidade futura.
            st.setInt(1, cliente.getId());
            st.setInt(2, cliente.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Nenhuma linha foi atualizada! Verifique se o ID do cliente existe.");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar Cliente: " + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }

        return true;
    }


    @Override
    public boolean deletar(int id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM clientes WHERE id = ?");
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                // Nenhum registro com o ID informado
                throw new DbException("Nenhum cliente encontrado com o ID informado!");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar Cliente: " + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }

        return true;
    }

    @Override
    public Cliente buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT c.id AS client_id, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM clientes c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE c.id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                // --- Criar objeto Endereco ---
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("end_id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));

                // --- Criar objeto Telefone ---
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("tel_id"));
                telefone.setNumero(rs.getString("telefone_numero"));

                // --- Criar objeto DadosBancarios ---
                DadosBancarios dados = new DadosBancarios(
                    rs.getString("codigo_agencia"),
                    rs.getString("numero_conta"),
                    rs.getString("codigo_banco")
                );
                dados.setId(rs.getInt("db_id"));

                // --- Criar objeto Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                
                Date dataNascimento = rs.getDate("data_nascimento");
                if (dataNascimento != null) {
                    usuario.setDataDeNascimento(dataNascimento.toLocalDate());
                }

                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar objeto Cliente ---
                Cliente cliente = new Cliente(usuario);
                cliente.setId(rs.getInt("client_id"));

                return cliente;
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
    public List<Cliente> listarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT c.id AS client_id, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM clientes c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "ORDER BY u.nome"
            );

            rs = st.executeQuery();

            List<Cliente> lista = new ArrayList<>();

            while (rs.next()) {
                // --- Criar Endereco ---
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("end_id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));

                // --- Criar Telefone ---
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("tel_id"));
                telefone.setNumero(rs.getString("telefone_numero"));

                // --- Criar Dados Bancários ---
                DadosBancarios dados = new DadosBancarios(
                    rs.getString("codigo_agencia"),
                    rs.getString("numero_conta"),
                    rs.getString("codigo_banco")
                );
                dados.setId(rs.getInt("db_id"));

                // --- Criar Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                
                java.sql.Date sqlData = rs.getDate("data_nascimento");
                if (sqlData != null) {
                    usuario.setDataDeNascimento(sqlData.toLocalDate()); // converte para LocalDate
                }

                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar Cliente ---
                Cliente cliente = new Cliente(usuario);
                cliente.setId(rs.getInt("client_id"));

                lista.add(cliente);
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
    public Cliente buscarPorEmail(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT c.id AS client_id, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM clientes c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE u.email = ?"
            );

            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                // --- Criar Endereco ---
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("end_id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));

                // --- Criar Telefone ---
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("tel_id"));
                telefone.setNumero(rs.getString("telefone_numero"));

                // --- Criar Dados Bancários ---
                DadosBancarios dados = new DadosBancarios(
                    rs.getString("codigo_agencia"),
                    rs.getString("numero_conta"),
                    rs.getString("codigo_banco")
                );
                dados.setId(rs.getInt("db_id"));

                // --- Criar Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));

                java.sql.Date sqlData = rs.getDate("data_nascimento");
                if (sqlData != null) {
                    usuario.setDataDeNascimento(sqlData.toLocalDate());
                }

                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar Cliente ---
                Cliente cliente = new Cliente(usuario);
                cliente.setId(rs.getInt("client_id"));

                return cliente;
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
    public Cliente buscarPorCpf(String cpf) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT c.id AS client_id, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM clientes c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE u.cpf = ?"
            );

            st.setString(1, cpf);
            rs = st.executeQuery();

            if (rs.next()) {
                // --- Criar Endereco ---
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("end_id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));

                // --- Criar Telefone ---
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("tel_id"));
                telefone.setNumero(rs.getString("telefone_numero"));

                // --- Criar Dados Bancários ---
                DadosBancarios dados = new DadosBancarios(
                    rs.getString("codigo_agencia"),
                    rs.getString("numero_conta"),
                    rs.getString("codigo_banco")
                );
                dados.setId(rs.getInt("db_id"));

                // --- Criar Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));

                java.sql.Date sqlData = rs.getDate("data_nascimento");
                if (sqlData != null) {
                    usuario.setDataDeNascimento(sqlData.toLocalDate());
                }

                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar Cliente ---
                Cliente cliente = new Cliente(usuario);
                cliente.setId(rs.getInt("client_id"));

                return cliente;
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
    public List<Cliente> buscarPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT c.id AS client_id, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM clientes c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE u.nome LIKE ? " +
                "ORDER BY u.nome"
            );

            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                // --- Criar Endereco ---
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("end_id"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));

                // --- Criar Telefone ---
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("tel_id"));
                telefone.setNumero(rs.getString("telefone_numero"));

                // --- Criar Dados Bancários ---
                DadosBancarios dados = new DadosBancarios(
                    rs.getString("codigo_agencia"),
                    rs.getString("numero_conta"),
                    rs.getString("codigo_banco")
                );
                dados.setId(rs.getInt("db_id"));

                // --- Criar Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));

                java.sql.Date sqlData = rs.getDate("data_nascimento");
                if (sqlData != null) {
                    usuario.setDataDeNascimento(sqlData.toLocalDate());
                }

                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar Cliente ---
                Cliente cliente = new Cliente(usuario);
                cliente.setId(rs.getInt("client_id"));

                lista.add(cliente);
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

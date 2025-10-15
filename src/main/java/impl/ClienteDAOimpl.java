package impl;

import dao.ClienteDAO;
import db.DB;
import db.DbException;
import model.Carrinho;
import model.Cliente;
import model.DadosBancarios;
import model.Endereco;
import model.Pedido;
import model.Telefone;
import model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    
    private Connection conn;

    public ClienteDAOImpl(Connection conn){
        this.conn = conn;
    }

    private Cliente instantiateCliente(ResultSet rs) throws SQLException {
        
        Endereco endereco = null;
        int endId = rs.getInt("end_id");
        if (endId > 0) {
            endereco = new Endereco();
            endereco.setId(endId);
            endereco.setEstado(rs.getString("estado"));
            endereco.setMunicipio(rs.getString("municipio"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setComplemento(rs.getString("complemento"));
        }

        
        Telefone telefone = null;
        int telId = rs.getInt("tel_id");
        if (telId > 0) {
            telefone = new Telefone();
            telefone.setId(telId);
            telefone.setNumero(rs.getString("telefone_numero"));
        }

        
        DadosBancarios dados = null;
        int dbId = rs.getInt("db_id");
        if (dbId > 0) {
            dados = new DadosBancarios(rs.getString("codigo_agencia"), rs.getString("numero_conta"), rs.getString("codigo_banco"));
            dados.setId(dbId);
        }
        
        
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

        
        Cliente cliente = new Cliente(usuario);
        cliente.setId(rs.getInt("client_id"));

        return cliente;
    }


<<<<<<< HEAD
    @Override
    public Cliente inserir(Cliente cliente){
        
        PreparedStatement st = null;

        try {
            // Insere na tabela 'cliente' usando o ID gerado na tabela 'usuarios'
            st = conn.prepareStatement(
                "INSERT INTO cliente (id) VALUES (?)",
                Statement.NO_GENERATED_KEYS // Não pede chave gerada, pois o ID já foi fornecido
            );

            
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
       
        return true;
    }

    @Override
    public boolean deletar(int id) {
        
        
        PreparedStatement st = null;

        try {
            
            st = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected == 0) {
                return false; 
            }

            return true;

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar Cliente: " + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
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
                "d.id AS db_id, d.codigo_agencia, d.codigo_banco, d.numero_conta " +
                "FROM cliente c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE c.id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateCliente(rs);
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
        List<Cliente> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT c.id AS client_id, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.codigo_banco, d.numero_conta " +
                "FROM cliente c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "ORDER BY u.nome"
            );

            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(instantiateCliente(rs));
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
                "d.id AS db_id, d.codigo_agencia, d.codigo_banco, d.numero_conta " +
                "FROM cliente c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE u.email = ?"
            );

            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateCliente(rs);
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
                "d.id AS db_id, d.codigo_agencia, d.codigo_banco, d.numero_conta " +
                "FROM cliente c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE u.cpf = ?"
            );

            st.setString(1, cpf);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateCliente(rs);
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
                "d.id AS db_id, d.codigo_agencia, d.codigo_banco, d.numero_conta " +
                "FROM cliente c " +
                "INNER JOIN usuarios u ON c.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE u.nome LIKE ?" +
                "ORDER BY u.nome"
            );

            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(instantiateCliente(rs));
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
    public Carrinho buscarCarrinhoDoCliente(int idCliente) {
        
        throw new UnsupportedOperationException("Método 'buscarCarrinhoDoCliente' não implementado neste DAO. Delegue ao Service.");
    }

    @Override
    public List<Pedido> listarPedidosDoCliente(int idCliente) {
        
        throw new UnsupportedOperationException("Método 'listarPedidosDoCliente' não implementado neste DAO. Delegue ao Service.");
    }
}
=======
public class ClienteDAOimpl implements ClienteDAO {
    /*
     * @Override
     * Cliente inserir(Cliente cliente){
     * 
     * }
     * 
     * @Override
     * boolean atualizar(Cliente cliente) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * boolean deletar(int id) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * Cliente buscarPorId(int id) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * List<Cliente> listarTodos() {
     * // código do CRUD com o BD
     * }
     * 
     * 
     * // Específicos
     * 
     * @Override
     * Cliente buscarPorEmail(String email) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * Cliente buscarPorCpf(String cpf) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * List<Cliente> buscarPorNome(String nome) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * Carrinho buscarCarrinhoDoCliente(int idCliente) {
     * // código do CRUD com o BD
     * }
     * 
     * @Override
     * List<Pedido> listarPedidosDoCliente(int idCliente) {
     * // código do CRUD com o BD
     * }
     * 
     */
}
>>>>>>> luciano

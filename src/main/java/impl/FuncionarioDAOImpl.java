package impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FuncionarioDAO;
import db.DB;
import db.DbException;
import model.DadosBancarios;
import model.Endereco;
import model.Funcionario;
import model.Telefone;
import model.Usuario;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    private Connection conn;


    public FuncionarioDAOImpl(Connection conn){
        this.conn = conn;
    }

    
    @Override
    public Funcionario inserir(Funcionario funcionario) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO funcionarios " +
                "(id, cargo, data_admissao, salario) " +
                "VALUES (?, ?, ?, ?)"
            );

            // o ID vem do usuário já inserido
            st.setInt(1, funcionario.getId()); 
            st.setString(2, funcionario.getCargo());
            st.setDate(3, Date.valueOf(funcionario.getDataAdmissao())); // LocalDate -> SQL Date
            st.setDouble(4, funcionario.getSalario());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }

        return funcionario;
    }

    @Override
    public boolean atualizar(Funcionario funcionario) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE funcionarios "
                + "SET cargo = ?, data_admissao = ?, salario = ? "
                + "WHERE id = ?"
            );

            st.setString(1, funcionario.getCargo());
            st.setDate(2, java.sql.Date.valueOf(funcionario.getDataAdmissao())); // LocalDate -> SQL Date
            st.setDouble(3, funcionario.getSalario());
            st.setInt(4, funcionario.getId()); // id herdado de Usuario

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Nenhuma linha foi atualizada! Verifique se o ID existe.");
            }

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
        ResultSet rs = null;

        try {
            // Primeiro, buscar os IDs relacionados ao usuário
            st = conn.prepareStatement(
                "SELECT id_endereco, id_telefone, id_dados_bancarios FROM usuarios WHERE id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            Integer idEndereco = null;
            Integer idTelefone = null;
            Integer idDadosBancarios = null;

            if (rs.next()) {
                idEndereco = rs.getInt("id_endereco");
                idTelefone = rs.getInt("id_telefone");
                idDadosBancarios = rs.getInt("id_dados_bancarios");
            }

            DB.closeResultSet(rs);
            DB.closeStatment(st);

            // 2️⃣ Deletar primeiro o funcionário (tabela filha)
            st = conn.prepareStatement("DELETE FROM funcionarios WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            DB.closeStatment(st);

            // 3️⃣ Deletar o usuário (tabela pai)
            st = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            DB.closeStatment(st);

            // 4️⃣ Deletar os dados relacionados, se existirem
            if (idEndereco != null && idEndereco > 0) {
                st = conn.prepareStatement("DELETE FROM enderecos WHERE id = ?");
                st.setInt(1, idEndereco);
                st.executeUpdate();
                DB.closeStatment(st);
            }

            if (idTelefone != null && idTelefone > 0) {
                st = conn.prepareStatement("DELETE FROM telefones WHERE id = ?");
                st.setInt(1, idTelefone);
                st.executeUpdate();
                DB.closeStatment(st);
            }

            if (idDadosBancarios != null && idDadosBancarios > 0) {
                st = conn.prepareStatement("DELETE FROM dados_bancarios WHERE id = ?");
                st.setInt(1, idDadosBancarios);
                st.executeUpdate();
                DB.closeStatment(st);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

        return true;

    }

    @Override
    public Funcionario buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT f.id AS func_id, f.cargo, f.data_admissao, f.salario, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM funcionarios f " +
                "INNER JOIN usuarios u ON f.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "WHERE f.id = ?"
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
                DadosBancarios dados = new DadosBancarios(rs.getString("codigo_agencia"), rs.getString("numero_conta"), rs.getString("codigo_banco"));
                dados.setId(rs.getInt("db_id"));

                // --- Criar objeto Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar objeto Funcionario ---
                Funcionario funcionario = new Funcionario(usuario, rs.getString("cargo"), rs.getDate("data_admissao").toLocalDate(), rs.getDouble("salario"));
                funcionario.setId(rs.getInt("func_id"));

                return funcionario;
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
    public List<Funcionario> listarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT f.id AS func_id, f.cargo, f.data_admissao, f.salario, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM funcionarios f " +
                "INNER JOIN usuarios u ON f.id = u.id " +
                "LEFT JOIN enderecos e ON u.id_endereco = e.id " +
                "LEFT JOIN telefones t ON u.id_telefone = t.id " +
                "LEFT JOIN dados_bancarios d ON u.id_dados_bancarios = d.id " +
                "ORDER BY u.nome"
            );

            rs = st.executeQuery();

            List<Funcionario> lista = new ArrayList<>();

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
                DadosBancarios dados = new DadosBancarios(rs.getString("codigo_agencia"), rs.getString("numero_conta"), rs.getString("codigo_banco"));
                dados.setId(rs.getInt("db_id"));
                // --- Criar Usuario ---
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("user_id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar Funcionario ---
                Funcionario funcionario = new Funcionario(usuario, rs.getString("cargo"), rs.getDate("data_admissao").toLocalDate(), rs.getDouble("salario"));
                funcionario.setId(rs.getInt("func_id"));

                lista.add(funcionario);
            }

            return lista;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }


    // Específicos
    @Override
    public List<Funcionario> buscarPorCargo(String cargo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Funcionario> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT f.*, u.nome, u.sobrenome, u.cpf, u.email, u.data_nascimento " +
                "FROM funcionarios f " +
                "INNER JOIN usuarios u ON f.id = u.id " +
                "WHERE f.cargo = ?"
            );

            st.setString(1, cargo);
            rs = st.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                // Dados herdados de Usuario
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSobrenome(rs.getString("sobrenome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());

                // Dados específicos de Funcionario
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
                funcionario.setSalario(rs.getDouble("salario"));

                lista.add(funcionario);
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
    public List<Funcionario> buscarPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Funcionario> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT f.id AS func_id, f.cargo, f.data_admissao, f.salario, " +
                "u.id AS user_id, u.nome, u.sobrenome, u.data_nascimento, u.senha, u.cpf, u.email, " +
                "e.id AS end_id, e.estado, e.municipio, e.bairro, e.rua, e.numero, e.complemento, " +
                "t.id AS tel_id, t.numero AS telefone_numero, " +
                "d.id AS db_id, d.codigo_agencia, d.numero_conta, d.codigo_banco " +
                "FROM funcionarios f " +
                "INNER JOIN usuarios u ON f.id = u.id " +
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
                usuario.setDataDeNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(endereco);
                usuario.setTelefone(telefone);
                usuario.setDadosBancarios(dados);

                // --- Criar Funcionario ---
                Funcionario funcionario = new Funcionario(
                    usuario,
                    rs.getString("cargo"),
                    rs.getDate("data_admissao").toLocalDate(),
                    rs.getDouble("salario")
                );
                funcionario.setId(rs.getInt("func_id"));

                lista.add(funcionario);
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

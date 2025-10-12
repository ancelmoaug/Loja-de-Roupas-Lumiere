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
import model.Funcionario;

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
                "(cargo, data_admissao, salario, id) " +
                "VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            // Atributos específicos da tabela funcionarios
            st.setString(1, funcionario.getCargo());
            st.setDate(2, Date.valueOf(funcionario.getDataAdmissao())); // supondo que é LocalDate
            st.setDouble(3, funcionario.getSalario());
            st.setInt(4, funcionario.getIdUsuario()); // chave estrangeira (usuário associado)

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    funcionario.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
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
        // código do CRUD com o BD
    }

    @Override
    public Funcionario buscarPorId(int id) {
        // código do CRUD com o BD
    }

    @Override
    public List<Funcionario> listarTodos() {
        // código do CRUD com o BD
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
    /* 
    @Override
    public List<Funcionario> buscarPorNome(String nome) {
        // código do CRUD com o BD
    }*/

    
}

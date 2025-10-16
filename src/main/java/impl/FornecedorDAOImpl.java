package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FornecedorDAO;
import db.DB;
import db.DbException;
import model.Categoria;
import model.Endereco;
import model.Fornecedor;
import model.ProdutoBase;
import model.Telefone;

public class FornecedorDAOImpl implements FornecedorDAO {

    private Connection conn;

    public FornecedorDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Fornecedor inserir(Fornecedor fornecedor) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO fornecedores " +
                "(razao_social, nome_comercial, cnpj, id_endereco, id_telefone_comercial, email_comercial) " +
                "VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, fornecedor.getRazaoSocial());
            st.setString(2, fornecedor.getNomeComercial());
            st.setString(3, fornecedor.getCnpj());
            st.setInt(4, fornecedor.getEndereco().getId());
            st.setInt(5, fornecedor.getTelefoneComercial().getId());
            st.setString(6, fornecedor.getEmailComercial());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    fornecedor.setId(id);
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
        return fornecedor;
    }

    @Override
    public boolean atualizar(Fornecedor fornecedor) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE fornecedores " +
                "SET razao_social = ?, nome_comercial = ?, cnpj = ?, id_endereco = ?, " +
                "id_telefone_comercial = ?, email_comercial = ? " +
                "WHERE id = ?"
            );

            st.setString(1, fornecedor.getRazaoSocial());
            st.setString(2, fornecedor.getNomeComercial());
            st.setString(3, fornecedor.getCnpj());
            st.setInt(4, fornecedor.getEndereco().getId());
            st.setInt(5, fornecedor.getTelefoneComercial().getId());
            st.setString(6, fornecedor.getEmailComercial());
            st.setInt(7, fornecedor.getId());

            st.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
    }

    @Override
    public boolean deletar(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM fornecedores WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
    }

    @Override
    public Fornecedor buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT * FROM fornecedores WHERE id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setNomeComercial(rs.getString("nome_comercial"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmailComercial(rs.getString("email_comercial"));

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id_endereco"));
                f.setEndereco(endereco);

                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id_telefone_comercial"));
                f.setTelefoneComercial(telefone);

                return f;
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
    public List<Fornecedor> listarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Fornecedor> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT * FROM fornecedores ORDER BY razao_social");
            rs = st.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setNomeComercial(rs.getString("nome_comercial"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmailComercial(rs.getString("email_comercial"));

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id_endereco"));
                f.setEndereco(endereco);

                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id_telefone_comercial"));
                f.setTelefoneComercial(telefone);

                lista.add(f);
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
    public Fornecedor buscarPorCnpj(String cnpj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fornecedores WHERE cnpj = ?");
            st.setString(1, cnpj);
            rs = st.executeQuery();

            if (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setNomeComercial(rs.getString("nome_comercial"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmailComercial(rs.getString("email_comercial"));

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id_endereco"));
                f.setEndereco(endereco);

                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id_telefone_comercial"));
                f.setTelefoneComercial(telefone);

                return f;
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
    public List<Fornecedor> buscarPorNomeComercial(String nomeComercial) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Fornecedor> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT * FROM fornecedores WHERE nome_comercial LIKE ?");
            st.setString(1, "%" + nomeComercial + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setNomeComercial(rs.getString("nome_comercial"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEmailComercial(rs.getString("email_comercial"));

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id_endereco"));
                f.setEndereco(endereco);

                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id_telefone_comercial"));
                f.setTelefoneComercial(telefone);

                lista.add(f);
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
    public List<ProdutoBase> listarProdutosDoFornecedor(int idFornecedor) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ProdutoBase> produtos = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT p.id AS id_produto, p.nome AS nome_produto, p.descricao, p.preco_base, " +
                "c.id AS id_categoria, c.nome_categoria " +
                "FROM produtos_base p " +
                "INNER JOIN categorias c ON p.id_categoria = c.id " +
                "WHERE p.id_fornecedor = ?"
            );

            st.setInt(1, idFornecedor);
            rs = st.executeQuery();

            while (rs.next()) {
                // Preenche ProdutoBase
                ProdutoBase produto = new ProdutoBase();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoBase(rs.getDouble("preco_base"));

                // Preenche Categoria
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                produto.setCategoria(categoria);

                produtos.add(produto);
            }

            return produtos;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }
}

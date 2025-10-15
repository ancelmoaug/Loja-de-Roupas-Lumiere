package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProdutoBaseDAO;
import model.ProdutoBase;
import model.Categoria;
import model.Fornecedor;

public class ProdutoBaseDAOImpl implements ProdutoBaseDAO {

    private Connection conn;

    public ProdutoBaseDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ProdutoBase inserir(ProdutoBase produto) {
        String sql = "INSERT INTO produto_base (nome, descricao, preco_base, id_categoria, id_fornecedor) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoBase());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getFornecedor().getId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }

            return produto;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean atualizar(ProdutoBase produto) {
        String sql = "UPDATE produto_base SET nome = ?, descricao = ?, preco_base = ?, id_categoria = ?, id_fornecedor = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoBase());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getFornecedor().getId());
            stmt.setInt(6, produto.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        String sql = "DELETE FROM produto_base WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public ProdutoBase buscarPorId(int id) {
        String sql = "SELECT * FROM produto_base WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarProduto(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ProdutoBase> listarTodos() {
        List<ProdutoBase> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto_base";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProduto(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<ProdutoBase> buscarPorNome(String nome) {
        List<ProdutoBase> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto_base WHERE nome LIKE ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProduto(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por nome: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<ProdutoBase> buscarPorCategoria(String categoria) {
        List<ProdutoBase> lista = new ArrayList<>();
        String sql = """
            SELECT pb.* FROM produto_base pb
            INNER JOIN categorias c ON pb.id_categoria = c.id
            WHERE c.nome_categoria LIKE ?
        """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + categoria + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProduto(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por categoria: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<ProdutoBase> buscarPorFornecedor(int idFornecedor) {
        List<ProdutoBase> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto_base WHERE id_fornecedor = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFornecedor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProduto(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por fornecedor: " + e.getMessage());
        }

        return lista;
    }

    // Método auxiliar para montar o objeto ProdutoBase a partir do ResultSet
    private ProdutoBase montarProduto(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria(rs.getInt("id_categoria"), null);
        Fornecedor fornecedor = new Fornecedor(rs.getInt("id_fornecedor"), null);

        return new ProdutoBase(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("descricao"),
            rs.getDouble("preco_base"),
            categoria,
            fornecedor
        );
    }
}

    
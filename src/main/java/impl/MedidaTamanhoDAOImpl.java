package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.MedidaTamanhoDAO;
import db.DB;
import db.DbException;
import model.MedidaTamanho;
import model.ProdutoBase;
import model.Tamanho;

public class MedidaTamanhoDAOImpl implements MedidaTamanhoDAO {

    private Connection conn;

    public MedidaTamanhoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public MedidaTamanho inserir(MedidaTamanho medida) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO medida_tamanho " +
                "(id_produto_base, id_tamanho, comprimento, quadril, cintura, busto, manga) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, medida.getProdutoBase().getId());
            st.setInt(2, medida.getTamanho().getId());
            st.setInt(3, medida.getComprimento());
            st.setInt(4, medida.getQuadril());
            st.setInt(5, medida.getCintura());
            st.setInt(6, medida.getBusto());
            st.setInt(7, medida.getManga());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    medida.setId(id);
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
        return medida;
    }

    @Override
    public boolean atualizar(MedidaTamanho medida) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE medida_tamanho " +
                "SET id_produto_base = ?, id_tamanho = ?, comprimento = ?, quadril = ?, " +
                "cintura = ?, busto = ?, manga = ? " +
                "WHERE id = ?"
            );

            st.setInt(1, medida.getProdutoBase().getId());
            st.setInt(2, medida.getTamanho().getId());
            st.setInt(3, medida.getComprimento());
            st.setInt(4, medida.getQuadril());
            st.setInt(5, medida.getCintura());
            st.setInt(6, medida.getBusto());
            st.setInt(7, medida.getManga());
            st.setInt(8, medida.getId());

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
            st = conn.prepareStatement("DELETE FROM medida_tamanho WHERE id = ?");
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
    public MedidaTamanho buscarPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT m.*, p.nome_produto, t.nome_tamanho " +
                "FROM medida_tamanho m " +
                "INNER JOIN produto_base p ON m.id_produto_base = p.id " +
                "INNER JOIN tamanhos t ON m.id_tamanho = t.id " +
                "WHERE m.id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                ProdutoBase produto = new ProdutoBase();
                produto.setId(rs.getInt("id_produto_base"));
                produto.setNomeProduto(rs.getString("nome_produto"));

                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id_tamanho"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                MedidaTamanho medida = new MedidaTamanho();
                medida.setId(rs.getInt("id"));
                medida.setProdutoBase(produto);
                medida.setTamanho(tamanho);
                medida.setComprimento(rs.getInt("comprimento"));
                medida.setQuadril(rs.getInt("quadril"));
                medida.setCintura(rs.getInt("cintura"));
                medida.setBusto(rs.getInt("busto"));
                medida.setManga(rs.getInt("manga"));

                return medida;
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
    public List<MedidaTamanho> listarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<MedidaTamanho> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement(
                "SELECT m.*, p.nome_produto, t.nome_tamanho " +
                "FROM medida_tamanho m " +
                "INNER JOIN produto_base p ON m.id_produto_base = p.id " +
                "INNER JOIN tamanhos t ON m.id_tamanho = t.id " +
                "ORDER BY p.nome_produto, t.nome_tamanho"
            );
            rs = st.executeQuery();

            while (rs.next()) {
                ProdutoBase produto = new ProdutoBase();
                produto.setId(rs.getInt("id_produto_base"));
                produto.setNomeProduto(rs.getString("nome_produto"));

                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id_tamanho"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                MedidaTamanho medida = new MedidaTamanho();
                medida.setId(rs.getInt("id"));
                medida.setProdutoBase(produto);
                medida.setTamanho(tamanho);
                medida.setComprimento(rs.getInt("comprimento"));
                medida.setQuadril(rs.getInt("quadril"));
                medida.setCintura(rs.getInt("cintura"));
                medida.setBusto(rs.getInt("busto"));
                medida.setManga(rs.getInt("manga"));

                lista.add(medida);
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
    public List<MedidaTamanho> buscarPorProduto(int idProduto) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<MedidaTamanho> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement(
                "SELECT m.*, p.nome_produto, t.nome_tamanho " +
                "FROM medida_tamanho m " +
                "INNER JOIN produto_base p ON m.id_produto_base = p.id " +
                "INNER JOIN tamanhos t ON m.id_tamanho = t.id " +
                "WHERE m.id_produto_base = ?"
            );
            st.setInt(1, idProduto);
            rs = st.executeQuery();

            while (rs.next()) {
                ProdutoBase produto = new ProdutoBase();
                produto.setId(rs.getInt("id_produto_base"));
                produto.setNomeProduto(rs.getString("nome_produto"));

                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id_tamanho"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                MedidaTamanho medida = new MedidaTamanho();
                medida.setId(rs.getInt("id"));
                medida.setProdutoBase(produto);
                medida.setTamanho(tamanho);
                medida.setComprimento(rs.getInt("comprimento"));
                medida.setQuadril(rs.getInt("quadril"));
                medida.setCintura(rs.getInt("cintura"));
                medida.setBusto(rs.getInt("busto"));
                medida.setManga(rs.getInt("manga"));

                lista.add(medida);
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
    public List<MedidaTamanho> buscarPorTamanho(String tamanhoNome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<MedidaTamanho> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement(
                "SELECT m.*, p.nome_produto, t.nome_tamanho " +
                "FROM medida_tamanho m " +
                "INNER JOIN produto_base p ON m.id_produto_base = p.id " +
                "INNER JOIN tamanhos t ON m.id_tamanho = t.id " +
                "WHERE t.nome_tamanho LIKE ?"
            );
            st.setString(1, "%" + tamanhoNome + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                ProdutoBase produto = new ProdutoBase();
                produto.setId(rs.getInt("id_produto_base"));
                produto.setNomeProduto(rs.getString("nome_produto"));

                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id_tamanho"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                MedidaTamanho medida = new MedidaTamanho();
                medida.setId(rs.getInt("id"));
                medida.setProdutoBase(produto);
                medida.setTamanho(tamanho);
                medida.setComprimento(rs.getInt("comprimento"));
                medida.setQuadril(rs.getInt("quadril"));
                medida.setCintura(rs.getInt("cintura"));
                medida.setBusto(rs.getInt("busto"));
                medida.setManga(rs.getInt("manga"));

                lista.add(medida);
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

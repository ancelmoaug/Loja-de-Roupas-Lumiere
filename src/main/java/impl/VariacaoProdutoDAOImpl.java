package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.VariacaoProdutoDAO;

import java.sql.Statement;

import db.DB;
import db.DbException;
import model.Tamanho;
import model.VariacaoProduto;

public class VariacaoProdutoDAOImpl implements VariacaoProdutoDAO {

    private Connection conn;


    public VariacaoProdutoDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public VariacaoProduto inserir(VariacaoProduto variacao) { //inserir
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
              "INSERT INTO variacoes_produto "
                    + "(id_produto_base, nome_cor, hexadecimal_cor, id_tamanho, quantidade_estoque) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );



            st.setInt(1, variacao.getProdutoBase().getId());
            st.setString(2, variacao.getNomeCor());
            st.setString(3, variacao.getHexaDecimalCor());
            st.setInt(4, variacao.getTamanho().getId());
            st.setInt(5, variacao.getQuantidadeEmEstoque());


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    int id = rs.getInt(1);
                    variacao.setId(id); 

                }
                DB.closeResultSet(rs);
            }else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatment(st);

        }
        return variacao;
    }


    @Override
    public boolean atualizar(VariacaoProduto variacao) { //atualizar
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE variacoes_produto "
                            + "SET id_produto_base = ?, nome_cor = ?, hexadecimal_cor = ?, id_tamanho = ?, quantidade_estoque = ? "
                            + "WHERE id = ?"

            );

            st.setInt(1, variacao.getProdutoBase().getId());
            st.setString(2, variacao.getNomeCor());
            st.setString(3, variacao.getHexaDecimalCor());
            st.setInt(4, variacao.getTamanho().getId());
            st.setInt(5, variacao.getQuantidadeEmEstoque());
            st.setInt(6, variacao.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatment(st);

        }
        return true;
    }

    @Override
    public boolean deletar(int id) { //deletar
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                    "DELETE FROM variacoes_produto WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatment(st);
        }
        return true;
    }

    @Override
    public VariacaoProduto buscarPorId(int id) { //busca por id
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT v.*, t.nome_tamanho " +
                "FROM variacoes_produto v " +
                "INNER JOIN tamanhos t ON v.id_tamanho = t.id " +
                "WHERE v.id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                // Criar objeto Tamanho
                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id_tamanho"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                // Criar objeto VariacaoProduto
                VariacaoProduto variacao = new VariacaoProduto();
                variacao.setId(rs.getInt("id"));
                variacao.setNomeCor(rs.getString("nome_cor"));
                variacao.setHexaDecimalCor(rs.getString("hexadecimal_cor"));
                variacao.setQuantidadeEmEstoque(rs.getInt("quantidade_estoque"));
                variacao.setUrlImagem(rs.getString("url_imagem"));
                variacao.setTamanho(tamanho);

                return variacao;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }


    // Específicos
    @Override
    public List<VariacaoProduto> buscarPorProdutoBase(int idProdutoBase) { //busca por outro atributo
        PreparedStatement st = null;
        ResultSet rs = null;
        List<VariacaoProduto> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT v.*, t.nome_tamanho " +
                "FROM variacoes_produto v " +
                "INNER JOIN tamanhos t ON v.id_tamanho = t.id " +
                "WHERE v.id_produto_base = ?"
            );

            st.setInt(1, idProdutoBase);
            rs = st.executeQuery();

            while (rs.next()) {
                // Criar objeto Tamanho
                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id_tamanho"));
                tamanho.setNomeTamanho(rs.getString("nome_tamanho"));

                // Criar objeto VariacaoProduto
                VariacaoProduto variacao = new VariacaoProduto();
                variacao.setId(rs.getInt("id"));
                variacao.setNomeCor(rs.getString("nome_cor"));
                variacao.setHexaDecimalCor(rs.getString("hexadecimal_cor"));
                variacao.setQuantidadeEmEstoque(rs.getInt("quantidade_estoque"));
                variacao.setUrlImagem(rs.getString("url_imagem"));
                variacao.setTamanho(tamanho);

                lista.add(variacao);
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

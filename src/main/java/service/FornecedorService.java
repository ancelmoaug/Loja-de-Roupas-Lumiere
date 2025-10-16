package service;

import db.DB;
import impl.FornecedorDAOImpl;
import java.util.List;

import model.Endereco;
import model.Fornecedor;
import model.ProdutoBase;
import model.Telefone;

public class FornecedorService {

    private FornecedorDAOImpl fornecedorDAOImpl;

    public FornecedorService() { // Service instancia um DAOImpl mandando um DB.getConnection no construtor
        fornecedorDAOImpl = new FornecedorDAOImpl(DB.getConnection());
    }

    public FornecedorDAOImpl getFornecedorDAOImpl() {
        return fornecedorDAOImpl;
    }

    // CRUD Básico
    public Fornecedor inserirFornecedor(Fornecedor fornecedor) {
        try {

            // Inserir endereço
            EnderecoService enderecoService = new EnderecoService();
            Endereco endereco = fornecedor.getEndereco();
            fornecedor.setEndereco(enderecoService.inserir(endereco));

            // Inserir telefone
            TelefoneService telefoneService = new TelefoneService();
            Telefone telefone = fornecedor.getTelefoneComercial();
            fornecedor.setTelefoneComercial(telefoneService.inserirTelefone(telefone));

            // Inserir fornecedor
            return fornecedorDAOImpl.inserir(fornecedor);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Fornecedor fornecedor) {
        try {
            return fornecedorDAOImpl.atualizar(fornecedor);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deletar(int id) {
        try {
            if (!(fornecedorDAOImpl.buscarPorId(id) instanceof Fornecedor)) {
                return false; // se não tiver, retorna false
            }
            fornecedorDAOImpl.deletar(id); // se tiver, deleta
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Fornecedor buscarPorId(int id) {
        return fornecedorDAOImpl.buscarPorId(id);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorDAOImpl.listarTodos();
    }

    // Específicos
    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedorDAOImpl.buscarPorCnpj(cnpj);
    }

    public List<Fornecedor> buscarPorNomeComercial(String nomeComercial) {
        return fornecedorDAOImpl.buscarPorNomeComercial(nomeComercial);
    }

    public List<ProdutoBase> listarProdutosDoFornecedor(int idFornecedor) {
        return fornecedorDAOImpl.listarProdutosDoFornecedor(idFornecedor);
    }
}

package controller;

import java.util.List;
import model.Endereco;
import model.Fornecedor;
import model.ProdutoBase;
import model.Telefone;
import service.FornecedorService;

public class FornecedorController {

    private FornecedorService fornecedorService;

    public FornecedorController() {
        fornecedorService = new FornecedorService();
    }

    public Fornecedor inserirFornecedor(String razaoSocial, String nomeComercial, String cnpj, 
                                String estado, String municipio, String cep, String bairro, String rua,
                                String numero, String complemento,
                                String telefoneComercial, String emailComercial) {
        Endereco endereco = new Endereco(estado, municipio, cep, bairro, rua, numero, complemento);
        Telefone telefone = new Telefone(telefoneComercial);
        Fornecedor fornecedor = new Fornecedor(razaoSocial, nomeComercial, cnpj, endereco, telefone, emailComercial);
        return fornecedorService.inserirFornecedor(fornecedor);
    }

    public boolean atualizar(int id, String razaoSocial, String nomeComercial, String cnpj, 
                                String estado, String municipio, String cep, String bairro, String rua,
                                String numero, String complemento,
                                String telefoneComercial, String emailComercial) {
        Endereco endereco = new Endereco(estado, municipio, cep, bairro, rua, numero, complemento);
        Telefone telefone = new Telefone(telefoneComercial);
        Fornecedor fornecedor = new Fornecedor(id, razaoSocial, nomeComercial, cnpj, endereco, telefone, emailComercial);
        return fornecedorService.atualizar(fornecedor);
    }

    public boolean deletar(int id) {
        return fornecedorService.deletar(id);
    }

    public Fornecedor buscarPorId(int id) {
        return fornecedorService.buscarPorId(id);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorService.listarTodos();
    }

    // Específicos
    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedorService.buscarPorCnpj(cnpj);
    }

    public List<Fornecedor> buscarPorNomeComercial(String nomeComercial) {
        return fornecedorService.buscarPorNomeComercial(nomeComercial);
    }

    public List<ProdutoBase> listarProdutosDoFornecedor(int idFornecedor) {
        return fornecedorService.listarProdutosDoFornecedor(idFornecedor);
    }
}

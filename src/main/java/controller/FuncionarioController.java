package controller;

import java.time.LocalDate;
import java.util.List;

import model.DadosBancarios;
import model.Endereco;
import model.Funcionario;
import model.Telefone;
import model.Usuario;
import service.FuncionarioService;
import service.VariacaoProdutoService;

public class FuncionarioController {

    private FuncionarioService funcionarioService;

    
    public FuncionarioController() {
        funcionarioService = new FuncionarioService();
    }
    

    // criar model de Endereco, Telefone, DadosBancarios, Usuario e Funcionario
    public Funcionario inserirFuncionario(String nome, String sobrenome, int anoNascimento, int mesNascimento, int diaNascimento,
                         String senha, String cpf, String email, 
                         String codigoAgenciaBanco, String numeroContaBanco, String codigoBanco,
                         String estado, String municipio, String cep, String bairro, String rua,
                         String numeroEndereco, String complementoEndereco, String numeroTelefone,
                         String cargo, int anoAdmissao, int mesAdmissao, int diaAdmissao, double salario) {

        // criando os models:
        Endereco endereco = new Endereco(estado, municipio, cep, bairro, rua, numeroEndereco, complementoEndereco);
        Telefone telefone = new Telefone(numeroTelefone);
        DadosBancarios dadosBancarios = new DadosBancarios(codigoAgenciaBanco, numeroContaBanco, codigoBanco);
        LocalDate dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);
        Usuario usuario = new Usuario(nome, sobrenome, senha, dataNascimento, cpf, telefone, endereco, email, dadosBancarios);
        LocalDate dataAdmissao = LocalDate.of(anoAdmissao, mesAdmissao, diaAdmissao);
        Funcionario funcionario = new Funcionario(usuario, cargo, dataAdmissao, salario);

        // 
        return funcionarioService.inserir(funcionario);
    }

    boolean atualizar(int id, String nome, String sobrenome, int anoNascimento, int mesNascimento,
                         int dataNascimento, String senha, String cpf, String email, 
                         String codigoAgenciaBanco, String numeroContaBanco, String codigoBanco,
                         String estado, String municipio, String cep, String bairro, String rua,
                         String numeroEndereco, String complementoEndereco, String telefone,
                         String cargo, LocalDate dataDeAdmissao, double salario) {
        // código do CRUD com o BD
    }

    boolean deletar(int id) {
        // código do CRUD com o BD
    }

    Funcionario buscarPorId(int id) {
        // código do CRUD com o BD
    }

    List<Funcionario> listarTodos() {
        // código do CRUD com o BD
    }


    // Específicos
    List<Funcionario> buscarPorCargo(String cargo) {
        // código do CRUD com o BD
    }

    List<Funcionario> buscarPorNome(String nome) {
        // código do CRUD com o BD
    }

    
}

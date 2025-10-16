package controller;

import java.time.LocalDate;
import java.util.List;

import model.DadosBancarios;
import model.Endereco;
import model.Funcionario;
import model.Telefone;
import model.Usuario;
import service.FuncionarioService;

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

        // inserindo no banco (passando pra service)
        return funcionarioService.inserir(funcionario);
    }

    public boolean atualizar(int id, String nome, String sobrenome, int anoNascimento, int mesNascimento, int diaNascimento,
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
        funcionario.setId(id);

        // atualizando no banco (passando pra service)
        return funcionarioService.atualizar(null);
    }

    public boolean deletar(int id) {
        return funcionarioService.deletar(id);
    }

    public Funcionario buscarPorId(int id) {
        return buscarPorId(id);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioService.listarTodos();
    }


    // Específicos
    public List<Funcionario> buscarPorCargo(String cargo) {
        return funcionarioService.buscarPorCargo(cargo);
    }

    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioService.buscarPorNome(nome);
    }

    
}

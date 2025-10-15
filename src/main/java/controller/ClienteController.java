package controller;

import java.time.LocalDate;
import java.util.List;

import model.Cliente;
import model.DadosBancarios;
import model.Endereco;
import model.Telefone;
import model.Usuario;
import service.ClienteService;


public class ClienteController {
    
    private ClienteService clienteService;

    
    public ClienteController() {
        clienteService = new ClienteService();
    }

    public Cliente inserirCliente(String nome, String sobrenome, int anoNascimento, int mesNascimento,
                             int diaNascimento, String senha, String cpf, String email, 
                             String codigoAgenciaBanco, String numeroContaBanco, String codigoBanco,
                             String estado, String municipio, String cep, String bairro, String rua,
                             String numeroEndereco, String complementoEndereco, String numeroTelefone){
        
        Endereco endereco = new Endereco(estado, municipio, cep, bairro, rua, numeroEndereco, complementoEndereco);
        Telefone telefone = new Telefone(numeroTelefone);
        
        DadosBancarios dadosBancarios = null;
        if (codigoAgenciaBanco != null && !codigoAgenciaBanco.trim().isEmpty()) {
             dadosBancarios = new DadosBancarios(codigoAgenciaBanco, numeroContaBanco, codigoBanco);
        }
       
        LocalDate dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);
        
        Usuario usuario = new Usuario(nome, sobrenome, senha, dataNascimento, cpf, telefone, endereco, email, dadosBancarios);
        
        Cliente cliente = new Cliente(usuario);

        return clienteService.inserir(cliente);
    }

    public boolean atualizar(int id, String nome, String sobrenome, int anoNascimento, int mesNascimento,
                             int diaNascimento, String senha, String cpf, String email, 
                             String codigoAgenciaBanco, String numeroContaBanco, String codigoBanco,
                             String estado, String municipio, String cep, String bairro, String rua,
                             String numeroEndereco, String complementoEndereco, String numeroTelefone) {

        
        Endereco endereco = new Endereco(estado, municipio, cep, bairro, rua, numeroEndereco, complementoEndereco);
        
        Telefone telefone = new Telefone(numeroTelefone);
        
        DadosBancarios dadosBancarios = null;
        if (codigoAgenciaBanco != null && !codigoAgenciaBanco.trim().isEmpty()) {
            dadosBancarios = new DadosBancarios(codigoAgenciaBanco, numeroContaBanco, codigoBanco);
        }

        LocalDate dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);
        
        Usuario usuario = new Usuario(nome, sobrenome, senha, dataNascimento, cpf, telefone, endereco, email, dadosBancarios);
        usuario.setId(id);
        
        Cliente cliente = new Cliente(usuario);
        cliente.setId(id);

        return clienteService.atualizar(cliente);
    }


    public boolean deletar(int id) {
        return clienteService.deletar(id);
    }


    public Cliente buscarPorId(int id) {
        return clienteService.buscarPorId(id);
    }


    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }


    public Cliente buscarPorEmail(String email) {
        return clienteService.buscarPorEmail(email);
    }


    public Cliente buscarPorCpf(String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }


    public List<Cliente> buscarPorNome(String nome) {
        return clienteService.buscarPorNome(nome);
    }


}
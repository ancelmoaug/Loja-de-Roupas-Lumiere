package service;

import java.util.List;

import db.DB;
import impl.FuncionarioDAOImpl;
import model.DadosBancarios;
import model.Endereco;
import model.Funcionario;
import model.Telefone;
import model.Usuario;

public class FuncionarioService {
    
    private FuncionarioDAOImpl funcionarioDAOImpl;

    
    public FuncionarioService() {//Service instancia um DAOImpl mandando um DB.getConnection no construtor
        funcionarioDAOImpl = new FuncionarioDAOImpl(DB.getConnection());
    }

    public FuncionarioDAOImpl getVariacaoDAOImpl() {
        return funcionarioDAOImpl;
    }
    
    // esse metodo deve chamar a funcao inserirUsuario de UsuarioService
    public Funcionario inserir(Funcionario funcionario) {
        try {
            // criar endereco no banco
            EnderecoService enderecoService = new EnderecoService();
            Endereco endereco = funcionario.getEndereco();
            funcionario.setEndereco(enderecoService.inserirEndereco(endereco));

            // criar telefone no banco
            TelefoneService telefoneService = new TelefoneService();
            Telefone telefone = funcionario.getEndereco();
            funcionario.setTelefone(telefoneService.inserirTelefone(telefone));

            // criar dados bancarios no banco
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            DadosBancarios dadosBancarios = funcionario.getDadosBancarios();
            funcionario.setDadosBancarios(dadosBancariosService.inserirDadosBancarios(dadosBancarios));

            // criar usuario no banco
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = funcionario;
            funcionario.


        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // esse metodo deve chamar a funcao atualizar de UsuarioService
    boolean atualizar(Funcionario funcionario) {
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

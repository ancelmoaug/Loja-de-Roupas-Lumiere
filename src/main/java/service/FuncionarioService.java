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

    
    // esse metodo deve chamar a funcao inserirUsuario de UsuarioService
    public Funcionario inserir(Funcionario funcionario) {
        try {
            // Inserir endereço
            EnderecoService enderecoService = new EnderecoService();
            Endereco endereco = funcionario.getEndereco();
            funcionario.setEndereco(enderecoService.inserirEndereco(endereco));

            // Inserir telefone
            TelefoneService telefoneService = new TelefoneService();
            Telefone telefone = funcionario.getTelefone();
            funcionario.setTelefone(telefoneService.inserirTelefone(telefone));

            // Inserir dados bancários
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            DadosBancarios dadosBancarios = funcionario.getDadosBancarios();
            funcionario.setDadosBancarios(dadosBancariosService.inserirDadosBancarios(dadosBancarios));

            // Inserir usuário
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = new Usuario(funcionario); // Cria cópia como Usuario
            Usuario usuarioInserido = usuarioService.inserirUsuario(usuario);
            funcionario.setId(usuarioInserido.getId());

            // Inserir funcionário
            return funcionarioDAOImpl.inserir(funcionario);

        } catch (Exception e) {
            throw e; // pode também logar o erro
        }
    }

    // esse metodo deve chamar a funcao atualizar de UsuarioService
    public boolean atualizar(Funcionario funcionario) {
        try {
            // Atualizar endereço
            EnderecoService enderecoService = new EnderecoService();
            Endereco endereco = funcionario.getEndereco();
            enderecoService.atualizar(endereco);

            // Atualizar telefone
            TelefoneService telefoneService = new TelefoneService();
            Telefone telefone = funcionario.getTelefone();
            telefoneService.atualizar(telefone);

            // Atualizar dados bancários
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            DadosBancarios dadosBancarios = funcionario.getDadosBancarios();
            dadosBancariosService.atualizar(dadosBancarios);

            // Atualizar usuário
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = new Usuario(funcionario); // converte para Usuario
            usuario.setId(funcionario.getId()); // mantém o mesmo ID
            usuarioService.atualizar(usuario);

            // Atualizar funcionário (camada DAO)
            return funcionarioDAOImpl.atualizar(funcionario);

        } catch (Exception e) {
            throw e; // Ou você pode logar o erro, dependendo da estrutura do seu projeto
        }
    }

    public boolean deletar(int id) {
        try {
            // Buscar funcionário completo para pegar os IDs dependentes
            Funcionario funcionario = funcionarioDAOImpl.buscarPorId(id);
            if (funcionario == null) {
                return false; // não existe
            }

            // Deletar dados bancários
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            if (funcionario.getDadosBancarios() != null) {
                dadosBancariosService.deletar(funcionario.getDadosBancarios().getId());
            }

            // Deletar telefone
            TelefoneService telefoneService = new TelefoneService();
            if (funcionario.getTelefone() != null) {
                telefoneService.deletar(funcionario.getTelefone().getId());
            }

            // Deletar endereço
            EnderecoService enderecoService = new EnderecoService();
            if (funcionario.getEndereco() != null) {
                enderecoService.deletar(funcionario.getEndereco().getId());
            }

            // Deletar usuário
            UsuarioService usuarioService = new UsuarioService();
            usuarioService.deletar(funcionario.getId());

            // Deletar funcionário
            return funcionarioDAOImpl.deletar(idFuncionario);

        } catch (Exception e) {
            throw e;
        }
    }

    public Funcionario buscarPorId(int id) {
        try {
            return funcionarioDAOImpl.buscarPorId(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Funcionario> listarTodos() {
        try {
            return funcionarioDAOImpl.listarTodos();
        } catch (Exception e) {
            throw e;
        }
    }


    // Específicos
    public List<Funcionario> buscarPorCargo(String cargo) {
        try {
            return funcionarioDAOImpl.buscarPorCargo(cargo);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Funcionario> buscarPorNome(String nome) {
        try {
            return funcionarioDAOImpl.buscarPorNome(nome);
        } catch (Exception e) {
            throw e;
        }
    }

    
}

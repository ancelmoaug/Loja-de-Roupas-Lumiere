package service;

import java.util.List;

import db.DB;
import impl.ClienteDAOImpl;
import model.Cliente;
import model.DadosBancarios;
import model.Endereco;
import model.Telefone;
import model.Usuario;

public class ClienteService {
    
    private ClienteDAOImpl clienteDAOImpl;


    public ClienteService() {
        clienteDAOImpl = new ClienteDAOImpl(DB.getConnection());
    } 

    public ClienteDAOImpl getClienteDAOImpl() {
        return clienteDAOImpl;
    }

    public Cliente inserir(Cliente cliente) {
        try {
            // Inserir endereço
            EnderecoService enderecoService = new EnderecoService();
            Endereco endereco = cliente.getEndereco();
            cliente.setEndereco(enderecoService.inserir(endereco));

            // Inserir telefone
            TelefoneService telefoneService = new TelefoneService();
            Telefone telefone = cliente.getTelefone();
            cliente.setTelefone(telefoneService.inserirTelefone(telefone));

            // Inserir dados bancários
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            DadosBancarios dadosBancarios = cliente.getDadosBancarios();
            cliente.setDadosBancarios(dadosBancariosService.inserirDadosBancarios(dadosBancarios));

            // Inserir usuário
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = new Usuario(cliente); // converte Cliente para Usuario
            Usuario usuarioInserido = usuarioService.inserirUsuario(usuario);
            cliente.setId(usuarioInserido.getId());

            // Inserir cliente
            return clienteDAOImpl.inserir(cliente);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Cliente cliente) {
        try {
            // Atualizar endereço
            EnderecoService enderecoService = new EnderecoService();
            Endereco endereco = cliente.getEndereco();
            enderecoService.atualizar(endereco);

            // Atualizar telefone
            TelefoneService telefoneService = new TelefoneService();
            Telefone telefone = cliente.getTelefone();
            telefoneService.atualizar(telefone);

            // Atualizar dados bancários
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            DadosBancarios dadosBancarios = cliente.getDadosBancarios();
            dadosBancariosService.atualizar(dadosBancarios);

            // Atualizar usuário
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = new Usuario(cliente); // converte Cliente para Usuario
            usuario.setId(cliente.getId()); // mantém o mesmo ID
            usuarioService.atualizar(usuario);

            // Atualizar cliente
            return clienteDAOImpl.atualizar(cliente);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deletar(int id) {
        try {
            // Buscar cliente completo
            Cliente cliente = clienteDAOImpl.buscarPorId(id);
            if (cliente == null) {
                return false; // não existe
            }

            // Deletar dados bancários
            DadosBancariosService dadosBancariosService = new DadosBancariosService();
            if (cliente.getDadosBancarios() != null) {
                dadosBancariosService.deletar(cliente.getDadosBancarios().getId());
            }

            // Deletar telefone
            TelefoneService telefoneService = new TelefoneService();
            if (cliente.getTelefone() != null) {
                telefoneService.deletar(cliente.getTelefone().getId());
            }

            // Deletar endereço
            EnderecoService enderecoService = new EnderecoService();
            if (cliente.getEndereco() != null) {
                enderecoService.deletar(cliente.getEndereco().getId());
            }

            // Deletar usuário
            UsuarioService usuarioService = new UsuarioService();
            usuarioService.deletar(cliente.getId());

            // Deletar cliente
            return clienteDAOImpl.deletar(id);

        } catch (Exception e) {
            throw e;
        }
    }

    public Cliente buscarPorId(int id) {
        return clienteDAOImpl.buscarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return clienteDAOImpl.listarTodos();
    }

    public Cliente buscarPorEmail(String email) {
        return clienteDAOImpl.buscarPorEmail(email);
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteDAOImpl.buscarPorCpf(cpf);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteDAOImpl.buscarPorNome(nome);
    }

}
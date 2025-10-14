package service;

import java.util.List;

import db.DB;
import impl.ClienteDAOImpl;
import model.Carrinho;
import model.Cliente;
import model.Pedido;
import model.Usuario;

public class ClienteService {
    
    private ClienteDAOImpl clienteDAOImpl;
    private UsuarioService usuarioService;
    private CarrinhoService carrinhoService;
    private PedidoService pedidoService;

    public ClienteService() {
        clienteDAOImpl = new ClienteDAOImpl(DB.getConnection());
        usuarioService = new UsuarioService();
        carrinhoService = new CarrinhoService();
        pedidoService = new PedidoService();
    } 

    public ClienteDAOImpl getClienteDAOImpl() {
        return clienteDAOImpl;
    }

    public Cliente inserir(Cliente cliente){ 
        Usuario usuarioInserido = usuarioService.inserirUsuario(cliente);
        
        cliente.setId(usuarioInserido.getId());

        return clienteDAOImpl.inserir(cliente);
    }

    public boolean atualizar(Cliente cliente) {
        usuarioService.atualizar(cliente);

        return clienteDAOImpl.atualizar(cliente);
    }

    public boolean deletar(int id) {
        // 1. O ClienteDAOImpl deleta apenas o cliente
        boolean clienteDeletado = clienteDAOImpl.deletar(id);

        // 2. O UsuarioService deleta o usuario e suas dependências
        if (clienteDeletado) {
             return usuarioService.deletar(id);
        }
        return false;
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

    public Carrinho buscarCarrinhoDoCliente(int idCliente) {
        return carrinhoService.buscarPorCliente(idCliente);
    }

    public List<Pedido> listarPedidosDoCliente(int idCliente) {
        return pedidoService.listarPedidosDoCliente(idCliente);
    }
}
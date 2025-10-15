package service;

import db.DB;
import impl.UsuarioDAOImpl;
import model.Usuario;
import java.util.List;

public class UsuarioService {


    private UsuarioDAOImpl usuarioDAOImpl;

    public UsuarioService() {
        usuarioDAOImpl = new UsuarioDAOImpl(DB.getConnection());
    }

    public UsuarioDAOImpl getUsuarioDAOImpl() {
        return usuarioDAOImpl;
    }

    public Usuario inserirUsuario(Usuario usuario) {
        try {
            return usuarioDAOImpl.inserir(usuario);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean atualizar(Usuario usuario) {
        try {
            return usuarioDAOImpl.atualizar(usuario);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deletar(int id) {
        try {
            Usuario usuario = usuarioDAOImpl.buscarPorId(id);
            if (!(usuario instanceof Usuario)) {
                return false; 
            }
            return usuarioDAOImpl.deletar(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAOImpl.buscarPorId(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAOImpl.listarTodos();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDAOImpl.buscarPorEmail(email);
    }

    public Usuario buscarPorNome(String nome) {
        return usuarioDAOImpl.buscarPorNome(nome);
    }

    public Usuario buscarPorCpf(String cpf) {
        return usuarioDAOImpl.buscarPorCpf(cpf);
    }
}
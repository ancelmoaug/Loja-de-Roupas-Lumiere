package dao;

import java.util.List;
import model.Endereco;

public interface EnderecoDAO {

    Endereco inserir(Endereco endereco);
    boolean atualizar(Endereco endereco);
    boolean deletar(int id);
    Endereco buscarPorId(int id);

    List<Endereco> buscarPorMunicipio(String municipio);
    List<Endereco> buscarPorEstado(String estado);
    List<Endereco> buscarPorCep(String cep);
}

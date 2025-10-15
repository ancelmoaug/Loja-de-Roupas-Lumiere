package dao;
    
import java.util.List;
import model.Categoria;

public interface CategoriaDAO {

   
    Categoria inserir(Categoria categoria);
    boolean atualizar(Categoria categoria);
    boolean deletar(int id);
    Categoria buscarPorId(int id);
    List<Categoria> listarTodas();

  
    int buscarPorCategoria(String nomeCategoria); 
}
  Categoria buscarPorId(int id);
    int buscarPorCategoria(String nomeCategoria); 
    


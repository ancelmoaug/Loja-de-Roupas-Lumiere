package dao;
    
import model.Categoria;

public interface CategoriaDAO {

  Categoria buscarPorId(int id);
  int buscarPorCategoria(String nomeCategoria); 
    
}
  
    


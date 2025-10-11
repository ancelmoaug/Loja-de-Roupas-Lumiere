package db;

import java.io.Serial;

public class DbException extends RuntimeException{

    @Serial
    private  static  final long serialVersionUID = 1L;//Numero de versão padrão

    //Construtor que informa uma mensagem
    public DbException(String msg){
        super(msg);
    }


}
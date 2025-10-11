package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
//Metodos para conectar e desconectar o banco

    private static Connection conn = null;

    public static Connection getConnection(){
        //pega as propriedades e conecta com o banco de dados
        if(conn == null){

            try {
                Properties props = loadProperties();

                String url = props.getProperty("dburl");

                conn = DriverManager.getConnection(url, props); // faz a chamada da conexão com o banco

            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConection(){
        //Operação de fechar um conexão com o banco
        if(conn != null){
            try{
                conn.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    private  static Properties loadProperties(){//Properties é do java util
        //Metodo utilizado para carregar as propriedades ques estão
        //em db.properties
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();

            props.load(fs);//lê o arquivo properties apontado por fs e guarda no objeto props

            return props;


        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }

    }

    public static void closeStatment(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    
}

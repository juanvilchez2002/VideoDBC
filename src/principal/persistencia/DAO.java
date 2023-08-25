package principal.persistencia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    DAO es una clase Abstracta y no sera instanciada
*/

public abstract class DAO {
    //atributos
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    
    //variables
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "perros";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    //metodos
    protected void conectarBase() throws ClassNotFoundException, SQLException{
        try{
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/"+DATABASE+"?useSSL=false";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }
    }
    
    protected void desconectarBase() throws Exception{
        try{
            if(resultado!=null){
                resultado.close();
            }
            
            if(sentencia!=null){
                sentencia.close();
            }
            
            if(conexion!=null){
                conexion.close();
            }            
        }catch(Exception e){
            throw e;
        }
    }
    
    protected void insertarModificarModificar(String consultaSQL) throws Exception{
        try{
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(consultaSQL);            
        }catch(ClassNotFoundException|SQLException e){ 
            throw e;
        }finally{
            desconectarBase();
        }
    }
    
    protected void consultarBase(String consultarSQL) throws Exception{
        try{
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consultarSQL);
        }catch(Exception ex){
            throw ex;
        }
    }
    
}

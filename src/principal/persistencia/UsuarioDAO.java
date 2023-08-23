/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.persistencia;

import principal.dominio.usuario.Usuario;

/**
 *
 * @author JUAN VILCHEZ
 */
public final class UsuarioDAO extends DAO {
    
    public void guardarUsuario(Usuario usuario) throws Exception{
       try{
           if(usuario == null){
               throw new Exception("Debe indicar un usuario");
           }
           
           String consultaSQL = "INSERT INTO Usuario(correoElectronico, clave) "+
                   "VALUES('"+usuario.getCorreoElectronico()+"', '"+usuario.getClave()+"');";
           
           insertarModificarModificar(consultaSQL);
       }catch(Exception e){
           throw e;
       }
    }
    
    public void modificarUsuario(Usuario usuario) throws Exception{
       try{
           if(usuario == null){
               throw new Exception("Debe indicar el usuario que desea modificar");
           }
           
           String consultaSQL = "UPDATE Usuario SET "+
                   "clave = '"+usuario.getClave()+"' WHERE correoElectronico ='"+usuario.getCorreoElectronico()+"';";
           
           insertarModificarModificar(consultaSQL);
       }catch(Exception e){
           throw e;
       }
    }
    
    public void eliminarUsuario(String correoElectronico) throws Exception{
       try{
           if(correoElectronico == null){
               throw new Exception("Debe indicar el usuario que desea eliminar");
           }
           
           String consultaSQL = "DELETE FROM Usuario WHERE correoElectronico ='"+correoElectronico+"';";           
           insertarModificarModificar(consultaSQL);
       }catch(Exception e){
           throw e;
       }
    }
    
    public Usuario buscarUsuarioPorCorreoElectronico(String correoElectronico) throws Exception{
        try{
            
            if(correoElectronico.isEmpty()){
                throw new Exception("Debe de ingresar un Correo Electronico.");
            }
            
            String consultaSQL = "SELECT * FROM Usuario "+
                    "WHERE correoElectronico = '"+correoElectronico+"';";
            
            consultarBase(consultaSQL);
            Usuario usuario = null;
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setId(resultado.getInt(1));
                usuario.setCorreoElectronico(resultado.getString(2));
                usuario.setClave(resultado.getString(3));
            }
            
            desconectarBase();
            return usuario;
        }catch(Exception e){
            desconectarBase();
            throw e;
        }
    }
    
}

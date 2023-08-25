
package principal.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import principal.dominio.usuario.Usuario;

public final class UsuarioDAO extends DAO {
    
    public void guardarUsuario(Usuario usuario) throws Exception{
       try{
           if(usuario == null){
               throw new Exception("Debe indicar un usuario");
           }
           
           String consultaSQL = "INSERT INTO Usuario(correoElectronico, clave) "+
                   "VALUES('"+usuario.getCorreoElectronico()+"', '"+usuario.getClave()+"');";
           
           insertarModificarEliminar(consultaSQL);
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
           
           insertarModificarEliminar(consultaSQL);
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
           insertarModificarEliminar(consultaSQL);
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
    
    public Collection<Usuario> listarUsuarios() throws Exception{
        try{
            String consultaSQL = "SELECT correoElectronico, clave FROM Usuario;";
            
            consultarBase(consultaSQL);
            
            Usuario usuario = null;
            Collection<Usuario> usuarios = new ArrayList();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCorreoElectronico(resultado.getString(1));
                usuario.setClave(resultado.getString(2));
                usuarios.add(usuario);
            }
            
            desconectarBase();
            return usuarios;
        }catch(Exception ex){
           ex.printStackTrace();
           desconectarBase();
           throw new Exception("Error de sistema");
        }
    }
    
    public Usuario buscarUsuarioPorId(Integer id)throws Exception{
        try{
            String consultaSQL = "SELECT * FROM Usuario "+
                    "WHERE id = '"+id+"';";
            
            consultarBase(consultaSQL);
            
            Usuario usuario = null;
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setId(resultado.getInt(1));
                usuario.setCorreoElectronico(resultado.getNString(2));
                usuario.setClave(resultado.getString(3));
            }
            
            desconectarBase();
            return usuario;
        }catch(Exception e){
            throw e;
        }
    }
}

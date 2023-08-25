
package principal.dominio.usuario;

import java.util.Collection;
import principal.persistencia.UsuarioDAO;


public class UsuarioServicio {
    
    //creamos un atributo de tipo UsuarioDAO
    private UsuarioDAO dao;

    //inicializamos el atributo

    public UsuarioServicio() {
        this.dao = new UsuarioDAO();
    }

    
    
    public void crearUsuario(String correoElectronico, String clave)throws Exception{
        try{
            //validaciones
            if(correoElectronico == null || correoElectronico.trim().isEmpty()){
                throw new Exception("Debe indicar el correo electronico");
            }            
            if(correoElectronico.contains("@")==false){
                throw new Exception("El correo electronico no es valido");                
            }
            if(clave == null || clave.trim().isEmpty()){
                throw new Exception("Debe de ingresar una clave"); 
            }
            if(clave.length()<8){
                throw new Exception("La clave debe tener mas de 8 caracteres"); 
            }
            if(dao.buscarUsuarioPorCorreoElectronico(correoElectronico)!=null){
                throw new Exception("El usuario ya existe con el "+correoElectronico);
            }
            
            //guardamos el usuario
            Usuario usuario = new Usuario();
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setClave(clave);
            dao.guardarUsuario(usuario);
            
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void modificarClaveUsuario(String correoElectronico, String claveActual, String claveNueva) throws Exception{
        try{
            //validamos
            if(correoElectronico == null || correoElectronico.trim().isEmpty()){
                throw new Exception("Debe indicar un usuario");
            }
            if(claveActual == null || claveActual.trim().isEmpty()){
                throw new Exception("Debe de ingresar la clave actual");
            }
            if(claveNueva == null || claveNueva.trim().isEmpty()){                
                throw new Exception("Debe de ingresar la clave nueva");
            }
            
            //buscamos el usuario
            Usuario usuario = dao.buscarUsuarioPorCorreoElectronico(correoElectronico);
            
            //validamos por parte de usuario
            if(usuario == null){
                throw new Exception("El usuario no existe");
            }
            //validamos si la contraseña del usuario es la corecta
            if(!usuario.getClave().equals(claveActual)){
                throw new Exception("La clave actual no es la registrada en la BD");
            }
            
            //modificando
            usuario.setClave(claveNueva);
            dao.modificarUsuario(usuario);
        }catch(Exception e){
            throw e;
        }
    }
    
    public void eliminarUsuario(String correoElectronico) throws Exception{
        try{
            //validamos                        
            if(correoElectronico == null || correoElectronico.trim().isEmpty()){
                throw new Exception("Debe de ingresar un usuario");
            }
            if(dao.buscarUsuarioPorCorreoElectronico(correoElectronico) == null){
                throw new Exception("No existe el usuario");
            }
            
            //elimando usuario
            dao.eliminarUsuario(correoElectronico);
        }catch(Exception e){
            throw e;
        }
    }
    
    public Usuario buscarUsuarioPorCorreoElectronico(String correoElectronico) throws Exception{
        try{
            //validamos
            if(correoElectronico == null || correoElectronico.trim().isEmpty()){
                throw new Exception("Debe de ingresar un correo electronico");
            }
            Usuario usuario = dao.buscarUsuarioPorCorreoElectronico(correoElectronico);
            return usuario;
        }catch(Exception e){
            throw e;
        }
    }
    
    public Usuario buscarUsuarioPorId(Integer id) throws Exception{
        try{
            //validmos
            if(id == null || id.equals(0)){
                throw new Exception("Debe de ingresar un Id");
            }
            Usuario usuario = buscarUsuarioPorId(id);
            return usuario;
        }catch(Exception e){
            throw e;
        }
    }
    
    public Collection<Usuario> listarUsuario()throws Exception{
        try{
            //listamos los usaurios
            Collection<Usuario> usuarios = dao.listarUsuarios();
            return usuarios;
        }catch(Exception e){
            throw e;
        }
    }
    
    public void imprimirUsuarios() throws Exception{
        try{
            //listamos los usuarios
             Collection<Usuario> usuarios = listarUsuario();
             
             //imprimimos
             if(usuarios.isEmpty()){
                 throw new Exception("No hay usuarios");
             }else{
                 for(Usuario user : usuarios){
                     System.out.println(user.toString());
                 }
             }
        }catch(Exception e){
            throw e;
        }
    }
}


package principal.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import principal.dominio.mascota.Mascota;
import principal.dominio.usuario.Usuario;
import principal.dominio.usuario.UsuarioServicio;


public class MascotaDAO extends DAO{
    
   private final UsuarioServicio usuarioServicio;
   
   public MascotaDAO(){
       this.usuarioServicio = new UsuarioServicio();
    }
    
   public void guardarMascota(Mascota mascota)throws Exception{
       try{
           //validamos
           if(mascota == null){
               throw new Exception("Debe de ingresar una Mascota");
           }
           
           String consulaSQL = "INSERT INTO Mascota (apodo, raza, idUsuario) "
                   +"VALUES ('"+mascota.getApodo()+"', '"+mascota.getRaza()+"' . "+mascota.getUsuario().getId()+"');";
           
           insertarModificarEliminar(consulaSQL);
       }catch(Exception e){
           throw e;
       }finally{
           desconectarBase();
       }
   }
   
   public void modificarMascota(Mascota mascota) throws Exception{
       try{
           //validamos
           if(mascota == null){
               throw new Exception("Debe ingresar la mascota a modificar");
           }
           
           String consultaSQL = "UPDATE Mascota SET "+
                   " apodo = '"+mascota.getApodo()+"' , raza = '"+mascota.getRaza()+"' , idUsuario ="+
                   mascota.getUsuario().getId() + "  WHERE id = "+mascota.getId();
           
           insertarModificarEliminar(consultaSQL);
       }catch(Exception e){
           throw e;
       }finally{
           desconectarBase();
       }
   }
   
   public void eliminarMascota(int id) throws Exception{
       try{
           String consultaSQL = "DELETE FROM Mascota WHERE id = "+id+";";
           insertarModificarEliminar(consultaSQL);
       }catch(Exception e){
           throw e;
       }finally{
           desconectarBase();
       }
   }
   
   public Mascota buscarMascotaPorId(Integer id) throws Exception{
       try{
           //validamos
           if(id == null || id == 0){
               throw new Exception("Debe de ingresar un Id");
           }
           
           String consulaSQL = "SELECT * FROM Mascotas WHERE id ="+id+";";
           consultarBase(consulaSQL);
           Mascota mascota = null;
           
           while(resultado.next()){
               mascota = new Mascota();
               
               mascota.setId(resultado.getInt(1));
               mascota.setApodo(resultado.getString(2));
               mascota.setRaza(resultado.getString(3));
               
               Integer idUsuario = resultado.getInt(4);
               
               Usuario usuario = usuarioServicio.buscarUsuarioPorId(idUsuario);
               mascota.setUsuario(usuario);               
           }
           
           desconectarBase();
           return mascota;
       }catch(Exception e){
           desconectarBase();
           throw e;
       }
   }
   
   public Collection<Mascota> listaMascota() throws Exception{
       try{
           String consultaSQL = "SELECT * FROM Mascota";
           consultarBase(consultaSQL);
           Mascota mascota = null;
           Collection<Mascota> mascotas = new ArrayList();
           
           while(resultado.next()){
               mascota = new Mascota();
               
               mascota.setId(resultado.getInt(1));
               mascota.setApodo(resultado.getString(2));
               mascota.setRaza(resultado.getString(3));
               
               Integer idUsuario = resultado.getInt(4);
               
               Usuario usuario = usuarioServicio.buscarUsuarioPorId(idUsuario);
               mascota.setUsuario(usuario);   
               
               mascotas.add(mascota);
           }
           
           desconectarBase();
           return mascotas;
       }catch(Exception e){
           e.printStackTrace();
           desconectarBase();
           throw e;
       }
   }
}

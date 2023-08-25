package principal;

import java.util.logging.Level;
import java.util.logging.Logger;
import principal.dominio.usuario.UsuarioServicio;

public class MainClass {

    public static void main(String[] args) {
        
        UsuarioServicio us = new UsuarioServicio();
        
        try{
            us.imprimirUsuarios();
        }catch(Exception e){
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}

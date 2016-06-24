package clasesPropias.Dominio.Controladores;

import java.io.FileNotFoundException;
import java.io.IOException;

import clasesComunes.Usuario_comp;
import clasesPropias.Persistencia.GestorFicherosUsuarios;
/**
 * 	Carga para la capa de dominio el acceso a los ficheros que estan relacionados con la clase Usuario
 *	
 *	@author Marc
 *	@version 1.0
 *	@since 14-11-2015
 */
public class ControladorUsuario {
	/**
	 * 	Escaner utilizado para la lectura de ficheros y entradas de pantalla
	 */
	/**
	 * 	Controladora que permitira acceder a los ficheros para su posterior modificaci&oacute;n
	 */
	private GestorFicherosUsuarios cf;
	public ControladorUsuario(){
		cf = new GestorFicherosUsuarios();
	}
	
	/**
	 * 	A&ntilde;ade un usuario a la lista de usuarios existentes
	 * 
	 * 	@param	u	Usuario que crearemos
	 * @throws ClassNotFoundException 
	 * 	@see Usuario_comp
	 * 	@throws	IOException	Si ocurre un problema con la lectura/escritura del fichero
	 */
	public boolean CrearUsuario(Usuario_comp u) throws ClassNotFoundException, IOException{
		return cf.CrearUsuario_i(u);

	}
	/**
	 * 	Inicia el proceso de inicio de sesion
	 * 
	 * 	@param	u	Usuario con el cual queremos realizar un login
	 * @throws ClassNotFoundException 
	 * 	@throws	FileNotFoundException	Si ocurre un problema con la lectura/escritura del fichero
	 */
	public boolean LogIN(Usuario_comp u) throws IOException, ClassNotFoundException{
		return cf.LogIN1(u);
	}
	
	public boolean borrarUsuario(Usuario_comp u) throws ClassNotFoundException, IOException {
		return cf.borrarUsuario(u);	
	}	
}

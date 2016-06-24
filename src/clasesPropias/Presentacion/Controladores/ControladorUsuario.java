package clasesPropias.Presentacion.Controladores;


import java.io.IOException;

import clasesComunes.Usuario_comp;
/**
 * 	Carga para la capa de dominio el acceso a los ficheros que estan relacionados con la clase Usuario
 *	
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 */
public class ControladorUsuario {
	
	public boolean uilogin(String Usuario, String Password) throws IOException, ClassNotFoundException{
		Usuario_comp u = new Usuario_comp(Usuario,Password);
		clasesPropias.Dominio.Controladores.ControladorUsuario cu = new clasesPropias.Dominio.Controladores.ControladorUsuario();
		boolean b = false;
		b = cu.LogIN(u);
		return b;
	}
	public boolean uiregister(String Usuario, String Password) throws ClassNotFoundException, IOException{
		clasesPropias.Dominio.Controladores.ControladorUsuario cu = new clasesPropias.Dominio.Controladores.ControladorUsuario();
		Usuario_comp u = new Usuario_comp(Usuario,Password);
		boolean b=cu.CrearUsuario(u);
		return b;
	
	}
	public boolean borrarUsuario(String Usuario, String Password){
		boolean b = false;
		clasesPropias.Dominio.Controladores.ControladorUsuario cu = new clasesPropias.Dominio.Controladores.ControladorUsuario();
		Usuario_comp u = new Usuario_comp(Usuario,Password);
		try {
			cu.borrarUsuario(u);
			b = true;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}

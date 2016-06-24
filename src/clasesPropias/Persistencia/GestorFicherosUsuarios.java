package clasesPropias.Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import clasesComunes.Usuario_comp;
import clasesPropias.Dominio.Clases.MyEntry;

public class GestorFicherosUsuarios extends GestorFicheros<Usuario_comp>{

	public void guardarUsuario(Usuario_comp u) throws IOException, ClassNotFoundException {	
		File f = new File(dataUsers);
		System.out.println(f);	
		String nombre = u.consultar_nombre();
		File [] conj = f.listFiles();
		System.out.print("hay estos archivos" + conj.length);
		int i = 0;
		boolean b = true;
		while (i < conj.length && b) {
			if (conj[i].equals(dataUsers + nombre)) {
				System.out.println("Ya existe");
				b = false;
			}
			++i;
		}
		f = new File(dataUsers + nombre);
		if (b) {
			escribir(u);
			GestorFicherosRanking gfr = new GestorFicherosRanking();
			MyEntry m = new MyEntry(nombre, 0);
			gfr.actualizarRanking(m);
		}
	}

	
	public void imprimirUsuarios( ) throws ClassNotFoundException, IOException {
		File p = new File(dataUsers);
		File[] conj = p.listFiles();
		if (conj.length > 0) {
			int i = 0;
			while (i < conj.length) {
				Usuario_comp u = new Usuario_comp();
				u = leer(new Usuario_comp(conj[i].getName()," "));
				System.out.println("El usuario es :" + u.consultar_nombre() + " " + u.consultar_password());
				++i;
			}
		}	
	}

	public boolean LogIN1(Usuario_comp u) throws ClassNotFoundException, IOException{
		boolean b = true;
		if(usuarioExiste(u)==false) {
			b = false;
		}
		return b;
	}

	public boolean borrarUsuario(Usuario_comp u) throws IOException, ClassNotFoundException {
		String nombre = u.consultar_nombre();
		File f = new File(dataUsers);
		boolean borrado = false;
		if (usuarioExiste(u)) {
			File conj [] = f.listFiles();
			File f2 = new File (dataUsers + nombre);
			int i = 0;
			FileInputStream fis = null;
			Usuario_comp u2 = new Usuario_comp();
			while (i < conj.length && !borrado) {
				if (conj[i].equals(f2)) {
					//fis = new FileInputStream(f2);
					//ObjectInputStream ois = new ObjectInputStream(fis);
					//u2 = (Usuario_comp)ois.readObject();
					u2=(Usuario_comp) super.leer(u);
					if (u.consultar_nombre().equals(u2.consultar_nombre()) && u.consultar_password().equals(u2.consultar_password())) {
						borrado = true;
						//fis.close();
						//ois.close();
						f2.delete();
						GestorFicherosRanking gf = new GestorFicherosRanking();
						gf.borrarRanking(u.consultar_nombre());
					}
				}
				++i;
			}
		}
		return borrado;
	}

	public boolean usuarioExiste(Usuario_comp u) throws ClassNotFoundException, IOException {
		boolean b=false;
		String user = u.consultar_nombre();
		File f= new File(dataUsers + user);
		System.out.println(f);
		Usuario_comp u2 = new Usuario_comp();
		if (f.length() !=0) {
			u2 = (Usuario_comp) leer(u);
		}
		System.out.println(u2.consultar_nombre() + u2.consultar_password());
		System.out.println(u.consultar_nombre() + u.consultar_password());
		if (u.consultar_nombre().equals(u2.consultar_nombre()) && u.consultar_password().equals(u2.consultar_password())) {
			b = true;
			System.out.println("si son iguals");
		}
		return b;
	}

	public boolean CrearUsuario_i(Usuario_comp u) throws FileNotFoundException, IOException, ClassNotFoundException{
		boolean b = false;
		if(existeNombre(u.consultar_nombre())){
			b = false;
		}
		else{
			u= new Usuario_comp(u.consultar_nombre(),u.consultar_password());
			guardarUsuario(u);
			b = true;

		}
		return b;

	}

	/**
	 * 	Comprueba si un nombre de usuario existe en el fichero
	 * 	@param	nombre	Nombre de usuario que se desea comprobar
	 * 	@return true si el nombre exisitia con anterioriedad, falso en caso contrario
	 * 	@throws	FileNotFoundException		El fichero no existe/Ruta no valida
	 */
	public boolean existeNombre(String nombre) throws IOException {
		File archivo= new File(dataUsers);
		String conj [] = archivo.list();
		int i = 0;
		boolean b = false;
		while (i < conj.length && !b) {
			if (conj[i].equals(nombre)) {
				b = true;
			}
			++i;
		}
		return b;
	}

	public Usuario_comp leer(Usuario_comp u) throws ClassNotFoundException, IOException {
		f = new File(dataUsers + u.consultar_nombre());
		return (Usuario_comp) super.leer(u);
	}

	public void escribir(Usuario_comp u) throws ClassNotFoundException, IOException {
		f = new File(dataUsers + u.consultar_nombre());
		super.escribir(u);
	}

}

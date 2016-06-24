package clasesPropias.Persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import clasesComunes.Usuario_comp;
import clasesPropias.Dominio.Clases.Partida;

public class GestorFicherosPartidas extends GestorFicheros<Partida>{
	
	public void guardarPartida (Partida p, String nomP) throws IOException, ClassNotFoundException {
        Usuario_comp u = p.getUsuario();
        String nombre = u.consultar_nombre();
		File folder = new File (dataPartidas + nombre);
		folder.mkdirs();
        f = new File(dataPartidas + nombre + "/" + nomP);
        f.createNewFile();
        super.escribir(p);
       
    }
	
	public Partida cargarPartida(Usuario_comp u, String nomP) throws ClassNotFoundException, IOException{
		f = new File(dataPartidas + u.consultar_nombre() + "/" + nomP);
		Partida p= new Partida();
		return (Partida) super.leer(p);
	}
	
	public ArrayList<String> mostrarPartidas (String usr) {
		ArrayList<String> s = new ArrayList<String>();
		f = new File(dataPartidas + usr);
			File conj[] = f.listFiles();
			int i = 0;
		if(conj != null){
			while (i < conj.length) {
				s.add(conj[i].getName());
				++i;
			}
		}
		return s;
	}
	
	public void borrarPartida(String u, String nameFile) {
		File f = new File(dataPartidas + u + "/" + nameFile);
		f.delete();
	}
	
}

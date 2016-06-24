package clasesPropias.Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public abstract class GestorFicheros<T> {

	/**
	 * 	Escaner utilizado para la lectura de ficheros y entradas de pantalla
	 */
	private Scanner s;
	/**
	 * 	Ruta del fichero de Usuarios
	 */
	protected String dataUsers;

	/**
	 * 	Ruta del fichero de tableros
	 */
	protected String dataTableros;

	/**
	 * 	Ruta del fichero de ranking
	 */
	protected String dataRanking;
	/**
	 * 	Ruta del fichero de partidas
	 */
	protected String dataPartidas;

	//private String data;
	protected int contf;
	/**
	 * Nivel de dificultad en letras (facil/medio/dificil)
	 */
	protected String diff;
	/**
	 * Tama&ntilde; de las filas/columnas del sudoku
	 */
	protected String tam;
	/**
	 * Constructora por defecto
	 * @throws IOException
	 */
	private FileInputStream fis;

	private FileOutputStream fos;

	private ObjectOutputStream oos;

	private ObjectInputStream ois;

	T obj;
	public File f;
	public GestorFicheros() {
		dataUsers = ("../data/usuarios/");
		dataRanking= "../data/ranking/rankings.txt";
		dataTableros="../data/tableros/";
		dataPartidas = "../data/partidas/";
		contf = 1;
		f = null;
	}


	public Object leer(T o) throws IOException, ClassNotFoundException {	
		Object r = new Object();
		if (f.length() !=0) {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			r = ois.readObject();
			fis.close();
			ois.close();
		}
		return r;	
	}

	public void escribir(T o) throws IOException, ClassNotFoundException {	
		fos = new FileOutputStream(f);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(o);
		oos.reset();
		fos.close();
		oos.close();	
	}   
}


package clasesPropias.Dominio.Controladores;

import java.io.IOException;
import java.util.Scanner;

import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Persistencia.GestorFicherosPartidas;
import clasesPropias.Persistencia.GestorFicherosTableros;
/**
 * 	Crea un tablero, asigna un valor y ademas, comprueba si es resolubre
 * 
 * 	@author Esteban Jesus
 * 	@version 1.0
 * 	@since 16-11-2015
 */
public class ControladorCreacionTablero {
	/**
	 * 	El tablero con el cual comprobaremos su creaci&oacute;n es correcta
	 * 	@see Tablero
	 */
	private Tablero t;

	/**
	 * Constructora vacia
	 */
	
	public  ControladorCreacionTablero() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Asigna a un un usuario para la creaci&oacute;n de tableros
	 * 
	 * @param us Usuario a asignar a la creaci&oacute;n de tableros
	 * @throws Exception
	 */


	/**
	 * Genera un sudoku de forma automatica
	 * @throws Exception
	 */
	public boolean creacionAutomatica(int tamano, int dificultad, int casillas) throws Exception {
		Scanner s= new Scanner(System.in);
		GenerarSudoku gs = new GenerarSudoku();
		ComprobarSolucionUnica csu= new ComprobarSolucionUnica();
		t = new Tablero(tamano,dificultad);
		if(!gs.generar(tamano, dificultad,casillas)) return false;
		t=gs.getTablero();
		csu.resolver(t);
		for (int i = 0; i < t.getMida(); i++) {
			for (int j = 0; j < t.getMida(); j++) {
				if(t.getValorTauler(i, j)!=0) t.setCasellaPor_defecto(i, j, true);
			}
		}
		return true;
		/*System.out.println("Fallo en creacion: numero casillas invalido");
					System.out.println("Este Sudoku tiene solución, pero no es unica");
						t = gs.generar(tamano, dificultad,casillas,false);
						csu.resolver(t);
						t.escribir();
					menuGuardado(t);*/
	}
	
	public Tablero getTablero(){
		return t;
	}

	/**
	 * 	Aplica una interfaz de la cual el usuario genera un sudoku y lo valida
	 * @throws Exception
	 */
	public void creacionManual(int tamano) throws Exception {
		GenerarSudoku gs = new GenerarSudoku();
		t= new Tablero(tamano,1);
	}
	
	public void guardarTablero(Tablero t, Integer[] id) throws ClassNotFoundException, IOException{
		GestorFicherosTableros gft= new GestorFicherosTableros();
		gft.escribirTablero(t, -1, id);
	}
	
	public boolean comprobarTablero() throws Exception{
		ComprobarSolucionUnica csu= new ComprobarSolucionUnica();
		if(csu.tieneSolucionUnica(t)){
			Tablero t1= new Tablero(t.getMida(),0);
			csu.resolver(t);
			GenerarSudoku gs= new GenerarSudoku();
			gs.copiar(t1, t);
			gs.eliminarCandidatos(t1);
			ComprobarDificultad cd= new ComprobarDificultad(t1);
			int diff=cd.getDificultad();
			t.setDificultad(diff);
			return true;
		}
		return false;
	}


	/**
	 * 	Define un tablero a este objeto
	 * @param t
	 */
/*	public void CargarTablero(Tablero t) {
		this.miTablero = t;
	}*/
	/**
	 * Asigna un valor a una casilla de un tablero
	 * 
	 * @param x Posici&oacute;n fila
	 * @param y Posici&oacute;n columna
	 * @param valor valor valido de un sudokyu a a&ntilde;adir
	 */
	public boolean setNum(int x, int y, int valor) {
		if(!correcto(valor, x, y, t)) return false;
		t.setValorTauler(x, y, valor);
		return true;
	}
	/**
	 * Comprueba si un valor a aplicar a una casilla es valido o no
	 * 
	 * @param num El numero que deseamos validdar
	 * @param row Posici&oacute;n fila
	 * @param col Posici&oacute;n columna
	 * @param t El tablero que deseamos agregar
	 * @return True si el valor es valido, false en caso contrario
	 */
	private static boolean correcto(int num, int row, int col, Tablero t) {
		int box_size=(int) Math.sqrt(t.getMida());
		int r = (row / box_size) * box_size;
		int c = (col / box_size) * box_size;
		int tam = box_size * box_size;

		for (int i = 0; i < tam; i++) {
			if ((t.getValorTauler(row, i) == num && i!=col)||
					(t.getValorTauler(i, col)== num && i!=row)||
					(t.getValorTauler(r + (i % box_size),c + (i / box_size)) == num && !(r + (i % box_size)==row && c + (i / box_size)== col))){
				return false;
			}
		}
		return true;
	}
	
	public void borrarPartida(String nameFile, String nameFile2) {
		// TODO Auto-generated method stub
		GestorFicherosPartidas gfp = new GestorFicherosPartidas();
		gfp.borrarPartida(nameFile, nameFile2);
		
	}
}



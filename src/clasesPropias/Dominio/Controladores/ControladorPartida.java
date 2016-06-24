package clasesPropias.Dominio.Controladores;


import java.io.IOException;
import java.util.Scanner;

import clasesComunes.Usuario_comp;
import clasesPropias.Dominio.Clases.MyEntry;
import clasesPropias.Dominio.Clases.Partida;
import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Persistencia.GestorFicherosPartidas;
import clasesPropias.Persistencia.GestorFicherosRanking;
import clasesPropias.Persistencia.GestorFicherosTableros;
/**
 * 	La partida se crea y el usuario va resolviendo un sudoku asociado a la partida
 * 
 *	@author Marc
 *	@version 1.0
 *	@since 14-11-2015
 *
 */
public class ControladorPartida {
	/**
	 * Partida asociada a esta clase	
	 */
	private Partida p;
	/**
	 * 	Escaner utilizado para la lectura de ficheros y entradas de pantalla
	 */
	private Scanner br;
	/**
	 * 	Fichero del cual contiene los datos de una partida asignada 
	 */
	private GestorFicherosPartidas cf;
	private GestorFicherosTableros cft;
	private GestorFicherosRanking cfr;

	/**
	 * 	Constructora con parametros
	 * 	<p>
	 * 		Carga los controladores por defecto
	 * 	</p>
	 * 
	 * 	@param	u	Usuario que se cargara
	 * 	@param	cargar	Partida nueva (true) o recuperar una guardada
	 * 	@throws	IOException	Problema al no poder leer una entrada pasada por teclado	
	 * @throws ClassNotFoundException 
	 */
	public ControladorPartida() throws IOException, ClassNotFoundException{
		cf = new GestorFicherosPartidas();
		cft = new GestorFicherosTableros();
		cfr = new GestorFicherosRanking();
	}
	
	public Integer getId(){
		return p.getid();
	}
	
	public void init(Usuario_comp u, boolean cargar,boolean nuevo, Tablero t,Integer[] id, String nombre) throws ClassNotFoundException, IOException {
		if(cargar) {
			p = cf.cargarPartida(u, nombre);
		}
		
		else{
			System.out.println("y lo sigo queriendo: "+ id[0]);
			inicializarPartida(u,nuevo,t,id);
		}
		/*	p.escribirRecord();
				cft.actualizarRecord(p.getTablero().getMida(), p.getid(),((Tablero)p.getTablero()).getDificultad() , m1);
				if(((Tablero)p.getTablero()).getDificultad() == 16) puntuacion *= 1.5;
				MyEntry m= new MyEntry(u.consultar_nombre(), puntuacion);
				cfr.actualizarRanking(m);
			}*/
	}
	
	public boolean isPordefecto(int i, int j) {
		return p.getPorDefecto(i,j);
	}
	
	public void setTiempoPartida(long tiempo) {
		p.setTiempo(tiempo);
	}
	
	public long getTiempoPartida() {
		return p.getTiempo();
	}
	
	public int getDificultad() {
		return ((Tablero) p.getTablero()).getDificultad();
	}
	
	public void setTiempo(String usuario, int tiempo) {
		((Tablero)p.getTablero()).setTiempoRecord(usuario, tiempo);
	}
	
	public String[][] getRecordTablero() {
		MyEntry[] tiempos = ((Tablero)p.getTablero()).getRecord();
		int mida = tiempos.length;
		String[][] res = new String[mida][2];
		//System.out.println(" el teiempo es: " + tiempos.length);
		for(int i = 0; i < tiempos.length; ++i) {
			res[i][0] = tiempos[i].fst();
		//	System.out.println(tiempos[i].snd());
			if((tiempos[i].snd()!=null))	res[i][1] = (tiempos[i].snd()).toString();
			else res[i][1]="0";
		}
		return res;
	}
	public void actualizarRanking(String usuario, int puntuacion) throws ClassNotFoundException, IOException {
		GestorFicherosRanking gfr = new GestorFicherosRanking();
		MyEntry m = new MyEntry(usuario,puntuacion);
		gfr.actualizarRanking(m);
	}
	public boolean setNum(int i, int j, int valor){
		boolean b= false;
		if(valor==0){
			p.getTablero().setValorTauler(i, j, valor);
			b=true;
		}
		else if(correcto(valor,i,j,(Tablero) p.getTablero())){
			p.getTablero().setValorTauler(i, j, valor);
			b=true;
		}
		return b;
	}

	public Integer[][] getMatrizValores(){
		Integer[][] mat= new Integer[p.getTablero().getMida()][p.getTablero().getMida()];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				mat[i][j]=p.getTablero().getValorTauler(i, j);
			}
		}
		return mat;
	}

	public void guardarTablero(int numL) throws ClassNotFoundException, IOException {
		GestorFicherosTableros cft = new GestorFicherosTableros();
		cft.escribirTablero((Tablero)p.getTablero(), numL, new Integer[1]);
	}
	
	public boolean ayuda(){
		//if (((Tablero)p.getTablero()).isSolucionUnica()) {
			if(p.ayuda((Tablero) p.getTablero())) {
				p.setPuntuacion(p.getPuntuacion()-10);
				return true;
			}
			else return false;
			
		/*}
		else {
			return p.ayudaAlternativa((Tablero) p.getTablero());
		}	*/
	}

	public void guardarPartida(String nombre) throws ClassNotFoundException, IOException {
		cf.guardarPartida(p, nombre);
	}

	
	/**
	 * 	Inicia la creaci&oacute;n de una partida, definiendo las caracterisiticas del tablero con el que deseamos jugar
	 * 	@param	u	Usuario que se le asignara la partida
	 * 	@throws	IOException	Problema al no poder leer una entrada pasada por teclado	
	 * @throws ClassNotFoundException 
	 */
	private void inicializarPartida(Usuario_comp u, boolean crear, Tablero t,Integer[] id) throws IOException, ClassNotFoundException {
		if(!crear)p= new Partida(t.getMida(),t.getDificultad(),u,0,id);
		else{
			System.out.println("voy a leer!!!");
			p= new Partida(t.getMida(),t.getDificultad(),u,id[0],id);
		}
		if(t.getMida() == 16) {
			p.setPuntuacion(250);
		}
		else {
			p.setPuntuacion(100);
		}
	}
	
	public int getPuntuacion() {
		return p.getPuntuacion();
	}
	/**
	 * 	Compara el tablero solucionado con el usuario o para un usuario 
	 * 	@return	True si la soluci&oacute;n es la correcta, false en caso contrario
	 */
	public boolean comprobarSolucionado() {
		boolean b = true;
		int mida = p.getTablero().getMida();
		for (int i = 0; i < mida && b; i++) {
			for (int j = 0; j < mida && b; j++) {
				if(p.getTablero().getValorTauler(i, j) == ((Tablero) p.getTablero()).getPosicionMatrizSolucion(i, j)) b = false;
			} 
		}
		return b;
	}
	/**
	 * Comprueba si la casilla es valida
	 * 
	 * @param num numero a validar
	 * @param row Posici&oacute;n fila
	 * @param col Posici&oacute;n columna
	 * @param t Tablero a comprobar
	 * @return True si es valido el numero a poner, false en caso contrario
	 */
	private static boolean correcto(int num, int row, int col, Tablero t) {
		int mida = t.getMida();
		int box_size=(int) Math.sqrt(mida);
		if(num >= 1 && num <= mida) {
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
		else return false;
	}
	
	public void actualizarRecords(int n, int numt, int dificultad, MyEntry m) throws ClassNotFoundException, IOException {
		cft.actualizarRecord(n, numt, dificultad, m);
	}



}
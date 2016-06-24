package clasesPropias.Dominio.Clases;

import java.io.IOException;
import java.io.Serializable;

import clasesComunes.Partida_comp;
import clasesComunes.Usuario_comp;
import clasesPropias.Persistencia.GestorFicherosTableros;
/**
 *  Representaci&oacute;n de una partida
 * 
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 */
public class Partida extends Partida_comp implements Serializable {
	/**
	 * El tiempo que lleva la partida activa
	 */
    private static final long serialVersionUID = 1L;
	private long tiempo;
	@SuppressWarnings("unused")
	private int puntuacion;
	/**
	 * Identificador de partida
	 */
	private Integer[] id;
	/**
	 * Si la partida ha terminado o aun sigue en juego
	 */
	private boolean terminada;
	/**
	 * 	Iniciadora de partida
	 * <p>
	 * 	El valor n se ha de encontrar entre los valores validos para crear un tablero
	 * </p>
	 * @param n	tama&ntilde;o del tablero
	 * @param dif	Nivel de dificultad
	 * @param u		Usuario Asignado a la partida
	 * @param tiempo	Tiempo
	 * @param id1	define el identificador de partida que queremos asignar a la partida
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * 	@see Tablero
	 * 	@see Usuario_comp
	 */
	public Partida () {
		super();
	}
	public Partida(int n, int dif,Usuario_comp u, long tiempo,Integer[] id1) throws IOException, ClassNotFoundException{
		GestorFicherosTableros cf= new GestorFicherosTableros(); 
		//id[0]=id1;
		tableroP=cf.leerTablero(n,dif,id1[0],id1);
		System.out.println("he leido el tablero " + id1[0]);
		id=id1;
		usuarioP=u;
		this.tiempo = tiempo;
		setTerminada(false);
	}
	/**
	 * 	Asigna a una partida un tablero, un usuario y un tiempo inicial
	 * @param n
	 * @param dif
	 * @param u	Usuario a asignar
	 * @param tiempo	Tiempo a asignar
	 * @param t	Tablero a asignar
	 * @throws IOException
	 */
	public Partida(int n, int dif,Usuario_comp u, long tiempo, Tablero t) throws IOException{
		tableroP=t;
		usuarioP=u;
		this.tiempo = tiempo;
		setTerminada(false);
	}
	/**
	 * Devuelve el identificador de la partida
	 * @return	El identificador de la partida
	 */
	public int getid(){
		return id[0];
	}

	/**
	 * Asigna un identificador de partida
	 */
	public void setid(int i){
		id= new Integer[1];
		id[0]=i;
	}
	/**
	 * Devuelve el tiempo de la partida
	 * @return El tiempo que tiene la partida
	 */
	public long getTiempo() {
		return tiempo;
	}
	/**
	 * Asigna un tiempo a la partida
	 * @param tiempo El tiempo que deseamos asignar
	 */
	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
	/**
	 * Escribe por consola el tablero asignado a la partida
	 */
	public void escribirTablero(){
		((Tablero) tableroP).escribir();
		//((Tablero) tableroP).escribirCandidatos();
	}
	/**
	 * Obtiene el valor de la casilla del tablero asignado a este objeto
	 * @param x	Posici&oacute;n fila
	 * @param y	Posici&oacute;n columna
	 * @return	El valor de la casilla definida del tablero asignado
	 */
	public int getValorCasilla(int x, int y) {
		return tableroP.getValorTauler(x, y);
	}
	/**
	 * 	Comprueba si una casilla del tablero tiene un valor definido por defecto o no
	 * 
	 * 	@param x Posici&oacute;n fila
	 * 	@param y Posici&oacute;n columna
	 * 	@return  True si su valor esta definido por defecto, false en caso contrario
	 * 	@see Tablero#getPorDefecto(int, int)
	 */
	public boolean getPorDefecto(int x, int y) {
		return ( (Tablero) tableroP).getPorDefecto(x,y);
	}
	/**
	 * 	Comprobamos si el numero que deseamos poner en una casilla existente en este tablero es posible o no
	 * 
	 * 	@param num Numero que deseamos validar si su posici&oacute;n es valida
	 * 	@param row Posici&oacute;n fila
	 * 	@param col Posici&oacute;n columna
	 * 	@param box_size Tama&ntilde;o de la caja
	 * 	@return True si se puede poner el numero deseado en la posici&oacute;n definida, false en caso contrario
	 * 	@see Tablero#check(int, int, int, int)
	 */
	public boolean check(int num, int row, int col,int box_size) {
		return ((Tablero) tableroP).check(num,row,col,box_size);
	}
	/**
	 * 	Pide ayuda para obtener una soluci&oacute;n de una casilla vacia
	 * <p>
	 * 	Las casillas vacias son aquellas en las cuales su valor es 0
	 * </p>
	 * 	@param t El tablero a buscar un valor por defecto
	 */
	public boolean ayuda(Tablero t) {
		boolean b = true;
		for (int i = 0; i < t.getMida() && b; ++i) {
			for (int j = 0; j < t.getMida() && b; ++j) {
				int num = t.getValorTauler(i, j);
				if (num != 0) {
					if (num != t.getPosicionMatrizSolucion(i, j)) return false;
				}
			}
		}
		if (b) {
			boolean encontrado = false;
			while (!encontrado) {
				int randomi = (int) Math.floor(Math.random()*((t.getMida()-1)-0+1)+0);
				int randomj = (int) Math.floor(Math.random()*((t.getMida()-1)-0+1)+0);
				if (t.getValorTauler(randomi, randomj) == 0) {
					t.setValorTauler(randomi, randomj, t.getPosicionMatrizSolucion(randomi, randomj));
					t.setCasellaPor_defecto(randomi, randomj, true);
					encontrado = true;
				}
				
			}
		}
		return true;
	}
	/**
	 * Devuelve por consola los records que contiene el tablero
	 */
	public void escribirRecord() {
		System.out.println("Los records del tablero son:");
		MyEntry[] m = ((Tablero) tableroP).getRecord();
		for(int i=0; i<((Tablero) tableroP).getNrecords(); ++i){
			System.out.println(i+1 + " -> " + m[i].fst() + ": " + m[i].snd() + "s");
		}

	}
	/**
	 * Comprueba si un tablero esta acabado o no
	 * @return True si el tablero esta acabado, false en caso contrario
	 */
	public boolean isTerminada() {
		return terminada;
	}
	/**
	 * Define si la partida esta terminada o no
	 * @param terminada
	 */
	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}
	
	public void setPuntuacion(int punt) {
		puntuacion = punt;
	}
	public int getPuntuacion() {
		return puntuacion;
	}


}

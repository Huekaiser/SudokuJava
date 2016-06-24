package clasesPropias.Dominio.Clases;

import java.io.Serializable;

import clasesComunes.Tablero_comp;
/**
 * 	Clase extendida del tablero_comp para implementarla en un sudoku
 * 
 * 	@author Grup 45 Sudoku
 * 	@version 1.0
 * 	@since	13-11-2015 
 *	@see 	Tablero_comp
 */
public class Tablero extends Tablero_comp implements Serializable{
	/**
	 * Define el nivel de dificultad
	 * <p>
	 * 1: Facil
	 * 2: Normal
	 * 3: Dificil
	 * </p>
	 */
	private static final long serialVersionUID = 1L;
	private boolean solucionUnica;
	private Integer dificultad; // 1: facil, 2: medio, 3: dificil
	/**
	 * Contiene el tablero solucionado
	 */
	private int solucion[][];
	/**
	 * Contiene el record asociado al tablero
	 * @see Record
	 */
	private Record record;
	/**
	 * 	Constructora de la clase
	 *	
	 *	@param N El tama&ntilde;o del tablero que tendra el tablero.
	 *	@param dificultad Define el nivel de dificultad del tablero.
	 */
	public Tablero () {
		super();
		setSolucionUnica(true);
	}
	
	public Tablero(int n) {
		super(n);
		setSolucionUnica(true);
	}
	
	public Tablero (int N, int dificultad) {
		super(N);
		this.dificultad = dificultad;
		record= new Record();
		setSolucionUnica(true);
	}
	/**
	 * 	A&ntilde;ade una matriz de la cual esta contendra la soluci&oacute; del sudoku.
	 * 	@param matriz Matriz entera bidimensional que contiene la soluci&oacute;n del sudoku
	 */
	public void setMatrizSolucion(int[][] matriz) {
		this.solucion = matriz;
	}
	/**
	 * 	Sobreescribe los conjuntos de booleanos de una casilla por otros conjuntos de booleanos
	 * 	@param x	define la fila en que se posiciona la casilla
	 * 	@param y	define la columna en que se posiciona la casilla
	 * 	@param b	booleanos que identifican los valores
	 */
	public void setCandidatsTauler(int x, int y, boolean[] b) {
		tauler[x][y].setCandidatos(b);
	}
	/**
	 * 	Devuelve de la casilla definida, el valor de la soluci&oacute;n. 
	 * 	@param x	define la fila en que se posiciona la casilla
	 * 	@param y	define la columna en que se posiciona la casilla
	 * 	@return		el valor de la casilla solucionada
	 */
	public Integer getPosicionMatrizSolucion(int x, int y) {
		return solucion[x][y];
	}
	/**
	 * 	Define la dificultad
	 * 	@param dificultad	El nivel de dificultad
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
	/**
	 * 	Devuelve la dificultad
	 * 	@return Nivel de dificultad
	 */
	public Integer getDificultad() {
		return dificultad;
	}
	/**
	 * 	Devuelve en un vector el record del sistema
	 * 	@return Devuelve un vector que contiene los mejores 5 tiempos.
	 * 	@see Record
	 */
	public MyEntry[] getRecord() {
		return record.getClasificacion();
	}
	/**
	 * 	Define una entrada de record y la aplica en la clasificaci&oacute;n de record
	 * 
	 * 	@param usuario 	El usuario que ha realizado el tiempo
	 * 	@param tiempo	El tiempo que ha tardado
	 * 	@see MyEntry
	 * 	@see Record
	 */
	public void setTiempoRecord(String usuario, int tiempo) {
		MyEntry res = new MyEntry(usuario,tiempo);
		record.setClasificacion(res);
	}
	/**
	 * 	Escribe por el canal de salida estandar de java un tablero de sudokus
	 */
	public  void escribir() {
		for(int i=0; i<mida; ++i){
			if(mida==9){
				if(i==(int)Math.sqrt(mida) || i==Math.sqrt(mida)*2) System.out.println("---------+----------+--------");
			}
			else if(i==(int)Math.sqrt(mida) || i==(int) Math.sqrt(mida)*2 || i==(int) Math.sqrt(mida)*3){
				System.out.println("------------+-------------+-------------+-----------");
			}
			for(int j=0; j<mida; ++j){
				if(j==(int)Math.sqrt(mida) || j==2*(int)Math.sqrt(mida) || j==3*(int)Math.sqrt(mida) ) System.out.print("| ");
				if(tauler[i][j].getValor()>9) System.out.print(tauler[i][j].getValor() + " ");
				else System.out.print(tauler[i][j].getValor() + "  ");
			}
			System.out.println(" ");
		}
	System.out.println();	
	}
	/**
	 * 	Devuelve la cantidad de records que posee el tablero
	 * 	@return las entradas totales de records que tiene el tablero
	 * 	@see Record
	 */
	public int getNrecords(){
		return record.getNrecords();
	}
	/** 
	 * 	Define el numero de records que puede tener el tablero
	 * 
	 * 	@param n El numero de records que tiene el tablero
	 *	@see Record
	 */
	public void setNrecords(int n){
		record.setnrecords(n);
	}
	/**
	 * 	Escribe todos los candidatos que hay en cada casilla por el canal de salida estandar
	 */
	public  void escribirCandidatos() {
		int n=mida;
		for(int i=0; i<n; ++i){
			for(int j=0; j<n; ++j){
				boolean res[] = tauler[i][j].getCandidatos();	
				System.out.print("["+i+"]["+j+"]: ");
				for(int l = 1; l < n+1; ++l) {
					if (res[l] == true) System.out.print(l);
				}
				System.out.println("");
			}
		}
		
	}
	/**
	 * 	Define si una casilla tiene un valor definido por defecto o no
	 * 
	 * 	@param x Posici&oacute;n fila
	 * 	@param y Posici&oacute;n columna
	 * 	@return  True si su valor esta definido por defecto, false en caso contrario
	 */
	public boolean getPorDefecto(int x, int y)
    {
            return tauler[x][y].isPor_defecto();
    }
	/**
	 * 	Comprobamos si el numero que deseamos poner en una casilla existente en este tablero es posible o no
	 * 
	 * 	@param num Numero que deseamos validar si su posici&oacute;n es valida
	 * 	@param row Posici&oacute;n fila
	 * 	@param col Posici&oacute;n columna
	 * 	@param box_size Tama&ntilde;o de la caja
	 * 	@return True si se puede poner el numero deseado en la posici&oacute;n definida, false en caso contrario
	 */
	public boolean check(int num, int row, int col,int box_size) {
		int r = (row / box_size) * box_size;
		int c = (col / box_size) * box_size;
		int tam = box_size * box_size;
		
		for (int i = 0; i < tam; i++) {
			if (solucion[row][i] == num ||
					solucion[i][col] == num ||
					solucion[r + (i % box_size)][c + (i / box_size)] == num){
				return false;
			}
		}
		return true;
	}

	public boolean isSolucionUnica() {
		return solucionUnica;
	}

	public void setSolucionUnica(boolean solucionUnica) {
		this.solucionUnica = solucionUnica;
	}

}

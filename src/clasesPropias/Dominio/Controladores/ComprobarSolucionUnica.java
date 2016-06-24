package clasesPropias.Dominio.Controladores;

import clasesPropias.Dominio.Clases.Tablero;

/**
 * 	Comprueba un sudoku para comprobar si este posee soluci&oacute;n &uacute;nica
 * 
 * 	@author Jesus Marc
 * 	@version 1.0
 * 	@since 16-11-2015
 *
 */
public class ComprobarSolucionUnica extends Algoritmo{
	/**
	 * 	Tablero con soluci&oacute;n generada comenzando por arriba a la izquierda
	 */
	private static int[][] board;
	/**
	 * 	Tablero con soluci&oacute;n generada comenzando por abajo a la derecha
	 */
	private static int[][] board2;
	
	private static long time=0;
	/**
	*	Constructora por defecto
	 */
	public ComprobarSolucionUnica() {}
	/**
	 * 	Genera una soluci&oacute;n de sudoku y la coloca en la variable de board
	 * 
	 * 	@param t Tablero a resolver
	 */
	public boolean resolver(Tablero t) {
		int mida = t.getMida();
		boolean b=false;
		board = new int[mida][mida];
		for(int i = 0; i < mida; ++i) {
			for(int j = 0; j < mida; ++j) {
				board[i][j] = t.getValorTauler(i, j);
			}
		}

		int box_size = (int) Math.sqrt(mida);
		int random = (int) Math.floor(Math.random()*(2-1+1)+1);
		if (random == 1) {
			time= System.currentTimeMillis();
			b=guess2Resolver(mida-1,mida-1,box_size);
			t.setMatrizSolucion(board);
		}
		else {
			time= System.currentTimeMillis();
			b=guess(0,0,box_size);
			t.setMatrizSolucion(board);
		}
		return b;
	}
	/**
	 * 	De un tablero pasado, comprueba si este tiene una soluci&oacute;n unica
	 * 	@param	t	Tablero que se desea saber si su soluci&oacute;n es unica
	 * 	@return	True si es soluci&oacute;n unica, false en caso contrario
	 * 	@throws	Exception	Si sale del vector, entonces significa que lo ha recorrido todo sin ningun problema
	 */
	public boolean tieneSolucionUnica(Tablero t) throws Exception {
		int mida = t.getMida();
		
		board = new int[mida][mida];
		board2 = new int[mida][mida];
		for(int i = 0; i < mida; ++i) {
			for(int j = 0; j < mida; ++j) {
				board[i][j] = t.getValorTauler(i, j);
				board2[i][j] = t.getValorTauler(i, j);
			}
		}
		time= System.currentTimeMillis();
		int box_size = (int) Math.sqrt(mida);
		if(!guess(0, 0,box_size)) return false;
		time= System.currentTimeMillis();
		if(!guess2(mida-1,mida-1,box_size)) return false;
		return compararSoluciones(board, board2, mida);
	}
	
	public boolean solucionValida(Tablero t) {
		t.escribir();
		boolean b = true;
		for(int i = 0; i < t.getMida() && b; ++i) {
			for(int j = 0; j < t.getMida() && b; ++j) {
				if(!correcto(t.getValorTauler(i, j),i,j,(int)Math.sqrt(t.getMida()))) {
					
					b = false;
					//////////System.out.println("tiene algo mal!! "+ i +" - " + j);
				}
			}
		}
		return b;
	}
	/**
	 * Devuelve el tablero Board
	 * @return Un tablero 
	 */
	public int[][] getBoard() {
		return board;
	}
	
	private static boolean correcto(int num, int row, int col,int box_size) {
		////////////System.out.println("b");
		int r = (row / box_size) * box_size;
		int c = (col / box_size) * box_size;
		int tam = box_size * box_size;
		
		/*	if (((board[row][i] == num) && (i != col)) ||
					((board[i][col] == num) && i != row) ||
					((board[r + (i % box_size)][c + (i / box_size)] == num) && (r + (i % box_size) != row && c + (i / box_size) != col)));
				return false;
			
		}*/
		if(num!=0){
		for (int i = 0; i < box_size*box_size; i++) {
			if(i!=row) if(num==board[i][col]) return false;
		}
		for ( int i = 0; i < box_size*box_size; i++) {
			if(i!=col) if(num==board[row][i]) return false;
		}
		for (int i = row / box_size * box_size; i < row / box_size *box_size +box_size; i++) {
			for (int j = col / box_size * box_size; j < col/ box_size*box_size+box_size; j++) {
				if(!(i==row) || !(j==col)) if(num==board[i][j]) return false;
			}
		}
		}
		
		return true;
	}
	/**
	 * 	Comprueba el valor que queremos asignar a una casilla se pueda asignar en el tablero 1
	 * 
	 *	@param num 	Valor que queremos comprobar si se puede aplicar
	 *	@param row	Posici&oacute;n fila de la casilla
	 *	@param col	Posici&oacute;n columna de la casilla
	 *	@param box_size	Tama&ntilde;o de la region
	 *	@return True si se puede asignar, false en caso contrario
	 */
	private static boolean check(int num, int row, int col,int box_size) {
		////////////System.out.println("b");
		int r = (row / box_size) * box_size;
		int c = (col / box_size) * box_size;
		int tam = box_size * box_size;
		
		for (int i = 0; i < tam; i++) {
			if (board[row][i] == num ||
					board[i][col] == num ||
					board[r + (i % box_size)][c + (i / box_size)] == num){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 	Comprueba el valor que queremos asignar a una casilla se pueda asignar
	 * 
	 *	@param num 	Valor que queremos comprobar si se puede aplicar
	 *	@param row	Posici&oacute;n fila de la casilla
	 *	@param col	Posici&oacute;n columna de la casilla
	 *	@param box_size	Tama&ntilde;o de la region
	 *	@return True si se puede asignar, false en caso contrario
	 * 
	 */
	private static boolean check2(int num, int row, int col,int box_size) {
		int r = (row / box_size) * box_size;
		int c = (col / box_size) * box_size;
		int tam = box_size * box_size;
		
		for (int i = 0; i < tam; i++) {
			if (board2[row][i] == num ||
					board2[i][col] == num ||
					board2[r + (i % box_size)][c + (i / box_size)] == num){
				return false;
			}
		}
		return true;
	}
	/**
	 * 	Comprueba todos los candidatos posibles para una celda definida hasta que el tablero board este resuelto
	 * 
     * 	
     * 	@param row Posici&oacute;n fila.
     * 	@param col Posici&oacute;n columna.
     * 	@param	box_size	Tama&ntilde;o de la region del sudoku
     * 	@return	falso si no hay ningun numero valido que se encuentre en esta celda
	 */
	public static boolean guess(int row, int col,int box_size){
		int tam = box_size * box_size;
		int nextCol = (col + 1) % tam;
		int nextRow = row;
		if(System.currentTimeMillis()-time > 10000){
			//////////System.out.println("peto por tiempo");
			return false;
		}
		if(nextCol == 0) ++nextRow;
		try {
			if (board[row][col] != 0)
				return guess(nextRow, nextCol,box_size);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
		for (int i = 1; i <= tam; i++) {
			if (check(i, row, col, box_size)) {
				board[row][col] = i;
				if (guess(nextRow, nextCol, box_size)) {
					return true;
				}
			}
		}
		board[row][col] = 0;
		return false;
	}
	/**
	 * 	Comprueba todos los candidatos posibles para una celda definida hasta que el tablero board2 este resuelto
	 * 
     * 	
     * 	@param row Posici&oacute;n fila.
     * 	@param col Posici&oacute;n columna.
     * 	@param	box_size	Tama&ntilde;o de la region del sudoku
     * 	@return	falso si no hay ningun numero valido que se encuentre en esta celda
	 */
	public static boolean guess2(int row, int col,int box_size){
		int tam = box_size * box_size;
		int nextCol = (col - 1) % tam;
		int nextRow = row;
		if(System.currentTimeMillis()-time > 10000){
			//////////System.out.println("peto por tiempo");
			return false;
		}
		if(nextCol == -1  ){
			--nextRow;
			 nextCol=tam-1;
		}
		try {
			if (board2[row][col] != 0)
				return guess2(nextRow, nextCol,box_size);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}

		for (int i = tam; i >= 0; --i) {
			if (check2(i, row, col, box_size)) {
				board2[row][col] = i;
				if (guess2(nextRow, nextCol, box_size)) {
					return true;
				}
			}
		}
		board2[row][col] = 0;
		return false;
	}
	/**
	 * Misma funcion que
	 * 
     * 	@param row Posici&oacute;n fila.
     * 	@param col Posici&oacute;n columna.
     * 	@param	box_size	Tama&ntilde;o de la region del sudoku
	 * 	@return
	 */
	public static boolean guess2Resolver(int row, int col,int box_size){
		int tam = box_size * box_size;
		int nextCol = (col - 1) % tam;
		int nextRow = row;
		if(System.currentTimeMillis()-time > 10000){
			//////////System.out.println("peto por tiempo");
			return false;
		}
		if(nextCol == -1  ){
			--nextRow;
			 nextCol=tam-1;
		}
		try {
			if (board[row][col] != 0)
				return guess2Resolver(nextRow, nextCol,box_size);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}

		for (int i = tam; i >= 0; --i) {
			if (check(i, row, col, box_size)) {
				board[row][col] = i;
				if (guess2Resolver(nextRow, nextCol, box_size)) {
					return true;
				}
			}
		}
		board[row][col] = 0;
		return false;
	}
	/**
	 * 	Compara dos tableros para ver si son iguales 
     * 	
     * 	@param 	b1		Tablero a comparar 1
     * 	@param 	b2 		Tablero a comparar 1
     * 	@param	mida	Tama&ntilde;o m&aacute;ximo de las filas/columnas
     * 	@return	True si son iguales, false en caso contrario
   	 */
	private static boolean compararSoluciones(int[][] b1, int[][] b2,int mida) {
		boolean b = true;
		for(int i = 0; i < mida; ++i) {
			for(int j = 0; j < mida && b; ++j) {
				if(board[i][j] != board2[i][j]) b = false;
			}
		}
		return b;
	}
	
	public int getPosicionBoard(int i, int j) {
		return board[i][j];
	}
}

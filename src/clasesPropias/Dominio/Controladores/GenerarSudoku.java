package clasesPropias.Dominio.Controladores;

import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Dominio.Controladores.ComprobarSolucionUnica;

/**
 *  Extension de Algoritmo que permite la generaci&oacute;n de un sudoku valido
 * 
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 */
public class GenerarSudoku extends Algoritmo{
	private Tablero t1;
	/**
	 * Genera un tablero con unas caracterisiticas deseadas
	 * <p>
	 * La variable n tiene que ser una variable n valida para la clase Tablero
	 * </p>
	 * @param n	Tama&ntilde;o de filas/columnas asociadas
	 * @param dificultadBuscada Dificultad que se desea que tenga el sudoku
	 * @param casillas Casillas con soluciones por defecto que se desea que contengan
	 * @return	El tablero generado que se deseaba
	 * @see Tablero
	 * @throws Exception
	 */
	public boolean generar(int n, int dificultadBuscada,int casillas) throws Exception {
		//System.out.println("me pongo a resolver");
		t1 = new Tablero(n, dificultadBuscada);
		long time= System.currentTimeMillis();
		int dificultad = 0;
		boolean solucionUnica;
		boolean dosSoluciones= false;
		boolean encontrado = false;
		int randomi = 0;
		int randomj = 0;
		int cont = 0;
		int diff=0;
		int sudokus=0;
		t1= new Tablero(n, dificultadBuscada);
		insertarPrimeros(t1);
		if(!resolver(t1)){
			//System.out.println("ha fallado el solver");
        
			return false;
		}
		ComprobarDificultad cd;
		//t1.escribir();
		while(!encontrado ){
			if(System.currentTimeMillis()-time>20000) return false;
			++sudokus;
			Tablero t= new Tablero(n, dificultadBuscada);
			copiar(t,t1);
			cd= new ComprobarDificultad(t);
			cont=0;
			solucionUnica = true;
			while(solucionUnica && cont<casillas) {
				ComprobarSolucionUnica csu = new ComprobarSolucionUnica();
				randomi= (int) Math.floor(Math.random()*((n-1)-0+1)+0);
				randomj= (int) Math.floor(Math.random()*((n-1)-0+1)+0);
				if(t.getValorTauler(randomi, randomj) != 0) {
					t.setValorTauler(randomi, randomj, 0);
					++cont;
				}
				solucionUnica = csu.tieneSolucionUnica(t);
				if(!solucionUnica) dosSoluciones = true;
			}
			t.escribir();
			Tablero Auxiliar= new Tablero(n, dificultadBuscada);
			copiar(Auxiliar, t);
			eliminarCandidatos(t);
			diff=cd.getDificultad();
			if(diff == dificultadBuscada && solucionUnica){
				encontrado= true;
				for (int j = 0; j < t1.getMida(); j++) {
					for (int j2 = 0; j2 < t1.getMida(); j2++) {
						t1.setValorTauler(j, j2, Auxiliar.getValorTauler(j, j2));
					}
				}
			}
		}
		//if (encontrado)//System.out.println("encontrado");
		t1.setDificultad(dificultadBuscada);
		return true;
	}
		

	public Tablero getTablero(){
		return t1;
	}
	
	/**
	 * Copia un tablero a otro objeto tabler
	 * @param t		Tablero destino
	 * @param t1	Tablero origen
	 */
	public void copiar(Tablero t, Tablero t1) {
		int [][] board= new int[t1.getMida()][t.getMida()];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j]=t1.getPosicionMatrizSolucion(i, j);
			}
		}
		t.setMatrizSolucion(board);
		for (int j = 0; j < t1.getMida(); j++) {
			for (int j2 = 0; j2 < t1.getMida(); j2++) {
				t.setValorTauler(j, j2, t1.getValorTauler(j, j2));
			}
		}
	}
		/**
		 * Elimina todos los candidatos posibles del tablero que enten en conflicto
		 * 
		 * @param t Tablero que se desea eliminar candidatos
		 */
		public void eliminarCandidatos(Tablero t){
			for (int i = 0; i < t.getMida(); i++) {
				for (int j = 0; j < t.getMida(); j++) {
					if(t.getValorTauler(i, j)!= 0){
						for (int j2 = 1; j2 < t.getMida()+1; j2++) {
							t.esborrarCandidatTauler(i, j, j2);
						}
						eliminarCandidatos_i(t, i, j, t.getValorTauler(i, j));
					}
				}
			}
		}
		/**
		 * Eliminamos un valor candidato de las posiciones que entre en conflicto
		 * <p>
		 * Si en la misma region y fila/columna posee un candidato con el valor pasado, 
		 * ese candidato sera eliminado 
		 * </p>
		 * @param t	Tablero del candidato a eliminar
		 * @param i Posici&oacute;n fila
		 * @param j	Posici&oacute;n columna
		 * @param valor	Valor del candidato que se desea eliminar
		 */
		private void eliminarCandidatos_i(Tablero t,int i , int j, int valor) {
			int box_size= (int) Math.sqrt(t.getMida());
			for (int it = 0; it < t.getMida(); ++it)  t.esborrarCandidatTauler(i, it, valor);
			for (int it2 = 0; it2 < t.getMida(); ++it2) t.esborrarCandidatTauler(it2, j, valor);
			for (int it3 = i / box_size*box_size ; it3 < i/box_size*box_size+box_size; ++it3) {
				for(int it4 =j / box_size*box_size; it4 < j/box_size*box_size+box_size; ++it4) {
					t.esborrarCandidatTauler(it3, it4, valor);
				}
			}		
		}
		/**
		 * Devuelve por parametro un sudoku solucionado a partir de un sudoku a completar
		 * 
		 * @param t Tablero a solucionar
		 * @return 
		 * @throws Exception 
		 */
		public boolean resolver(Tablero t) throws Exception {
			ComprobarSolucionUnica csu = new ComprobarSolucionUnica();
			Algoritmo a = new Algoritmo();
			int mida = t.getMida();
			boolean b = csu.resolver(t);
			for(int i = 0; i < mida; ++i) {
				for(int j = 0; j < mida; ++j) {
					t.setValorTauler(i, j, t.getPosicionMatrizSolucion(i, j));
				}
			}
			return b;
		}
		/**
		 * 
		 * @param t
		 */
		private void insertarPrimeros(Tablero t) {
			int n = t.getMida();
			int limite=0;
			if(n==9) limite=3;
			else limite =1;
			int box_size = (int) Math.sqrt(n);
			int contador = 0;
			int random = 0;
			int randomi= 0;
			int randomj = 0;
			boolean puedo = true;
			random = (int) Math.floor(Math.random()*(n-1+1)+1);
			t.setValorTauler(0, 0, random);
			while(contador < limite) {
				randomi= (int) Math.floor(Math.random()*((n-1)-0+1)+0);
				randomj= (int) Math.floor(Math.random()*((n-1)-0+1)+0);
				random = (int) Math.floor(Math.random()*(n-1+1)+1);
				if(t.getValorTauler(randomi, randomj) == 0){
					puedo = true;
					for (int it = 0; it < n && puedo; ++it)  {
						if(random == t.getValorTauler(randomi, it)) {
							puedo = false;

						}
					}
					for (int it2 = 0; it2 < n && puedo; ++it2) {
						if(random == t.getValorTauler(it2, randomj)) {
							puedo = false;
						}
					}
					for (int it3 = randomi / box_size*box_size; it3 < randomi/box_size*3+box_size && puedo; ++it3) {
						for(int it4 = randomj / box_size*3; it4 < randomj/box_size*box_size+box_size && puedo; ++it4) {
							if(random == t.getValorTauler(it3, it4)) {
								puedo = false;
							}
						}
					}
					if (puedo) {
						t.setValorTauler(randomi, randomj, random);
						++contador;
					}
				}


			}
		}
	}

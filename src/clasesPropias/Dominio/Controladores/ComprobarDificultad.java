package clasesPropias.Dominio.Controladores;

import clasesPropias.Dominio.Clases.Tablero;

/**
 * 	Comprueba la dificultad asignada a un tablero
 * 	
 * 	@author Grup 45 Sudoku
 * 	@version 1.0
 * 	@since 13-11-2015
 */
public class ComprobarDificultad extends Algoritmo{
	private Algoritmo a;
	private NakedPairs np;
	private NakedTriples nt;
	private HiddenPairs hp;
	private HiddenSingles hs;
	private Tablero t;
	/**
	 * Asigna un tablero para comprobar su dificultad
	 * @param t1	Tablero a mirar la dificultad
	 */
	public ComprobarDificultad(Tablero t1) {
		t=t1;
		hs= new HiddenSingles(t);
		np= new NakedPairs();
		hp= new HiddenPairs();
	}
	/**
	 * Devuelve el valor de dificultad del tablero asignado
	 * <p> 1: F&aacute;cil 2: Medio 3: Dif&iacute;cil </p>
	 * @return El valor de dificultad
	 */
	public int getDificultad() {
		if(diff1(t)) return 1;
		if(diff2(t)) return 2;
		return 3;
	}
	/**
	 * Comprueba si la dificultad es de nivel 1
	 * 
	 * @param t Tablero a comprobar
	 * @return True si su dificultad es 1, false en caso contrario
	 */
	private boolean diff1(Tablero t) {
		while(a.recorrer(t) );// || hs.hidenSingles()
		if(solucionado()) return true;
		return false;
	}

	/**
	 * Comprueba si la dificultad es de nivel 2
	 * 
	 * @param t Tablero a comprobar
	 * @return True si su dificultad es 2, false en caso contrario
	 */
	private boolean diff2(Tablero t) {
		while(a.recorrer(t) || hs.hidenSingles()){
			//hp.hiddenPairs(t);
			//np.nakedPairs(t);
			//nt.nakedTriples(t);
		};
		if(solucionado()) return true;
		return false;
	}
	/**
	 * Comprueba si el tablero esta solucionado
	 * 
	 * @return True si esta solucionado, false en caso contrario
	 */
	private boolean solucionado() {
		for (int i = 0; i < t.getMida(); i++) {
			for (int j = 0; j < t.getMida(); j++) {
				//System.out.println("es diferente "+t.getPosicionMatrizSolucion(i, j)+t.getValorTauler(i, j));
				if(t.getPosicionMatrizSolucion(i, j)!= t.getValorTauler(i, j)) return false;
			}
		}
		return true;
	}

}

package clasesPropias.Dominio.Controladores;

import java.util.ArrayList;

import clasesPropias.Dominio.Clases.Tablero;
/**
 * 	Clase que define algoritmos sencillos para solventar un sudoku
 * 
 * 	@author Grup 45 Sudoku
 * 	@version 1.0
 * 	@since 13-11-2015
 *
 */
public class Algoritmo {
	/**
	 * Constructora por defecto
	 */
	public Algoritmo() {
	}

	/**
	 * 	Actualiza los posibles candidatos con las 
	 * 	coordenadas de la casilla definida con un valor determinado
	 * 
	 *  <p>
	 *  	Elimina los candidatos que se encuentren en la casilla posici&oacute;n
	 *  </p>
	 * 
	 *	@param 	i		posici&oacute;n fila
	 * 	@param 	j		posici&oacute;n columna
	 * 	@param 	valor	Valor candidato a eliminar
	 * 	@param 	t		Tablero a modificar
	 * 	@see 	Tablero
	 */
	public static void actualizarPosiblesValorEscrito(int i, int j, int valor, Tablero t) {
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
	 * 	Aplicamos la t&eacute;cnica de busqueda hiddenPairs para encontrar candidatos 
	 * 	a descartar
	 * 
	 * 	@param 	t	Tablero en el cual trabajamos
	 * 	@see 	Tablero 
	 */
	public static boolean recorrer(Tablero t) {
		boolean b=false;
				for(int i=0; i<t.getMida(); ++i){
					for(int j=0; j<t.getMida(); ++j){
						ArrayList<Integer> res = Candidatos(t.getCandidatsTauler(i, j));
						if(res.size()==1){
							b=true;
							t.setValorTauler(i, j, res.get(0));
							actualizarPosiblesValorEscrito(i, j, res.get(0), t);
						}


					}
				}
		return b;
	}
	/**
	 * Devuelve de un vector de candidatos una lista numerica de los cadidatos
	 * 
	 * @param candidatos Vector de candidatos que queremos pasar a lsta
	 * @return Lista de candidatos numericos
	 */
	protected static ArrayList<Integer> Candidatos(boolean[] candidatos) {
		ArrayList<Integer> aux= new ArrayList<>();
		for(int i=1; i<candidatos.length; ++i){
			if(candidatos[i]) aux.add(i);
		}
		return aux;

	}
}

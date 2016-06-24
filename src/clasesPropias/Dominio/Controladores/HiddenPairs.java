package clasesPropias.Dominio.Controladores;

import clasesPropias.Dominio.Clases.Tablero;

/**
 *  Aplicaci&oacute;n de la estrategia de soluci&oacute;n de sudokus Hidden Pairs
 * 
 *	@author Jesus
 *	@version 1.0
 *	@since 14-11-2015
 */
public class HiddenPairs {
	public boolean hiddenPairs(Tablero t) {
		boolean res[];
		boolean b1;
		boolean b2;
		boolean b3;
		boolean def = false;
		int n = t.getMida();
		for(int i=0; i<n; ++i){
			for(int j=0; j<n; ++j){
				if(t.getValorTauler(i, j) == 0){
					b1 =comprobarFila(t,i,j);
					b2 = comprobarColumna(t,i,j);
					b3 = comprobarRegion(t,i,j);
					if (b1 || b2 || b3) def = true;
				}
			}
		}
		return def;

	}

	private static boolean comprobarFila(Tablero t, int i, int j) {
		int n = t.getMida();
		boolean def = false;
		int cont = 0;
		int cont2 = t.getMida();
		for(int k = 0; k < n; ++k) {
			if (k != j && t.getValorTauler(i, k) == 0) {
				cont = 0;
				cont2 = t.getMida();
				boolean res[] = t.getCandidatsTauler(i, j);	//candidatos del que analizamos
				boolean aux[] = t.getCandidatsTauler(i, k); //candidatos de aquel con el que comparamos
				boolean aux2[] = new boolean[t.getMida()+1];			//candidatos de las casillas del ultimo recorrido
				for(int it = 1; it <= n; ++it) {
					if (res[it] && aux[it]) {
						aux2[it] = true;
					}
					else {
						aux2[it] = false;
						--cont2;
					}
				}
				if (cont2>=2) {

					for(int it2 = 1; it2 <= n; ++it2) {
						if(it2-1 != j && it2-1 != k) {
							boolean aux3[] = t.getCandidatsTauler(i, it2-1);
							for(int it3 = 1; it3 < n+1; ++it3) {
								if(aux2[it3] && aux3[it3]) aux2[it3] = false;
							}
						}
					}


					for(int last = 0; last < n; ++last) {
						if(aux2[last]) ++cont;
					}


					if(cont == 2) {
						def = true;
						System.out.println("");
						for (int deff = 1; deff < n+1; ++deff) {
							if (!aux2[deff])
								t.esborrarCandidatTauler(i, j, deff);
						}
						for (int deff = 1; deff < n+1; ++deff) {
							if (!aux2[deff])
								t.esborrarCandidatTauler(i, k, deff);
						}
					}
				}
			}

		}
		return def;
	}

	private static boolean comprobarColumna(Tablero t, int i, int j) {
		int n = t.getMida();
		int cont = 0;
		int cont2 = t.getMida();
		boolean def = false;
		for(int k = 0; k < n; ++k) {
			if (k != i && t.getValorTauler(k, j) == 0) {
				cont = 0;
				cont2 = t.getMida();
				boolean res[] = t.getCandidatsTauler(i, j);	//candidatos del que analizamos
				boolean aux[] = t.getCandidatsTauler(k, j); //candidatos de aquel con el que comparamos
				boolean aux2[] = new boolean[t.getMida()+1];			//candidatos de las casillas del ultimo recorrido
				for(int it = 1; it <= n; ++it) {
					if (res[it] && aux[it]) {
						aux2[it] = true;
					}
					else {
						aux2[it] = false;
						--cont2;
					}
				}


				if (cont2>=2) {

					for(int it2 = 1; it2 <= n; ++it2) {
						if(it2-1 != i && it2-1 != k) {
							boolean aux3[] = t.getCandidatsTauler(it2-1, j);

							for(int it3 = 1; it3 < n+1; ++it3) {
								if(aux2[it3] && aux3[it3]) aux2[it3] = false;
							}
						}
					}


					for(int last = 0; last < n; ++last) {
						if(aux2[last]) ++cont;
					}


					if(cont == 2) {
						def = true;
						for (int deff = 1; deff < n+1; ++deff) {
							if (!aux2[deff])
								t.esborrarCandidatTauler(i, j, deff);
						}
						for (int deff = 1; deff < n+1; ++deff) {
							if (!aux2[deff])
								t.esborrarCandidatTauler(k, j, deff);
						}
					}
				}
			}

		}
		return def;
	}

	private static boolean comprobarRegion(Tablero t, int i, int j) {
		int n = t.getMida();
		int box_size = (int) Math.sqrt(n);
		int cont = 0;
		int cont2 = t.getMida();
		boolean def = false;
		for(int k=i/box_size*box_size; k<i/box_size*box_size+box_size; ++k){
			for (int l=j/box_size*box_size; l < j/box_size*box_size+box_size; ++l) {
				if ((k != i || l != j) && t.getValorTauler(k, l) == 0) {
					cont = 0;
					cont2 = t.getMida();
					boolean res[] = t.getCandidatsTauler(i, j);	//candidatos del que analizamos
					boolean aux[] = t.getCandidatsTauler(k, l); //candidatos de aquel con el que comparamos
					boolean aux2[] = new boolean[t.getMida()+1];			//candidatos de las casillas del ultimo recorrido



					for(int it = 1; it <= n; ++it) {
						if (res[it] && aux[it]) {
							aux2[it] = true;
						}
						else {
							aux2[it] = false;
							--cont2;
						}
					}

					if (cont2>=2) {

						for(int it2=(i/box_size*box_size); it2< i/box_size*box_size+box_size; ++it2){
							for (int it22=(j/box_size*box_size); it22 < j/box_size*box_size+box_size; ++it22) {
								if((it2 == i && it22 == j || (it2 == k && it22 == l))) {
									int a = 0;
								}
								else{
									boolean aux3[] = t.getCandidatsTauler(it2, it22);
									for(int it3 = 1; it3 < n+1; ++it3) {
										if(aux2[it3] && aux3[it3]) aux2[it3] = false;
									}
								}
							}
						}

						for(int last = 0; last < n; ++last) {
							if(aux2[last]) ++cont;
						}


						if(cont == 2) {
							def = true;
							for (int deff = 1; deff < n+1; ++deff) {
								if (!aux2[deff])
									t.esborrarCandidatTauler(i, j, deff);
							}
							for (int deff = 1; deff < n+1; ++deff) {
								if (!aux2[deff])
									t.esborrarCandidatTauler(k, l, deff);
							}
						}
					}
				}

			}
		}
		return def;
	}
}

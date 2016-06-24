package clasesPropias.Dominio.Controladores;

import clasesPropias.Dominio.Clases.Tablero;

public class BoxLineReduction extends Algoritmo{
	
	public boolean boxLineReduction(Tablero t) {
		boolean b = false;
		int mida = t.getMida();
		int box_size = (int) Math.sqrt(mida);
		int it1 = 0;
		int it2 = 0;
		for(int i = 0; i < mida; ++i) {
			it1 = (i / mida) * mida;
			it2 = (i%mida) * mida;
			for(int j = it1; j < it1+box_size; ++j) {
				for(int k = it2; k < it2+box_size; ++k) {
					//for (int it10 = j; it10 < b)
					
					
					
				}
			}
		}
		
		
		
		
		
		
		return b;
	}

}

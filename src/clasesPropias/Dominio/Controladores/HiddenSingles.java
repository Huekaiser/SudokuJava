package clasesPropias.Dominio.Controladores;

import java.util.ArrayList;

import clasesPropias.Dominio.Clases.Tablero;
/**
 *  Aplicaci&oacute;n de la estrategia de soluci&oacute;n de sudokus Hidden Single
 * 
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 */
public class HiddenSingles extends Algoritmo {
	Tablero t;
	HiddenSingles(Tablero t1){
		t=t1;
	}
	public boolean hidenSingles(){
		boolean b=false;
		Integer[] n= new Integer[1];
		for(int i=0; i<t.getMida(); ++i){
			for(int j=0; j<t.getMida(); ++j){
				ArrayList<Integer> cand=Candidatos(t.getCandidatsTauler(i, j));
				if(!cand.isEmpty()){
					if(HsFila(i,j,n)) {
						t.setValorTauler(i, j, n[0]);
						eliminarCandidatos(i,j);
					//	System.out.println("HS en la casilla: ["+i+ "]["+j+ "] el numero " +n[0]);
						actualizarPosiblesValorEscrito(i, j, n[0], t);
						b=true;
					}

					if(HsColumna(i, j, n)){
						t.setValorTauler(i, j, n[0]);
						eliminarCandidatos(i,j);
					//	System.out.println("HS en la casilla: ["+i+ "]["+j+ "] el numero " +n[0]);
						actualizarPosiblesValorEscrito(i, j, n[0], t);
						b=true;
					}
					if(HsRegion(i, j, n)){
						t.setValorTauler(i, j, n[0]);
						eliminarCandidatos(i,j);
					//	System.out.println("HS en la casilla: ["+i+ "]["+j+ "] el numero " +n[0]);
						actualizarPosiblesValorEscrito(i, j, n[0], t);
						b=true;
					}
				}
			}
		}
		return b;
	}

	private void eliminarCandidatos(int i, int j) {
		for(int k=1; k<=t.getMida();++k){
			t.esborrarCandidatTauler(i, j, k);
		}
		
	}
	private boolean HsFila(int i, int j, Integer[] n) {
		boolean b=true;
		ArrayList<Integer> cand=Candidatos(t.getCandidatsTauler(i, j));
		//System.out.println(cand.size());
		for(int k=0; k<cand.size(); ++k){
			b=true;
			for(int l=0; l<t.getMida();++l){
				if(l!=j){
					ArrayList<Integer> aux=Candidatos(t.getCandidatsTauler(i, l));
					if(aux.contains(cand.get(k))){
						//	System.out.println("el candidato:" + cand.get(k)+ "esta en [" + i + "][" + l + "] con el numero: " +n[0]);
						b=false;
					}
				}
			}
			if(b){
				n[0]=cand.get(k);
				return b;
			}

		}
		return b;
	}
	
	private boolean HsColumna(int i, int j, Integer[] n) {
		boolean b=true;
		ArrayList<Integer> cand=Candidatos(t.getCandidatsTauler(i, j));
		//System.out.println(cand.size());
		for(int k=0; k<cand.size(); ++k){
			b=true;
			for(int l=0; l<t.getMida();++l){
				if(l!=i){
					ArrayList<Integer> aux=Candidatos(t.getCandidatsTauler(l, j));
					if(aux.contains(cand.get(k))){
						//	System.out.println("el candidato:" + cand.get(k)+ "esta en [" + i + "][" + l + "] con el numero: " +n[0]);
						b=false;
					}
				}
			}
			if(b){
				n[0]=cand.get(k);
				return b;
			}

		}
		return b;
	}

	private boolean HsRegion(int i, int j, Integer[] n) {
		boolean b=true;
		int box_size=(int) Math.sqrt(t.getMida());
		ArrayList<Integer> cand=Candidatos(t.getCandidatsTauler(i, j));
		//System.out.println(cand.size());
		for(int k=0; k<cand.size(); ++k){
			b=true;
			for(int l=i/box_size*box_size; l < i/ box_size*box_size+box_size; ++l){
				for(int m=j/box_size*box_size; m < j/ box_size*box_size+box_size;++m){
					if(!(l==i && m==j)){
						ArrayList<Integer> aux=Candidatos(t.getCandidatsTauler(l, m));
						if(aux.contains(cand.get(k))){
							//System.out.println("el candidato:" + cand.get(k)+ "esta en [" + i + "][" + l + "] con el numero: " +n[0]);
							b=false;
						}
					}
				}
			}
			if(b){
				n[0]=cand.get(k);
				return b;

			}

		}
		return b;
	}
}

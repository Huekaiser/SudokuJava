package clasesPropias.Dominio.Controladores;

import java.util.ArrayList;

import clasesPropias.Dominio.Clases.Tablero;
/**
 *  Aplicaci&oacute;n de la estrategia de soluci&oacute;n de sudokus Naked Pairs
 * 
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 */
public class NakedPairs extends Algoritmo{

	public boolean nakedPairs(Tablero t){
		boolean b=false;
		for(int i=0; i<t.getMida();++i){
			for(int j=0; j<t.getMida(); ++j){
				ArrayList<Integer> cand;
				boolean[] aux = t.getCandidatsTauler(i, j);
				cand=Candidatos(aux);
				Integer[] ij=new Integer[1];
				if(cand.size()==2){
					if(NpFila(t,i,j,cand,ij)){
						borrarNpF(cand, i,j,ij,t);
						b=true;
					}
					if(NpColumna(t,i,j,cand,ij)){
						b=true;
						borrarNpC(cand,i,j,ij,t);
					}
					ij=new Integer[2];
					if(NpRegion(t,i,j,cand,ij)){
						b=true;
						borrarNpR(cand, i, j, ij, t);
					}
				}
			}
		}
		return b;
	}

	private boolean NpRegion(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij) {
		boolean b=false;
		int box_size= (int) Math.sqrt(t.getMida());
		for(int k=i/box_size*box_size; k<i/box_size*box_size+box_size; ++k){
			for (int l=j/box_size*box_size; l < j/box_size*box_size+box_size; ++l){
				if(!(k==i && l==j)){ // solo entra si no es ella misma
					boolean[] aux = t.getCandidatsTauler(k, l);
					ArrayList<Integer> cand2=Candidatos(aux);
					if(cand1.equals(cand2)){
						ij[0]=k;
						ij[1]=l;
						b=true;
						break;
					}
				}
			}
		}
		return b;
		
	}

	private boolean NpColumna(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij) {
		boolean b=false;
		for(int k=0; k<t.getMida(); ++k){
			if(k!=i){
				boolean[] aux = t.getCandidatsTauler(k, j);
				ArrayList<Integer> cand2=Candidatos(aux);
				if(cand1.equals(cand2)){
					ij[0]=k;
					b=true;
					break;
				}
			}
		}
		return b;
		
	}

	private boolean NpFila(Tablero t, int i, int j,ArrayList<Integer> cand1, Integer[] ij) {
		boolean b=false;
		for(int k=0; k<t.getMida(); ++k){
			if(k!=j){
				boolean[] aux = t.getCandidatsTauler(i, k);
				ArrayList<Integer> cand2=Candidatos(aux);
				if(cand1.equals(cand2)){
					ij[0]=k;
					b=true;
					break;
				}
			}
		}
		return b;
	}

	private void borrarNpF(ArrayList<Integer> cand1, int i, int j, Integer[] ij,Tablero t) {
		for(int k=0; k<t.getMida(); ++k){
			if(k!=j && k!= ij[0] ){
				t.esborrarCandidatTauler(i, k, cand1.get(0));
				t.esborrarCandidatTauler(i, k, cand1.get(1));
			}
		}
		
	}

	private void borrarNpC(ArrayList<Integer> cand1, int i, int j,Integer[] ij, Tablero t) {
		for(int k=0; k<t.getMida(); ++k){
			if(k!=i && k!=ij[0]){
				t.esborrarCandidatTauler(k, j, cand1.get(0));
				t.esborrarCandidatTauler(k, j, cand1.get(1));
			}
		}
		
	}

	private void borrarNpR(ArrayList<Integer> cand1, int i, int j, Integer[] ij, Tablero t) {
		int box_size= (int) Math.sqrt(t.getMida());
		for(int k=i/box_size*box_size; k<i/box_size*box_size+box_size; ++k){
			for (int l=j/box_size*box_size; l < j/box_size*box_size+box_size; ++l){
				//System.out.println("Estoy en [" +i + "][" + j+ "] mi nakedPair es: "+ ij[0] + " " + ij[1] +"boramos: " + cand1.get(0)+ " y "+ cand1.get(1));
				if(!((k==i && l==j) || (k==ij[0] && l==ij[1]))){
				//	System.out.println("Me toca borrar: "+ cand1.get(0)+ " y "+ cand1.get(1) + " de "+ k + " " + l);
					t.esborrarCandidatTauler(k, l, cand1.get(0));
					t.esborrarCandidatTauler(k, l, cand1.get(1));
				}
			}
		}
	}
	
	
}

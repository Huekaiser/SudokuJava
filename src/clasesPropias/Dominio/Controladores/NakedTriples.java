package clasesPropias.Dominio.Controladores;

import java.util.ArrayList;

import clasesPropias.Dominio.Clases.Tablero;
/**
 *  Aplicaci&oacute;n de la estrategia de soluci&oacute;n de sudokus NakedTriple
 * 
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 */
public class NakedTriples extends Algoritmo {
	NakedTriples(){}

	public static boolean nakedTriples(Tablero t){
		boolean b= false;
		for(int i=0; i<t.getMida();++i){
			for(int j=0; j<t.getMida(); ++j){
				ArrayList<Integer> cand;
				boolean[] aux = t.getCandidatsTauler(i, j);
				cand=Candidatos(aux);
				Integer[] ij1=new Integer[1];
				Integer[] ij2=new Integer[1];
				if(cand.size()==3 || cand.size()==2){
					if(NtFila(t,i,j,cand,ij1,ij2)){
						//System.out.println("He encontrado un NT: ["+i+"]["+j+"] "+"["+i+"]["+ij1[0]+"] "+"["+i+"]["+ij2[0]+"] ");
						borrarNtF(cand, i,j,ij1,ij2,t);
						b=true;
					}
					if(NtColumna(t,i,j,cand,ij1,ij2)){
						//System.out.println("He encontrado un NT: ["+i+"]["+j+"] "+"["+ij1[0]+"]["+j+"] "+"["+ij2[0]+"]["+j+"] ");
						borrarNtC(cand,i,j,ij1,ij2,t);
						b=true;
					}
					ij1=new Integer[2];
					ij2= new Integer[2];
					if(NtRegion(t,i,j,cand,ij1,ij2)){
						//System.out.println("He encontrado un NT: ["+i+"]["+j+"] "+"["+ij1[0]+"]["+ij1[1]+"] "+"["+ij2[0]+"]["+ij2[1]+"] ");
						borrarNtR(cand, i, j, ij1,ij2, t);
						b=true;
					}
				}
			}
		}
		return b;
	}

	private static boolean NtFila(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij1, Integer[] ij2) {
		boolean b=false;
		for(int k=0; k<t.getMida(); ++k){
			if(k!=j){
				boolean[] aux = t.getCandidatsTauler(i, k);
				ArrayList<Integer> cand2=Candidatos(aux);
				if(cand1.size()==3){
					if(cand1.containsAll(cand2) && !cand2.isEmpty()){
						ij1[0]=k;
						if(NtFila_i(t,i,j,cand1,ij1,ij2)){
							b=true;
							break;
						}
					}
				}
				else if(cand2.containsAll(cand1) && cand2.size()==3){
					ij1[0]=k;
					cand1.clear();
					cand1.addAll(cand2);
					if(NtFila_i(t,i,j,cand1,ij1,ij2)){
						b=true;
						break;
					}
				}
				else if(cand2.size()==2){// dos candidatos
					//System.out.println(cand2.get(0) + " " +cand2.get(1) + "esta contenido en:"+ cand1.get(0)+" " + cand1.get(1));
					if(cand1.containsAll(cand2)){
						//	System.out.println("SI");
						ij1[0]=k;
						cand1.addAll(cand2);
						if(NtFila_i(t,i,j,cand1,ij1,ij2)){
							b=true;
							break;
						}
					}
				}
			}
		}
		return b;
	}

	private static boolean NtFila_i(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij1, Integer[] ij2) {
		boolean b=false;
		for(int k=0; k<t.getMida(); ++k){
			if(k!=j && k!=ij1[0]){
				if(cand1.size()>2){
					boolean[] aux = t.getCandidatsTauler(i, k);
					ArrayList<Integer> cand2=Candidatos(aux);
					if(cand1.containsAll(cand2) && !cand2.isEmpty()){
						ij2[0]=k;
						b=true;
						break;
					}
				}
				else{
					boolean[] aux = t.getCandidatsTauler(i, k);
					ArrayList<Integer> cand2=Candidatos(aux);
					if(cand2.containsAll(cand1) && cand2.size()==3){
						cand1=cand2;
						ij2[0]=k;
						b=true;
						break;
					}
				}
			}
		}
		return b;
	}

	private static void borrarNtR(ArrayList<Integer> cand1, int i, int j, Integer[] ij1, Integer[] ij2, Tablero t) {
		int box_size= (int) Math.sqrt(t.getMida());
		//System.out.print("borar las casillas: ");
		for(int k=i/box_size*box_size; k<i/box_size*box_size+box_size; ++k){
			for (int l=j/box_size*box_size; l < j/box_size*box_size+box_size; ++l){
				//System.out.println("borar los candidadtos ");
				if(!((k==i && l==j)) && !(k==ij1[0] && l==ij1[1]) && !(k==ij2[0] && l==ij2[1])){
					//System.out.print("["+k+"]["+l+"] ");
					for(int r=0; r<cand1.size(); ++r)
						t.esborrarCandidatTauler(k, l, cand1.get(r));
				}
			}
		}
		//System.out.println();
	}

	private static boolean NtRegion(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij1,Integer[] ij2) {
		int box_size=(int) Math.sqrt(t.getMida());
		boolean b=false;
		for(int k=i/box_size*box_size; k<i/box_size*box_size+box_size; ++k){
			for (int l=j/box_size*box_size; l < j/box_size*box_size+box_size; ++l){
				if(!(k==i && l==j)){
					boolean[] aux = t.getCandidatsTauler(k, l);
					ArrayList<Integer> cand2=Candidatos(aux);
					if(cand1.size()==3){
						if(cand1.containsAll(cand2) && !cand2.isEmpty()){
							ij1[0]=k;
							ij1[1]=l;
							if(NtRegion_i(t,i,j,cand1,ij1,ij2)){
								b=true;
								break;
							}
						}
					}
					else if(cand2.containsAll(cand1) && cand2.size()==3){
						ij1[0]=k;
						ij1[1]=l;
						cand1.clear();
						cand1.addAll(cand2);
						if(NtRegion_i(t,i,j,cand1,ij1,ij2)){
							b=true;
							break;
						}
					}
					else if(cand2.size()==2){// dos candidatos
						//System.out.println(cand2.get(0) + " " +cand2.get(1) + "esta contenido en:"+ cand1.get(0)+" " + cand1.get(1));
						if(cand1.containsAll(cand2)){
							//	System.out.println("SI");
							ij1[0]=k;
							ij1[1]=l;
							cand1.addAll(cand2);
							if(NtRegion_i(t,i,j,cand1,ij1,ij2)){
								b=true;
								break;
							}
						}
					}
				}
			}
		}
		return b;
	}

	private static boolean NtRegion_i(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij1,Integer[] ij2) {
		boolean b= false;
		int box_size=(int) Math.sqrt(t.getMida());
		for(int k=i/box_size*box_size; k<i/box_size*box_size+box_size; ++k){
			for (int l=j/box_size*box_size; l < j/box_size*box_size+box_size; ++l){
				if(!(k==i && l==j) &&!(k==ij1[0] && l==ij1[1]) ){
					if(cand1.size()>2){
						boolean[] aux = t.getCandidatsTauler(k, l);
						ArrayList<Integer> cand2=Candidatos(aux);
						if(cand1.containsAll(cand2) && !cand2.isEmpty()){
							ij2[0]=k;
							ij2[1]=l;
							b=true;
							break;
						}
					}
					else{
						boolean[] aux = t.getCandidatsTauler(i, k);
						ArrayList<Integer> cand2=Candidatos(aux);
						if(cand2.containsAll(cand1) && cand2.size()==3){
							cand1=cand2;
							ij2[0]=k;
							ij2[1]=l;
							b=true;
							break;
						}
					}
				}
			}
		}
		return b;
	}

	private static boolean NtColumna(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij1, Integer[] ij2) {
		boolean b=false;
		for(int k=0; k<t.getMida(); ++k){
			if(k!=i){
				boolean[] aux = t.getCandidatsTauler(k, j);
				ArrayList<Integer> cand2=Candidatos(aux);
				if(cand1.size()==3){
					if(cand1.containsAll(cand2) && !cand2.isEmpty()){
						ij1[0]=k;
						if(NtColumna_i(t,i,j,cand1,ij1,ij2)){
							b=true;
							break;
						}
					}
				}
				else if(cand2.containsAll(cand1) && cand2.size()==3){
					ij1[0]=k;
					cand1.clear();
					cand1.addAll(cand2);
					if(NtColumna_i(t,i,j,cand1,ij1,ij2)){
						b=true;
						break;
					}
				}
				else if(cand2.size()==2){// dos candidatos
					//System.out.println(cand2.get(0) + " " +cand2.get(1) + "esta contenido en:"+ cand1.get(0)+" " + cand1.get(1));
					if(cand1.containsAll(cand2)){
						//	System.out.println("SI");
						ij1[0]=k;
						cand1.addAll(cand2);
						if(NtColumna_i(t,i,j,cand1,ij1,ij2)){
							b=true;
							break;
						}
					}
				}
			}
		}
		return b;
	}

	private static boolean NtColumna_i(Tablero t, int i, int j, ArrayList<Integer> cand1, Integer[] ij1, Integer[] ij2) {
		boolean b=false;
		for(int k=0; k<t.getMida(); ++k){
			if(k!=i && k!=ij1[0]){
				if(cand1.size()>2){
					boolean[] aux = t.getCandidatsTauler(k, j);
					ArrayList<Integer> cand2=Candidatos(aux);
					if(cand1.containsAll(cand2) && !cand2.isEmpty()){
						ij2[0]=k;
						b=true;
						break;
					}
				}
				else{
					boolean[] aux = t.getCandidatsTauler(k, j);
					ArrayList<Integer> cand2=Candidatos(aux);
					if(cand2.containsAll(cand1) && cand2.size()==3){
						cand1=cand2;
						ij2[0]=k;
						b=true;
						break;
					}
				}
			}
		}
		return b;
	}

	private static void borrarNtC(ArrayList<Integer> cand, int i, int j, Integer[] ij1, Integer[] ij2, Tablero t) {
		// TODO Auto-generated method stub
		for(int k=0; k<t.getMida(); ++k){
			if(k!=i && k!= ij1[0] && k!=ij2[0]){
				for(int r=0; r<cand.size(); ++r)
					t.esborrarCandidatTauler(k, j, cand.get(r));
			}
		}	
	}

	private static void borrarNtF(ArrayList<Integer> cand, int i, int j, Integer[] ij1, Integer[] ij2, Tablero t) {
		//System.out.println(cand.size());
		//System.out.println("borar en linea "+ i +"los candidadtos " + cand.get(0)+cand.get(1)+cand.get(2)+ " menos posicion"+j+ij1[0]+ij2[0]);
		for(int k=0; k<t.getMida(); ++k){
			if(k!=j && k!= ij1[0] && k!=ij2[0]){
				for(int r=0; r<cand.size(); ++r)
					t.esborrarCandidatTauler(i, k, cand.get(r));
			}
		}	
	}		
}

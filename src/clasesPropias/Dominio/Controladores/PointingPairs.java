package clasesPropias.Dominio.Controladores;

import java.util.ArrayList;

import clasesPropias.Dominio.Clases.Tablero;
 
public class PointingPairs extends Algoritmo {
 
        private Tablero t;
 
        public PointingPairs(Tablero tp){
                t=tp;
        }
 
        public void pointingPairs(){
                for(int i=0; i<t.getMida();++i){
                        for(int j=0; j<t.getMida(); ++j){
                                ArrayList<Integer> cand;
                                boolean[] aux = t.getCandidatsTauler(i, j);
                                cand=Candidatos(aux);
                                Integer[] ij=new Integer[1];
                                for (int k = 0; k < cand.size(); k++) {
                                        //if(ppFila(i,j,cand.get(k),ij)){
                                                //System.out.println("PP entre ["+i+"]["+j+"] y ["+i+"]["+ij[0]+"] el cand: " +cand.get(k));
                                                //borrarF(cand.get(k), i,j,ij);
                                        //}
                                        if(ppColumna(i,j,cand.get(k),ij)){
                                        System.out.println("PP entre ["+i+"]["+j+"] y ["+ij[0]+"]["+j+"] el cand: " +cand.get(k));
                                                borrarC(cand.get(k),i,j,ij);
                                        }
                                }
                        }
                }
        }
 
        private void borrarF(Integer cand, int i, int j, Integer[] ij) {
                for (int k = 0; k < t.getMida(); k++) {
                        if(k!=ij[0] && k!=j){
                                t.esborrarCandidatTauler(i, k, cand);
                        }
                }
        }
 
        private void borrarC(Integer cand, int i, int j, Integer[] ij) {
                for (int k = 0; k < t.getMida(); k++) {
                        if(k!=ij[0] && k!=i){
                                t.esborrarCandidatTauler(k, j, cand);
                        }
                }
        }
 
        private boolean ppColumna( int i, int j, Integer cand, Integer[] ij) {
                boolean b=false;
                int cont=0;
                int box_size = (int) Math.sqrt(t.getMida());
                for(int k=i+1; k <i/box_size*box_size+box_size;++k){
                        ArrayList<Integer> cand2 = Candidatos(t.getCandidatsTauler(k, j));
                                if(cand2.contains(cand)){
                                        ++cont;
                                        ij[0]=k;
                                }
                }
                if(cont==1){
                        for(int l=i/box_size*box_size; l<i/box_size*box_size+box_size;++l){
                                for (int k = j/box_size*box_size; k < j/box_size*box_size+box_size; ++k) {
                                        ArrayList<Integer> cand2 = Candidatos(t.getCandidatsTauler(l, k));
                                        if(cand2.contains(cand)){
                                                ++cont;
                                        }
                                }
                        }
                }
                if(cont==3) b=true;
                return b;
        }
 
        private boolean ppFila(int i, int j, Integer cand, Integer[] ij) {
                boolean b=false;
                int cont=0;
                int box_size = (int) Math.sqrt(t.getMida());
                for(int k=j+1; k <j/box_size*box_size+box_size;++k){
                        ArrayList<Integer> cand2 = Candidatos(t.getCandidatsTauler(i, k));
                                if(cand2.contains(cand)){
                                        ++cont;
                                        ij[0]=k;
                                }
                }
                if(cont==1){
                        System.out.println("posible pp "+ i + " " + j + " cand: " + ij[0]);
                        for(int l=i/box_size*box_size; l<i/box_size*box_size+box_size;++l){
                                for (int k = j/box_size*box_size; k < j/box_size*box_size+box_size; ++k) {
                                        ArrayList<Integer> cand2 = Candidatos(t.getCandidatsTauler(l, k));
                                        if(cand2.contains(cand)){
                                                ++cont;
                                        }
                                }
                        }
                }
                if(cont==3) b=true;
                return b;
        }
 
}
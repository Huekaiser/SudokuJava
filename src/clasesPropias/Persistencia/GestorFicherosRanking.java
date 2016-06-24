package clasesPropias.Persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import clasesPropias.Dominio.Clases.MyEntry;
import clasesPropias.Dominio.Clases.Ranking;



public class GestorFicherosRanking extends GestorFicheros<Ranking>{
	
    private int estaRanking(String m, ArrayList<MyEntry> r) {
    	int pos = -1;
		for (int i = 0; i < r.size(); ++i) {
			if (r.get(i).fst().equals(m)) {
				pos = i;
				return pos;
			}
		}
		return pos;
	}
    
	public void actualizarRanking(MyEntry m) throws IOException, ClassNotFoundException {
		Ranking ra = new Ranking();
		ArrayList<MyEntry> r = new ArrayList<MyEntry>();
		r.add(m);
		File f = new File(dataRanking);
        if (f.length() == 0) {
        	 ra.setRankings(r);
        	 escribir(ra);
        }
        else {
        	ra = (Ranking) leer(ra);
        if (ra != null){
        	//System.out.println("leo algo qe ay valor");
	        r = ra.getRankings();
	        int pos = estaRanking(m.fst(), r);
	        if (pos != -1) {
	        	Integer punt_r = r.get(pos).snd();
	        	m.setComponent2(punt_r + m.snd()); 
	        	r.remove(pos);
	        	r.add(m);
	        }
	        else r.add(m);
	        ra.ordenar(r);
	        ra.setRankings(r);
		}
        //System.out.println("escribo ranking");
        escribir(ra);
        //System.out.println("lo he escrito");
	}
	}
	
	void borrarRanking(String m) throws IOException, ClassNotFoundException {

		File archivo = new File(dataRanking); 
		Ranking ra = new Ranking();
        ra = (Ranking) leer(ra);
        if (ra != null){
	        ArrayList<MyEntry> r = ra.getRankings();
	        int pos = estaRanking(m, r);
	        if (pos != -1) {
	        	r.remove(pos);
	        	//System.out.println("lo encuentrooo");
	        }
	        ra.ordenar(r);
	        ra.setRankings(r);
	        escribir(ra);
		}
		
	}

	public Ranking leer(Ranking ra) throws ClassNotFoundException, IOException {
		f = new File(dataRanking);
		return (Ranking) super.leer(ra);
	}
	
	public void escribir(Ranking ra) throws ClassNotFoundException, IOException {
		f = new File(dataRanking);
		super.escribir(ra);
	}
	
}

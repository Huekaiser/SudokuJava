package clasesPropias.Presentacion.Controladores;

import java.io.IOException;
import java.util.ArrayList;

import clasesPropias.Dominio.Clases.MyEntry;
import clasesPropias.Dominio.Clases.Ranking;
public class ControladorRanking {
		public ArrayList<ArrayList<String>> getRanking() throws IOException, ClassNotFoundException{
			clasesPropias.Dominio.Controladores.ControladorRanking ca = new clasesPropias.Dominio.Controladores.ControladorRanking();
			Ranking ra = ca.getRanking();
			
			
			ArrayList<MyEntry> m = new ArrayList<MyEntry>();
			m = ra.getRankings();
			ArrayList<ArrayList<String>> newv = new ArrayList<ArrayList<String>>();
			
			for (int i = 0; i < m.size(); ++i) {
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(Integer.toString(i+1));
				aux.add(m.get(i).fst());
				aux.add(Integer.toString(m.get(i).snd()));
				newv.add(aux);
			}
			return newv;
		}
		/*public Ranking getRanking() throws ClassNotFoundException, IOException {
		cf = new GestorFicherosRanking();
		r = cf.leer(r);
		return r;
	}*/
}

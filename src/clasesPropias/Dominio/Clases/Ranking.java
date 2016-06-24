package clasesPropias.Dominio.Clases;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import clasesPropias.Persistencia.GestorFicherosRanking;
/**
 * 	Contiene el ranking de los jugadores
 * 
 *	@author Jesus
 *	@version 1.0
 *	@since 14-11-2015
 */
public class Ranking  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Arraylist que contiene la lista de entradas
	 */
	private ArrayList<MyEntry> r; 
	/**
	*	Constructora por defecto
	*/
	public Ranking(){
		r = new ArrayList<MyEntry>();
	}
	
	/**
	*	Devuelve las entrada de ranking
	*	@return Un listado de los rankings que contiene el objeto
	*	@see MyEntry
	*/
    public ArrayList<MyEntry> getRankings(){
    	return r;
    }
	/**
	*	Devuelve las entrada de ranking 
	*	@see MyEntry
	*/
    public void setRankings(ArrayList<MyEntry> ra){
    	r = ra;
    }
	/**
	*	Ordena el vector pasado por parametro segun su entrada de tiempo
	*	@see MyEntry
	*/
	public void ordenar(ArrayList<MyEntry> r) {
		for (int i = 0; i < r.size(); ++i) {
			for (int j = i+1; j < r.size(); ++j) {
				if (r.get(j).snd() > r.get(i).snd()) {
					MyEntry aux = new MyEntry();
					aux = r.get(j);
					r.set(j, r.get(i));
					r.set(i, aux);
				}
			}
		}
	}
	/**
	*	Devuelve por consola el listado de rankings existentes en este objeto
	*	@throws IOException
	 * @throws ClassNotFoundException 
	*/
	public void escribirRanking() throws IOException, ClassNotFoundException {
		GestorFicherosRanking cf = new GestorFicherosRanking();
		Ranking ranking1 = new Ranking();
		ranking1 = (Ranking)cf.leer(ranking1);
		ArrayList<MyEntry> ranking = ranking1.getRankings();
		for(int i = 0; i < ranking.size(); ++i) {
			System.out.print(ranking.get(i).fst()+ ":  ");
			System.out.println(ranking.get(i).snd());
		}
	}
	
}

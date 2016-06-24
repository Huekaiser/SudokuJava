package clasesPropias.Dominio.Controladores;

import java.io.IOException;

import clasesPropias.Dominio.Clases.Ranking;
import clasesPropias.Persistencia.GestorFicherosRanking;
/**
* 
*	@author Jorge
*	@version 1.0
*	@since 15-11-2015
*/
public class ControladorRanking {
	/**
	 * 	Ranking Usuario
	 */
	private Ranking r;
	/**
	 * Controladora para acceder al fichero de ranking
	 */
	private GestorFicherosRanking cf;
	/**
	 * Constructora por defecto. 
	 * <p>
	 * 	Escribe el ranking
	 * </p>
	 * @throws IOException
	 */
	public ControladorRanking() throws IOException{ }
	
	public Ranking getRanking() throws ClassNotFoundException, IOException {
		cf = new GestorFicherosRanking();
		r = cf.leer(r);
		return r;
	}
	
}

package clasesPropias.Dominio.Clases;

import java.io.Serializable;

/**
 * 	Contiene las entradas de un record
 * 	<p>
 * 	Como m&aacute;ximo solamente puede tener 5 entradas
 * 	</p>
 *	@author Grup 45 Sudoku: Jesus
 *	@version 1.0
 *	@since 21-10-2015
 */
public class Record implements Serializable {
	/**
	 *	Vector que contiene los records asociadas a un tablero
	 *	@see MyEntry
	 */
	private static final long serialVersionUID = 1L;
    private MyEntry[] clasificacion;

    /**
     * 	Numero de records que contiene la clase
     */
    private int nrecords;

    /**
     * Constructora por defecto
     */
    public Record(){
    	this.nrecords = 0;
    	clasificacion = new MyEntry[5];
    	for(int i=0; i<5; ++i){
    		clasificacion[i]=new MyEntry();
    	}
    }
    /**
     * 	Constructora con parametros
     * 
     * 	@param 	clasif	Vector de entradas que contiene las entradas asociadas al tablero
     * 	@param 	nrecords	Numero total de records que contendra la clase.
     * 	@see 	MyEntry
     */
    public Record(MyEntry clasif[], int nrecords){
        this.clasificacion = clasif;
        this.nrecords = nrecords;
    }
    /**
     * 	A&ntilde;ade una nueva entrada al listado de records
     * 	<p>
     * 		Si el m&aacute;ximo de records asignados es menor que 5 se a&ntilde;adira
     * 		un nuevo record, en caso contrario, si este tiene un mejor tiempo que el 
     * 		&uacute;ltimo, este sera sustituido por la nueva entrada
     * 	</p>
     * 	@param 	clasif 	Numero de records que contiene la clase
     * 	@see 	MyEntry
     */
    public void setClasificacion(MyEntry clasif) {
    	if (nrecords < 5) {
    		clasificacion[nrecords]=clasif;
    		++nrecords;
    		sort_i(clasificacion,nrecords);
    	}
    	else {
    		if (clasif.snd() < clasificacion[4].snd()) {
    			clasificacion[4]=clasif;
    			sort_i(clasificacion, nrecords);
    		}
    	}
    	
    }
    /**
     * 	Devuelve un valor int que contiene el numero de records que contiene la clase
     * 	@return Numero de records que contiene la clase
     */
    public int getNrecords(){
    	return nrecords;
    }
    /**
     * 	Define el total de records que contiene esta clase
     * 
     * 	@param n Numero total de records que contendra la clase.
     */

    public void setnrecords(int n){
    	nrecords = n;
    }
    /**
     * 	Devuelve un vector que contiene los mejores tiempos.
     * 	<p>
     * 	Estan ordenados de forma ascendiente por su tiempo.
     * 	</p>
     * 	@return Vector que contiene los mejores tiempos
     * 	@see MyEntry
     */
    public MyEntry[] getClasificacion() {
    	return clasificacion;
    }
   
    
    /**
     * 	Ordena los registros pasados como parametros
     * 
     * 	@param M		Vector que se desea ordenar
     * 	@param nrecords Numero de entradas que seran ordenadas
     */
	private static void sort_i(MyEntry[] m, int nrecords){
    	boolean fin=true;
    	int i=nrecords-1;
    	while(fin && i>0){
    		if(m[i].snd()<m[i-1].snd()) {
    			swap(m[i],m[i-1]);
    			--i;
    		}
    		else fin=false;
    	}
    }
	/**
	 * 	Intercambia los datos de las dos entradas pasadas
	 * 
	 * 	@param a	Entrada que se desea intercambiar
	 * 	@param b	Entrada que se desea intercambiar
	 * 	@see MyEntry
	 */
    private static void swap(MyEntry a, MyEntry b){
    	int auxI=a.snd();
    	String auxS= a.fst();
    	a.setComponent1(b.fst());
    	a.setComponent2(b.snd());
    	b.setComponent1(auxS);
    	b.setComponent2(auxI);
 
    }
    
}

package clasesPropias.Dominio.Clases;

import java.io.Serializable;

/**
 *  Entradas de la clase Ranking
 * 
 *	@author Grup 45 Sudoku
 *	@version 1.0
 *	@since 14-11-2015
 *	@see Ranking
 */
public class MyEntry implements Serializable {
	/**
	 * Componente de la entrada que contiene el nombre del usuario
	 */
	private static final long serialVersionUID = 1L;
    private String component1;
    /**
     * Componente de la entrada que contiene la puntuaci&oacute;n total
     */
    private Integer component2;
    /**
     * Constructora por defecto
     */
    public MyEntry() {
    }
    /**
     * Constructora con parametro
     * @param component1	Nombre de usuario a asignar a este objeto
     * @param component2	Puntuaci&oacute;n actual a asignar a este objeto
     */
    public MyEntry(String component1, Integer component2) {
            this.component1 = component1;
            this.component2 = component2;
    }
    /**
     * 	Devuelve una cadena que contiene el nombre de usuari
     * @return	Devuelve una cadena que contiene el nombre de usuario
     */
    public String fst() {
            return component1;
    }
    /**
     * Asigna un nuevo valor para el parametro nombre de la entrada proporcionada
     * @param component1	Nombre a asignar
     */
    public void setComponent1(String component1) {
            this.component1 = component1;
    }
    /**
     * Devuelve la puntuaci&oacute;n actual del usuario 
     * @return Devuelve la puntuaci&oacute;n actual del usuario
     */
    public Integer snd() {
            return component2;
    }
    /**
     * Asigna una nueva puntuaci&oacute;n a la entrada
     * @param component2	Nombre a asignar
     */
    public void setComponent2(Integer component2) {
            this.component2 = component2;
    }
    
    /*
    @Override
    public String toString() {
            return "<" + component1 + "," + component2 + ">";
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                            + ((component1 == null) ? 0 : component1.hashCode());
            result = prime * result
                            + ((component2 == null) ? 0 : component2.hashCode());
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            final MyEntry<?, ?> other = (MyEntry<?, ?>) obj;
            if (component1 == null) {
                    if (other.component1 != null)
                            return false;
            } else if (!component1.equals(other.component1))
                    return false;
            if (component2 == null) {
                    if (other.component2 != null)
                            return false;
            } else if (!component2.equals(other.component2))
                    return false;
            return true;
    }

    public static <A, B> MyEntry<A, B> create(A component1, B component2) {
            return new MyEntry<A, B>(component1, component2);
    }*/
}
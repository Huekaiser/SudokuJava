package clasesPropias.Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import clasesPropias.Dominio.Clases.MyEntry;
import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Dominio.Controladores.Algoritmo;



public class GestorFicherosTableros extends GestorFicheros<Tablero> {
		
	public void escribirTablero(Tablero t, int numL, Integer id[]) throws IOException, ClassNotFoundException {
		int dificultad=t.getDificultad();
		if (dificultad <= 3 && dificultad > 0) {
			int n = t.getMida();
			if(n == 9) tam = "9/";
			else if(n == 16) tam = "16/";
			if(dificultad==1) diff="facil";
			else if (dificultad ==2) diff = "medio";
			else diff = "dificil";
			File lloc = new File (dataTableros + tam + diff);
			String s [] = lloc.list();
			contf = s.length;
    		if (numL == -1){
	    		id[0]=contf;
	    		f =new File(dataTableros + tam + diff + "/tablero" + diff + contf + ".txt");
    		}
    		else f =new File(dataTableros + tam + diff + "/tablero" + diff + numL + ".txt");
            super.escribir(t);
		}
	
	}
	
	public Tablero leerTablero(int n, int dificultad, int num, Integer id[]) throws IOException, ClassNotFoundException{
		if(dificultad==1) diff="facil";
		else if (dificultad ==2) diff = "medio";
		else diff = "dificil";
		if(n == 9) tam = "9/";
		else if(n == 16) tam = "16/";
		File lloc = new File(dataTableros + tam + diff);
		String l [] = lloc.list();
		int modulo = l.length;
		int valorEntero = (int) Math.floor(Math.random()*(modulo-1));
		if(num == -1) {
			//System.out.println(dataTableros + tam + diff + "/tablero" + diff + valorEntero + ".txt");
			f = new File(dataTableros + tam + diff + "/tablero" + diff + valorEntero + ".txt");
			id[0] = valorEntero;
		}
		else f = new File(dataTableros + tam + diff + "/tablero" + diff + num + ".txt");
		//System.out.println(f);
		Tablero t = new Tablero();
		t=(Tablero) super.leer(t);
		t.escribir();
		return t;
	}
	
	public void actualizarRecord (int n, int numt, int dif, MyEntry m) throws IOException, ClassNotFoundException {
		//Tablero t = leerTablero();
		switch (dif) {
			case 1: diff = "facil";
			break;
			case 2: diff = "medio";
			break; 
			case 3: diff = "dificil";
			break;
		}
		if (!diff.equals(null)) {
			f = new File(dataTableros + diff + "/tablero" + diff + numt);
			Integer id[] = new Integer[1];
			Tablero t = leerTablero(n, dif, numt, id);
			t.setTiempoRecord(m.fst(), m.snd());
			escribirTablero(t, numt,new Integer[1]);
		}
	}

	public Tablero importTablero(String nomT, int n) throws FileNotFoundException{
        Tablero t= new Tablero(n);
        Algoritmo a= new Algoritmo();
        File archivo = new File(dataTableros+ "imports/" + nomT);
        Scanner s = new Scanner(archivo);
        for(int i=0; i<n; ++i){
                for(int j=0; j<n; ++j){
                        int valor=s.nextInt();
                    	t.setValorTauler(i, j, valor);
                        if (valor != 0) {
                                t.setCandidatsTauler(i, j, new boolean[n+1]);
                                Algoritmo.actualizarPosiblesValorEscrito(i,j,valor,t);
                                t.setCasellaPor_defecto(i, j, true);
                        }
                        else{
                        	t.setCasellaPor_defecto(i, j, false);
                        }


                }
        }
        s.close();
        return t;
	}

	public boolean existe(String fileName) {
		// TODO Auto-generated method stub
		File archivos = new File(dataTableros + "imports/");
		File conj [] = archivos.listFiles();
		int i = 0;
		boolean b = false;
		while (i < conj.length && !b) {
			if (fileName.equals(conj[i].getName())) b=true;
			//System.out.println(conj[i].getName());
			++i;
		}
		return b;
	}
}
all:
	javac ./clasesComunes/*.java ./clasesPropias/Persistencia/*.java ./clasesPropias/Dominio/Controladores/*.java ./clasesPropias/Dominio/Clases/*.java ./clasesPropias/Presentacion/*.java 

jar:
	jar cvfm Sudoku.jar manifest.txt .
		
run:
	java -jar Sudoku.jar

clean:
	rm Sudoku.jar ./clasesComunes/*.class ./clasesPropias/Persistencia/*.class ./clasesPropias/Dominio/Controladores/*.class ./clasesPropias/Dominio/Clases/*.class ./clasesPropias/Presentacion/*.class 


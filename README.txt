Algoritmos:					 
					 
		-HiddenPairs: Entendemos unidad como fila, columna o region. Cuando en una unidad hay dos casillas que tienen un subconjunto igual de sus candidatos, los nuevos candidatos
				      de esas casillas seran exactamente el subconjunto que comparten.
					  
		-HiddenSingles: Cuando en una unidad, tenemos una casilla con solo un candidato, esa casilla contendra ese candidato. 
		
		-NakedPairs: Cuando en una misma unidad, tenemos exactamente dos casillas con exactamente los dos
		             mismos candidatos, podemos borrar esos dos candidatos de las demas casillas de su misma unidad, ya que esos son los ˙nicos
					 candidatos para esas dos casillas.
					 
		-NakedTriples: Cuando en una misma unidad, hay tres casillas que el conjunto de su uniÛn tiene tamano 3, podemos borrar los candidatos de ese
		               conjunto de las demas casillas.

		-Pointing Pairs: Si sabemos que dos candidatos solo aparecen en dos casillas de una fila o columna y esas dos casillas pertenecen a la misma región, podemos eliminar esos candidatos de todas las demás casillas de esa región.
					   
		-Generar: Este algoritmo empieza con un Sudoku vacio del tamano deseado por el usuario. Se colocan n˙meros aleatorios pero que cumplen las reglas
		          del Sudoku, asÌ tenemos mas variabilidad a la hora de generar Sudokus. Resolvemos el Sudoku generado con la funcion de resolver, y
				  seguidamente, borramos la cantidad de casillas elegida por el usuario (se borran de forma aleatoria). A continucacion, comprobamos si el Sudoku 
				  tiene solucion unica y si su dificultad es la deseada por el usuario. Si no cumple una de estas dos cosas, vuelve al Sudoku resuelto y quita otras casillas.
		-Comprobar Dificultad: aplica distintas estrategias en grupos para asignarle una dificultad a cada Tablero.
				  
		-Guess: Es el backtracking que usamos para resolver el Sudoku. Va probando posibles valores para cada casilla hasta tener el Sudoku resuelto correctamente.
		

************************************************************

Explicacion Estrucutras de datos:

	En nuestro programa usamos distintas estructuras de datos dependiendo del elemento que queramos tratar. Entre ellas creamos una estructura llamada MyEntry.
	Un MyEntry es un par formado por un String y un Integer. Esta clase la usamos para asociar a cada usuario con su tiempo o con su puntuacion.
	
	Tambien usamos ArrayList. El ArrayList lo usamos para almacenar el Ranking. En cada posicion del Array guardamos un MyEntry con el 
    nombre del usuario y su puntuacion. 
	
    Por ultimo usamos arrays, ya que tenemos tableros de tamano fijo y con un número no muy elevado de posiciones. Los usamos para almacenar los records del tablero,
	donde en cada posicion del array guardamos un MyEntry con el nombre del usuario y el tiempo que ha tardado en resolverlo.
    Tambien usamos arrays para almacenar los candidatos que tiene cada casilla, arrays de casillas para representar el tablero y arrays de integers para representar
    la solucion del tablero. 


*************************************************************

Repartición del trabajo:

El nombre del principal desarrollador de cada clase aparece en la parte inicial del documento de la misma. Ante cualquier duda que el profesor de esta asignatura pudiera tener sobre la adecuación de la repartición del trabajo por parte de los integrantes del grupo, es decir, nosotros, queremos que quede claro que todos estamos satisfechos con el reparto y el trabajo de nuestros compañeros, y que nos sentimos obligados a poner un solo nombre en cada clase cuando la colaboración entre nosotros ha sido total, y creemos que este hecho podría desvirtuar la percepción del profesor sobre este tema.

*************************************************************

Relación de clases hechas por cada miembro del grupo:



	- Esteban: ControladorCreacionTablero, GenerarSudoku, GestorFicheros, GestorFicherosRanking, GestorFicherosUsuarios, HiddenPairs, HiddenSingles, NakedPairs, NakedTriples, PointingPairs, PopUp Creación, TableroJuego16
	
	- Jesus: CargarSudoku, ComprobarDificultad, ComprobarSolucionUnica, ControladorRanking, Partida, Ranking, Ranking(vista), Record, top5
	
	- Jorge: Algoritmo, ControladorPresentacion, JFLogin, JFMenu, MyEntry, opcionesPartida, TableroCreacion16 

	- Marc: ControladorPartida, ControladorUsuario, GestorFicherosPartidas, GestorFicherosTableros, main,  Tablero, TableroCreacion9, TableroJuego9

*************************************************************

Makefile:

Las opciones del Makefile proporcionado son las siguientes:

>> make: compila todas las clases .java y crea los .class

>> make jar: crea el archivo Sudoku.jar utilizando la clase main como raíz

>> make run: ejecuta Sudoku.jar

>> make clean: borra todos los .java, .class, y el .jar
	
	
*************************************************************

	
RANGOS DE FUNCIONAMIENTO DEL PROGRAMA:

//////Estos rangos son tomados en nuestros ordenadores, es posible que en otros equipos con menos potencia los rangos se vean reducidos////////

Tamano 9:

FACIL: 28-30 tarda un poco, 30-50 instantaneo
MEDIO: 28-30, POCAS VECES tarda unos cuantos segundos pero generalmente instantaneo , 30-50 instantaneo
DIFICIL: 28-30 instantaneo

Tamano 16:

FACIL: 
	140-150: muy variable, a veces tarda entre 30 y 40 segundos y a veces poco.
	150-160: algunas veces tarda pero generalmente sale rápido, +160 instantáneo

MEDIO:
	A veces encuentra alguno en tiempo razonable cerca de las 140-150 casillas, pero le cuesta.

DIFICIL:
	160-180: razonablemente rápido,180-210 tarda siempre varios segundos pero lo suele encontrar, por encima y por debajo de esos limites muy dificil.
	

*************************************************************


Explicación de los time outs al generar tableros:

	En la modalidad de crear tablero, hay dos timers implementados, uno que salta a los 20 segundos de estar buscando tableros y no encontrar ninguno valido, pero otro que salta a los 10 segundos de estar intentando resolver un único tablero durante las comprobaciones necesarias para la generación, para controlar los puzzles con los que nuestro “solver” tiene problemas.

*************************************************************

Después de cargar una partida, si el usuario no desea que se borre, tendrá que volver a seleccionar la opción guardar.
		
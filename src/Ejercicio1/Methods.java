package Ejercicio1;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
 * En esta clase vamos a implementar todos los métodos necesarios para la realización del ejercicio
 * 
 * @author javie
 *
 */
public class Methods {

	Scanner S = new Scanner(System.in);

	List<Videogame> videogames = new ArrayList<>();
	
	/**
	 * Este método se va a encargar de mostrar el menú que verá el usuario por consola
	 */
	
	Videogame v3 = new Videogame(false);
	public void menu() {
		try {
		int opcion;
		do {
		System.out.println("===========================");
		System.out.println("====== Gestión de videojuegos =======");
		System.out.println("===========================");

		System.out.println("1.- Añadir videojuego");
		System.out.println("2.- Listar videojuegos");
        System.out.println("3.- Borrar un videojuego");
		System.out.println("4.- Guardar datos en fichero");
		System.out.println("5.- Recuperar datos desde fichero");
		System.out.println("0.- Salir de la aplicación");
		System.out.println("===========================");

		System.out.println("Introduzca la opción elegida");
	
		opcion = S.nextInt();
		
		switch(opcion) {
		case 1:
			addVideogame();
		break;
		case 2:
			listVideogames();
		break;
		case 3:
			deleteVideogames();
		break;
		case 4:
			saveChanges();
		break;
		case 5:
			saveChangesInTheFile();
		break;
		case 0:
			exitAplication();
		break;
		}
		}while(opcion != 0);
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR DE CARÁCTER STRING, REINICIE LA APLICACIÓN");
			e.printStackTrace();
		}
	}

	/**
	 * Este método se va a encargar de añadir videojuegos a la colección
	 */
	public void addVideogame() {
		try {
		Validar v = new Validar();
		
		//La variable tomará el valor de false, para su posterior evaluación en el método saveChangesInTheFile
		v3.setEstado(false);
		
		String videogame_name, plataform;
		int anio, mes, dia;
		System.out.println("Introduzca los datos del videojuego");
		System.out.println("Nombre del videojuego :");
		videogame_name = S.next();
		System.out.println("Plataforma principal :");
		plataform = S.next();
		System.out.println("Fecha de lanzamiento");
		System.out.println("Introduzca el año");
		anio = S.nextInt();
		System.out.println("Introduzca el mes");
		mes = S.nextInt();
		System.out.println("Introduzca el día");
		dia = S.nextInt();
		v.validar(LocalDate.of(anio, mes, dia));

		//Creación de un objeto videojuego
		Videogame v1 = new Videogame(videogame_name,LocalDate.of(anio, mes, dia),Plataforma.valueOf(plataform));
		
		//Se añade a la colección
		videogames.add(v1);

		System.out.println("Videojuego añadido");
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}

	/**
	 * Este método se va a encargar de listar todos los videojuegos de la colección
	 */
	public void listVideogames() {
		//Mostraremos la información, usando la API stream
		System.out.println("Videojuegos en la colección : ");
		System.out.println("Código : ");
		videogames.stream().map(v->v.getCodigo_videojuego()).forEach(System.out::println);
		System.out.println("Nombre del videojuego");
		videogames.stream().map(v->v.getNombre_videojuego()).forEach(System.out::println);
		System.out.println("Plataforma : ");
		videogames.stream().map(v->v.getPlataforma()).forEach(System.out::println);
		System.out.println("Fecha de lanzamiento : ");
		videogames.stream().map(v->v.getFecha_de_lanzamiento()).forEach(System.out::println);
		
	}

	/**
	 * Este método se va a encargar de eliminar el videojuego, en el cual el usuario va a introducir el código del videojuego que quiere borrar
	 */
	public void deleteVideogames() {
		try {
		//La variable tomará el valor de false, para su posterior evaluación en el método saveChangesInTheFile
		v3.setEstado(false);
		
		int codigo;
		//Creación de un variable procedente de la clase videogame, cuya función será almacenar el objeto
		//que se quiere borrar
		Videogame tache = null;
		System.out.println("Introduzca el código del videojuego para borrar");
		codigo = S.nextInt();
		
		//Recorrido de la colección
		for(Videogame v : videogames) {
			//Si el codigo introducido por el usuario coincide con algún codigo establecido antes
			if(codigo == v.getCodigo_videojuego()) {
				//Mostrará la información de ese objeto
				System.out.println(v.toString());
				
				//Guardará ese objeto en la variable
				tache = v;
			}else {
				System.out.println("VIDEOJUEGO INEXISTENTE");
			}
		}
		//Ahora eliminaremos ese objeto metido en la variable
		videogames.remove(tache);
		videogames.stream().forEach(System.out::println);
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}

	/**
	 * Este método se va a encargar de guardar los cambios
	 * 
	 */
	public void saveChanges(){
		
		//La variable tomará el valor de false, para su posterior evaluación en el método saveChangesInTheFile
		v3.setEstado(true);
		
		//Método creado más abajo, que recibirá como parámetro el nombre del fichero y el nombre de la colección
		crearArchivo("./videojuego.dat",videogames);
		
		//Método también creado más abajo que recibirá también, el nombre del fichero y el nombre de la colección,
		//y esto se encargará de leer el fichero
		readFile("./videojuego.dat",videogames);
		
		System.out.println("¡YUJUUU! : Los datos se han guardado en el fichero");

	}
	
	/**
	 * Método que se encargará de guardar los cambios, y esos cambios quedarán registrados en el fichero
	 */
	public void saveChangesInTheFile() {
		try {
		//La variable estado, toma el valor de true
		if(v3.isEstado() == true) {
			System.out.println("Usted, ha guardado los cambios en el fichero");
		}else {
			//En la segunda parte de la estructura, metemos una variable para recoger la decisión del usuario
			//si quiere restaurar los cambios del fichero, o no
			String opcion;
			System.out.println("Ha realizado cambios que no ha guardado en disco.");
			System.out.println("Si continua la carga del archivo se restaurarán los datos ");
			System.out.println("de disco y se perderán los cambios no guardados");
			System.out.println("¿Desea continuar con la carga y restaurar los datos del archivo?(S/N)");
			opcion = S.next();
			if(opcion.equals("S")) {
				System.out.println("¡¡ARCHIVOS RESTAURADOS!!");
			}else if(opcion.equals("N")) {
				System.out.println("¡¡LOS ARCHIVOS SE HAN MANTENIDO EN EL FICHERO!!");
			}
		}
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}
	
	/**
	 * Este método recibirá el nombre del fichero, y la colección por parámetro y se va a encargar de crear el
	 * archivo
	 * @param nombre
	 * @param List
	 */
	public static void crearArchivo(String nombre, List<Videogame> List){
		try {
			FileOutputStream fops = new FileOutputStream(nombre);
			ObjectOutputStream oops = new ObjectOutputStream(fops);
			
			for(Videogame v : List) {
				oops.writeObject(v);
			}
			if(oops != null) {
				oops.close();
				fops.close();
			}
			
			System.out.println("La información ha quedado registrada en : videojuego.dat");
		}catch(IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
		}
    }
	
	/**
	 * Este método también recibirá por parámetro el nombre del fichero y la colección de videojuegos,
	 * y se va a encargar de leer dicho fichero, anteriormente creado
	 * @param nombre
	 * @param lista
	 */
	public static void readFile(String nombre, List<Videogame> lista) {
		try {
			File f = null;
			FileInputStream fe = null;
			ObjectInputStream ois = null;
			try {
				f = new File(nombre);
				if (f.exists()) {
					fe = new FileInputStream(f);
					ois = new ObjectInputStream(fe);
					while (true) {
						Videogame vj = null;
						vj = (Videogame) ois.readObject();
						lista.add(vj);
					}
				}
			} catch (EOFException eof) {
				System.out.println(" --------------------------");
			} catch (FileNotFoundException fnf) {
				System.err.println("Fichero no encontrado " + fnf);
			} catch (IOException e) {
				System.err.println("Se ha producido una IOException");
				e.printStackTrace();
			} catch (Throwable e) {
				System.err.println("Error de programa: " + e);
				e.printStackTrace();
			} finally {
				if (ois != null) {
					ois.close();
					fe.close();
				}
				Videogame.setCodigo_videojuego(lista.get(lista.size() - 1).getCodigo_videojuego() + 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para salir de la aplicación
	 */
	public void exitAplication() {
		try {
		if(v3.isEstado() == true) {
			System.out.println("!!TODO ESTÁ BIEN GUARDADO, VUELVA PRONTO¡¡");
		}else {
			String opcion;
			System.out.println("USTED HA REALIZADO CAMBIOS EN EL FICHERO, QUE NO ESTÁN GUARDADOS");
			System.out.println("¿DESEA GUARDARLOS? (S/N)");
			opcion = S.next();
			if(opcion.equals("S")) {
				v3.setEstado(true);
				System.out.println("!!TODO ESTÁ BIEN GUARDADO, VUELVA PRONTO¡¡");
			}else if(opcion.equals("N")) {
				System.out.println("EL CONTENIDO DEL FICHERO SE HA BORRADO, ASEGÚRESE LA PRÓXIMA VEZ DE QUE HA GUARDADO LOS CAMBIOS");
				System.out.println("VUELVA PRONTO");
			}
		}
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}
    
}

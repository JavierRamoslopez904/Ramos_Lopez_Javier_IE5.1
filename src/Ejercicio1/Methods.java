package Ejercicio1;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
 * En esta clase vamos a implementar todos los m�todos necesarios para la realizaci�n del ejercicio
 * 
 * @author javie
 *
 */
public class Methods {

	Scanner S = new Scanner(System.in);

	List<Videogame> videogames = new ArrayList<>();
	
	/**
	 * Este m�todo se va a encargar de mostrar el men� que ver� el usuario por consola
	 */
	
	Videogame v3 = new Videogame(false);
	public void menu() {
		try {
		int opcion;
		do {
		System.out.println("===========================");
		System.out.println("====== Gesti�n de videojuegos =======");
		System.out.println("===========================");

		System.out.println("1.- A�adir videojuego");
		System.out.println("2.- Listar videojuegos");
        System.out.println("3.- Borrar un videojuego");
		System.out.println("4.- Guardar datos en fichero");
		System.out.println("5.- Recuperar datos desde fichero");
		System.out.println("0.- Salir de la aplicaci�n");
		System.out.println("===========================");

		System.out.println("Introduzca la opci�n elegida");
	
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
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR DE CAR�CTER STRING, REINICIE LA APLICACI�N");
			e.printStackTrace();
		}
	}

	/**
	 * Este m�todo se va a encargar de a�adir videojuegos a la colecci�n
	 */
	public void addVideogame() {
		try {
		Validar v = new Validar();
		
		//La variable tomar� el valor de false, para su posterior evaluaci�n en el m�todo saveChangesInTheFile
		v3.setEstado(false);
		
		String videogame_name, plataform;
		int anio, mes, dia;
		System.out.println("Introduzca los datos del videojuego");
		System.out.println("Nombre del videojuego :");
		videogame_name = S.next();
		System.out.println("Plataforma principal :");
		plataform = S.next();
		System.out.println("Fecha de lanzamiento");
		System.out.println("Introduzca el a�o");
		anio = S.nextInt();
		System.out.println("Introduzca el mes");
		mes = S.nextInt();
		System.out.println("Introduzca el d�a");
		dia = S.nextInt();
		v.validar(LocalDate.of(anio, mes, dia));

		//Creaci�n de un objeto videojuego
		Videogame v1 = new Videogame(videogame_name,LocalDate.of(anio, mes, dia),Plataforma.valueOf(plataform));
		
		//Se a�ade a la colecci�n
		videogames.add(v1);

		System.out.println("Videojuego a�adido");
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}

	/**
	 * Este m�todo se va a encargar de listar todos los videojuegos de la colecci�n
	 */
	public void listVideogames() {
		//Mostraremos la informaci�n, usando la API stream
		System.out.println("Videojuegos en la colecci�n : ");
		System.out.println("C�digo : ");
		videogames.stream().map(v->v.getCodigo_videojuego()).forEach(System.out::println);
		System.out.println("Nombre del videojuego");
		videogames.stream().map(v->v.getNombre_videojuego()).forEach(System.out::println);
		System.out.println("Plataforma : ");
		videogames.stream().map(v->v.getPlataforma()).forEach(System.out::println);
		System.out.println("Fecha de lanzamiento : ");
		videogames.stream().map(v->v.getFecha_de_lanzamiento()).forEach(System.out::println);
		
	}

	/**
	 * Este m�todo se va a encargar de eliminar el videojuego, en el cual el usuario va a introducir el c�digo del videojuego que quiere borrar
	 */
	public void deleteVideogames() {
		try {
		//La variable tomar� el valor de false, para su posterior evaluaci�n en el m�todo saveChangesInTheFile
		v3.setEstado(false);
		
		int codigo;
		//Creaci�n de un variable procedente de la clase videogame, cuya funci�n ser� almacenar el objeto
		//que se quiere borrar
		Videogame tache = null;
		System.out.println("Introduzca el c�digo del videojuego para borrar");
		codigo = S.nextInt();
		
		//Recorrido de la colecci�n
		for(Videogame v : videogames) {
			//Si el codigo introducido por el usuario coincide con alg�n codigo establecido antes
			if(codigo == v.getCodigo_videojuego()) {
				//Mostrar� la informaci�n de ese objeto
				System.out.println(v.toString());
				
				//Guardar� ese objeto en la variable
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
	 * Este m�todo se va a encargar de guardar los cambios
	 * 
	 */
	public void saveChanges(){
		
		//La variable tomar� el valor de false, para su posterior evaluaci�n en el m�todo saveChangesInTheFile
		v3.setEstado(true);
		
		//M�todo creado m�s abajo, que recibir� como par�metro el nombre del fichero y el nombre de la colecci�n
		crearArchivo("./videojuego.dat",videogames);
		
		//M�todo tambi�n creado m�s abajo que recibir� tambi�n, el nombre del fichero y el nombre de la colecci�n,
		//y esto se encargar� de leer el fichero
		readFile("./videojuego.dat",videogames);
		
		System.out.println("�YUJUUU! : Los datos se han guardado en el fichero");

	}
	
	/**
	 * M�todo que se encargar� de guardar los cambios, y esos cambios quedar�n registrados en el fichero
	 */
	public void saveChangesInTheFile() {
		try {
		//La variable estado, toma el valor de true
		if(v3.isEstado() == true) {
			System.out.println("Usted, ha guardado los cambios en el fichero");
		}else {
			//En la segunda parte de la estructura, metemos una variable para recoger la decisi�n del usuario
			//si quiere restaurar los cambios del fichero, o no
			String opcion;
			System.out.println("Ha realizado cambios que no ha guardado en disco.");
			System.out.println("Si continua la carga del archivo se restaurar�n los datos ");
			System.out.println("de disco y se perder�n los cambios no guardados");
			System.out.println("�Desea continuar con la carga y restaurar los datos del archivo?(S/N)");
			opcion = S.next();
			if(opcion.equals("S")) {
				System.out.println("��ARCHIVOS RESTAURADOS!!");
			}else if(opcion.equals("N")) {
				System.out.println("��LOS ARCHIVOS SE HAN MANTENIDO EN EL FICHERO!!");
			}
		}
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}
	
	/**
	 * Este m�todo recibir� el nombre del fichero, y la colecci�n por par�metro y se va a encargar de crear el
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
			
			System.out.println("La informaci�n ha quedado registrada en : videojuego.dat");
		}catch(IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
		}
    }
	
	/**
	 * Este m�todo tambi�n recibir� por par�metro el nombre del fichero y la colecci�n de videojuegos,
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
	 * M�todo para salir de la aplicaci�n
	 */
	public void exitAplication() {
		try {
		if(v3.isEstado() == true) {
			System.out.println("!!TODO EST� BIEN GUARDADO, VUELVA PRONTO��");
		}else {
			String opcion;
			System.out.println("USTED HA REALIZADO CAMBIOS EN EL FICHERO, QUE NO EST�N GUARDADOS");
			System.out.println("�DESEA GUARDARLOS? (S/N)");
			opcion = S.next();
			if(opcion.equals("S")) {
				v3.setEstado(true);
				System.out.println("!!TODO EST� BIEN GUARDADO, VUELVA PRONTO��");
			}else if(opcion.equals("N")) {
				System.out.println("EL CONTENIDO DEL FICHERO SE HA BORRADO, ASEG�RESE LA PR�XIMA VEZ DE QUE HA GUARDADO LOS CAMBIOS");
				System.out.println("VUELVA PRONTO");
			}
		}
		}catch(Exception e) {
			System.out.println("ERROR, NO HA INTRODUCIDO UN VALOR CORRESPONDIENTE AL TIPO DE DATO DE SU VARIABLE, REINICIE EL PROGRAMA");
			e.printStackTrace();
		}
	}
    
}

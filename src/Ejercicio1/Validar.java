package Ejercicio1;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Clase que tendrá únicamente un método, y se encargará de validar la fecha
 * 
 * @author javie
 *
 */
public class Validar {

	Scanner S = new Scanner(System.in);

	/**
	 * Este método se encargará de determinar si la fecha de lanzamiento del videojuego es correcta o no
	 * 
	 * @param l
	 * @return
	 */
	public static boolean validar(LocalDate l) {
		if (l.isBefore(LocalDate.now())) {
			System.out.println("!!Fecha correcta¡¡");
			return true;
		} else {
			System.err.println("!!Fecha incorrecta¡¡");
			return false;
		}
	}

	public static boolean validarPlataforma(String platafo) {
		for (Plataforma c : Plataforma.values()) {
			if (c.name().contains(platafo)) {
				return true;
			}
		}
		return false;
	}

}

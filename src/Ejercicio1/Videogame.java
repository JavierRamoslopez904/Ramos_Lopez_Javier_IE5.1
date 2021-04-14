package Ejercicio1;

/**
 * Clases importadas
 */
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author javie
 *
 */
public class Videogame implements Serializable {

	/**
	 * Este atributo es característico de la interfaz Serializable, pero no hará falta meterlo en el constructor
	 */
	private static final long serialVersionUID = 1L;

	// Atributos
	private int codigo_videojuego = 0;

	private String nombre_videojuego;

	private Plataforma plataforma;

	private LocalDate fecha_de_lanzamiento;
	
	public static Integer contador = 0;
	
	//Atributo boolean para determinar si el usuario a guardado los cambios antes de salir
	private boolean estado;

	// Constructor
	public Videogame(String nom_vid,LocalDate fecha, Plataforma pla) {
		this.nombre_videojuego = nom_vid;

		this.fecha_de_lanzamiento = fecha;

		this.plataforma = pla;
			
		contador++;
		codigo_videojuego = contador;
	
	}

	/**
	 * Creación del constructor para el atributo booleano estado
	 */
	public Videogame(boolean estado) {
		this.estado = estado;
	}
	
	//Métodos getters y setters del atributo booleano estado
	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// Métodos getters y setters
	/**
	 * @return the codigo_videojuego
	 */
	public  int getCodigo_videojuego() {
		return codigo_videojuego;
	}

	/**
	 * @param codigo_videojuego
	 *            the codigo_videojuego to set
	 */
	public static void setCodigo_videojuego(int codigo_videojuego) {
		codigo_videojuego = codigo_videojuego;
	}

	/**
	 * @return the nombre_videojuego
	 */
	public String getNombre_videojuego() {
		return nombre_videojuego;
	}

	/**
	 * @param nombre_videojuego
	 *            the nombre_videojuego to set
	 */
	public void setNombre_videojuego(String nombre_videojuego) {
		if (nombre_videojuego.isEmpty()) {
			throw new IllegalArgumentException("No ha introducido ningún valor");
		} else {
			this.nombre_videojuego = nombre_videojuego;
		}
	}

	/**
	 * @return the plataforma
	 */
	public Plataforma getPlataforma() {
		return plataforma;
	}

	/**
	 * Este método se va a encargar de mostrarnos si la plataforma introducida por el usuario es correcta o no,
	 * y en el caso de que no sea correcta, lanzará una excepción
	 * @param plataforma
	 */
	public void setPlataforma(String plataforma) {
		if(Validar.validarPlataforma(plataforma)) {
			this.plataforma = Plataforma.valueOf(plataforma);
		}else {
			System.out.println("ERROR, el campo Plataforma, está vacío");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the fecha_de_lanzamiento
	 */
	public LocalDate getFecha_de_lanzamiento() {
		return fecha_de_lanzamiento;
	}

	/**
	 * @param fecha_de_lanzamiento
	 *            the fecha_de_lanzamiento to set
	 */
	public void setFecha_de_lanzamiento(LocalDate fecha_de_lanzamiento) {
		if(Validar.validar(fecha_de_lanzamiento)) {
			this.fecha_de_lanzamiento = fecha_de_lanzamiento;
		}else {
			System.out.println("ERROR en la fecha, es superior a la actual");
		}
	}

	@Override
	public String toString() {
		return "Videogame [nombre_videojuego=" + nombre_videojuego + ", plataforma=" + plataforma + ", fecha_de_lanzamiento=" + fecha_de_lanzamiento + "]";
	}

}

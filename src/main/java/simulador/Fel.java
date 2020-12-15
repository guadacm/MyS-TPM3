/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

import java.util.Collections;
import java.util.LinkedList;

public class Fel {
	private static Fel fel;
	private LinkedList<Evento> lista;

	private Fel() {
		lista = new LinkedList<Evento>();
	}

	public static Fel getFel() {
		if (fel == null)
			fel = new Fel();
		return (fel);
	}

	public void insertarFel(Evento e) {
		lista.add(e);
		Collections.sort(lista);
	}

	public Evento suprimirFel() {
		return lista.removeFirst();
	}

	public void mostrarFel() {
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).toString());
		}
	}

	/**
	 * @return Returns the lista.
	 */
	public LinkedList<Evento> getLista() {
		return lista;
	}

	public static Fel inicializarFel() { 
		//fel = null;
		fel = new Fel();
		return(fel);
	}

}

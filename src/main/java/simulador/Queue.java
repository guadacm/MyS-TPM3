/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

import java.util.LinkedList;

public class Queue {
	private int cantidadItems;
	private LinkedList<Item> cola;

	public Queue() {
		cola = new LinkedList<Item>();
		cantidadItems = 0;
	}

	public void insertarCola(Item item) {
		cola.add(item);
		cantidadItems++;
	}

	public Item suprimirCola() {
		cantidadItems--;
		return cola.removeFirst();
	}

	public boolean HayCola() {
		if (cantidadItems == 0)
			return false;
		else
			return true;
	}

	int getSize() {
		return cola.size();
	}

	public void mostrarCola() {
		if (!this.HayCola())
			System.out.println("Vacío");
		else {
			System.out.println(cantidadItems + " vuelos");
			for (int i = 0; i < cola.size(); i++) {
				System.out.println(cola.get(i).toString());
			}
		}
	}

	public String mostrarPrimero() {
		String s = cola.get(0).toString();
		return s;
	}
}

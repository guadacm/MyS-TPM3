/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

import java.lang.Float;

public abstract class Evento implements Comparable<Evento> {
	private int tipo;// 0: Arribo - 1:Fin de Servicio - 2: Fin de Simulacion
	private float tiempo;
	private Item item;

	public Evento(int tipo, float tiempo, Item item) {
		this.tipo = tipo;
		this.tiempo = tiempo;
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public float getTiempo() {
		return tiempo;
	}

	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Implementa la planiificaci�n de eventos.
	 */
	public abstract void planificarEvento(Servidor servidor);

	public String toString() {
		String s = "Evento: ";
		switch (tipo) {
			case 0:
				s += ("Arribo | Tiempo: " + this.tiempo + " | " + this.item.toString());
				break;
			case 1:
				s += ("Salida | Tiempo: " + this.tiempo + " | " + this.item.toString());
				break;
			case 2:
				s += ("   Fin | Tiempo: " + this.tiempo + " | " + this.item.toString());
				break;
			default:
				s += ("Error");
		}
		return s;
	}

	@Override
	public int compareTo(Evento o) {
		int aux = Float.compare(this.getTiempo(), o.getTiempo());
		if (aux == 0)
			return Integer.compare(o.getTipo(), this.getTipo());
		else
			return aux;
	}
}

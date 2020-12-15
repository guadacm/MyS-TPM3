/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

public class Servidor {
	private Item item;
	private long recaudacion;
	private boolean estado; // false:desocupado, true:ocupado
	private Queue queue;
	private float tiempoOcioso;
	private float tiempoInicioOcio;

	public Servidor() {
		item = null; // No hay items en el servidor
		recaudacion = 0;
		estado = false; // Desocupado
		queue = new Queue(); // Cola vacía
		tiempoOcioso = 0;// No hay tiempo Ocioso
		tiempoInicioOcio = 0;// Inicio de Ocio en 0
	}

	public Queue getQueue() {
		return queue;
	}

	/**
	 * @return Returns the item.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item The item to set.
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	public long getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(long costo) {
		this.recaudacion += costo;
	}

	/**
	 * @return Returns the estado.
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the tiempoOcioso.
	 */
	public float getTiempoOcioso() {
		return tiempoOcioso;
	}

	/**
	 * @param tiempoOcioso The tiempoOcioso to set.
	 */
	public void setTiempoOcioso(float tiempoOcioso) {
		// calcular el tiempo de inicio de ocio
		this.tiempoOcioso += tiempoOcioso;
	}

	/**
	 * @return Returns the tiempoInicioOcio.
	 */
	public float getTiempoInicioOcio() {
		return tiempoInicioOcio;
	}

	/**
	 * @param tiempoInicioOcio The tiempoInicioOcio to set.
	 */
	public void setTiempoInicioOcio(float tiempoInicioOcio) {
		this.tiempoInicioOcio = tiempoInicioOcio;
	}
}

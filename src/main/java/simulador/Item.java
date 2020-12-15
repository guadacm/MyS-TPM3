/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

public class Item {
	private int numero;
	private int tipo; // 0:pesado 1:mediano 2:liviano
	private long costo;
	private float tiempoArribo;
	private float tiempoDuracionServicio;
	// private static float tiempoTransito = 0;
	private static float tiempoTransitoPesado = 0;
	private static float tiempoTransitoMediano = 0;
	private static float tiempoTransitoLiviano = 0;

	private static float tiempoEsperaColaPesado = 0;
	private static float tiempoEsperaColaMediano = 0;
	private static float tiempoEsperaColaLiviano = 0;
	private static int cantidadItemsPesado = 0;
	private static int cantidadItemsMediano = 0;
	private static int cantidadItemsLiviano = 0;

	public Item(int numero, int tipo, float tiempoArribo) {
		this.numero = numero;
		this.tipo = tipo;
		if (tipo == 0) {
			costo = 70000;
			cantidadItemsPesado++;
		} else if (tipo == 1) {
			costo = 45000;
			cantidadItemsMediano++;
		} else if (tipo == 2) {
			costo = 25000;
			cantidadItemsLiviano++;
		}
		this.tiempoArribo = tiempoArribo;
		this.tiempoDuracionServicio = 0;
	}

	public static void inicializar() {
		tiempoTransitoPesado = 0;
		tiempoTransitoMediano = 0;
		tiempoTransitoLiviano = 0;

		tiempoEsperaColaPesado = 0;
		tiempoEsperaColaMediano = 0;
		tiempoEsperaColaLiviano = 0;

		cantidadItemsPesado = 0;
		cantidadItemsMediano = 0;
		cantidadItemsLiviano = 0;
	}

	public static int getCantidadItemsPesado() {
		return cantidadItemsPesado;
	}

	public static void setCantidadItemsPesado(int cantidadItems) {
		Item.cantidadItemsPesado = cantidadItems;
	}

	public static int getCantidadItemsMediano() {
		return cantidadItemsMediano;
	}

	public static void setCantidadItemsMediano(int cantidadItems) {
		Item.cantidadItemsMediano = cantidadItems;
	}

	public static int getCantidadItemsLiviano() {
		return cantidadItemsLiviano;
	}

	public static void setCantidadItemsLiviano(int cantidadItems) {
		Item.cantidadItemsLiviano = cantidadItems;
	}

	public static float getTiempoEsperaColaPesado() {
		return tiempoEsperaColaPesado;
	}

	public static float getTiempoEsperaColaMediano() {
		return tiempoEsperaColaMediano;
	}

	public static float getTiempoEsperaColaLiviano() {
		return tiempoEsperaColaLiviano;
	}

	public static void setTiempoEsperaCola(int tipo, float tiempoActual, float tiempoArribo) {
		// calcular el tiempo de espera en cola
		if (tipo == 0)
			tiempoEsperaColaPesado += (tiempoActual - tiempoArribo);
		else if (tipo == 1)
			tiempoEsperaColaMediano += (tiempoActual - tiempoArribo);
		else
			tiempoEsperaColaLiviano += (tiempoActual - tiempoArribo);
	}

	public static float getTiempoTransitoPesado() {
		return tiempoTransitoPesado;
	}

	public static float getTiempoTransitoMediano() {
		return tiempoTransitoMediano;
	}

	public static float getTiempoTransitoLiviano() {
		return tiempoTransitoLiviano;
	}

	/**
	 * @param tiempoTransito The tiempoTransito to set.
	 */
	public static void setTiempoTransito(int tipo, float tiempoActual, float tiempoArribo) {
		// calcular el tiempo de transito
		if (tipo == 0)
			tiempoTransitoPesado += (tiempoActual - tiempoArribo);
		else if (tipo == 1)
			tiempoTransitoMediano += (tiempoActual - tiempoArribo);
		else
			tiempoTransitoLiviano += (tiempoActual - tiempoArribo);
	}

	/**
	 * @return Returns the numero.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public long getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	/**
	 * @return Returns the tiempoArribo.
	 */
	public float getTiempoArribo() {
		return tiempoArribo;
	}

	/**
	 * @param tiempoArribo The tiempoArribo to set.
	 */
	public void setTiempoArribo(float tiempoArribo) {
		this.tiempoArribo = tiempoArribo;
	}

	/**
	 * @return Returns the tiempoDuracionServicio.
	 */
	public float getTiempoDuracionServicio() {
		return tiempoDuracionServicio;
	}

	/**
	 * @param tiempoDuracionServicio The tiempoDuracionServicio to set.
	 */
	public void setTiempoDuracionServicio(float tiempoDuracionServicio) {
		this.tiempoDuracionServicio = tiempoDuracionServicio;
	}

	public String toString() {
		String aux;
		switch (this.getTipo()) {
			case 0:
				aux = "Internacional";
				break;
			case 1:
				aux = "Cabotaje";
				break;
			case 2:
				aux = "Privado";
				break;
			default:
				aux = "FIN";
		}
		return ("\tItem nro: " + this.getNumero() + " | Tipo: " + aux + " | Tiempo de arribo: " + this.getTiempoArribo()
				+ " | Tiempo de duracion de servicio: " + this.getTiempoDuracionServicio());
	}
}

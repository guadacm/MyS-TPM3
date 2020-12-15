/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

public class EventoFinSimulacion extends Evento {

	public EventoFinSimulacion(float tiempo) {
		super(2, tiempo, new Item(-1, -1, tiempo));
	}

	public void planificarEvento(Servidor servidor) {
		// No hacer nada
	}

}

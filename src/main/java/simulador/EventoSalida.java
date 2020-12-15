/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

public class EventoSalida extends Evento {

    public EventoSalida(float tiempo, Item item) {
        super(1, tiempo, item);
    }

    public void planificarEvento(Servidor servidor) {
        // planifico evento de salida
        Fel fel = Fel.getFel();

        if (!servidor.getQueue().HayCola()) {
            servidor.setEstado(false); // Si no hay cola, servidor=desocuapdo
            servidor.setTiempoInicioOcio(this.getTiempo());
        } else {
            Item aux = servidor.getQueue().suprimirCola();
            aux.setTiempoDuracionServicio(GeneradorTiempos.getTiempoDuracionServicio(this.getItem().getTipo()));
            fel.insertarFel(new EventoSalida((this.getTiempo() + aux.getTiempoDuracionServicio()), aux));
            servidor.setItem(aux);
            Item.setTiempoEsperaCola(aux.getTipo(), this.getTiempo(), aux.getTiempoArribo()); // ESTADISTICAS
        }
        // ESTADISTICAS
        Item.setTiempoTransito(this.getItem().getTipo(),this.getTiempo(), this.getItem().getTiempoArribo());
    }

}

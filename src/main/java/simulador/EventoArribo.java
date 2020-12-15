/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

public class EventoArribo extends Evento {
    public EventoArribo(float tiempo, Item item) {
        super(0, tiempo, item);

    }

    public void planificarEvento(Servidor servidor) {
        // planifico el evento de arribo
        Fel fel = Fel.getFel();

        if (servidor.isEstado()) // Si esta ocupado lo dejo en la cola
            servidor.getQueue().insertarCola(this.getItem());
        else { // Si el servidor no esta ocupado planifico salida
            servidor.setEstado(true);
            servidor.setItem(this.getItem());
            servidor.setRecaudacion(this.getItem().getCosto()); // ESTADISTICAS
            servidor.setTiempoOcioso(this.getTiempo() - servidor.getTiempoInicioOcio()); // ESTADISTICAS
            this.getItem()
                    .setTiempoDuracionServicio(GeneradorTiempos.getTiempoDuracionServicio(this.getItem().getTipo()));
            fel.insertarFel(
                    new EventoSalida((this.getTiempo() + this.getItem().getTiempoDuracionServicio()), this.getItem()));
        }
        // planifico proximo arribo
        float nuevoArribo = GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(), calcularDias(this.getTiempo()),
                this.getItem().getTipo());
        int nroItem = this.getItem().getNumero();
        for (int i = 0; i < fel.getLista().size(); i++) {
            if (fel.getLista().get(i).getItem().getNumero() > nroItem)
                nroItem = fel.getLista().get(i).getItem().getNumero();
        }
        Item nuevo = new Item(nroItem + 1, this.getItem().getTipo(), this.getTiempo() + nuevoArribo);
        fel.insertarFel(new EventoArribo(this.getTiempo() + nuevoArribo, nuevo));
    }

    static int calcularDias(float tiempoSimulacion) {
        return (int) tiempoSimulacion / 1440; // 1dia=1440minutos
    }

}

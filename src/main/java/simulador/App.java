/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

public class App {

        public static void main(String[] args) {
                Evento actual;
                // float tiempoSimulacion = 0;
                int replicaciones = 50;// 50; // cantidad de replicaciones
                Fel fel = Fel.getFel();// Creo la lista de eventos futuros
                // Hay 6 piestas
                Servidor servidorPesado;// = new Servidor();// Creo el servidor
                Servidor servidorMediano1;// = new Servidor();
                Servidor servidorMediano2;// = new Servidor();
                Servidor servidorMediano3;// = new Servidor();
                Servidor servidorMediano4;// = new Servidor();
                Servidor servidorLiviano;// = new Servidor();
                System.out.println("\n----------------------------------------------------------\n"
                                + "||\t\tMODELOS Y SIMULACION 2020\t\t||\n"
                                + "----------------------------------------------------------\n"
                                + "||------------------ Guadalupe Medina ------------------||\n"
                                + "----------------------------------------------------------\n\n");
                System.out.println("     ----------- Corriendo replicaciones ------------     \n");
                //System.out.println("     ------------- Corriendo simulación -------------     \n");

                Estadisticas.inicializarVblesReplicacion(replicaciones);
                while (replicaciones > 0) {                        
                        // Inicializo el sistema
                        fel = Fel.inicializarFel();// .inicializarFel(); // inicializo la fel
                        Item.inicializar();
                        servidorPesado = new Servidor();// Creo el servidor
                        servidorMediano1 = new Servidor();
                        servidorMediano2 = new Servidor();
                        servidorMediano3 = new Servidor();
                        servidorMediano4 = new Servidor();
                        servidorLiviano = new Servidor();
                        // Fin inicializacion
                        actual = new EventoFinSimulacion(10080); // una semana = 10080 minutos
                        fel.insertarFel(actual);// Inserto el evento de fin de simulación en la FEL
                        // Genero 3 arribo para empezar
                        actual = new EventoArribo(0, new Item(1, 0, 0)); // Creo primer evento de arribo, en tiempo 0
                        fel.insertarFel(actual);
                        actual = new EventoArribo(0, new Item(2, 1, 0)); // Creo primer evento de arribo, en tiempo 0
                        fel.insertarFel(actual);
                        actual = new EventoArribo(0, new Item(3, 2, 0)); // Creo primer evento de arribo, en tiempo 0
                        fel.insertarFel(actual);
                        // System.out.println("\n>> Tiempo Inicio: " + tiempoSimulacion + " << ");
                        // System.out.println("* FEL");
                        // fel.mostrarFel();

                        while (actual.getItem().getNumero() != -1) {
                                actual = fel.suprimirFel();
                                if (actual.getItem().getNumero() != -1) { // Si no es el fin de simulacion
                                        // tiempoSimulacion = actual.getTiempo();
                                        // System.out.println("\n>> Tiempo Actual: " + tiempoSimulacion + " <<\nSaco de
                                        // FEL: "
                                        // + actual.toString());
                                        // INTERNACIONAL
                                        if (actual.getItem().getTipo() == 0)
                                                actual.planificarEvento(servidorPesado);
                                        // PRIVADO
                                        else if (actual.getItem().getTipo() == 2)
                                                actual.planificarEvento(servidorLiviano);
                                        else {
                                                // CABOTAJE
                                                if (actual.getTipo() == 1) {
                                                        // FIN DE SERVICIO
                                                        if (actual.getItem() == servidorMediano1.getItem())
                                                                actual.planificarEvento(servidorMediano1);
                                                        else if (actual.getItem() == servidorMediano2.getItem())
                                                                actual.planificarEvento(servidorMediano2);
                                                        else if (actual.getItem() == servidorMediano3.getItem())
                                                                actual.planificarEvento(servidorMediano3);
                                                        else if (actual.getItem() == servidorMediano4.getItem())
                                                                actual.planificarEvento(servidorMediano4);
                                                } else { // ARRIBO CABOTAJE
                                                        if (servidorMediano1.isEstado() == false)
                                                                actual.planificarEvento(servidorMediano1);
                                                        else if (servidorMediano2.isEstado() == false)
                                                                actual.planificarEvento(servidorMediano2);
                                                        else if (servidorMediano3.isEstado() == false)
                                                                actual.planificarEvento(servidorMediano3);
                                                        else if (servidorMediano4.isEstado() == false)
                                                                actual.planificarEvento(servidorMediano4);
                                                        else {
                                                                int aux = servidorMediano1.getQueue().getSize(), s = 1;
                                                                if (servidorMediano2.getQueue().getSize() < aux) {
                                                                        aux = servidorMediano2.getQueue().getSize();
                                                                        s = 2;
                                                                } else if (servidorMediano3.getQueue()
                                                                                .getSize() < aux) {
                                                                        aux = servidorMediano3.getQueue().getSize();
                                                                        s = 3;
                                                                } else if (servidorMediano4.getQueue()
                                                                                .getSize() < aux) {
                                                                        aux = servidorMediano4.getQueue().getSize();
                                                                        s = 4;
                                                                }

                                                                if (s == 1)
                                                                        actual.planificarEvento(servidorMediano1);
                                                                else if (s == 2)
                                                                        actual.planificarEvento(servidorMediano2);
                                                                else if (s == 3)
                                                                        actual.planificarEvento(servidorMediano3);
                                                                else
                                                                        actual.planificarEvento(servidorMediano4);
                                                        }
                                                }
                                        }
                                        /*
                                         * System.out.println("* FEL"); fel.mostrarFel();
                                         * System.out.print("\n* Pista Internacional: "); if (servidorPesado.isEstado())
                                         * { System.out.print("Ocupada con vuelo " +
                                         * servidorPesado.getItem().getNumero() + ". Cola de espera: ");
                                         * servidorPesado.getQueue().mostrarCola(); } else System.out.println("Libre");
                                         * 
                                         * System.out.print("* Pista Cabotaje 1: "); if (servidorMediano1.isEstado()) {
                                         * System.out.print("Ocupada con vuelo " +
                                         * servidorMediano1.getItem().getNumero() + ". Cola de espera: ");
                                         * servidorMediano1.getQueue().mostrarCola(); } else
                                         * System.out.println("Libre");
                                         * 
                                         * System.out.print("* Pista Cabotaje 2: "); if (servidorMediano2.isEstado()) {
                                         * System.out.print("Ocupada con vuelo " +
                                         * servidorMediano2.getItem().getNumero() + ". Cola de espera: ");
                                         * servidorMediano2.getQueue().mostrarCola(); } else
                                         * System.out.println("Libre");
                                         * 
                                         * System.out.print("* Pista Cabotaje 3: "); if (servidorMediano3.isEstado()) {
                                         * System.out.print("Ocupada con vuelo " +
                                         * servidorMediano3.getItem().getNumero() + ". Cola de espera: ");
                                         * servidorMediano3.getQueue().mostrarCola(); } else
                                         * System.out.println("Libre");
                                         * 
                                         * System.out.print("* Pista Cabotaje 4: "); if (servidorMediano4.isEstado()) {
                                         * System.out.print("Ocupada con vuelo " +
                                         * servidorMediano4.getItem().getNumero() + ". Cola de espera: ");
                                         * servidorMediano4.getQueue().mostrarCola(); } else
                                         * System.out.println("Libre");
                                         * 
                                         * System.out.print("* Pista Privada: "); if (servidorLiviano.isEstado()) {
                                         * System.out.print("Ocupada con vuelo " + servidorLiviano.getItem().getNumero()
                                         * + ". Cola de espera: "); servidorLiviano.getQueue().mostrarCola(); } else
                                         * System.out.println("Libre");
                                         */
                                } else {
                                        // calculo el tiempo de ocio hasta el tiempo de fin de simulacion
                                        if (!servidorPesado.isEstado())
                                                servidorPesado.setTiempoOcioso(actual.getTiempo()
                                                                - servidorPesado.getTiempoInicioOcio());
                                        if (!servidorMediano1.isEstado())
                                                servidorMediano1.setTiempoOcioso(actual.getTiempo()
                                                                - servidorMediano1.getTiempoInicioOcio());
                                        if (!servidorMediano2.isEstado())
                                                servidorMediano2.setTiempoOcioso(actual.getTiempo()
                                                                - servidorMediano2.getTiempoInicioOcio());
                                        if (!servidorMediano3.isEstado())
                                                servidorMediano3.setTiempoOcioso(actual.getTiempo()
                                                                - servidorMediano3.getTiempoInicioOcio());
                                        if (!servidorMediano4.isEstado())
                                                servidorMediano4.setTiempoOcioso(actual.getTiempo()
                                                                - servidorMediano4.getTiempoInicioOcio());
                                        if (!servidorLiviano.isEstado())
                                                servidorLiviano.setTiempoOcioso(actual.getTiempo()
                                                                - servidorLiviano.getTiempoInicioOcio());

                                        // descuento los items que arribaron despues del fin de simulacion
                                        int pesado = 0, mediano = 0, liviano = 0;
                                        for (int i = 0; i < fel.getLista().size(); i++) {
                                                if (fel.getLista().get(i).getTipo() == 0) {
                                                        if (fel.getLista().get(i).getItem().getTipo() == 0)
                                                                pesado++;
                                                        else if (fel.getLista().get(i).getItem().getTipo() == 1)
                                                                mediano++;
                                                        else if (fel.getLista().get(i).getItem().getTipo() == 2)
                                                                liviano++;
                                                }
                                        }
                                        Item.setCantidadItemsPesado(Item.getCantidadItemsPesado() - pesado);
                                        Item.setCantidadItemsMediano(Item.getCantidadItemsMediano() - mediano);
                                        Item.setCantidadItemsLiviano(Item.getCantidadItemsLiviano() - liviano);
                                        //System.out.println(
                                        //                "     ------------- Fin de la simulación -------------     \n");
                                        //System.out.println("\n> Tiempo de simulación: " + actual.getTiempo());

                                        int canTotalVuelos = Item.getCantidadItemsPesado()
                                                        + Item.getCantidadItemsMediano()
                                                        + Item.getCantidadItemsLiviano();
                                        //System.out.println("> Cantidad total de vuelos: " + canTotalVuelos);

                                        //System.out.println(
                                        //                "\n----------------------------------------------------------\n"
                                        //                                + "||                     Estadísticas                     ||\n"
                                        //                                + "----------------------------------------------------------\n");
                                        Estadisticas.cantReplicaciones();

                                        //System.out.println(
                                        //                "\n> Vuelos internacionales: " + Item.getCantidadItemsPesado());
                                        Estadisticas.tiempoMedioEspera(Item.getTiempoEsperaColaPesado(),
                                                        Item.getCantidadItemsPesado(), 0);
                                        Estadisticas.tiempoMedioOciosidad(servidorPesado.getTiempoOcioso(),
                                                        Item.getCantidadItemsPesado(), 0);
                                        Estadisticas.tiempoMedioTransito(Item.getTiempoTransitoPesado(),
                                                        Item.getCantidadItemsPesado(), 0);
                                        Estadisticas.recaudacion(servidorPesado.getRecaudacion());

                                        //System.out.println("\n> Vuelos de cabotaje: " + Item.getCantidadItemsMediano());
                                        Estadisticas.tiempoMedioEspera(Item.getTiempoEsperaColaMediano(),
                                                        Item.getCantidadItemsMediano(), 1);
                                        Estadisticas.tiempoMedioOciosidad(
                                                        servidorMediano1.getTiempoOcioso()
                                                                        + servidorMediano2.getTiempoOcioso()
                                                                        + servidorMediano3.getTiempoOcioso()
                                                                        + servidorMediano4.getTiempoOcioso(),
                                                        Item.getCantidadItemsMediano(), 1);
                                        Estadisticas.tiempoMedioTransito(Item.getTiempoTransitoMediano(),
                                                        Item.getCantidadItemsMediano(), 1);
                                        Estadisticas.recaudacion(servidorMediano1.getRecaudacion()
                                                        + servidorMediano2.getRecaudacion()
                                                        + servidorMediano3.getRecaudacion()
                                                        + servidorMediano4.getRecaudacion());

                                        //System.out.println("\n> Vuelos privados: " + Item.getCantidadItemsLiviano());
                                        Estadisticas.tiempoMedioEspera(Item.getTiempoEsperaColaLiviano(),
                                                        Item.getCantidadItemsLiviano(), 2);
                                        Estadisticas.tiempoMedioOciosidad(servidorLiviano.getTiempoOcioso(),
                                                        Item.getCantidadItemsLiviano(), 2);
                                        Estadisticas.tiempoMedioTransito(Item.getTiempoTransitoLiviano(),
                                                        Item.getCantidadItemsLiviano(), 2);
                                        Estadisticas.recaudacion(servidorLiviano.getRecaudacion());
                                        //System.out.println("\n\n");
                                }
                        }

                        replicaciones--;
                }
                // estadisticas de replicacion
                System.out.println("     ------------- Fin de replicaciones -------------     \n");

                Estadisticas.analisisResultados();
        }
}

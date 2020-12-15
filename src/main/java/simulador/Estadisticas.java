/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

import java.text.DecimalFormat;

public class Estadisticas {

	static float[] mediasEsperaPesado;
	static float[] mediasEsperaMediano;
	static float[] mediasEsperaLiviano;

	static float[] mediasOcioPesado;
	static float[] mediasOcioMediano;
	static float[] mediasOcioLiviano;

	static float[] mediasTransitoPesado;
	static float[] mediasTransitoMediano;
	static float[] mediasTransitoLiviano;

	static int replicacion = 0; // cantidad de replicaciones

	static DecimalFormat decimal = new DecimalFormat("#.##");

	public static void inicializarVblesReplicacion(int replicaciones) {
		mediasEsperaPesado = new float[replicaciones];
		mediasEsperaMediano = new float[replicaciones];
		mediasEsperaLiviano = new float[replicaciones];

		mediasOcioPesado = new float[replicaciones];
		mediasOcioMediano = new float[replicaciones];
		mediasOcioLiviano = new float[replicaciones];

		mediasTransitoPesado = new float[replicaciones];
		mediasTransitoMediano = new float[replicaciones];
		mediasTransitoLiviano = new float[replicaciones];
	}

	public static void cantReplicaciones() {
		replicacion++;
	}

	public static void tiempoMedioTransito(float tiempoTransito, int cantidadItems, int tipo) {
		// System.out.println("> Tiempo total de transito: " +
		// decimal.format(tiempoTransito) + " mins");
		// System.out.println("> Tiempo medio de transito: " +
		// decimal.format(tiempoTransito / cantidadItems) + " mins");

		if (tipo == 0)
			mediasTransitoPesado[replicacion - 1] = (tiempoTransito / cantidadItems);
		else if (tipo == 1)
			mediasTransitoMediano[replicacion - 1] = (tiempoTransito / cantidadItems);
		else
			mediasTransitoLiviano[replicacion - 1] = (tiempoTransito / cantidadItems);
	}

	public static void tiempoMedioEspera(float tiempoEsperaCola, int cantidadItems, int tipo) {
		//System.out.println("\t- Tiempo total de espera: " + decimal.format(tiempoEsperaCola) + " mins");
		//System.out.println("\t- Tiempo medio de espera: " + decimal.format(tiempoEsperaCola / cantidadItems) + " mins");
		if (tipo == 0)
			mediasEsperaPesado[replicacion - 1] = (tiempoEsperaCola / cantidadItems);
		else if (tipo == 1)
			mediasEsperaMediano[replicacion - 1] = (tiempoEsperaCola / cantidadItems);
		else
			mediasEsperaLiviano[replicacion - 1] = (tiempoEsperaCola / cantidadItems);
	}

	public static void tiempoMedioOciosidad(float tiempoOcioso, int cantidadItems, int tipo) {
		// System.out.println("\t- Tiempo total de ociosidad: " +
		// decimal.format(tiempoOcioso) + " mins");
		// System.out.println("\t- Tiempo medio de ociosidad: " +
		// decimal.format(tiempoOcioso / cantidadItems) + " mins");

		if (tipo == 0)
			mediasOcioPesado[replicacion - 1] = (tiempoOcioso / cantidadItems);
		else if (tipo == 1)
			mediasOcioMediano[replicacion - 1] = (tiempoOcioso / cantidadItems);
		else
			mediasOcioLiviano[replicacion - 1] = (tiempoOcioso / cantidadItems);
	}

	public static void recaudacion(long recaudacion) {
		// System.out.println("\t- Recaudacion: $" + (recaudacion));
	}

	public static void analisisResultados() {
		System.out.println("\n----------------------------------------------------------\n"
				+ "||                Análisis de Resultados                ||\n"
				+ "----------------------------------------------------------\n");
		System.out.println("\nCantidad de replicaciones: " + replicacion);

		System.out.println("\n> Vuelos internacionales: ");
		// Calculos para vuelos internacionales
		float sumaMediasEsperaPesado = 0;
		float sumaMediasOcioPesado = 0;
		float sumaMediasTransitoPesado = 0;
		// Sumas de medias
		for (int i = 0; i < replicacion; i++) {
			//System.out.print(mediasEsperaPesado[i] + "   ");
			sumaMediasEsperaPesado += mediasEsperaPesado[i];
			sumaMediasOcioPesado += mediasOcioPesado[i];
			sumaMediasTransitoPesado += mediasTransitoPesado[i];
		}
		// Medias de medias (media global)
		float mediaDeMediasEsperaPesado = sumaMediasEsperaPesado / replicacion;
		float mediaDeMediasOcioPesado = sumaMediasOcioPesado / replicacion;
		float mediaDeMediasTransitoPesado = sumaMediasTransitoPesado / replicacion;
		// varianza
		double varianzaEsperaPesado = 0;
		double varianzaOcioPesado = 0;
		double varianzaTransitoPesado = 0;
		for (int i = 0; i < replicacion; i++) {
			varianzaEsperaPesado += Math.pow((mediasEsperaPesado[i] - mediaDeMediasEsperaPesado), 2)
					/ (replicacion - 1);
			varianzaOcioPesado += Math.pow((mediasOcioPesado[i] - mediaDeMediasOcioPesado), 2) / (replicacion - 1);
			varianzaTransitoPesado += Math.pow((mediasTransitoPesado[i] - mediaDeMediasTransitoPesado), 2)
					/ (replicacion - 1);
		}
		
		System.out.println("\t- Media de medias de espera: " + decimal.format(mediaDeMediasEsperaPesado) + " mins");
		// Estimacion por intervalos, con Z=1,645 para obtener intervalos con 90% de
		// confianza
		double Z = 1.645;
		double estimador = Math.sqrt(varianzaEsperaPesado);
		double intervaloIzq, intervaloDer;
		intervaloIzq = mediaDeMediasEsperaPesado - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasEsperaPesado + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");
		System.out.println("\n\t- Media de medias de ociosidad: " + decimal.format(mediaDeMediasOcioPesado) + " mins");
		estimador = Math.sqrt(varianzaOcioPesado);
		intervaloIzq = mediaDeMediasOcioPesado - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasOcioPesado + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");
		System.out
				.println("\n\t- Media de medias de Transito: " + decimal.format(mediaDeMediasTransitoPesado) + " mins");
		estimador = Math.sqrt(varianzaTransitoPesado);
		intervaloIzq = mediaDeMediasTransitoPesado - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasTransitoPesado + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");

		System.out.println("\n> Vuelos de cabotaje: ");
		// Calculos para vuelos de cabotaje
		float sumaMediasEsperaMediano = 0;
		float sumaMediasOcioMediano = 0;
		float sumaMediasTransitoMediano = 0;
		// Sumas de medias
		for (int i = 0; i < replicacion; i++) {
			sumaMediasEsperaMediano += mediasEsperaMediano[i];
			sumaMediasOcioMediano += mediasOcioMediano[i];
			sumaMediasTransitoMediano += mediasTransitoMediano[i];
		}
		// Medias de medias (media global)
		float mediaDeMediasEsperaMediano = sumaMediasEsperaMediano / replicacion;
		float mediaDeMediasOcioMediano = sumaMediasOcioMediano / replicacion;
		float mediaDeMediasTransitoMediano = sumaMediasTransitoMediano / replicacion;
		// varianza
		double varianzaEsperaMediano = 0;
		double varianzaOcioMediano = 0;
		double varianzaTransitoMediano = 0;
		for (int i = 0; i < replicacion; i++) {
			varianzaEsperaMediano += Math.pow((mediasEsperaMediano[i] - mediaDeMediasEsperaMediano), 2)
					/ (replicacion - 1);
			varianzaOcioMediano += Math.pow((mediasOcioMediano[i] - mediaDeMediasOcioMediano), 2) / (replicacion - 1);
			varianzaTransitoMediano += Math.pow((mediasTransitoMediano[i] - mediaDeMediasTransitoMediano), 2)
					/ (replicacion - 1);
		}
		System.out.println("\t- Media de medias de espera: " + decimal.format(mediaDeMediasEsperaMediano) + " mins");
		// Estimacion por intervalos, con Z=1,645 para obtener intervalos con 90% de
		// confianza
		estimador = Math.sqrt(varianzaEsperaMediano);
		intervaloIzq = mediaDeMediasEsperaMediano - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasEsperaMediano + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");
		System.out.println("\n\t- Media de medias de ociosidad: " + decimal.format(mediaDeMediasOcioMediano) + " mins");
		estimador = Math.sqrt(varianzaOcioMediano);
		intervaloIzq = mediaDeMediasOcioMediano - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasOcioMediano + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");
		System.out.println(
				"\n\t- Media de medias de Transito: " + decimal.format(mediaDeMediasTransitoMediano) + " mins");
		estimador = Math.sqrt(varianzaTransitoMediano);
		intervaloIzq = mediaDeMediasTransitoMediano - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasTransitoMediano + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");

		System.out.println("\n> Vuelos privados: ");
		// Calculos para vuelos privados
		float sumaMediasEsperaLiviano = 0;
		float sumaMediasOcioLiviano = 0;
		float sumaMediasTransitoLiviano = 0;
		// Sumas de medias
		for (int i = 0; i < replicacion; i++) {
			sumaMediasEsperaLiviano += mediasEsperaLiviano[i];
			sumaMediasOcioLiviano += mediasOcioLiviano[i];
			sumaMediasTransitoLiviano += mediasTransitoLiviano[i];
		}
		// Medias de medias (media global)
		float mediaDeMediasEsperaLiviano = sumaMediasEsperaLiviano / replicacion;
		float mediaDeMediasOcioLiviano = sumaMediasOcioLiviano / replicacion;
		float mediaDeMediasTransitoLiviano = sumaMediasTransitoLiviano / replicacion;
		// varianza
		double varianzaEsperaLiviano = 0;
		double varianzaOcioLiviano = 0;
		double varianzaTransitoLiviano = 0;
		for (int i = 0; i < replicacion; i++) {
			varianzaEsperaLiviano += Math.pow((mediasEsperaLiviano[i] - mediaDeMediasEsperaLiviano), 2)
					/ (replicacion - 1);
			varianzaOcioLiviano += Math.pow((mediasOcioLiviano[i] - mediaDeMediasOcioLiviano), 2) / (replicacion - 1);
			varianzaTransitoLiviano += Math.pow((mediasTransitoLiviano[i] - mediaDeMediasTransitoLiviano), 2)
					/ (replicacion - 1);
		}
		System.out.println("\t- Media de medias de espera: " + decimal.format(mediaDeMediasEsperaLiviano) + " mins");
		// Estimacion por intervalos, con Z=1,645 para obtener intervalos con 90% de
		// confianza
		estimador = Math.sqrt(varianzaEsperaLiviano);
		intervaloIzq = mediaDeMediasEsperaLiviano - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasEsperaLiviano + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");
		System.out.println("\n\t- Media de medias de ociosidad: " + decimal.format(mediaDeMediasOcioLiviano) + " mins");
		estimador = Math.sqrt(varianzaOcioLiviano);
		intervaloIzq = mediaDeMediasOcioLiviano - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasOcioLiviano + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");
		System.out.println(
				"\n\t- Media de medias de Transito: " + decimal.format(mediaDeMediasTransitoLiviano) + " mins");
		estimador = Math.sqrt(varianzaTransitoLiviano);
		intervaloIzq = mediaDeMediasTransitoLiviano - Z * (estimador / Math.sqrt(replicacion));
		intervaloDer = mediaDeMediasTransitoLiviano + Z * (estimador / Math.sqrt(replicacion));
		System.out.println("\t- Estimación (con 90% de certeza): (" + decimal.format(intervaloIzq) + " ; "
				+ decimal.format(intervaloDer) + ")");

		System.out.println("\n\n");
	}

}

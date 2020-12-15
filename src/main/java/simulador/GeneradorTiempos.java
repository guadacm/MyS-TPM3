/** Modelos y Simulación 2020 - UNSL
 * 
 * @author Guadalupe Medina
 */

package simulador;

import java.util.Random;

public class GeneradorTiempos {
    static GeneradorTiempos gt;
    int n = 0;
    float z = 0;
    private static Random random;

    public GeneradorTiempos() {
        n = 0;
        z = 0;
    }

    static {
        random = new Random(System.currentTimeMillis());
    }

    public static GeneradorTiempos getGeneradorTiempos() {
        if (gt == null)
            gt = new GeneradorTiempos();
        return gt;
    }

    public int getN() {
        return n;
    }

    public void setN() {
        n++;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z += z;
    }

    public static int getTiempoEntreArribos(float tiempo, int dias, int tipo) {
        if ((tiempo >= 420 * dias && tiempo <= 540 * dias) || (tiempo >= 1200 * dias && tiempo <= 1320 * dias)) {
            // HORARIO PICO
            if (tipo == 0) {
                // Pesado
                if (random.nextFloat() < 0.4)
                    return 60;
                else
                    return 90;
            } else if (tipo == 1) {
                // Mediano
                if (random.nextFloat() < 0.5)
                    return 10;
                else if (random.nextFloat() < 0.85)
                    return 20;
                else
                    return 30;
            } else {
                // Liviano
                if (random.nextFloat() < 0.35)
                    return 40;
                else
                    return 50;
            }
        } else {
            // HORARIO NORMAL
            if (tipo == 0) {
                // Pesado
                if (random.nextFloat() < 0.5)
                    return 120;
                else
                    return 180;
            } else if (tipo == 1) {
                // Mediano
                if (random.nextFloat() < 0.3)
                    return 20;
                else if (random.nextFloat() < 0.7)
                    return 30;
                else
                    return 40;
            } else {
                // Liviano
                if (random.nextFloat() < 0.25)
                    return 60;
                else
                    return 70;
            }
        }
    }

    public static float getTiempoDuracionServicio(int tipo) {
        GeneradorTiempos gt = GeneradorTiempos.getGeneradorTiempos();
        if (tipo == 0) {
            // Pesado distribucion exponencial mu=30(media) lambda=1/30
            float aux = (float) ((-30) * Math.log(1 - random.nextFloat()));
            gt.setN();
            gt.setZ(aux); // Hago la suma para obtener la normal aproximada
            return aux;

        } else if (tipo == 1) {
            // Medinano uniforme[10..20]
            return 10 + (20 - 10) * random.nextFloat();
        } else {
            // Liviano normal(120,30)
            // Uso la técnica de convolución con las variables de distribucion exponencial
            // generadas para transito liviano
            // 1. Normal aproximada N(n*30,n*(1/(1/30)^2))
            float mu = gt.getN() * 30; // media de la normal aproximada
            float sigma2 = gt.getN() * (1 / (float) Math.pow(1 / 30, 2)); // varianza de la normal aproximada
            // 2. Generar variable normal estandar
            float z1 = (gt.getZ() - mu) / (float) Math.sqrt(sigma2);
            // 3. Generar y retornar la variable deseada
            return (z1 * 120) + 30;
        }
    }

}

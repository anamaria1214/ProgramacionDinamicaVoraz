package algoritmos.Dinamica;

import java.util.Arrays;

public class MochilaEntera {

    public static void main(String[] args) {
        int[] valores = {60, 100, 120};
        int[] pesos = {10, 20, 30};
        int maxPeso = 50;

        //Prueba con Tabulación
        Objeto maxBeneficioTabulacion = mochilaEnteraTabulacion(valores, pesos, maxPeso);
        System.out.println("Beneficio máximo: " + maxBeneficioTabulacion.beneficio);
        System.out.println("Objetos que tomó: " + maxBeneficioTabulacion.camino);

        //Prueba con Memoización
        Objeto maxBeneficioMemoizacion = mochilaEnteraMemoizacion(valores, pesos, maxPeso);
        System.out.println("Beneficio máximo: " + maxBeneficioMemoizacion.beneficio);
        System.out.println("Objetos que tomó: " + maxBeneficioMemoizacion.camino);
    }

    private static Objeto mochilaEnteraMemoizacion(int[] valores, int[] pesos, int maxPeso) {
        Objeto[][] memo = new Objeto[valores.length + 1][maxPeso + 1];

        return mochilaRecursiva(valores, pesos, valores.length, maxPeso, memo);
    }

    private static Objeto mochilaRecursiva(int[] valores, int[] pesos, int n, int capacidad, Objeto[][] memo) {
        if (n == 0 || capacidad == 0) {
            return new Objeto(0, "");
        }

        if (memo[n][capacidad] != null) {
            return memo[n][capacidad];
        }

        int pesoActual = pesos[n - 1];
        int valorActual = valores[n - 1];

        Objeto resultado;

        if (pesoActual > capacidad) {
            resultado = mochilaRecursiva(valores, pesos, n - 1, capacidad, memo);
        }
        else {
            Objeto sinIncluir = mochilaRecursiva(valores, pesos, n - 1, capacidad, memo);
            int valorSinIncluir = sinIncluir.getBeneficio();

            Objeto incluyendo = mochilaRecursiva(valores, pesos, n - 1, capacidad - pesoActual, memo);
            int valorIncluyendo = valorActual + incluyendo.getBeneficio();

            int maxValor = Math.max(valorIncluyendo, valorSinIncluir);

            if (maxValor == valorIncluyendo) {
                resultado = new Objeto(
                        valorIncluyendo,
                        incluyendo.getCamino() + (n - 1) + " "
                );
            } else {
                resultado = new Objeto(
                        valorSinIncluir,
                        sinIncluir.getCamino()
                );
            }
        }

        memo[n][capacidad] = resultado;

        return resultado;
    }

    private static Objeto mochilaEnteraTabulacion(int[] valores, int[] pesos, int maxPeso) {
        Objeto[][] dp = new Objeto[valores.length + 1][maxPeso + 1];

        for (int i = 0; i <= valores.length; i++) {
            for (int j = 0; j <= maxPeso; j++) {
                dp[i][j] = new Objeto(0, "");
            }
        }

        for (int i = 1; i <= valores.length; i++) {
            for (int j = 1; j <= maxPeso; j++) {
                int pesoActual = pesos[i - 1];
                int valorActual = valores[i - 1];

                if (pesoActual > j) {
                    dp[i][j] = new Objeto(
                            dp[i - 1][j].getBeneficio(),
                            dp[i - 1][j].getCamino()
                    );
                }
                else {
                    int valorSinIncluir = dp[i - 1][j].getBeneficio();

                    int valorIncluyendo = valorActual + dp[i - 1][j - pesoActual].getBeneficio();

                    int maxValor = Math.max(valorIncluyendo, valorSinIncluir);

                    if (maxValor == valorIncluyendo) {
                        dp[i][j] = new Objeto(
                                valorIncluyendo,
                                dp[i - 1][j - pesoActual].getCamino() + (i - 1) + " "
                        );
                    } else {
                        dp[i][j] = new Objeto(
                                valorSinIncluir,
                                dp[i - 1][j].getCamino()
                        );
                    }
                }
            }
        }

        return dp[valores.length][maxPeso];
    }

    static class Objeto{

        int beneficio;
        String camino;

        public Objeto(int beneficio, String camino){
            this.beneficio =beneficio;
            this.camino=camino;
        }

        public int getBeneficio() {
            return beneficio;
        }

        public String getCamino() {
            return camino;
        }

        public void setBeneficio(int beneficio) {
            this.beneficio = beneficio;
        }

        public void setCamino(String camino) {
            this.camino = camino;
        }
    }

}

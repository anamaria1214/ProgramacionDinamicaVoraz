package algoritmos.Dinamica;

import java.util.Arrays;

public class MochilaEntera {

    public static void main(String[] args) {

    }

    public static int mochilaEntera(int [] valores, int [] pesos, int maxValue){
        Objeto [][] dp = new Objeto[valores.length+1][maxValue+1];

        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], new Objeto(0, ""));
        }

        for(int i=1; i<valores.length; i++){
            for(int j=1; j<maxValue+1; j++){
                if( j - pesos[i] > 0 ){
                    dp[i][j] = dp[i-1][j];
                }else{
                    int max = Math.max(dp[i-1][j].getBeneficio(), valores[i] + dp[i-1][j - pesos[i]].beneficio);
                }
            }

        }



        return 0;
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

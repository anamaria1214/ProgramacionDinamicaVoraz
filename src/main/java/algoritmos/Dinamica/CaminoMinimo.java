package algoritmos.Dinamica;

import java.util.Arrays;

import static java.util.Collections.min;

public class CaminoMinimo {

    public static void main(String[] args) {
        int [][] matriz= {{1,3,1}, {1,5,1}, {4,2,1}};
        System.out.println("Matriz inicial");
        System.out.println(Arrays.deepToString(matriz));
        System.out.println("Matriz Resultante");
        System.out.println(Arrays.deepToString(caminoMinimo2(matriz)));
    }

    public static int[][] caminoMinimo2(int [][] matriz){
        int [][] dp= new int[matriz.length][matriz[0].length];
        dp[0][0] = matriz[0][0];

        for(int i = 1; i<matriz[0].length; i++){
            dp[0][i] = matriz[0][i] + dp[0][i-1];
        }

        for(int i = 1; i<matriz.length; i++){
            dp[i][0] = matriz[i][0] + dp[i-1][0];
        }

        for(int i = 1; i < matriz.length; i++){
            for(int j = 1; j < matriz[0].length; j++){

                int min = Math.min( dp[i-1][j], dp[i][j-1] );
                dp[i][j] = matriz[i][j] + min;

            }
        }
        return dp;
    }

}

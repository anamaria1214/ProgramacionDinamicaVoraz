package algoritmos.Voraz;

import java.util.ArrayList;
import java.util.Arrays;

public class Devuelta {

    public static void main(String[] args) {
        int [] monedas= {50,50,100,100,200,200,100,100,200,200};
        Arrays.sort(monedas);


        ArrayList<Integer> aux= devuelta(monedas, 700);

        System.out.println(aux);
    }

    public static ArrayList<Integer> devuelta (int [] monedas, int dineroRestante){

        ArrayList<Integer> aux= new ArrayList<>();
        Arrays.sort(monedas);

        for(int i=monedas.length-1; i>=0 && dineroRestante!=0; i--){
            if(dineroRestante-monedas[i]>=0){
                aux.add(monedas[i]);
                dineroRestante-=monedas[i];
            }

        }
        return aux;
    }

}

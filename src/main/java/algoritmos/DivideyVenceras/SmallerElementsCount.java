package algoritmos.DivideyVenceras;
import java.util.*;

public class SmallerElementsCount {

    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] counts = new Integer[n];
        Arrays.fill(counts, 0);

        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        mergeSort(pairs, 0, n - 1, counts);
        return Arrays.asList(counts);
    }

    private static void mergeSort(int[][] pairs, int left, int right, Integer[] counts) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(pairs, left, mid, counts);
        mergeSort(pairs, mid + 1, right, counts);
        merge(pairs, left, mid, right, counts);
    }

    private static void merge(int[][] pairs, int left, int mid, int right, Integer[] counts) {
        List<int[]> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (pairs[i][0] <= pairs[j][0]) {
                counts[pairs[i][1]] += rightCount;
                temp.add(pairs[i++]);
            } else {
                rightCount++;
                temp.add(pairs[j++]);
            }
        }

        while (i <= mid) {
            counts[pairs[i][1]] += rightCount;
            temp.add(pairs[i++]);
        }

        while (j <= right) {
            temp.add(pairs[j++]);
        }

        for (int k = left; k <= right; k++) {
            pairs[k] = temp.get(k - left);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== SMALLER ELEMENTS COUNT ===");
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<Integer> result = countSmaller(nums);
        System.out.println("Resultado: " + result);
    }
}



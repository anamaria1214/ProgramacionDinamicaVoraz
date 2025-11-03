package algoritmos.DivideyVenceras;

import java.util.Scanner;

public class MajorityElement {

    public static int majorityElement(int[] nums) {
        return majorityRec(nums, 0, nums.length - 1);
    }

    private static int majorityRec(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = (left + right) / 2;
        int leftMajor = majorityRec(nums, left, mid);
        int rightMajor = majorityRec(nums, mid + 1, right);

        if (leftMajor == rightMajor) return leftMajor;

        int leftCount = count(nums, leftMajor, left, right);
        int rightCount = count(nums, rightMajor, left, right);

        return leftCount > rightCount ? leftMajor : rightMajor;
    }

    private static int count(int[] nums, int num, int left, int right) {
        int cnt = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== MAYORITY ELEMENT ===");
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int candidate = majorityElement(nums);

        // Verificar si realmente aparece más de la mitad
        int count = 0;
        for (int num : nums) if (num == candidate) count++;

        if (count > n / 2)
            System.out.println("Elemento mayoritario: " + candidate);
        else
            System.out.println("No hay mayoría. Retorna: -1");
    }
}



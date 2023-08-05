package CoreJava2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Ex16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashSet<Integer> hashSet = new HashSet<>();
        System.out.print("Nhập số lượng phần tử: ");
        int n = sc.nextInt();
        System.out.println("Nhập các phần tử:");
        for (int i = 0; i < n; i++) {
            hashSet.add(sc.nextInt());
        }
        int max = Collections.max(hashSet);
        int min = Collections.min(hashSet);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}

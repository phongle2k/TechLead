package Codelearn.MethodJava;

import java.util.Scanner;

public class Ex76_83 {
    public static int sumOfValue(int[] a){
        int sum = 0;
        for(int i = 0; i <a.length; i++){
            sum += a[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int []a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        System.out.println(sumOfValue(a));
    }
}

package Codelearn;

import java.util.Scanner;

public class Matran {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a[][] = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                a[i][j] = sc.nextInt();
            }
        }
        int sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sum += a[i][j];
            }
        }
        System.out.println(sum);
    }
}

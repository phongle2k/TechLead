package Codelearn.MethodJava;

import java.util.Scanner;

public class Ex77 {
    // Bạn hãy viết phương thức hiển thị ra màn hình các số chia hết cho 3 mà
    // không chia hết cho 5 trong mảng các số nguyên arr được nhập vào từ bàn phím.
    public static void chiaHet(int [] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 3 == 0 && arr[i] % 5 != 0){
                System.out.println(arr[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        chiaHet(a);
    }
}

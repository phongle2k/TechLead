package CoreJava2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.*;

/*
Hãy tạo một chương trình Java để nhập hai tập hợp (HashSet) các số nguyên từ người dùng.
Hãy tìm và hiển thị các phần tử chung (giao) của hai tập hợp
 */
public class Ex14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập tập hợp thứ nhất
        HashSet<Integer> set1 = new HashSet<>();
        System.out.print("Nhập số lượng phần tử trong tập hợp thứ nhất: ");
        int n1 = sc.nextInt();
        System.out.println("Nhập các phần tử của tập hợp thứ nhất:");
        for (int i = 0; i < n1; i++) {
            set1.add(sc.nextInt());
        }

        // Nhập tập hợp thứ hai
        HashSet<Integer> set2 = new HashSet<>();
        System.out.print("Nhập số lượng phần tử trong tập hợp thứ hai: ");
        int n2 = sc.nextInt();
        System.out.println("Nhập các phần tử của tập hợp thứ hai:");
        for (int i = 0; i < n2; i++) {
            set2.add(sc.nextInt());
        }

        // Tìm các phần tử chung (giao) của hai tập hợp
        HashSet<Integer> chung = new HashSet<>(set1);
        chung.retainAll(set2);

        // Hiển thị các phần tử chung (giao) của hai tập hợp
        if (!chung.isEmpty()) {
            System.out.println("Các phần tử chung của hai tập hợp là:");
            for (int ele : chung) {
                System.out.print(ele + " ");
            }
        } else {
            System.out.println("Không có phần tử chung nào giữa hai tập hợp.");
        }

    }
}

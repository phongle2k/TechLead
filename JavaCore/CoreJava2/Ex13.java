package CoreJava2;

import java.util.HashSet;
import java.util.Scanner;

/*
Hãy viết một chương trình Java để nhập một mảng các số nguyên từ người dùng và tạo một HashSet để lưu trữ các phần tử
của mảng. Sau đó, hãy hiển thị các phần tử trùng lặp trong mảng.
 */
public class Ex13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số lượng phần tử trong mảng: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng:");

        // Nhập các phần tử của mảng từ người dùng
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Tạo HashSet để lưu trữ các phần tử của mảng
        HashSet<Integer> uniqueElements = new HashSet<>();
        // Tạo HashSet để lưu trữ các phần tử trùng lặp trong mảng
        HashSet<Integer> duplicateElements = new HashSet<>();

        // Kiểm tra và lưu trữ các phần tử trùng lặp vào HashSet duplicateElements
        for (int element : arr) {
            if (!uniqueElements.add(element)) {
                duplicateElements.add(element);
            }
        }

        // Hiển thị các phần tử trùng lặp trong mảng
        if (!duplicateElements.isEmpty()) {
            System.out.println("Các phần tử trùng lặp trong mảng là:");
            for (int element : duplicateElements) {
                System.out.print(element + " ");
            }
        } else {
            System.out.println("Không có phần tử trùng lặp trong mảng.");
        }
    }
}

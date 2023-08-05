package CoreJava2;

import java.util.HashSet;
import java.util.Scanner;

/*
Hãy viết một chương trình Java để nhập một tập hợp (HashSet) các chuỗi từ người dùng và xóa tất cả các phần tử trùng lặp,
chỉ giữ lại một phần tử duy nhất cho mỗi giá trị.
 */
public class Ex17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập tập hợp thứ nhất
        HashSet<Integer> hashSet = new HashSet<>();
        System.out.print("Nhập số lượng phần tử: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập các phần tử:");
        for (int i = 0; i < n; i++) {
            hashSet.add(sc.nextInt());
        }
        // check trung lap
        HashSet<Integer> uniqueNum = new HashSet<>(hashSet); // Ham xoa trung lap

        System.out.println("Tap hop sau khi xoa phan tu trung lap: ");
        for(int num : uniqueNum){
            System.out.println(num);
        }
    }
}

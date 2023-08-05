package CoreJava2;

import java.util.HashSet;
import java.util.Scanner;

/*
Hãy viết một chương trình Java để nhập một tập hợp (HashSet) các chuỗi từ người dùng và đếm số lượng phần tử trong tập hợp.
 */
public class Ex18 {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong tap hop: ");
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++){
            String str = sc.nextLine();
            hashSet.add(str);
        }
        // dem so luong
        int count = hashSet.size();
        System.out.println(count);
    }
}

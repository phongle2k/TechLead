package CoreJava2;

import java.util.*;

/*
Bài tập 1: ArrayList Hãy tạo một chương trình Java để thực hiện các thao tác cơ bản trên một ArrayList.
Hãy bắt đầu với một ArrayList chứa các số nguyên và thực hiện các thao tác sau:
- Thêm các phần tử vào ArrayList.
- Hiển thị các phần tử trong ArrayList.
- Tính tổng của tất cả các phần tử trong ArrayList.
- Tìm giá trị lớn nhất và nhỏ nhất trong ArrayList.
- Xóa một phần tử cụ thể khỏi ArrayList.
- Kiểm tra một phần tử có tồn tại trong ArrayList hay không.
 */
public class Ex1 {
    public static void main(String[] args) {
        // Add
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        System.out.print("Enter number: ");
        int n = sc.nextInt();

        System.out.print("Enter array: ");
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }
        // Hien thi
        System.out.print("Mang vua nhap: ");
        System.out.println(numbers);
        // tinh tong
        int sum = 0;
        for(int num : numbers){
            sum += num;
        }
        System.out.println("Tong cac phan tu trong mang: " + sum);
        // Find Max, min
//        Arrays.sort(a);
//        System.out.println("Max: " + a[n-1]);
//        System.out.println("Min: " + a[0]);
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        // Xoa 1 phan tu mang
        System.out.print("Nhap phan tu can xoa: ");
        int x = sc.nextInt();
        boolean removed = numbers.remove(Integer.valueOf(x));
        if(removed){
            System.out.println("Xoa " +x+ " ra khoi mang");
        }
        else {
            System.out.println("Khong co " +x+" trong mang");
        }
        System.out.print("Mang sau khi xoa: ");
        System.out.println(numbers);

        // Kiem tra 1 so co nam trong nam hay k?
        System.out.println("Nhap so can kiem tra: ");
        int y = sc.nextInt();
        boolean exist = numbers.contains(y);
        if(exist){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }

}

package CoreJava2;

import java.util.*;

/*
Sắp xếp ArrayList Hãy tạo một chương trình Java để sắp xếp một ArrayList chứa các số nguyên theo thứ tự tăng dần và giảm dần.
Sử dụng các phương thức sắp xếp có sẵn trong Collection Framework để làm điều này.
 */
public class Ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        System.out.print("Nhap so luong array: ");
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }
        Collections.sort(numbers);
        System.out.println(numbers);

    }
}

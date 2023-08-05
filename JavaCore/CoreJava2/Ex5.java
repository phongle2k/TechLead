package CoreJava2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class Ex5 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap n: ");
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }
        System.out.print("Nhap target: ");
        int x = sc.nextInt();
        int [] ketqua = twoSum(numbers, x);
        System.out.print("[");
        for(int i = 0; i < ketqua.length; i++){
            System.out.print(ketqua[i]);
            if(i != ketqua.length - 1){
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    private static int[] twoSum(List<Integer> numbers, int x) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < numbers.size(); i++){
            int complement = x - numbers.get(i);
            if(hashMap.containsKey(complement)){
                return new int[]{ hashMap.get(complement), i};
            }
            hashMap.put(numbers.get(i), i);
        }
        return new int[]{};
    }
}

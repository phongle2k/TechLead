package CoreJava2;

import java.util.Scanner;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to
sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 */
public class Ex6 {
    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int maxProfit = maxProfit(prices);
//        System.out.println("Output: " + maxProfit);
//    }
//    public static int maxProfit(int[] prices) {
//        int minPrice = Integer.MAX_VALUE; // Initialize minPrice as the maximum possible value
//        int maxProfit = 0; // Initialize maxProfit as 0
//
//        for (int price : prices) {
//            // Update the minPrice if the current price is lower
//            if (price < minPrice) {
//                minPrice = price;
//            }
//            // Update the maxProfit if the difference between the current price and minPrice is higher
//            else if (price - minPrice > maxProfit) {
//                maxProfit = price - minPrice;
//            }
//        }
//
//        return maxProfit;
        Scanner scanner = new Scanner(System.in);

        // Nhập mảng giá cổ phiếu từ người dùng
        System.out.print("Nhập số lượng ngày giao dịch: ");
        int n = scanner.nextInt();
        int[] prices = new int[n];
        System.out.println("Nhập giá cổ phiếu trong từng ngày:");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        // Tìm ngày mua và ngày bán cổ phiếu để đạt lợi nhuận tối đa
        int maxProfit = 0;
        int buyDay = -1;
        int sellDay = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                    buyDay = i;
                    sellDay = j;
                }
            }
        }

        // Hiển thị kết quả
        if (maxProfit > 0) {
            System.out.println("Ngày mua: Ngày " + (buyDay + 1) + " (giá = " + prices[buyDay] + ")");
            System.out.println("Ngày bán: Ngày " + (sellDay + 1) + " (giá = " + prices[sellDay] + ")");
            System.out.println("Lợi nhuận tối đa: " + maxProfit);
        } else {
            System.out.println("Không có cách mua và bán cổ phiếu để đạt lợi nhuận.");
        }
    }
}

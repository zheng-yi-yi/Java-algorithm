package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 279. 完全平方数 https://leetcode.cn/problems/perfect-squares/description/
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */
public class Code_06_numSquares {
    static class Solution {
        public int numSquares(int n) {
            // dp[i]：整数 i 最少需要多少个完全平方数才能表示
            int[] dp = new int[n + 1];
            // 1 是完全平方数，任意正整数 i 都可以由 i 个 1 组成
            for (int i = 0; i <= n; i++) {
                dp[i] = i;
            }
            // 对于每个正整数 i，我们都尝试减去一个完全平方数 j*j，
            // 然后查看剩下的数（即 i-j*j）需要多少个完全平方数才能表示
            // 注意，要取最小，因此这里用 Math.min(a, b);
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numSquares(12)); // 3
        System.out.println(s.numSquares(13)); // 2
    }
}

package cn.zhengyiyi.leetcode.p07_number_theory;

/**
 * <a href="https://leetcode.cn/problems/fibonacci-number/description/">509. 斐波那契数</a>
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 */
public class Code_01_fib {
    static class Solution1 {    // 最优解法：矩阵快速幂，时间复杂度O(logn)
        public int fib(int n) {
            if (n == 0)
                return 0;
            if (n == 1)
                return 1;
            int[][] start = { // 初始矩阵
                    { 1, 0 }
            };
            int[][] base = { // 关系矩阵
                    { 1, 1 },
                    { 1, 0 }
            };
            int[][] ans = multiply(start, power(base, n - 1));
            return ans[0][0];
        }

        // 矩阵a乘矩阵b，其中a的列数一定要等于b的行数
        public int[][] multiply(int[][] a, int[][] b) {
            int n = a.length;
            int m = b[0].length;

            int[][] ans = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        ans[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return ans;
        }

        // 矩阵快速幂
        public int[][] power(int[][] m, int p) {
            int n = m.length;
            int[][] ans = new int[n][n];
            for (int i = 0; i < n; i++) {
                ans[i][i] = 1;
            }
            for (; p != 0; p >>= 1) {
                if ((p & 1) != 0) {
                    ans = multiply(ans, m);
                }
                m = multiply(m, m);
            }
            return ans;
        }
    }

    static class Solution2 {    // 动态规划，时间复杂度O(n)
        public int fib(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            int lastLast = 0, last = 1;
            for (int i = 2, cur; i <= n; i++) {
                cur = lastLast + last;
                lastLast = last;
                last = cur;
            }
            return last;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Solution1().fib(n)); // 55
        System.out.println(new Solution2().fib(n));       // 55
    }
}

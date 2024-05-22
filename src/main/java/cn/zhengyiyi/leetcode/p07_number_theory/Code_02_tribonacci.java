package cn.zhengyiyi.leetcode.p07_number_theory;

/**
 * <a href="https://leetcode.cn/problems/n-th-tribonacci-number/description/">1137. 第 N 个泰波那契数</a>
 * 泰波那契序列 Tn 定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 */
public class Code_02_tribonacci {
    static class Solution { // 矩阵快速幂
        public static int tribonacci(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 1;
            }
            int[][] start = { { 1, 1, 0 } };
            int[][] base = {
                    { 1, 1, 0 },
                    { 1, 0, 1 },
                    { 1, 0, 0 }
            };
            int[][] ans = multiply(start, power(base, n - 2));
            return ans[0][0];
        }

        // 矩阵相乘
        // a的列数一定要等于b的行数
        public static int[][] multiply(int[][] a, int[][] b) {
            int n = a.length;
            int m = b[0].length;
            int k = a[0].length;
            int[][] ans = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int c = 0; c < k; c++) {
                        ans[i][j] += a[i][c] * b[c][j];
                    }
                }
            }
            return ans;
        }

        // 矩阵快速幂
        public static int[][] power(int[][] m, int p) {
            int n = m.length;
            int[][] ans = new int[n][n];
            for (int i = 0; i < n; i++) {
                ans[i][i] = 1;
            }
            while(p != 0) {
                if((p & 1) == 1) {
                    ans = multiply(m, ans);
                }
                m = multiply(m, m);
                p >>= 1;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.tribonacci(4));
        System.out.println(Solution.tribonacci(25));
    }
}

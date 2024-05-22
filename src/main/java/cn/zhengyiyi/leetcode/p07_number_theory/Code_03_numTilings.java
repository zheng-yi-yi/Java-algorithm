package cn.zhengyiyi.leetcode.p07_number_theory;

/**
 * <a href="https://leetcode.cn/problems/domino-and-tromino-tiling/description/">790. 多米诺和托米诺平铺</a>
 * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。返回对 109 + 7 取模 的值。
 * 平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 * 示例 1:
 * 输入: n = 3
 * 输出: 5
 * 解释: 五种不同的方法如上所示。
 * 示例 2:
 * 输入: n = 1
 * 输出: 1
 */
public class Code_03_numTilings { // 一维k阶递推式，用矩阵快速幂加速求解
    static class Solution {
        int mod = 1000000007;

        public int numTilings(int n) {
            return f(n - 1);
        }

        // f(1) = 1
        // f(2) = 2
        // f(3) = 5
        // f(n) = 2 * f(n-1) + 0 * f(n-2) + f(n-3)
        public int f(int n) {
            if (n == 0) return 1;
            if (n == 1) return 2;
            if (n == 2) return 5;
            int[][] start = {
                    { 5, 2, 1, }
            };
            int[][] base = {
                    { 2, 1, 0 },
                    { 0, 0, 1 },
                    { 1, 0, 0 }
            };
            int[][] ans = multipy(start, power(base, n - 2));
            return ans[0][0];
        }

        public int[][] power(int[][] m, int p) {
            int n = m.length;
            int[][] ans = new int[n][n];
            for (int i = 0; i < n; i++) {
                ans[i][i] = 1;
            }
            while (p != 0) {
                if ((p & 1) == 1) {
                    ans = multipy(ans, m);
                }
                m = multipy(m, m);
                p >>= 1;
            }
            return ans;
        }

        // 矩阵相乘
        public int[][] multipy(int[][] a, int[][] b) {
            int n = a.length;
            int m = b[0].length;
            int[][] ans = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        ans[i][j] = (int) (((long) a[i][k] * b[k][j] + ans[i][j]) % mod);
                    }
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numTilings(3));
        System.out.println(solution.numTilings(1));
    }
}

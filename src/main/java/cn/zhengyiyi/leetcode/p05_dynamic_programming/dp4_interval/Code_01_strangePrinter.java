package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp4_interval;

/**
 * 664. 奇怪的打印机 https://leetcode.cn/problems/strange-printer/description/
 * 有台奇怪的打印机有以下两个特殊要求：
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。

 * 示例 1：
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 * 提示：
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 */
public class Code_01_strangePrinter {

    static class Solution1 {    // 记忆化搜索
        public int strangePrinter(String s) {
            char[] str = s.toCharArray();
            int n = str.length;
            int[][] dp = new int[n][n];
            for(int i = 0; i<n; i++) {
                for(int j = 0; j<n; j++) {
                    dp[i][j] = -1;
                }
            }
            return f(str, 0, n-1, dp);
        }

        public static int f(char[] str, int l, int r, int[][] dp) {
            if(dp[l][r] != -1) {
                return dp[l][r];
            }
            if(l == r) {
                return 1;
            }
            if(l + 1 == r) {
                return str[l] == str[r] ? 1 : 2;
            }
            int ans = 0;
            if(str[l] == str[r]) {
                ans = f(str, l+1, r, dp);
            } else {
                ans = Integer.MAX_VALUE;
                for(int m = l; m < r; m++) {
                    ans = Math.min(ans, f(str, l, m, dp) + f(str, m+1, r, dp));
                }
            }
            dp[l][r] = ans;
            return ans;
        }
    }

    static class Solution2 {    // 区间DP
        public int strangePrinter(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[][] dp = new int[n][n];
            dp[n - 1][n - 1] = 1;
            for (int i = 0; i < n - 1; i++) {
                dp[i][i] = 1;
                dp[i][i + 1] = s[i] == s[i + 1] ? 1 : 2;
            }
            for (int l = n - 3, ans; l >= 0; l--) {
                for (int r = l + 2; r < n; r++) {
                    if (s[l] == s[r]) {
                        dp[l][r] = dp[l][r - 1]; // 或者：dp[l][r] = dp[l + 1][r];
                    } else {
                        ans = Integer.MAX_VALUE;
                        for (int m = l; m < r; m++) {
                            ans = Math.min(ans, dp[l][m] + dp[m + 1][r]);
                        }
                        dp[l][r] = ans;
                    }
                }
            }
            return dp[0][n - 1];
        }
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        String s1 = "aaabbb";
        System.out.println(solution1.strangePrinter(s1));  // Expected output: 2

        Solution2 solution2 = new Solution2();
        String s2 = "aba";
        System.out.println(solution2.strangePrinter(s2));  // Expected output: 2
    }
}

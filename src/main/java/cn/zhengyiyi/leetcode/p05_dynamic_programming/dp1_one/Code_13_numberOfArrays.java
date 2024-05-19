package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 1416. 恢复数组 <a href="https://leetcode.cn/problems/restore-the-array/description/">...</a>
 * 某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 [1, k] 之间，且数组中的数字都没有前导 0 。
 * 给你字符串 s 和整数 k 。可能会有多种不同的数组恢复结果。
 * 按照上述程序，请你返回所有可能输出字符串 s 的数组方案数。
 * 由于数组方案数可能会很大，请你返回它对 10^9 + 7 取余 后的结果。
 * 示例 1：
 * 输入：s = "1000", k = 10000
 * 输出：1
 * 解释：唯一一种可能的数组方案是 [1000]
 * 示例 2：
 * 输入：s = "1000", k = 10
 * 输出：0
 * 解释：不存在任何数组方案满足所有整数都 >= 1 且 <= 10 同时输出结果为 s 。
 * 示例 3：
 * 输入：s = "1317", k = 2000
 * 输出：8
 * 解释：可行的数组方案为 [1317]，[131,7]，[13,17]，[1,317]，[13,1,7]，[1,31,7]，[1,3,17]，[1,3,1,7]
 * 示例 4：
 * 输入：s = "2020", k = 30
 * 输出：1
 * 解释：唯一可能的数组方案是 [20,20] 。 [2020] 不是可行的数组方案，原因是 2020 > 30 。 [2,020] 也不是可行的数组方案，因为 020 含有前导 0 。
 */
public class Code_13_numberOfArrays {
    static class Solution {
        public int numberOfArrays(String s, int k) {
            int n = s.length();
            int mod = 1000000007;
            long[] dp = new long[n + 1];    // dp[i]为字符串s的前i个字符可以构成的数组方案数
            dp[n] = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    continue;
                }
                long num = 0;
                for (int j = i; j < n; j++) {
                    num = num * 10 + s.charAt(j) - '0';
                    if (num > k) {
                        break;
                    }
                    dp[i] = (dp[i] + dp[j + 1]) % mod;
                }
            }
            return (int)dp[0];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numberOfArrays("1000", 10000)); // 1
        System.out.println(s.numberOfArrays("1000", 10)); // 0
        System.out.println(s.numberOfArrays("1317", 2000)); // 8
        System.out.println(s.numberOfArrays("2020", 30)); // 1
    }
}

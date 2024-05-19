package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 91. 解码方法 <a href="https://leetcode.cn/problems/decode-ways/">...</a>
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Code_12_numDecodings {
    static class Solution1 { // 记忆化搜索
        public int numDecodings(String str) {
            char[] s = str.toCharArray();
            int n = s.length;
            int[] dp = new int[n];
            for(int i = 0; i<n; i++) {
                dp[i] = -1;
            }
            return f(s, dp, 0);
        }

        public int f(char[] s, int[] dp, int idx) {
            if(idx == s.length) {
                return 1;
            }
            if(dp[idx] != -1) {
                return dp[idx];
            }
            int ans = 0;
            if(s[idx] != '0') {
                ans += f(s, dp, idx+1);
                if(idx + 1 < s.length) {
                    if((s[idx] == '1') || ((s[idx] == '2') && (s[idx+1] < '7'))) {
                        ans += f(s, dp, idx+2);
                    }
                }
            }
            dp[idx] = ans;
            return ans;
        }
    }

    static class Solution2 {
        public int numDecodings(String s) {
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                int a = s.charAt(i - 2) - '0';
                int b = s.charAt(i - 1) - '0';
                if (a != 0 && a * 10 + b <= 26) {
                    dp[i] += dp[i - 2];
                }
                if (b != 0) {
                    dp[i] += dp[i - 1];
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        System.out.println(s1.numDecodings("12")); // 2
        System.out.println(s1.numDecodings("226")); // 3

        Solution2 s2 = new Solution2();
        System.out.println(s2.numDecodings("12")); // 2
        System.out.println(s2.numDecodings("226")); // 3
    }
}

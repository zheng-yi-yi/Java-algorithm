package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;
/**
 * 2606. 找到最大开销的子字符串 https://leetcode.cn/problems/find-the-substring-with-maximum-cost/description/
 * 给你一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。
 * 子字符串的开销 是一个子字符串中所有字符对应价值之和。空字符串的开销是 0 。
 * 字符的价值 定义如下：
 * 如果字符不在字符串 chars 中，那么它的价值是它在字母表中的位置（下标从 1 开始）。
 * 比方说，'a' 的价值为 1 ，'b' 的价值为 2 ，以此类推，'z' 的价值为 26 。
 * 否则，如果这个字符在 chars 中的位置为 i ，那么它的价值就是 vals[i] 。
 * 请你返回字符串 s 的所有子字符串中的最大开销。
 * 示例 1：
 * 输入：s = "adaa", chars = "d", vals = [-1000]
 * 输出：2
 * 解释：字符 "a" 和 "d" 的价值分别为 1 和 -1000 。
 * 最大开销子字符串是 "aa" ，它的开销为 1 + 1 = 2 。
 * 2 是最大开销。
 * 示例 2：
 * 输入：s = "abc", chars = "abc", vals = [-1,-1,-1]
 * 输出：0
 * 解释：字符 "a" ，"b" 和 "c" 的价值分别为 -1 ，-1 和 -1 。
 * 最大开销子字符串是 "" ，它的开销为 0 。
 * 0 是最大开销。
 */
public class Code_03_maximumCostSubstring {
    static public class Solution {
        public int maximumCostSubstring(String s, String chars, int[] vals) {
            // 生成cost数组
            int[] cost = new int[26];
            for(int i = 0; i<26; i++) {
                cost[i] = i+1;
            }
            for (int i = 0; i < chars.length(); i++) {
                cost[chars.charAt(i) - 'a'] = vals[i];
            }
            // dp[i]: 以第i个字符结尾的子字符串的最大开销
            int[] dp = new int[s.length()];
            dp[0] = cost[s.charAt(0) - 'a'];
            int maxCost = dp[0];
            for (int i = 1; i < s.length(); i++) {
                dp[i] = Math.max(dp[i - 1] + cost[s.charAt(i) - 'a'], cost[s.charAt(i) - 'a']);
                maxCost = Math.max(maxCost, dp[i]);
            }

            return Math.max(maxCost, 0);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "adaa";
        String chars1 = "d";
        int[] vals1 = {-1000};
        String s2 = "abc";
        String chars2 = "abc";
        int[] vals2 = {-1, -1, -1};
        System.out.println(s.maximumCostSubstring(s1, chars1, vals1)); // 2
        System.out.println(s.maximumCostSubstring(s2, chars2, vals2)); // 0
    }
}

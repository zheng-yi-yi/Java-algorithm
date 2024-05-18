package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 467. 环绕字符串中唯一的子字符串 <a href="https://leetcode.cn/problems/unique-substrings-in-wraparound-string/description/">...</a>
 * 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串，所以 base 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现。
 * 示例 1：
 * 输入：s = "a"
 * 输出：1
 * 解释：字符串 s 的子字符串 "a" 在 base 中出现。
 * 示例 2：
 * 输入：s = "cac"
 * 输出：2
 * 解释：字符串 s 有两个子字符串 ("a", "c") 在 base 中出现。
 * 示例 3：
 * 输入：s = "zab"
 * 输出：6
 * 解释：字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class Code_09_findSubstringInWraproundString {
    static class Solution {
        public int findSubstringInWraproundString(String str) {
            int n = str.length();
            int[] s = new int[n];
            // abc...z -> 0, 1, 2....25
            for (int i = 0; i < n; i++) {
                s[i] = str.charAt(i) - 'a';
            }
            // dp[0] : s中必须以'a'作结尾的连续子串，最大延伸长度是多少，延伸必须跟据base串规则
            int[] dp = new int[26];
            dp[s[0]] = 1;
            for (int i = 1, cur, pre, len = 1; i < n; i++) {
                pre = s[i - 1];
                cur = s[i];
                if ((pre == 25 && cur == 0) || pre + 1 == cur) {
                    // (前一个字符是'z' && 当前字符是'a') || 前一个字符比当前字符的ascii码少1
                    len++;
                } else {
                    len = 1;
                }
                dp[cur] = Math.max(dp[cur], len);
            }
            int ans = 0;
            for (int i = 0; i < 26; i++) {
                ans += dp[i];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findSubstringInWraproundString("a")); // 1
        System.out.println(s.findSubstringInWraproundString("cac")); // 2
        System.out.println(s.findSubstringInWraproundString("zab")); // 6
    }
}

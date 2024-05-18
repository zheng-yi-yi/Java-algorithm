package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/word-break/">139. 单词拆分</a>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class Code_11_wordBreak {
    static class Solution {
        // 解题思路：判断字符串s是否能拆成若干个字典单词
        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            Set<String> set = new HashSet<>();
            int maxLen = 0;
            for(String word : wordDict) {
                set.add(word);
                maxLen = Math.max(maxLen, word.length());
            }
            boolean[] dp = new boolean[n+1];    // dp[i]: 表示字符串s的长度为i的前缀是否可以拆分为字典单词
            dp[0] = true;   // 长度为0的前缀可以拆分为字典单词（空串）
            for(int i = 1, end; i<=n; i++) {
                end = Math.min(i, maxLen);
                for(int j = 1; j<=end; j++) {
                    dp[i] |= (dp[i - j] && set.contains(s.substring(i-j, i)));
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("leet");
        wordDict1.add("code");
        System.out.println(s.wordBreak("leetcode", wordDict1)); // true
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");
        wordDict2.add("pen");
        System.out.println(s.wordBreak("applepenapple", wordDict2)); // true
    }
}

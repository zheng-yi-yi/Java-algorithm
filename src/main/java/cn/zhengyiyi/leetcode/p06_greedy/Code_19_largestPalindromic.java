package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/largest-palindromic-number/description/">2384. 最大回文数字</a>
 * 给你一个仅由数字（0 - 9）组成的字符串 num 。
 * 请你找出能够使用 num 中数字形成的 最大回文 整数，并以字符串形式返回。该整数不含 前导零 。
 * 注意：
 * 你 无需 使用 num 中的所有数字，但你必须使用 至少 一个数字。
 * 数字可以重新排序。
 * 示例 1：
 * 输入：num = "444947137"
 * 输出："7449447"
 * 解释：
 * 从 "444947137" 中选用数字 "4449477"，可以形成回文整数 "7449447" 。
 * 可以证明 "7449447" 是能够形成的最大回文整数。
 * 示例 2：
 * 输入：num = "00009"
 * 输出："9"
 * 解释：
 * 可以证明 "9" 能够形成的最大回文整数。
 * 注意返回的整数不应含前导零。
 */
public class Code_19_largestPalindromic {
    static class Solution {
        public String largestPalindromic(String num) {
            int n = num.length();
            // '0' ~ '9'字符对应整数为48 ~ 57
            int[] cnts = new int[58];
            for (char a : num.toCharArray()) {
                cnts[a]++;
            }
            char[] ans = new char[n];
            int leftSize = 0;
            char mid = 0;
            for (char i = '9'; i >= '1'; i--) {
                if ((cnts[i] & 1) == 1 && mid == 0) {
                    mid = i;
                }
                for (int j = cnts[i] / 2; j > 0; j--) {
                    ans[leftSize++] = i;
                }
            }
            if (leftSize == 0) { // '1'~'9'每一种字符出现次数 <= 1
                if (mid == 0) { // '1'~'9'每一种字符出现次数 == 0
                    return "0";
                } else { // '1'~'9'有若干字符出现次数 == 1，其中最大的字符是mid
                    return String.valueOf(mid);
                }
            }
            // '1'~'9'有若干字符出现次数 >= 2，左部分已经建立，再考虑把'0'字符填进来
            for (int j = cnts['0'] / 2; j > 0; j--) {
                ans[leftSize++] = '0';
            }
            int len = leftSize;
            // 把中点安插进去
            if (mid == 0 && (cnts['0'] & 1) == 1) {
                mid = '0';
            }
            if (mid != 0) {
                ans[len++] = mid;
            }
            // 左部分逆序拷贝给右部分
            for (int i = leftSize - 1; i >= 0; i--) {
                ans[len++] = ans[i];
            }
            return new String(ans, 0, len);
        }
    }

    public static void main(String[] args) {
        String num = "444947137";
        Solution solution = new Solution();
        System.out.println(solution.largestPalindromic(num));
    }
}

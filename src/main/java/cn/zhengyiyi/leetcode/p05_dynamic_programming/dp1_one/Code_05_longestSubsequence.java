package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

import java.util.HashMap;
import java.util.Map;

/**
 * 1218. 最长定差子序列 https://leetcode.cn/problems/longest-arithmetic-subsequence-of-given-difference/
 * 给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 * 示例 1：
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 */
public class Code_05_longestSubsequence {
    static class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            int n = arr.length;
            int[] dp = new int[n]; // dp[i]: 以i结尾的最长等差子序列的长度是多少？
            dp[0] = 1;
            int ans = dp[0];
            Map<Integer, Integer> map = new HashMap<>();
            map.put(arr[0], 0);
            // 可能性分析：
            // 设当前遍历的元素是 arr[i]，因为公差为 difference，则上一个元素 x 是 arr[i] - difference
            // （1）如果 [0...i-1] 范围内没有出现过 x，则此时 dp[i] = 1
            // （2）否则，dp[i] = dp[x所处的位置] + 1
            for(int i = 1; i<n; i++) {
                if(map.containsKey(arr[i] - difference)) {
                    dp[i] = dp[map.get(arr[i] - difference)] + 1;
                } else {
                    dp[i] = 1;
                }
                map.put(arr[i], i);
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr1 = {1, 2, 3, 4};
        int difference1 = 1;
        int[] arr2 = {1, 3, 5, 7};
        int difference2 = 1;
        int[] arr3 = {1, 5, 7, 8, 5, 3, 4, 2, 1};
        int difference3 = -2;
        System.out.println(s.longestSubsequence(arr1, difference1)); // 4
        System.out.println(s.longestSubsequence(arr2, difference2)); // 1
        System.out.println(s.longestSubsequence(arr3, difference3)); // 4
    }
}

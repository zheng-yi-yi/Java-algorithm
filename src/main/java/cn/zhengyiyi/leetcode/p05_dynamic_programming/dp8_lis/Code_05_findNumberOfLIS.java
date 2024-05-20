package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp8_lis;

import java.util.Arrays;
/**
 * <a href="https://leetcode.cn/problems/number-of-longest-increasing-subsequence/">673. 最长递增子序列的个数</a>
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 * 注意 这个数列必须是 严格 递增的。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 */
public class Code_05_findNumberOfLIS {
    static class Solution {
        public int findNumberOfLIS(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];  // dp[i]: 以下标 i 结尾的最长递增子序列长度
            int[] counts = new int[n];  // counts[i]: 以下标i结尾的最长递增子序列的个数
            int maxLen = 1;     // 数组 nums 中最长递增子序列长度
            // 最终答案：所有满足 dp[i]=maxLen的下标i对应的counts[i]之和
            Arrays.fill(dp, 1);
            Arrays.fill(counts, 1);
            int ans = 1;
            for(int i = 1; i<n; i++) {
                for(int j = 0; j<i; j++) {
                    if (nums[i] > nums[j]) {
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            counts[i] = counts[j];
                        } else if (dp[j] + 1 == dp[i]) {
                            counts[i] += counts[j];
                        }
                    }
                }
                if(dp[i] > maxLen) {
                    maxLen = dp[i];
                    ans = counts[i];
                } else if(dp[i] == maxLen) {
                    ans += counts[i];
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findNumberOfLIS(new int[]{1,3,5,4,7})); // 2
        System.out.println(s.findNumberOfLIS(new int[]{2,2,2,2,2})); // 5
    }
}

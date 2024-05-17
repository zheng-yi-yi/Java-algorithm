package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 53. 最大子数组和 https://leetcode.cn/problems/maximum-subarray/description/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。

 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class Code_02_maxSubArray {
    static class Solution {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n]; // dp[i]：以下标 i 结尾的最大子数组之和
            dp[0] = nums[0];
            int ans = dp[0];

            // 以i结尾的子数组，是否包含以i-1结尾的子数组
            for(int i = 1; i<n; i++) {
                dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
                ans = Math.max(ans, dp[i]);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(s.maxSubArray(nums)); // 6
    }
}

package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

import java.util.Arrays;

/**
 * 2369. 检查数组是否存在有效划分 <a href="https://leetcode.cn/problems/check-if-there-is-a-valid-partition-for-the-array/">...</a>
 * 给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为一个或多个 连续 子数组。
 * 如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
 * 子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
 * 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
 * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
 * 如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
 * 示例 1：
 * 输入：nums = [4,4,4,5,6]
 * 输出：true
 * 解释：数组可以划分成子数组 [4,4] 和 [4,5,6] 。
 * 这是一种有效划分，所以返回 true 。
 */
public class Code_07_validPartition {
    static class Solution1 {   // 递归，记忆化搜索
        public boolean validPartition(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, -1);
            return f(nums, nums.length, 0, dp);
        }

        public boolean f(int[] nums,int n, int idx, int[] dp) {
            if(idx == nums.length) {
                return true;
            }
            if(dp[idx] != -1) {
                return dp[idx] == 1;
            }
            boolean p = false;
            if(idx < n && idx+1<n && nums[idx] == nums[idx+1]) {
                p |= f(nums, n, idx+2, dp);
            }
            if(idx < n && idx+1<n && idx+2<n && nums[idx] == nums[idx+1] && nums[idx+1] == nums[idx+2]) {
                p |= f(nums, n, idx+3, dp);
            }
            if(idx < n && idx+1<n && idx+2<n && nums[idx]+1 == nums[idx+1] && nums[idx+1]+1 == nums[idx+2]) {
                p |= f(nums, n, idx+3, dp);
            }
            dp[idx] = p ? 1 : 0;
            return p;
        }
    }

    static class Solution2 {
        public boolean validPartition(int[] nums) {
            int n = nums.length;
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 2; i <= n; i++) {
                if (nums[i - 1] == nums[i - 2]) {
                    dp[i] = dp[i] || dp[i - 2];
                }
                if (i >= 3) {
                    if (nums[i - 1] == nums[i - 2] &&
                        nums[i - 2] == nums[i - 3] || nums[i - 1] - nums[i - 2] == 1 &&
                        nums[i - 2] - nums[i - 3] == 1) {
                        dp[i] = dp[i] || dp[i - 3];
                    }
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 4, 4, 5, 6};
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.validPartition(nums)); // true
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.validPartition(nums)); // true
    }
}

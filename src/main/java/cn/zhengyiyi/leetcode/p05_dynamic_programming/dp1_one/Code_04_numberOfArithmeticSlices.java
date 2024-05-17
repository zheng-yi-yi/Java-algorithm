package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 413. 等差数列划分 https://leetcode.cn/problems/arithmetic-slices/
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 */
public class Code_04_numberOfArithmeticSlices {
    static class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            if(n <= 2) {
                return 0;
            }
            int[] dp = new int[n];
            dp[2] = nums[2] - nums[1] == nums[1] - nums[0] ? 1 : 0;
            int ans = dp[2];
            for(int i = 3; i<n; i++) {
                if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                    dp[i] = dp[i-1] + 1;
                } else {
                    dp[i] = 0;
                }
                ans += dp[i];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 2, 3, 4};
        System.out.println(s.numberOfArithmeticSlices(nums)); // 3
    }
}

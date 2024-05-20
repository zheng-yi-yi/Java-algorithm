package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 *  <a href="https://leetcode.cn/problems/partition-array-for-maximum-sum/description/">1043. 分隔数组以得到最大和</a>
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 * 示例 1：
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 * 示例 2：
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * 输入：arr = [1], k = 1
 * 输出：1
 */
public class Code_15_maxSumAfterPartitioning {
    static class Solution {
        public int maxSumAfterPartitioning(int[] arr, int k) {
            int n = arr.length;
            // dp[i]: arr数组前i个元素组成的数组，分割后的元素最大和
            int[] dp = new int[n + 1];
            dp[0] = 0;  // 子数组为空时，元素最大和为0
            for (int i = 1; i <= n; i++) {
                int maxVal = 0; // 最后一个子数组（范围在 [j,...,i-1] 以内）的最大元素
                int start = Math.max(i - k, 0);
                for (int j = i - 1; j >= start; j--) {
                    maxVal = Math.max(maxVal, arr[j]);
                    dp[i] = Math.max(dp[i], dp[j] + maxVal * (i - j));
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3)); // 84
        System.out.println(s.maxSumAfterPartitioning(new int[]{1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3}, 4)); // 83
        System.out.println(s.maxSumAfterPartitioning(new int[]{1}, 1)); // 1
    }
}
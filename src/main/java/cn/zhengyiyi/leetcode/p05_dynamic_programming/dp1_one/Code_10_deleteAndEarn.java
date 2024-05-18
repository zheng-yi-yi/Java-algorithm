package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

import java.util.Arrays;

/**
 * 740. 删除并获得点数 <a href="https://leetcode.cn/problems/delete-and-earn/description/">...</a>
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 */
public class Code_10_deleteAndEarn {
    static class Solution {
        public int deleteAndEarn(int[] nums) {
            int m = Arrays.stream(nums).max().getAsInt();
            int[] cnt = new int[m+1];
            for(int num : nums) {
                cnt[num]++;
            }
            int[] dp = new int[m+1]; // dp[i]: 不超过 i 的正整数范围中的最大点数
            dp[0] = 0;
            dp[1] = cnt[1];
            for(int i = 2, p1, p2; i<=m; i++) {
                p1 = dp[i-1];   // 不删除整数i
                p2 = dp[i-2] + i*cnt[i];    // 删除整数i
                dp[i] = Math.max(p1, p2);
            }
            return dp[m];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.deleteAndEarn(new int[]{3, 4, 2})); // 6
        System.out.println(s.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4})); // 9
    }
}

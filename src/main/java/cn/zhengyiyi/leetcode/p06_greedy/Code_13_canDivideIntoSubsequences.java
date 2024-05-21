package cn.zhengyiyi.leetcode.p06_greedy;

/**
 * 将数组分成几个递增序列：
 * 给定一个有序的正数数组 nums 和整数 K，需要判断该数组是否可以被分成一个或几个长度至少为 K 的不相交的递增子序列。
 * 数组中的所有数字都需要被若干不相交的递增子序列包含。
 * 题目链接：<a href="https://leetcode.cn/problems/divide-array-into-increasing-sequences/">将数组分成几个递增序列</a>
 * 示例：
 * 输入：nums = [1,2,2,3,3,4,4], K = 3
 * 输出：true
 * 解释：数组可以被分为 [1,2,3,4] 和 [2,3,4] 两个递增子序列。
 * 输入：nums = [5,6,6,7,8], K = 3
 * 输出：false
 * 解释：无法将数组分为长度至少为3的递增子序列。
 * 注意：
 * - 1 <= nums.length <= 10^5
 * - 1 <= K <= nums.length
 * - 1 <= nums[i] <= 10^9
 * - nums 是一个非递减数组
 */
public class Code_13_canDivideIntoSubsequences {
    static class Solution {
        public boolean canDivideIntoSubsequences(int[] nums, int k) {
            int cnt = 1;
            // maxCnt : 最大词频
            int maxCnt = 1;
            // 在有序数组中，求某个数的最大词频
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] != nums[i]) {
                    maxCnt = Math.max(maxCnt, cnt);
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
            // 向下取整如果满足 >= k
            // 那么所有的递增子序列长度一定 >= k
            return nums.length / maxCnt >= k;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.canDivideIntoSubsequences(new int[]{1, 2, 2, 3, 3, 4, 4}, 3)); // true
        System.out.println(s.canDivideIntoSubsequences(new int[]{5, 6, 6, 7, 8}, 3)); // false
    }
}
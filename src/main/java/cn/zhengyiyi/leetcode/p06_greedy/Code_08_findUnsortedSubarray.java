package cn.zhengyiyi.leetcode.p06_greedy;

/**
 * 581. 最短无序连续子数组 https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/description/
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 */
public class Code_08_findUnsortedSubarray {
    static class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int n = nums.length;
            // max > 当前数，认为不达标
            // 从左往右遍历，记录最右不达标的位置
            int right = -1;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if (max > nums[i]) {
                    right = i;
                }
                max = Math.max(max, nums[i]);
            }
            int min = Integer.MAX_VALUE;
            // min < 当前数，认为不达标
            // 从右往左遍历，记录最左不达标的位置
            int left = n;
            for (int i = n - 1; i >= 0; i--) {
                if (min < nums[i]) {
                    left = i;
                }
                min = Math.min(min, nums[i]);
            }
            return Math.max(0, right - left + 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(solution.findUnsortedSubarray(nums1));  // Expected output: 5
    }
}

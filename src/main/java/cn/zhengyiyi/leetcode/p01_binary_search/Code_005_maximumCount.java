package cn.zhengyiyi.leetcode.p01_binary_search;
/**
 * 744. 寻找比目标字母大的最小字母 https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer/
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
 * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
 * 注意：0 既不是正整数也不是负整数。
 *
 * 示例 1：
 * 输入：nums = [-2,-1,-1,1,2,3]
 * 输出：3
 * 解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
 * 示例 2：
 * 输入：nums = [-3,-2,-1,0,0,1,2]
 * 输出：3
 * 解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
 * 示例 3：
 * 输入：nums = [5,20,66,1314]
 * 输出：4
 * 解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
 */
public class Code_005_maximumCount {
    //找到第一个非负数（即0）和第一个正数（即1）的位置，通过二者的位置计算出负数和正数的数量即可
    public static int maximumCount(int[] nums) {
        // 原理：
        int p1 = lowerBound(nums, 0);
        int p2 = lowerBound(nums, 1);
        return Math.max(p1, nums.length - p2);
    }

    /**
     * 二分查找：找到第一个大于等于 val 的数的位置
     * @param nums  数组
     * @param val   目标值
     * @return   第一个大于等于 val 的数的位置
     */
    public static int lowerBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] >= val) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    /**
     * 暴力：遍历数组，统计正数和负数的个数，返回最大值
     */
    public static int func(int[] nums) {
        int pos = 0, neg = 0;
        for (int num : nums) {
            if (num > 0) {
                pos++;
            } else if (num < 0) {
                neg++;
            }
        }
        return Math.max(pos, neg);
    }

    public static void main(String[] args) {
        int[] nums1 = {-2,-1,-1,1,2,3};
        System.out.println(maximumCount(nums1));  // 输出: 3

        int[] nums2 = {-3,-2,-1,0,0,1,2};
        System.out.println(maximumCount(nums2));  // 输出: 3

        int[] nums3 = {5,20,66,1314};
        System.out.println(maximumCount(nums3));  // 输出: 4
    }
}
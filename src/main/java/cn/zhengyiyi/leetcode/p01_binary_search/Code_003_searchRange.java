package cn.zhengyiyi.leetcode.p01_binary_search;
/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置 https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 */
public class Code_003_searchRange {
    public static int[] searchRange(int[] nums, int target) {
        int ans1 = f1(nums, target);
        int ans2 = f2(nums, target);
        return new int[]{ans1, ans2};
    }

    // 查找元素第一次出现的位置，如果不存在返回-1
    public static int f1(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while(l <= r) {
            int mid = l + (r-l)/2;

            if(nums[mid] == target) {
                ans = mid;
                r = mid - 1;
            } else if(nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    // 查找元素最后一次出现的位置，如果不存在返回-1
    public static int f2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while(l <= r) {
            int mid = l + (r-l)/2;

            if(nums[mid] == target) {
                ans = mid;
                l = mid + 1;
            } else if(nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,10};
        int target1 = 8;
        int[] result1 = searchRange(nums1, target1);
        System.out.println("[" + result1[0] + "," + result1[1] + "]");  // 输出: [3,4]

        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        int[] result2 = searchRange(nums2, target2);
        System.out.println("[" + result2[0] + "," + result2[1] + "]");  // 输出: [-1,-1]

        int[] nums3 = {};
        int target3 = 0;
        int[] result3 = searchRange(nums3, target3);
        System.out.println("[" + result3[0] + "," + result3[1] + "]");  // 输出: [-1,-1]
    }
}
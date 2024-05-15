package cn.zhengyiyi.leetcode.p01_binary_search;
/**
 * 35. 搜索插入位置 https://leetcode-cn.com/problems/search-insert-position/
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 提示:
 * 1 <= nums.length <= 10^4
 * -104 <= nums[i] <= 10^4
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 10^4
 */
public class Code_002_search_insert_position {
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,5,6};
        int target1 = 5;
        System.out.println(searchInsert(nums1, target1));  // 输出: 2

        int[] nums2 = {1,3,5,6};
        int target2 = 2;
        System.out.println(searchInsert(nums2, target2));  // 输出: 1

        int[] nums3 = {1,3,5,6};
        int target3 = 7;
        System.out.println(searchInsert(nums3, target3));  // 输出: 4
    }
}
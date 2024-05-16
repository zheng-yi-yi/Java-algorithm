package cn.zhengyiyi.leetcode.p02_prefix_sum;

import java.util.Arrays;
/**
 * 2389. 和有限的最长子序列 https://leetcode.cn/problems/longest-subsequence-with-limited-sum/description/
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 * 示例 1：
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * 示例 2：
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 */
public class Code_02_answerQueries {
    // 元素在数组中的位置无所谓，因此我们计算前缀元素和，对应的元素和一定能在原数组中找到对应的子序列
    // 有了前缀和处理后，我们从小到大选择尽量多的元素使得这些元素的和不超过询问值。
    // 可使用二分搜索，加速计算。
    static class Solution {
        public int[] answerQueries(int[] nums, int[] queries) {
            int n = nums.length;
            int m = queries.length;
            Arrays.sort(nums);
            for (int i = 1; i < n; ++i) {
                nums[i] += nums[i - 1]; // 原地求前缀和
            }
            int[] ans = new int[m];
            for (int i = 0; i < m; i++) {
                ans[i] = binarySearch(nums, queries[i]) == -1 ? n : binarySearch(nums, queries[i]);
            }

            return ans;
        }

        // 查找第一个 > tar 的元素的位置
        public int binarySearch(int[] nums, int tar) {
            int l = 0, r = nums.length - 1;
            int ans = -1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (nums[mid] > tar) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {4, 5, 2, 1};
        int[] queries1 = {3, 10, 21};
        System.out.println(Arrays.toString(solution.answerQueries(nums1, queries1)));  // Expected output: [2,3,4]
        int[] nums2 = {2, 3, 4, 5};
        int[] queries2 = {1};
        System.out.println(Arrays.toString(solution.answerQueries(nums2, queries2)));  // Expected output: [0]
    }
}

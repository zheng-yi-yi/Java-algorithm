package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.Arrays;

/**
 * 179. 最大数 https://leetcode.cn/problems/largest-number/description/
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"

 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 */
public class Code_01_largestNumber {
    static class Solution {
        public String largestNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
            if (strs[0].equals("0")) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {10, 2};
        System.out.println(solution.largestNumber(nums1));  // Expected output: "210"
        int[] nums2 = {3, 30, 34, 5, 9};
        System.out.println(solution.largestNumber(nums2));  // Expected output: "9534330"
    }
}

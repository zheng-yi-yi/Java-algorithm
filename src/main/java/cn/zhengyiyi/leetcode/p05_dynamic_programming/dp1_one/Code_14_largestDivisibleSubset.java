package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 368. 最大整除子集 <a href="https://leetcode.cn/problems/largest-divisible-subset/description/">...</a>
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
 * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 */
public class Code_14_largestDivisibleSubset {
    static class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            Arrays.sort(nums);
            int size = 1;   // 最大子集的元素数量初始化为1
            int lastIdx = 0;    // 最大子集的结尾元素，下标初始化为0
            int n = nums.length;
            int[] dp = new int[n];  // dp[i]：以nums[i]为最大元素的最大整除子集的大小
            Arrays.fill(dp, 1);
            int[] prev = new int[n];    // prev[i]：在最大整除子集中nums[i]的前一个元素的索引
            Arrays.fill(prev, -1);

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    // 如果满足条件，并且可以答案增加，则更新dp[i]和prev[i]
                    if (nums[i] % nums[j] == 0 && (dp[j] + 1 > dp[i])) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
                // 如果以nums[i]为最大元素的最大整除子集的大小大于当前最大子集的大小
                if (dp[i] > size) {
                    // 则更新最大子集的大小和最后一个元素的索引
                    size = dp[i];
                    lastIdx = i;
                }
            }
            List<Integer> answer = new ArrayList<Integer>();
            int idx = lastIdx;
            // 从最大子集的最后一个元素开始，按照prev数组回溯，将元素添加到答案列表中
            while (idx >= 0) {
                answer.add(nums[idx]);
                idx = prev[idx];
            }
            // 因为添加元素时是从后向前添加的，所以最后需要将答案列表反转
            Collections.reverse(answer);

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = {1, 2, 3};
        System.out.println(s.largestDivisibleSubset(nums1)); // [1, 2] or [1, 3]
        int[] nums2 = {1, 2, 4, 8};
        System.out.println(s.largestDivisibleSubset(nums2)); // [1, 2, 4, 8]
    }
}
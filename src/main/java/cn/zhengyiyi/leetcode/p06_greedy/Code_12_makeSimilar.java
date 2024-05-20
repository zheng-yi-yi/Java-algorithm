package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar/description/">2449. 使数组相似的最少操作次数</a>
 * <p>给你两个正整数数组 nums 和 target ，两个数组长度相等。
 * 在一次操作中，你可以选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < nums.length ，</p>
 * 并且 令 nums[i] = nums[i] + 2 且 令 nums[j] = nums[j] - 2 。
 * 如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。
 * 请你返回将 nums 变得与 target 相似的最少操作次数。测试数据保证 nums 一定能变得与 target 相似。
 * 示例 1：
 * 输入：nums = [8,12,6], target = [2,14,10]
 * 输出：2
 * 解释：可以用两步操作将 nums 变得与 target 相似：
 * - 选择 i = 0 和 j = 2 ，nums = [10,12,4] 。
 * - 选择 i = 1 和 j = 2 ，nums = [10,14,2] 。
 * 2 次操作是最少需要的操作次数。
 * 示例 2：
 * 输入：nums = [1,2,5], target = [4,1,3]
 * 输出：1
 * 解释：一步操作可以使 nums 变得与 target 相似：
 * - 选择 i = 1 和 j = 2 ，nums = [1,4,3] 。
 */
public class Code_12_makeSimilar {
    /**
     * 贪心策略：
     * 将 nums 和 target 分别拆分成奇数和偶数两部分，分别排序， 依次计算 nums 和 target 的每个元素的差值的绝对值之和，最后除以 4 即可
     */
    static class Solution {
        public static long makeSimilar(int[] nums, int[] target) {
            int n = nums.length;
            int oddSize = split(nums, n);
            split(target, n);
            Arrays.sort(nums, 0, oddSize);
            Arrays.sort(nums, oddSize, n);
            Arrays.sort(target, 0, oddSize);
            Arrays.sort(target, oddSize, n);
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += Math.abs((long) nums[i] - target[i]);
            }
            return ans / 4;
        }

        /**
         * 把数组分割成左部分全是奇数，右部分全是偶数
         * @param arr   数组
         * @param n     数组长度
         * @return      左部分的长度
         */
        public static int split(int[] arr, int n) {
            int oddSize = 0;
            for (int i = 0; i < n; i++) {
                if ((arr[i] & 1) == 1) {
                    swap(arr, i, oddSize++);
                }
            }
            return oddSize;
        }

        // 交换数组中的两个元素
        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.makeSimilar(new int[]{8, 12, 6}, new int[]{2, 14, 10})); // 2
        System.out.println(Solution.makeSimilar(new int[]{1, 2, 5}, new int[]{4, 1, 3})); // 1
    }
}

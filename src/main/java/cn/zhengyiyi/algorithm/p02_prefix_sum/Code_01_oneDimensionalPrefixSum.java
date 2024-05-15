package cn.zhengyiyi.algorithm.p02_prefix_sum;

/**
 * 一维前缀和
 * 给定一个整数数组 nums，求 nums 的前缀和。
 * 前缀和 prefixSum[i] 表示前 i 个元素的和。
 * 初始化：prefixSum[i] = prefixSum[i-1] + nums[i]
 * 用法：sum(i, j) = prefixSum[j + 1] - prefixSum[i];
 */
public class Code_01_oneDimensionalPrefixSum {
    private static int[] prefixSum;

    public static void build(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    /**
     * 查询区间 [i, j] 的和
     */
    public static int query(int i, int j) {
        return prefixSum[j + 1] - prefixSum[i];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        //            0  1  2  3  4
        build(nums);
        System.out.println(query(0, 2));  // 输出: 6
        System.out.println(query(1, 3));  // 输出: 9
        System.out.println(query(2, 4));  // 输出: 12
    }
}
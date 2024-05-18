package cn.zhengyiyi.algorithm.p03_lis;

/**
 * LIS问题就是：给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 这里的子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 最优解法：时间复杂度为 O(nlogn)
 * 做法是：维护一个ends数组，ends[i]表示长度为 i+1 的递增子序列的最小结尾
 *        利用二分搜索，找到 >= nums[i] 的最左位置，然后更新ends数组（如果不存在这个数，就扩充ends，否则就更新该位置上的数）
 *        最后返回ends数组的有效元素个数（即最长递增子序列的长度）
 * 可以注意到的是，这里要求的是严格递增子序列，
 * 如果要求【非严格】递增子序列（即相同元素可出现多次），只需修改二分策略（将 >= 改为 >）即可。
 */
public class Code_01_lengthOfLIS {
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // ends[i]: 目前所有长度为i+1的递增子序列的最小结尾
        int[] ends = new int[n];
        // ends数组中的有效元素个数
        int size = 0;
        ends[size++] = nums[0];
        for (int i = 1, pos = 0; i < n; i++) {
            pos = f(ends, nums[i], size);
            if (pos == -1) {
                ends[size++] = nums[i];
            } else {
                ends[pos] = nums[i];
            }
        }
        return size;
    }

    // 在nums中寻找 >= tar 的最左位置，如果不存在，返回-1
    public static int f(int[] nums, int tar, int end) {
        int ans = -1;
        int l = 0, r = end - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= tar) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums)); // 4
    }
}

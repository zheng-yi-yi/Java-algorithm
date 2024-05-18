package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp8_lis;

/**
 * 300. 最长递增子序列 https://leetcode.cn/problems/longest-increasing-subsequence/
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 */
public class Code_01_lengthOfLIS {
    // 最优解法，时间复杂度为 O(nlogn)
    static class Solution1 {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            // ends[i]: 目前所有长度为i+1的递增子序列的最小结尾
            int[] ends = new int[n];
            // ends数组中的有效元素个数
            int size = 0;
            ends[size++] = nums[0];
            for(int i = 1, pos = 0; i<n; i++) {
                pos = f(ends, nums[i], size);
                if(pos == -1) {
                    ends[size++] = nums[i];
                } else {
                    ends[pos] = nums[i];
                }
            }
            return size;
        }

        // 在nums中寻找 >= tar 的最左位置，如果不存在，返回-1
        public int f(int[] nums, int tar, int end) {
            int ans = -1;
            int l = 0, r = end - 1;
            while(l <= r) {
                int mid = l + (r - l) / 2;
                if(nums[mid] >= tar) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return ans;
        }
    }

    // 动态规划解法，时间复杂度为 O(n^2)
    static class Solution2 {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n]; // dp[i]: 以i结尾的最长递增子序列的长度是多少？
            dp[0] = 1;
            int ans = dp[0];
            for(int i = 1; i<n; i++) {
                dp[i] = 1;
                for(int j = 0; j<i; j++) {
                    if(nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        Solution2 s2 = new Solution2();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(s1.lengthOfLIS(nums)); // 4
        System.out.println(s2.lengthOfLIS(nums)); // 4
    }
}

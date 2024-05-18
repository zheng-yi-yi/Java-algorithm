package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp8_lis;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 646. 最长数对链 https://leetcode.cn/problems/maximum-length-of-pair-chain/description/
 * 给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [lefti, righti] 且 lefti < righti 。
 * 现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造 数对链 。
 * 找出并返回能够形成的 最长数对链的长度 。
 * 你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 示例 1：
 * 输入：pairs = [[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4] 。
 * 示例 2：
 * 输入：pairs = [[1,2],[7,8],[4,5]]
 * 输出：3
 * 解释：最长的数对链是 [1,2] -> [4,5] -> [7,8] 。
 */
public class Code_04_findLongestChain {
    static class Solution1 {    // 最长递增子序列
        public int findLongestChain(int[][] pairs) {
            int n = pairs.length;
            Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));  // 按照开始位置排序
            int[] ends = new int[n];
            int len = 0;
            for (int[] pair : pairs) {
                int find = f(ends, len, pair[0]);
                if (find == -1) {
                    ends[len++] = pair[1];
                } else {
                    ends[find] = Math.min(ends[find], pair[1]);
                }
            }
            return len;
        }

        // 在 ends 数组中找 >= num 的最左位置
        public int f(int[] ends, int len, int num) {
            int l = 0, r = len - 1, m, ans = -1;
            while (l <= r) {
                m = (l + r) / 2;
                if (ends[m] >= num) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return ans;
        }
    }

    static class Solution2 {    // 贪心算法
        public int findLongestChain(int[][] pairs) {
            int preEnd = Integer.MIN_VALUE;
            Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
            int ans = 0;
            for(int[] pair : pairs) {
                if(pair[0] > preEnd) {
                    ans++;
                    preEnd = pair[1];
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        Solution2 s2 = new Solution2();
        int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(s1.findLongestChain(pairs)); // 2
        System.out.println(s2.findLongestChain(pairs)); // 2
        pairs = new int[][]{{1, 2}, {7, 8}, {4, 5}};
        System.out.println(s1.findLongestChain(pairs)); // 3
        System.out.println(s2.findLongestChain(pairs)); // 3
    }
}

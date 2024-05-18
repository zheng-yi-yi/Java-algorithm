package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp8_lis;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题 https://leetcode.cn/problems/russian-doll-envelopes/description/
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * 示例 1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class Code_02_maxEnvelopes {

    static class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            int n = envelopes.length;
            // 按照宽度从小到大排列，如果宽度一样，按照高度从大到小排列
            // 然后按照排序好的结果，取出高度，求最长递增子序列即可
            Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]));
            int[] ends = new int[n];
            int len = 0;
            for (int i = 0, find, num; i < n; i++) {
                num = envelopes[i][1];
                find = f(ends, len, num);
                if (find == -1) {
                    ends[len++] = num;
                } else {
                    ends[find] = num;
                }
            }
            return len;
        }

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

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(s.maxEnvelopes(envelopes)); // 3
    }
}

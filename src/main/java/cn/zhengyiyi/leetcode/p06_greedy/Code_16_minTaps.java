package cn.zhengyiyi.leetcode.p06_greedy;

/**
 * <a href="https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/">1326. 灌溉花园的最少水龙头数目</a>
 * 在 x 轴上有一个一维的花园。花园长度为 n，从点 0 开始，到点 n 结束。
 * 花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。
 * 给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：如果打开点 i 处的水龙头，可以灌溉的区域为 [i -  ranges[i], i + ranges[i]] 。
 * 请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。
 * 示例 1：
 * 输入：n = 5, ranges = [3,4,1,1,0,0]
 * 输出：1
 * 解释：
 * 点 0 处的水龙头可以灌溉区间 [-3,3]
 * 点 1 处的水龙头可以灌溉区间 [-3,5]
 * 点 2 处的水龙头可以灌溉区间 [1,3]
 * 点 3 处的水龙头可以灌溉区间 [2,4]
 * 点 4 处的水龙头可以灌溉区间 [4,4]
 * 点 5 处的水龙头可以灌溉区间 [5,5]
 * 只需要打开点 1 处的水龙头即可灌溉整个花园 [0,5] 。
 * 示例 2：
 * 输入：n = 3, ranges = [0,0,0,0]
 * 输出：-1
 * 解释：即使打开所有水龙头，你也无法灌溉整个花园。
 */
public class Code_16_minTaps {
    static class Solution {
        public static int minTaps(int n, int[] ranges) {
            // right[i] = j：所有水龙头能覆盖的位置中，如果左边界能覆盖到i，那么右边界一定能覆盖到j
            int[] right = new int[n + 1];
            for (int i = 0, start; i <= n; ++i) {
                start = Math.max(0, i - ranges[i]);
                right[start] = Math.max(right[start], i + ranges[i]);
            }
            int ans = 0;
            int curBestPos = 0;    // 当前最右能覆盖到的位置
            int nextBestPos = 0;   // 如果再多打开一个水龙头，能覆盖到的最右边界
            for (int curPos = 0; curPos < n; curPos++) {
                // 先看看能不能更新nextBestPos，即再多打开一个水龙头的话，最远能覆盖到哪里？
                nextBestPos = Math.max(nextBestPos, right[curPos]);
                // 一旦当前位置curPos到达了当前最右能覆盖到的位置curBestPos，就需要分析两种情况：
                // 1. 如果nextBestPos > curPos，说明还可以再多打开一个水龙头，更新当前最右能覆盖到的位置
                // 2. 如果nextBestPos <= curPos，说明再多打开一个水龙头也无法覆盖到当前位置，返回-1
                if (curPos == curBestPos) {
                    if (nextBestPos > curPos) {
                        curBestPos = nextBestPos;
                        ans++;
                    } else {
                        return -1;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] ranges = {3, 4, 1, 1, 0, 0};
        System.out.println(Solution.minTaps(n, ranges));
    }
}

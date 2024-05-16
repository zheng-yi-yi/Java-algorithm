package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1553. 吃掉 N 个橘子的最少天数 https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/description/
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 * 吃掉一个橘子。
 * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 * 每天你只能从以上 3 种方案中选择一种方案。
 * 请你返回吃掉所有 n 个橘子的最少天数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：你总共有 10 个橘子。
 * 第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
 * 第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
 * 第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
 * 第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
 * 你需要至少 4 天吃掉 10 个橘子。
 */
public class Code_03_minDays {
    static class Solution {
        Map<Integer, Integer> dp = new HashMap<>();

        public int minDays(int n) {
            if(n == 0) return 0;
            if(n == 1) return 1;
            if(dp.containsKey(n)) return dp.get(n);

            int p1 = n%2 + 1 + minDays(n/2);
            int p2 = n%3 + 1 + minDays(n/3);
            int ans = Math.min(p1, p2);

            dp.put(n, ans);
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDays(10));  // Expected output: 4
    }
}

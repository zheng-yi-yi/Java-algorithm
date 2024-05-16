package cn.zhengyiyi.leetcode.p06_greedy;

/**
 * LCR 132. 砍竹子 II  https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/description/
 * 现需要将一根长为正整数 bamboo_len 的竹子砍为若干段，每段长度均为 正整数。请返回每段竹子长度的 最大乘积 是多少。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 * 输入：bamboo_len = 12
 * 输出：81
 * 提示：
 * 2 <= bamboo_len <= 1000
 */
public class Code_06_cuttingBamboo {
    static class Solution {
        static int mod = 1000000007;

        // 乘法快速幂：求x的n次方，最终得到的结果 % mod
        public long power(long x, int n, int mod) {
            long ans = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    ans = (ans * x) % mod;
                }
                x = (x * x) % mod;
                n >>= 1;
            }
            return ans;
        }

        /**
         * 砍竹子
         * @param bamboo_len    竹子长度
         * @return  每段竹子长度的最大乘积
         */
        public int cuttingBamboo(int bamboo_len) {
            if (bamboo_len == 2) {
                return 1;
            }
            if (bamboo_len == 3) {
                return 2;
            }
            // 贪心策略：尽可能多地切出长度为3的竹子段
            int tail = bamboo_len % 3 == 0 ? 1 : (bamboo_len % 3 == 1 ? 4 : 2);
            int power = (tail == 1 ? bamboo_len : (bamboo_len - tail)) / 3;
            return (int) (power(3, power, mod) * tail % mod);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.cuttingBamboo(12));  // Expected output: 3
        System.out.println(solution.cuttingBamboo(27));  // Expected output: 19683
        System.out.println(solution.cuttingBamboo(100));  // Expected output: 703522804
    }
}

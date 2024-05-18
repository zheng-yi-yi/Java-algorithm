package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp1_one;

/**
 * 96. 不同的二叉搜索树 <a href="https://leetcode.cn/problems/unique-binary-search-trees/">...</a>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 提示：
 * 1 <= n <= 19
 */
public class Code_08_numTrees {

    static class Solution1 {
        private int[] memo;

        public int numTrees(int n) {
            memo = new int[n + 1];
            return count(1, n);
        }

        private int count(int start, int end) {
            if (start > end) {
                return 1;
            }
            if (memo[end - start] != 0) {
                return memo[end - start];
            }
            int sum = 0;
            for (int i = start; i <= end; i++) {
                int left = count(start, i - 1);
                int right = count(i + 1, end);
                sum += left * right;
            }
            memo[end - start] = sum;
            return sum;
        }
    }

    static class Solution2 {
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] += dp[j - 1] * dp[i - j];
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        System.out.println(s1.numTrees(3)); // 5
        System.out.println(s1.numTrees(1)); // 1

        Solution2 s2 = new Solution2();
        System.out.println(s2.numTrees(3)); // 5
        System.out.println(s2.numTrees(1)); // 1
    }
}

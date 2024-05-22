package cn.zhengyiyi.leetcode.p07_number_theory;

/**
 * <p>
 * <a href="https://leetcode.cn/problems/count-vowels-permutation/description/">1220. 统计元音字母序列的数目</a>
 * </p>
 * <p>
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 * </p>
 * <ul>
 * <li>字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）</li>
 * <li>每个元音 'a' 后面都只能跟着 'e'</li>
 * <li>每个元音 'e' 后面只能跟着 'a' 或者是 'i'</li>
 * <li>每个元音 'i' 后面 不能 再跟着另一个 'i'</li>
 * <li>每个元音 'o' 后面只能跟着 'i' 或者是 'u'</li>
 * <li>每个元音 'u' 后面只能跟着 'a'</li>
 * </ul>
 * <p>
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 * </p>
 * <p>
 * <b>示例 1：</b>
 * <br>输入：n = 1
 * <br>输出：5
 * <br>解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * </p>
 * <p>
 * <b>示例 2：</b>
 * <br>输入：n = 2
 * <br>输出：10
 * <br>解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * </p>
 * <p>
 * <b>示例 3：</b>
 * <br>输入：n = 5
 * <br>输出：68
 * </p>
 * <p>
 * <b>提示：</b>
 * <br>1 <= n <= 2 * 10^4
 * </p>
 */
public class Code_04_countVowelPermutation {
    static class Solution {
        public static int MOD = 1000000007;

        public static int countVowelPermutation(int n) {
            // 长度为1的时候，以a、e、i、o、u结尾的合法数量
            int[][] start = { { 1, 1, 1, 1, 1 } };
            int[][] base = {
                    { 0, 1, 0, 0, 0 },
                    { 1, 0, 1, 0, 0 },
                    { 1, 1, 0, 1, 1 },
                    { 0, 0, 1, 0, 1 },
                    { 1, 0, 0, 0, 0 }
            };
            int[][] ans = multiply(start, power(base, n - 1));
            int ret = 0;
            for (int a : ans[0]) {
                ret = (ret + a) % MOD;
            }
            return ret;
        }

        // 矩阵相乘（其中a的列数一定要等于b的行数）
        public static int[][] multiply(int[][] a, int[][] b) {
            int n = a.length;
            int m = b[0].length;
            int k = a[0].length;
            int[][] ans = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int c = 0; c < k; c++) {
                        ans[i][j] = (int) (((long) a[i][c] * b[c][j] + ans[i][j]) % MOD);
                    }
                }
            }
            return ans;
        }

        // 矩阵快速幂
        public static int[][] power(int[][] m, int p) {
            int n = m.length;
            int[][] ans = new int[n][n];
            for (int i = 0; i < n; i++) {
                ans[i][i] = 1;
            }
            for (; p != 0; p >>= 1) {
                if ((p & 1) != 0) {
                    ans = multiply(ans, m);
                }
                m = multiply(m, m);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.countVowelPermutation(1));
        System.out.println(Solution.countVowelPermutation(2));
        System.out.println(Solution.countVowelPermutation(5));
    }
}

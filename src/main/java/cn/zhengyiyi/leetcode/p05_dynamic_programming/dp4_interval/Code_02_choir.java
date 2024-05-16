package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp4_interval;

import java.io.*;

/**
 * 合唱队问题 (HNOI2010) https://www.luogu.com.cn/problem/P3205
 *
 * <p>小 A 是合唱队的负责人，他需要根据队员的身高排出一个队形。合唱队有 n 个人，每个人的身高都不同。
 * 小 A 让所有的人先按任意顺序站成一个初始队形，然后从左到右按以下原则依次将每个人插入最终排出的队形中：
 * 第一个人直接插入空的当前队形中。对从第二个人开始的每个人，如果他比前面那个人高，那么将他插入当前队形的最右边。
 * 如果他比前面那个人矮，那么将他插入当前队形的最左边。当 n 个人全部插入当前队形后便获得最终排出的队形。
 * 小 A 想知道有多少种初始队形可以获得他心中的理想队形。
 *
 * <p>输入格式：
 * 第一行一个整数 n。
 * 第二行 n 个整数，表示小 A 心中的理想队形。
 *
 * <p>输出格式：
 * 输出一行一个整数，表示答案对 19650827 取模的值。
 *
 * <p>约束条件：
 * 对于 30% 的数据，n <= 100。
 * 对于 100% 的数据，n <= 1000，1000 <= h_i <= 2000。
 */
public class Code_02_choir {
    public static int MAXN = 1001;
    public static int[] nums = new int[MAXN];
    public static int n;
    public static int MOD = 19650827;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            if (n == 1) { // 只有一个人，只有一种队形
                out.println(1);
            } else {
                out.println(compute());
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        // 人的编号范围 : 1...n
        // dp[l][r][0] : 形成l...r的状况的方法数，同时要求l位置的数字是最后出现的
        // dp[l][r][1] : 形成l...r的状况的方法数，同时要求r位置的数字是最后出现的
        int[][][] dp = new int[n + 1][n + 1][2];    // 对角线上的格子不需要用到
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i + 1]) {
                dp[i][i + 1][0] = 1;
                dp[i][i + 1][1] = 1;
            }
        }
        for (int l = n - 2; l >= 1; l--) {
            for (int r = l + 2; r <= n; r++) {
                if (nums[l] < nums[l + 1]) {
                    dp[l][r][0] = (dp[l][r][0] + dp[l + 1][r][0]) % MOD;
                }
                if (nums[l] < nums[r]) {
                    dp[l][r][0] = (dp[l][r][0] + dp[l + 1][r][1]) % MOD;
                }
                if (nums[r] > nums[l]) {
                    dp[l][r][1] = (dp[l][r][1] + dp[l][r - 1][0]) % MOD;
                }
                if (nums[r] > nums[r - 1]) {
                    dp[l][r][1] = (dp[l][r][1] + dp[l][r - 1][1]) % MOD;
                }
            }
        }
        return (dp[1][n][0] + dp[1][n][1]) % MOD;
    }
}

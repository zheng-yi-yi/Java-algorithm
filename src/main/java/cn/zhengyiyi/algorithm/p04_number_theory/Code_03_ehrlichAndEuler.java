package cn.zhengyiyi.algorithm.p04_number_theory;

/**
 * 质数统计：埃氏筛和欧拉筛
 */
public class Code_03_ehrlichAndEuler {
    /**
     * 埃氏筛：统计0 ~ n范围内的质数个数，时间复杂度O(n * log(logn))
     */
    public static int ehrlich(int n) {
        // true代表合数，false代表质数（初始时认为都是质数）
        boolean[] vis = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            // 如果i是质数，那么i的倍数就不是质数
            if (!vis[i]) {
                // j：[i*i, ..., n]，每次递增i
                for (int j = i * i; j <= n; j += i) {
                    vis[j] = true;
                }
            }
        }
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) {
                cnt++;  // 此时i就是质数
            }
        }
        return cnt;
    }

    /**
     * 欧拉筛：统计0 ~ n范围内的质数个数，时间复杂度O(n)
     */
    public static int euler(int n) {
        // true代表合数，false代表质数（初始时认为都是质数）
        boolean[] vis = new boolean[n + 1];
        // prime收集所有的质数，收集的个数是cnt
        int[] prime = new int[n / 2 + 1];
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; j < cnt; j++) {
                if (i * prime[j] > n) {
                    break;
                }
                vis[i * prime[j]] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println(ehrlich(n)); // 25
        System.out.println(euler(n));   // 25
    }
}

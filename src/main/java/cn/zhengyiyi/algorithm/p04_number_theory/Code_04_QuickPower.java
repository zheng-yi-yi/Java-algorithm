package cn.zhengyiyi.algorithm.p04_number_theory;

/**
 * 乘法快速幂
 */
public class Code_04_QuickPower {
    public static void main(String[] args) {
        System.out.println(power(2, 10, 1000000007)); // 1024
    }

    /**
     * 快速计算 a^b % p
     */
    public static int power(long a, long b, long p) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a) % p;
            }
            a = (a * a) % p;
            b >>= 1;
        }
        return (int) ans;
    }
}

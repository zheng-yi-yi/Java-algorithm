package cn.zhengyiyi.algorithm.p04_number_theory;

/**
 * 求一个数的所有质因子
 */
public class Code_02_primeFactors {
    public static void main(String[] args) {
        int n = 1159;
        primeFactors(n);
    }

    /**
     * 打印所有n的质因子
     * @param n 待分解的数
     */
    public static void primeFactors(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.println(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            System.out.println(n);
        }
    }
}

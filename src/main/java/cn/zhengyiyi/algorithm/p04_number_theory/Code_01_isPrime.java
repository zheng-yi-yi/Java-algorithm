package cn.zhengyiyi.algorithm.p04_number_theory;

import java.math.BigInteger;

/**
 * 对于较小的数字，可使用isPrime1方法；对于较大的数字，可使用isPrime2方法
 */
public class Code_01_isPrime {

    /**
     * 判断一个数是否是素数
     * @param n 待判断的数
     * @return  是素数返回true，否则返回false
     */
    public static boolean isPrime1(long n) {
        if (n <= 1) {
            return false;
        }
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个数是否是素数
     * @param n 待判断的数
     * @return  是素数返回true，否则返回false
     */
    public static boolean isPrime2(String n) {
        BigInteger number = new BigInteger(n);
        return number.isProbablePrime(11);
    }

    public static void main(String[] args) {
        long n = 1000000007;
        System.out.println(isPrime1(n));
        System.out.println(isPrime2(String.valueOf(n)));
    }
}

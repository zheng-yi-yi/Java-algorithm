package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * [NOIP2004 提高组] 合并果子 / [USACO06NOV] Fence Repair G
 * 链接：https://www.luogu.com.cn/problem/P1090
 *
 * <p>题目描述：
 * 在一个果园里，多多已经将所有的果子打了下来，而且按果子的不同种类分成了不同的堆。多多决定把所有的果子合成一堆。
 * 每一次合并，多多可以把两堆果子合并到一起，消耗的体力等于两堆果子的重量之和。可以看出，所有的果子经过 n-1 次合并之后，就只剩下一堆了。
 * 多多在合并果子时总共消耗的体力等于每次合并所耗体力之和。
 * 因为还要花大力气把这些果子搬回家，所以多多在合并果子时要尽可能地节省体力。假定每个果子重量都为 1 ，并且已知果子的种类数和每种果子的数目，
 * 你的任务是设计出合并的次序方案，使多多耗费的体力最少，并输出这个最小的体力耗费值。
 *
 * <p>例如有 3 种果子，数目依次为 1 ， 2 ， 9 。可以先将 1 、 2 堆合并，新堆数目为 3 ，耗费体力为 3 。
 * 接着，将新堆与原先的第三堆合并，又得到新的堆，数目为 12 ，耗费体力为 12 。所以多多总共耗费体力 =3+12=15 。可以证明 15 为最小的体力耗费值。
 *
 * <p>输入格式：
 * 共两行。
 * 第一行是一个整数 n(1<= n<= 10000) ，表示果子的种类数。
 * 第二行包含 n 个整数，用空格分隔，第 i 个整数 a_i(1<= a_i<= 20000) 是第 i 种果子的数目。
 *
 * <p>输出格式：
 * 一个整数，也就是最小的体力耗费值。输入数据保证这个值小于 2^31 。
 *
 * <p>样例 #1：
 * 样例输入 #1：
 * 3
 * 1 2 9
 * 样例输出 #1：
 * 15
 *
 * <p>提示：
 * 对于 30% 的数据，保证有 n <= 1000：
 * 对于 50% 的数据，保证有 n <= 5000；
 * 对于全部的数据，保证有 n <= 10000。
 */
public class Code_05_merge_fruits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.offer(scanner.nextInt());
        }
        int total = 0;
        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            total += a + b;
            queue.offer(a + b);
        }
        System.out.println(total);
    }
}

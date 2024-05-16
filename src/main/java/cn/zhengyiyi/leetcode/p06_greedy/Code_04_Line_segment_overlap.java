package cn.zhengyiyi.leetcode.p06_greedy;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
/**
 * 线段重合 https://www.nowcoder.com/practice/1ae8d0b6bb4e4bcdbf64ec491f63fc37
 * 描述
 * 每一个线段都有start和end两个数据项，表示这条线段在X轴上从start位置开始到end位置结束。
 * 给定一批线段，求所有重合区域中最多重合了几个线段，首尾相接的线段不算重合。
 * 例如：线段[1,2]和线段[2.3]不重合。
 * 线段[1,3]和线段[2,3]重合

 * 输入描述：
 * 第一行一个数N，表示有N条线段
 * 接下来N行每行2个数，表示线段起始和终止位置
 * 输出描述：
 * 输出一个数，表示同一个位置最多重合多少条线段
 */
public class Code_04_Line_segment_overlap {
    public static int MAXN = 10001;

    public static int[][] line = new int[MAXN][2];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                line[i][0] = (int) in.nval;
                in.nextToken();
                line[i][1] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Arrays.sort(line, 0, n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (!heap.isEmpty() && heap.peek() <= line[i][0]) { // 如果之前的线段末尾位置比当前线段的起始位置小或等于，则弹出，代表不重合，它的影响过期了
                heap.poll();
            }
            heap.add(line[i][1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }
}

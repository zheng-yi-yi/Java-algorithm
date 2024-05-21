package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/eliminate-maximum-number-of-monsters/description/">1921. 消灭怪物的最大数量</a>
 * 你正在玩一款电子游戏，在游戏中你需要保护城市免受怪物侵袭。给定一个 下标从 0 开始 且大小为 n 的整数数组 dist ，其中 dist[i] 是第 i 个怪物与城市的 初始距离（单位：千米）。
 * 怪物以 恒定 的速度走向城市。每个怪物的速度都以一个长度为 n 的整数数组 speed 表示，其中 speed[i] 是第 i 个怪物的速度（单位：千米/分）。
 * 你有一种武器，一旦充满电，就可以消灭 一个 怪物。但是，武器需要 一分钟 才能充电。武器在游戏开始时是充满电的状态，怪物从 第 0 分钟 时开始移动。
 * 一旦任一怪物到达城市，你就输掉了这场游戏。如果某个怪物 恰好 在某一分钟开始时到达城市（距离表示为0），这也会被视为 输掉 游戏，在你可以使用武器之前，游戏就会结束。
 * 返回在你输掉游戏前可以消灭的怪物的 最大 数量。如果你可以在所有怪物到达城市前将它们全部消灭，返回  n 。
 * 示例 1：
 * 输入：dist = [1,3,4], speed = [1,1,1]
 * 输出：3
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [1,3,4]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,2,3]，你消灭了第二个怪物。
 * 第 3 分钟开始时，怪物的距离是 [X,X,2]，你消灭了第三个怪物。
 * 所有 3 个怪物都可以被消灭。
 * 示例 2：
 * 输入：dist = [1,1,2,3], speed = [1,1,1,1]
 * 输出：1
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [1,1,2,3]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,0,1,2]，所以你输掉了游戏。
 * 你只能消灭 1 个怪物。
 * 示例 3：
 * 输入：dist = [3,2,4], speed = [5,3,2]
 * 输出：1
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [3,2,4]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,0,2]，你输掉了游戏。
 * 你只能消灭 1 个怪物。
 *
 */
public class Code_18_eliminateMaximum {
    static class Solution {
        public int eliminateMaximum(int[] dist, int[] speed) {
            int n = dist.length;
            int[] time = new int[n];
            for (int i = 0; i < n; i++) {
                // a / b 向上取整 -> (a + b - 1) / b
                time[i] = (dist[i] + speed[i] - 1) / speed[i];
            }
            Arrays.sort(time);
            for (int i = 0; i < n; i++) {
                // 当前来到i的时刻
                if (time[i] <= i) {
                    return i;
                }
            }
            return n;
        }
    }

    public static void main(String[] args) {
        int[] dist = {1, 3, 4};
        int[] speed = {1, 1, 1};
        Solution solution = new Solution();
        System.out.println(solution.eliminateMaximum(dist, speed));
    }
}

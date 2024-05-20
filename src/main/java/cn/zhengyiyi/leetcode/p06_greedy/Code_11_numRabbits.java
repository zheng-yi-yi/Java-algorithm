package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.Arrays;

/**
 * 781. 森林中的兔子 https://leetcode.cn/problems/rabbits-in-forest/
 * 森林中有未知数量的兔子。提问其中若干只兔子 "还有多少只兔子与你（指被提问的兔子）颜色相同?" ，将答案收集到一个整数数组 answers 中，其中 answers[i] 是第 i 只兔子的回答。
 * 给你数组 answers ，返回森林中兔子的最少数量。
 * 示例 1：
 * 输入：answers = [1,1,2]
 * 输出：5
 * 解释：
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5 只：3 只回答的和 2 只没有回答的。
 */
public class Code_11_numRabbits {
    static class Solution {
        public int numRabbits(int[] answers) {
            int n = answers.length;
            Arrays.sort(answers);
            int next = 0, idx = 0, ans = 0;
            // 2  2  2  2  2  2  2  2  2  5   5   5
            // 0  1  2  3  4  5  6  7  8  9  10  11
            // idx                       next
            // cur = 2
            // ans += 组数 * (cur + 1)
            // 备注：组数 = 元素个数 / (cur + 1) 并向上取整
            // 另外：a / b 向上取整 -> (a + b - 1) / b
            while(idx < n) {
                int cur = answers[idx];
                while(next < n && answers[next] == cur) {
                    next++;
                }
                ans += ((next - idx + cur) / (cur + 1)) * (cur + 1);
                idx = next;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numRabbits(new int[]{1, 1, 2})); // 5
    }
}

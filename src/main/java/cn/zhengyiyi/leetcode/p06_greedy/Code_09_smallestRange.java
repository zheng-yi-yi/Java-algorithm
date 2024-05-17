package cn.zhengyiyi.leetcode.p06_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
/**
 * 632. 最小区间 https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/description/
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 * 示例 1：
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 */
public class Code_09_smallestRange {

    static class Solution {
        public class Node {
            public int v; 	// 值
            public int p; 	// 指向哪个数组
            public int idx; // 位于数组的哪个位置

            public Node(int a, int b, int c) {
                v = a;
                p = b;
                idx = c;
            }
        }

        public int[] smallestRange(List<List<Integer>> nums) {
            int k = nums.size();
            // 为什么排序的时候p要参与？
            // 确保即使值相同，但来自不同的数组，此时能被 TreeSet 正确地识别和处理，而不会被覆盖
            TreeSet<Node> set = new TreeSet<>((a, b) -> a.v != b.v ? (a.v - b.v) : (a.p - b.p));
            for (int i = 0; i < k; i++) {
                set.add(new Node(nums.get(i).get(0), i, 0));
            }
            int bestLen = Integer.MAX_VALUE; // 当前最短区间有多长
            int l = 0; 	// 最短区间的开头
            int r = 0; 	// 最短区间的结尾
            Node max;	// 有序表中，值最大的记录
            Node min;	// 有序表中，值最小的记录

            // 一旦有序表中无法维持k个元素，表明至少有一个数组的所有元素都已经被处理过了，此时无法找到新区间
            while (set.size() == k) {
                max = set.last();
                min = set.pollFirst();

                // 如果最短区间能更新得更短，则更新，同时也要更新l和r
                if (max.v - min.v < bestLen) {
                    bestLen = max.v - min.v;
                    l = min.v;
                    r = max.v;
                }

                if (min.idx + 1 < nums.get(min.p).size()) {
                    // 查询有序表中弹出的最小记录位于哪个数组，并将其下一个元素加到有序表中
                    set.add(new Node(nums.get(min.p).get(min.idx + 1), min.p, min.idx + 1));
                }
            }
            return new int[] { l, r };
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // 创建测试数据
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(new ArrayList<>(Arrays.asList(4, 10, 15, 24, 26)));
        nums.add(new ArrayList<>(Arrays.asList(0, 9, 12, 20)));
        nums.add(new ArrayList<>(Arrays.asList(5, 18, 22, 30)));

        int[] result = s.smallestRange(nums);
        System.out.println(Arrays.toString(result));  // 输出：[20, 24]
    }
}

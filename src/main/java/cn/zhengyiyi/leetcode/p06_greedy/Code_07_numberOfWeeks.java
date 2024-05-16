package cn.zhengyiyi.leetcode.p06_greedy;

/**
 * 1953. 你可以工作的最大周数 https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work/description/
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 * 你可以按下面两个规则参与项目中的工作：
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。

 * 示例 1：
 * 输入：milestones = [1,2,3]
 * 输出：6
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * 总周数是 6 。
 *
 * 示例 2：
 * 输入：milestones = [5,2,1]
 * 输出：7
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 7 周，你参与并完成项目 0 中的一个阶段任务。
 * 总周数是 7 。
 * 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
 * 因此，项目 0 中会有一个阶段任务维持未完成状态。
 */
public class Code_07_numberOfWeeks {
    static class Solution {
        public long numberOfWeeks(int[] milestones) {
            long sum = 0, max = 0;
            for (int milestone : milestones) {
                sum += milestone;
                max = Math.max(max, milestone);
            }
            long rest = sum - max;
            if (max > rest + 1) {
                return rest * 2 + 1;
            } else {
                return sum;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] milestones1 = {1, 2, 3};
        System.out.println(solution.numberOfWeeks(milestones1));  // Expected output: 6
        int[] milestones2 = {5, 2, 1};
        System.out.println(solution.numberOfWeeks(milestones2));  // Expected output: 7
    }
}

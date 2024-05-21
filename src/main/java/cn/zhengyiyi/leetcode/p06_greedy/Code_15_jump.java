package cn.zhengyiyi.leetcode.p06_greedy;

/**
 * <a href="https://leetcode.cn/problems/jump-game-ii/description/">45. 跳跃游戏 II</a>
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 */
public class Code_15_jump {
    static class Solution {
        public static int jump(int[] arr) {
            int cur = 0;    // 当前跳一步，最远能跳到哪？
            int next = 0;   // 如果再跳一步，最远能跳到哪？
            int ans = 0;    // 最终答案：即最小跳跃次数
            for (int pos = 0; pos < arr.length; pos++) {
                // 如果当前跳的那一步无法跳到pos位置，那么就需要再跳一步，此时更新cur为next
                if (cur < pos) {
                    cur = next;
                    ans++;
                }
                next = Math.max(next, pos + arr[pos]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.jump(new int[]{2, 3, 1, 1, 4})); // 2
    }
}

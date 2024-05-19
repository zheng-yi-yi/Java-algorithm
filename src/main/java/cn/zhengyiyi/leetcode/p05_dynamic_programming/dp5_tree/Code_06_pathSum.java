package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp5_tree;

import java.util.HashMap;
/**
 * 437. 路径总和 III <a href="https://leetcode.cn/problems/path-sum-iii/description/">...</a>
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Code_06_pathSum {
    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static void main(String[] args) {
        // 构造二叉树： [10,5,-3,3,2,null,11,3,-2,null,1]
        TreeNode root = new TreeNode();
        root.val = 10;

        root.left = new TreeNode();
        root.left.val = 5;
        root.right = new TreeNode();
        root.right.val = -3;

        root.left.left = new TreeNode();
        root.left.left.val = 3;
        root.left.right = new TreeNode();
        root.left.right.val = 2;
        root.right.left = null;
        root.right.right = new TreeNode();
        root.right.right.val = 11;

        root.left.left.left = new TreeNode();
        root.left.left.left.val = 3;
        root.left.left.right = new TreeNode();
        root.left.left.right.val = -2;
        root.left.right.left = null;
        root.left.right.right = new TreeNode();
        root.left.right.right.val = 1;

        System.out.println(new Solution().pathSum(root, 8)); // 3
    }

    static class Solution {
        private int ans;

        public int pathSum(TreeNode root, int sum) {
            // 前缀和映射(key: 前缀和, value: 该前缀和出现的次数)
            HashMap<Long, Integer> preSum = new HashMap<>();
            // 这一步也很重要，要记得加上，即前缀和为0的路径出现1次
            preSum.put(0L, 1);
            ans = 0;
            f(root, sum, 0, preSum);
            return ans;
        }

        /**
         * 路径必须以x作为结尾，路径累加和是target的路径数量，将答案累加到全局变量ans上
         * @param cur       当前节点
         * @param target    目标值
         * @param sum       从头节点出发，来到x的时候，上方累加和是多少
         * @param preSum    前缀和映射
         */
        public void f(TreeNode cur, int target, long sum, HashMap<Long, Integer> preSum) {
            if (cur != null) {
                // 更新当前路径和
                sum += cur.val;
                // 如果当前路径和减去目标值的路径存在于前缀和映射中
                // 说明存在路径的和等于目标值
                // 将出现频次累加到ans中去
                ans += preSum.getOrDefault(sum-target, 0);
                // 更新当前路径和的数量
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
                // 递归左右子树
                f(cur.left, target, sum, preSum);
                f(cur.right, target, sum, preSum);
                // 回溯，恢复当前路径和的数量，因为接下来要遍历其他路径
                preSum.put(sum, preSum.get(sum) - 1);
            }
        }
    }
}

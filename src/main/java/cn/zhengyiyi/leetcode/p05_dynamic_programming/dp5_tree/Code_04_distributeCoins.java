package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp5_tree;

/**
 * 979. 在二叉树中分配硬币 <a href="https://leetcode.cn/problems/distribute-coins-in-binary-tree/description/">...</a>
 * 给你一个有 n 个结点的二叉树的根结点 root ，其中树中每个结点 node 都对应有 node.val 枚硬币。整棵树上一共有 n 枚硬币。
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。移动可以是从父结点到子结点，或者从子结点移动到父结点。
 * 返回使每个结点上 只有 一枚硬币所需的 最少 移动次数。
 * 示例 1：
 * 输入：root = [3,0,0]
 * 输出：2
 * 解释：一枚硬币从根结点移动到左子结点，一枚硬币从根结点移动到右子结点。
 * 示例 2：
 * 输入：root = [0,3,0]
 * 输出：3
 * 解释：将两枚硬币从根结点的左子结点移动到根结点（两次移动）。然后，将一枚硬币从根结点移动到右子结点。
 */
public class Code_04_distributeCoins {
    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static void main(String[] args) {
        // 构造二叉树： [3,0,0]
        TreeNode root = new TreeNode();
        root.val = 3;

        root.left = new TreeNode();
        root.left.val = 0;
        root.right = new TreeNode();
        root.right.val = 0;

        root.left.left = null;
        root.left.right = null;
        root.right.left = null;
        root.right.right = null;

        System.out.println(new Solution1().distributeCoins(root)); // 2
        System.out.println(new Solution2().distributeCoins(root)); // 2
    }

    // --------------------- 方式一：套模板 ---------------------
    static class Solution1 {
        public int distributeCoins(TreeNode root) {
            return f(root).move;
        }

        public static class Info {
            public int cnt;
            public int sum;
            public int move;

            public Info(int a, int b, int c) {
                cnt = a;
                sum = b;
                move = c;
            }
        }

        public Info f(TreeNode x) {
            // base case
            if (x == null) {
                return new Info(0, 0, 0);
            }

            // 后序遍历, 递归计算左右子树的信息
            Info leftInfo = f(x.left);
            Info rightInfo = f(x.right);

            // 合并左右子树的信息
            // 1. 当前节点的硬币数量 = 左子树硬币数量 + 右子树硬币数量 + 1
            int cnts = leftInfo.cnt + rightInfo.cnt + 1;
            // 2. 当前节点的硬币总数 = 左子树硬币总数 + 右子树硬币总数 + 当前节点硬币数量
            int sums = leftInfo.sum + rightInfo.sum + x.val;
            // 3. 当前节点的移动次数 = 左子树移动次数 + 右子树移动次数 + 左子树硬币数量与硬币总数的差的绝对值 + 右子树硬币数量与硬币总数的差的绝对值
            int moves = leftInfo.move + rightInfo.move + Math.abs(leftInfo.cnt - leftInfo.sum) + Math.abs(rightInfo.cnt - rightInfo.sum);

            // 返回当前节点的信息
            return new Info(cnts, sums, moves);
        }
    }

    // --------------------- 方式二：简洁写法 ---------------------
    static class Solution2 {
        private int ans;

        public int distributeCoins(TreeNode root) {
            f(root);
            return ans;
        }

        /**
         * 后序遍历，返回子树硬币个数和节点数
         * @param node  当前子树的根节点
         * @return  当前子树的硬币个数和节点数
         */
        private int[] f(TreeNode node) {
            // base case
            if (node == null) {
                return new int[]{0, 0};
            }

            // 递归计算左右子树的信息
            int[] left = f(node.left);
            int[] right = f(node.right);

            // 根据左右子树的信息，计算当前树的信息
            // 子树硬币个数 = 左子树硬币个数 + 右子树硬币个数 + 当前节点硬币个数
            int coins = left[0] + right[0] + node.val;

            // 子树节点数 = 左子树节点数 + 右子树节点数 + 1
            int nodes = left[1] + right[1] + 1;

            // 更新答案
            ans += Math.abs(coins - nodes);

            // 返回当前子树的硬币个数和节点数
            return new int[]{coins, nodes};
        }
    }

}

package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp5_tree;

/**
 * 543. 二叉树的直径 https://leetcode.cn/problems/diameter-of-binary-tree/
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 */
public class Code_02_diameterOfBinaryTree {
    static class Solution1 {
        int ans = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            f(root);
            return ans;
        }

        // 返回节点的最大深度，也就是从该节点到叶子节点的最长路径
        public int f(TreeNode root) {
            if(root == null) {
                return -1;
            }
            int lLen = f(root.left) + 1;
            int rLen = f(root.right) + 1;
            ans = Math.max(ans, lLen+rLen);
            return Math.max(lLen, rLen);
        }
    }

    static class Solution2 {
        public int diameterOfBinaryTree(TreeNode root) {
            return f(root).diameter;
        }

        public static class Info {
            public int diameter;
            public int height;

            public Info(int a, int b) {
                diameter = a;
                height = b;
            }

        }

        public Info f(TreeNode x) {
            if (x == null) {
                return new Info(0, 0);
            }
            Info leftInfo = f(x.left);
            Info rightInfo = f(x.right);
            int height = Math.max(leftInfo.height, rightInfo.height) + 1;
            int diameter = Math.max(leftInfo.diameter, rightInfo.diameter);
            diameter = Math.max(diameter, leftInfo.height + rightInfo.height);
            return new Info(diameter, height);
        }
    }

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        Solution2 s2 = new Solution2();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(s1.diameterOfBinaryTree(root)); // 3
        System.out.println(s2.diameterOfBinaryTree(root)); // 3

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

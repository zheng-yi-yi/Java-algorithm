package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp5_tree;

/**
 * <a href="https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/description/">1373. 二叉搜索子树的最大键值和</a>
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * 二叉搜索树的定义如下：
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 * 示例 1：
 * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * 输出：20
 * 解释：键值为 3 的子树是和最大的二叉搜索树。
 * 示例 2：
 * 输入：root = [4,3,null,1,2]
 * 输出：2
 * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 */
public class Code_02_MaximumSumBst {
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static void main(String[] args) {
		// 构造二叉树： [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
		TreeNode root = new TreeNode();
		root.val = 1;

		root.left = new TreeNode();
		root.left.val = 4;
		root.right = new TreeNode();
		root.right.val = 3;

		root.left.left = new TreeNode();
		root.left.left.val = 2;
		root.left.right = new TreeNode();
		root.left.right.val = 4;
		root.right.left = new TreeNode();
		root.right.left.val = 2;
		root.right.right = new TreeNode();
		root.right.right.val = 5;

		root.left.left.left = null;
		root.left.left.right = null;
		root.left.right.left = null;
		root.left.right.right = null;
		root.right.left.left = null;
		root.right.left.right = null;
		root.right.right.left = new TreeNode();
		root.right.right.left.val = 4;
		root.right.right.right = new TreeNode();
		root.right.right.right.val = 6;

		System.out.println(new Solution1().maxSumBST(root)); // 20
		System.out.println(new Solution2().maxSumBST(root)); // 20
	}

	// --------------------- 方式一：套模板 ---------------------
	static class Solution1 {
		public int maxSumBST(TreeNode root) {
			return f(root).maxBstSum;
		}

		// 递归函数的返回值，包含当前树的信息
		public static class Info {
			public int max;
			public int min;
			public int sum;
			public boolean isBst;
			public int maxBstSum;

			public Info(int a, int b, int c, boolean d, int e) {
				max = a;
				min = b;
				sum = c;
				isBst = d;
				maxBstSum = e;
			}
		}

		public Info f(TreeNode x) {
			// base case
			if (x == null) {
				return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true, 0);
			}

			// 递归计算左右子树的信息
			Info leftInfo = f(x.left);
			Info rightInfo = f(x.right);

			// 根据左右子树的信息，计算当前树的信息

			// 当前树的最大值 = max(当前节点值, 左子树的最大值, 右子树的最大值)
			int max = Math.max(x.val, Math.max(leftInfo.max, rightInfo.max));

			// 当前树的最小值 = min(当前节点值, 左子树的最小值, 右子树的最小值)
			int min = Math.min(x.val, Math.min(leftInfo.min, rightInfo.min));

			// 当前树的节点和 = 左子树的节点和 + 右子树的节点和 + 当前节点值
			int sum = leftInfo.sum + rightInfo.sum + x.val;

			// 判断当前树是否是二叉搜索树 = 左子树是二叉搜索树 && 右子树是二叉搜索树 && 左子树的最大值 < 当前节点值 < 右子树的最小值
			boolean isBst = leftInfo.isBst && rightInfo.isBst && leftInfo.max < x.val && x.val < rightInfo.min;

			// 当前树的最大二叉搜索子树的节点和 = max(左子树的最大二叉搜索子树的节点和, 右子树的最大二叉搜索子树的节点和, 当前树的节点和)
			int maxBstSum = Math.max(leftInfo.maxBstSum, rightInfo.maxBstSum);
			if (isBst) {
				maxBstSum = Math.max(maxBstSum, sum);
			}

			// 返回当前树的信息
			return new Info(max, min, sum, isBst, maxBstSum);
		}
	}

	// --------------------- 方式二：简洁写法 ---------------------
	static class Solution2 {
		private int ans;

		public int maxSumBST(TreeNode root) {
			f(root);
			return ans;
		}

		/**
		 * 返回一个数组，包含三个元素, 分别是：当前子树的最小值、最大值、节点和
		 * @param node	当前子树的根节点
		 * @return	当前子树的最小值、最大值、节点和
		 */
		private int[] f(TreeNode node) {
			// base case
			if (node == null) {
				return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
			}

			// 递归计算左右子树的信息
			int[] left = f(node.left);
			int[] right = f(node.right);

			// 根据左右子树的信息，计算当前树的信息

			// 如果不是二叉搜索树，返回一个不可能的值
			int cur = node.val;
			if (cur <= left[1] || cur >= right[0]) {
				return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
			}

			// 如果当前子树是二叉搜索树，则尝试更新答案
			int s = left[2] + right[2] + cur;
			ans = Math.max(ans, s);

			// 返回当前子树的最小值、最大值、节点和
			return new int[]{Math.min(left[0], cur), Math.max(right[1], cur), s};
		}
	}
}
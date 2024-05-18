package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp5_tree;

/**
 * 968. 监控二叉树 <a href="https://leetcode.cn/problems/binary-tree-cameras/description/">...</a>
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：一台摄像头足以监控所有节点。
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。
 */
public class Code_05_minCameraCover {
	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static void main(String[] args) {
		// 构造二叉树： [0,0,null,0,0]
		TreeNode root = new TreeNode();
		root.val = 0;

		root.left = new TreeNode();
		root.left.val = 0;
		root.right = null;

		root.left.left = new TreeNode();
		root.left.left.val = 0;
		root.left.right = new TreeNode();
		root.left.right.val = 0;

		System.out.println(new Solution().minCameraCover(root)); // 1
	}

	static class Solution {
		public int minCameraCover(TreeNode root) {
			ans = 0;
			if (f(root) == 0) {
				// 如果根节点未被覆盖，那么根节点需要安装摄像头
				ans++;
			}
			return ans;
		}

		public static int ans;

		/**
		 * 假设x上方一定有父亲的情况下，f函数的返回值代表了当前节点的状态，
		 * 有三种可能的值：
		 * 0：当前节点未被覆盖，需要父节点安装摄像头。
		 * 1：当前节点被覆盖，但没有安装摄像头，可以被父节点或子节点覆盖。
		 * 2：当前节点安装了摄像头。
		 */
		private int f(TreeNode x) {
			if (x == null) {	// 空节点不需要被覆盖。可视为1状态
				return 1;
			}
			// 左右子树的状态
			int left = f(x.left);
			int right = f(x.right);
			// 只要有一个子节点未被覆盖，当前节点就需要安装摄像头
			// 此时更新全局变量ans，并返回2状态
			if (left == 0 || right == 0) {
				ans++;
				return 2;
			}
			// 如果左右子节点都被覆盖，则当前节点不用安装摄像头
			// 直接返回0状态，让父节点安装摄像头即可
			if (left == 1 && right == 1) {
				return 0;
			}
			// 12 || 21 || 22
			// 如果左右子节点有一个安装了摄像头，另一个被覆盖但没安装摄像头，又或者两个都安装了摄像头
			// 那么当前节点就被覆盖，但不安装摄像头，返回1状态
			return 1;
		}
	}
}
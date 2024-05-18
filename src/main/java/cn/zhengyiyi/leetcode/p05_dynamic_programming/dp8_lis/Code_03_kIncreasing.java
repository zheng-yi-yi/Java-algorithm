package cn.zhengyiyi.leetcode.p05_dynamic_programming.dp8_lis;

/**
 * 2111. 使数组 K 递增的最少操作次数
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing/description/
 * 给你一个下标从 0 开始包含 n 个正整数的数组 arr ，和一个正整数 k 。
 * 如果对于每个满足 k <= i <= n-1 的下标 i ，都有 arr[i-k] <= arr[i] ，那么我们称 arr 是 K 递增 的。
 * 比方说，arr = [4, 1, 5, 2, 6, 2] 对于 k = 2 是 K 递增的，因为：
 * arr[0] <= arr[2] (4 <= 5)
 * arr[1] <= arr[3] (1 <= 2)
 * arr[2] <= arr[4] (5 <= 6)
 * arr[3] <= arr[5] (2 <= 2)
 * 但是，相同的数组 arr 对于 k = 1 不是 K 递增的（因为 arr[0] > arr[1]），对于 k = 3 也不是 K 递增的（因为 arr[0] > arr[3] ）。
 * 每一次 操作 中，你可以选择一个下标 i 并将 arr[i] 改成任意 正整数。
 * 请你返回对于给定的 k ，使数组变成 K 递增的 最少操作次数 。
 * 示例 1：
 * 输入：arr = [5,4,3,2,1], k = 1
 * 输出：4
 * 解释：
 * 对于 k = 1 ，数组最终必须变成非递减的。
 * 可行的 K 递增结果数组为 [5,6,7,8,9]，[1,1,1,1,1]，[2,2,3,4,4] 。它们都需要 4 次操作。
 * 次优解是将数组变成比方说 [6,7,8,9,10] ，因为需要 5 次操作。
 * 显然我们无法使用少于 4 次操作将数组变成 K 递增的。
 * 示例 2：
 * 输入：arr = [4,1,5,2,6,2], k = 2
 * 输出：0
 * 解释：
 * 这是题目描述中的例子。
 * 对于每个满足 2 <= i <= 5 的下标 i ，有 arr[i-2] <= arr[i] 。
 * 由于给定数组已经是 K 递增的，我们不需要进行任何操作。
 * 示例 3：
 * 输入：arr = [4,1,5,2,6,2], k = 3
 * 输出：2
 * 解释：
 * 下标 3 和 5 是仅有的 3 <= i <= 5 且不满足 arr[i-3] <= arr[i] 的下标。
 * 将数组变成 K 递增的方法之一是将 arr[3] 变为 4 ，且将 arr[5] 变成 5 。
 * 数组变为 [4,1,5,4,6,5] 。
 * 可能有其他方法将数组变为 K 递增的，但没有任何一种方法需要的操作次数小于 2 次。
 */
public class Code_03_kIncreasing {
    public static int MAXN = 100001;

    public static int[] nums = new int[MAXN];

    public static int[] ends = new int[MAXN];

    public static int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0, size; i < k; i++) {
            size = 0;
            // 把每一组的数字单独拎出来，然后跑最长不下降子序列
            // 然后用当前这组数组的长度减去最长不下降子序列的长度，就是当前组至少需要修改的数字个数
            for (int j = i; j < n; j += k) {
                nums[size++] = arr[j];
            }
            ans += size - lengthOfNoDecreasing(size);
        }
        return ans;
    }

    // 求长度为size的nums数组中，最长不下降子序列的长度（注意，不是最长严格递增子序列）
    public static int lengthOfNoDecreasing(int size) {
        int len = 0;
        for (int i = 0, find; i < size; i++) {
            find = f(len, nums[i]);
            if (find == -1) {
                ends[len++] = nums[i];
            } else {
                ends[find] = nums[i];
            }
        }
        return len;
    }

    // 在 ends 数组中寻找 > tar 的最左位置，如果不存在，返回-1
    public static int f(int len, int tar) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] > tar) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 2, 1};
        int k1 = 1;
        System.out.println(kIncreasing(arr1, k1)); // 4

        int[] arr2 = {4, 1, 5, 2, 6, 2};
        int k2 = 2;
        System.out.println(kIncreasing(arr2, k2)); // 0

        int[] arr3 = {4, 1, 5, 2, 6, 2};
        int k3 = 3;
        System.out.println(kIncreasing(arr3, k3)); // 2
    }
}

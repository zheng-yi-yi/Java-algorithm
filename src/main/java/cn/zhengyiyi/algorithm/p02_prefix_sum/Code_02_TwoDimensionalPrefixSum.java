package cn.zhengyiyi.algorithm.p02_prefix_sum;
/**
 * 二维前缀和
 * 给定一个二维矩阵 matrix，求 matrix 的前缀和。
 * 前缀和 prefixSum[i][j] 表示左上角为 (0, 0)，右下角为 (i, j) 的子矩阵的和。
 * 初始化：prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i][j]
 * 用法：sum(x1, y1, x2, y2) = prefixSum[x2+1][y2+1] - prefixSum[x1][y2+1] - prefixSum[x2+1][y1] + prefixSum[x1][y1]
 */
public class Code_02_TwoDimensionalPrefixSum {
    private static int[][] prefixSum;

    public static void build(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        prefixSum = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum[i + 1][j + 1] = prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j] + matrix[i][j];
            }
        }
    }

    public static int query(int x1, int y1, int x2, int y2) {
        x2++;
        y2++;
        return prefixSum[x2][y2] - prefixSum[x1][y2] - prefixSum[x2][y1] + prefixSum[x1][y1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {2, 3, 4, 5},
                {3, 4, 5, 6},
                {4, 5, 6, 7}
        };
        build(matrix);
        System.out.println(query(0, 0, 1, 1)); // 8，即（0，0）到（1，1）的子矩阵和
        System.out.println(query(0, 0, 2, 1)); // 15，即（0，0）到（2，1）的子矩阵和
        System.out.println(query(2, 2, 3, 3)); // 24，即（2，2）到（3，3）的子矩阵和
    }
}
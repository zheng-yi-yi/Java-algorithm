package cn.zhengyiyi.algorithm.p04_number_theory;

/**
 * 矩阵快速幂
 */
public class Code_05_MatrixFastPower {
    // 测试
    public static void main(String[] args) {
        int[][] a = { { 1, 2 }, { 3, 4 } };
        // 连乘得到矩阵a的5次方
        int[][] b = matrixMultiply(a, matrixMultiply(a, matrixMultiply(a, matrixMultiply(a, a))));
        printMatrix(b);
        System.out.println("===========");
        // 矩阵快速幂得到矩阵a的5次方
        printMatrix(matrixFastPower(a, 5));
    }

    /**
     * 矩阵相乘，注意A的列数必须等于B的行数
     */
    public static int[][] matrixMultiply(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    /**
     * 矩阵快速幂，快速计算A的n次方
     */
    public static int[][] matrixFastPower(int[][] A, int n) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            result[i][i] = 1;
        }

        while (n > 0) {
            if ((n & 1) == 1) {
                result = matrixMultiply(result, A);
            }
            A = matrixMultiply(A, A);
            n >>= 1;
        }

        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] m : matrix) {
            for (int n : m) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
package cn.zhengyiyi.algorithm.p04_number_theory;

import java.io.*;

/**
 * 高斯-约当消元法，用于解线性方程组
 * 可参考洛谷这道题：<a href="https://www.luogu.com.cn/problem/P3389">高斯消元</a>
 */
public class Code_06_GaussJordanElimination {
    static double[][] matrix = new double[105][105];
    static int n;
    static double EPSILON = 1e-7;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer in = new StreamTokenizer(bf);
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        while(in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            for(int i = 1; i<=n; i++) {
                for(int j = 1; j<=n+1; j++) {
                    in.nextToken();
                    matrix[i][j] = in.nval;
                }
            }
            // 高斯消元法
            compute();
            for(int i = 1; i<=n; i++) {
                // 最后得到简化阶梯矩阵
                out.printf("%.2f\n", matrix[i][n+1]);
            }
            break;
        }
        out.flush();
        out.close();
        bf.close();
    }

    // 高斯-约当消元法
    private static void compute() {
        for (int i = 1; i <= n; ++i) {    // 枚举每一列
            // 1、选择一个非0系数的行（具体代码实现可以是：选择该列的最大系数）
            int selectRow = i;
            for(int curRow = selectRow + 1; curRow <= n; ++curRow) {
                if(Math.abs(matrix[curRow][i]) > Math.abs(matrix[selectRow][i])) {
                    selectRow = curRow;
                }
            }

            // 2、交换两行：将选定的行移动到前面
            swapRows(selectRow, i);

            // 3、判断：如果对角线上的主元系数为0，说明没有唯一解
            if(Math.abs(matrix[i][i]) < EPSILON) {
                out.println("No Solution");
                return;
            }

            // 4、把这一行的主元系数变为1
            for(int j = n+1; j>=1; --j) {
                matrix[i][j] /= matrix[i][i];
            }

            // 5、消去主元所在列的其他行的主元系数
            for(int j = 1; j<=n; ++j) {
                if(i != j) {
                    double temp = matrix[j][i] / matrix[i][i];
                    for(int k = 1; k<=n+1; ++k) {
                        matrix[j][k] -= temp * matrix[i][k];
                    }
                }
            }
        }
    }

    public static void swapRows(int row1, int row2) {
        for (int col = 1; col <= n+1; col++) {
            double temp = matrix[row1][col];
            matrix[row1][col] = matrix[row2][col];
            matrix[row2][col] = temp;
        }
    }
}
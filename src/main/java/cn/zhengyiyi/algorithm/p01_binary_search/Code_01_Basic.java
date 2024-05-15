package cn.zhengyiyi.algorithm.p01_binary_search;

import java.util.Arrays;
import java.util.Random;
/**
 * 二分搜索：基础实现
 *
 * 二分搜索是一种在有序数组中查找特定元素的搜索算法。
 * 搜索过程从数组的中间元素开始，如果中间元素正好是目标值，则搜索结束；
 * 如果目标值大于或小于中间元素，则在数组大于或小于中间元素的那一半中查找。
 * 通过这种方式，每次比较都使搜索范围减半，直到找到目标值，或搜索范围为空。
 */
public class Code_01_Basic {
    /**
     * 二分搜索
     * @param array 有序数组
     * @param target    目标值
     * @return  目标值在数组中的索引，如果不存在则返回-1
     */
    public int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Code_01_Basic bs = new Code_01_Basic();
        Random rand = new Random();

        // 生成随机测试样本
        for (int i = 0; i < 10; i++) {
            int[] array = new int[20];
            for (int j = 0; j < array.length; j++) {
                array[j] = rand.nextInt(100);
            }

            // 对数组进行排序，因为二分搜索需要在有序数组中进行
            Arrays.sort(array);

            // 随机选择一个目标值
            int target = array[rand.nextInt(array.length)];

            // 使用二分搜索查找目标值
            int index = bs.binarySearch(array, target);

            // 验证结果
            if (index == -1 || array[index] != target) {
                System.out.println("Test failed!");
            } else {
                System.out.println("Test passed!");
            }
        }
    }
}

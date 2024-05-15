package cn.zhengyiyi.algorithm.p01_binary_search;

import java.util.Arrays;
import java.util.Random;
/**
 * 二分搜索：查找最后一个等于目标值的元素
 *
 * 这个版本的二分搜索用于在有序数组中查找最后一个等于目标值的元素。
 * 当找到一个等于目标值的元素时，我们不会立即返回，而是继续在右侧的元素中查找，直到找到最后一个等于目标值的元素。
 */
public class Code_03_findLastIdx {
    /**
     * 在有序数组 nums 中查询最后一个值为 tar 的元素的下标
     * @param nums  有序数组
     * @param tar   目标值
     * @return  目标值在数组中的索引，如果不存在则返回-1
     */
    public static int findLastIndex(int[] nums, int tar) {
        int ans = -1;
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == tar) {
                ans = mid;
                l = mid + 1;
            } else if(nums[mid] > tar){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // 生成随机测试样本
        for (int i = 0; i < 10; i++) {
            int[] array = new int[20];
            for (int j = 0; j < array.length; j++) {
                array[j] = rand.nextInt(10);  // 生成小于10的随机数，以增加重复元素的可能性
            }

            // 对数组进行排序，因为二分搜索需要在有序数组中进行
            Arrays.sort(array);

            // 随机选择一个目标值
            int target = array[rand.nextInt(array.length)];

            // 使用二分搜索查找目标值
            int index = findLastIndex(array, target);

            // 验证结果
            if (index == -1 || array[index] != target) {
                System.out.println("Test failed!");
            } else {
                System.out.println("Test passed!");
            }
        }
    }
}

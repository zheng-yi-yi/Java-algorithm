package cn.zhengyiyi.algorithm.p01_binary_search;

/**
 * 在有序数组中查找小于或等于目标值的最右位置。
 */
public class Code_05_findRight {
    /**
     * 在有序数组 nums 中查找 <=tar 的最右位置，如果不存在，返回-1
     * @param nums  有序数组
     * @param tar   目标值
     * @return  目标值在数组中的索引，如果不存在则返回-1
     */
    public static int findRightmostLessOrEqual(int[] nums, int tar) {
        int ans = -1;
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] <= tar) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 24, 37, 55, 68, 71};
        int tar = 40;
        int result = findRightmostLessOrEqual(nums, tar);
        System.out.println("The rightmost position of " + tar + " in the array is: " + result);
    }
}
package cn.zhengyiyi.leetcode.p01_binary_search;

import java.util.Arrays;
/**
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，
 * 其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 * 同时给你一个整数 success 。
 * 一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 *
 * 例如：
 * 输入：spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 输出：[4,0,3]
 * 解释：
 * - 第 0 个咒语：5 * [1,2,3,4,5] = [5,10,15,20,25] 。总共 4 个成功组合。
 * - 第 1 个咒语：1 * [1,2,3,4,5] = [1,2,3,4,5] 。总共 0 个成功组合。
 * - 第 2 个咒语：3 * [1,2,3,4,5] = [3,6,9,12,15] 。总共 3 个成功组合。
 * 所以返回 [4,0,3] 。
 *
 * 输入：spells = [3,1,2], potions = [8,5,8], success = 16
 * 输出：[2,0,2]
 * 解释：
 * - 第 0 个咒语：3 * [8,5,8] = [24,15,24] 。总共 2 个成功组合。
 * - 第 1 个咒语：1 * [8,5,8] = [8,5,8] 。总共 0 个成功组合。
 * - 第 2 个咒语：2 * [8,5,8] = [16,10,16] 。总共 2 个成功组合。
 * 所以返回 [2,0,2] 。
 */
public class Code_006_successfulPairs {
    // 对药水数组进行排序，通过二分搜索找到每个咒语的成功组合的开始位置
    public static int[] successfulPairs(int[] spells, int[] potions, int success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        Arrays.sort(potions);

        for (int i = 0; i < n; i++) {
            int l = 0, r = m - 1;
            int ans = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;

                if ((long) spells[i] * potions[mid] >= success) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // 每次二分搜索结束时，ans指向的是第一个使得咒语能量强度与药水能量强度的乘积小于success的药水的位置
            // 如果没有找到（ans==-1），则答案为0，否则答案为m-ans
            pairs[i] = ans == -1 ? 0 : m - ans;
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] spells1 = {5,1,3};
        int[] potions1 = {1,2,3,4,5};
        System.out.println(Arrays.toString(successfulPairs(spells1, potions1, 7)));  // 输出: [4,0,3]

        int[] spells2 = {3,1,2};
        int[] potions2 = {8,5,8};
        System.out.println(Arrays.toString(successfulPairs(spells2, potions2, 16)));  // 输出: [2,0,2]
    }
}
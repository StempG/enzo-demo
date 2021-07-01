package com.enzo.algorithm.lccoder;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * 
 *
 * 提示：
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * 
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 寻找两个正序数组的中位数 {


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
        分两种情况
        A：m+n为奇数，则中位数是两个有序数组中第(m+n+1)/2 大的数
        B：m+n为偶数，则求的是两个数组中第（m+n）/2 大的数 与 (m+n)/2 +1 大的数的均值

        由于两个数组都是正序数组，考虑使用双指针i ,j
        比较num1[i] 与num2[j]的大小。比较之后使得较小值的那个指针后移一位，直到i+j=X，且X为两个数组中的中间

         */


        int m = nums1.length;
        int n = nums2.length;
        if (m+n<=0){
            return 0d;
        }

        if ((m+n)%2 ==0){//偶数情况
            int x = (m+n)/2;

            int i=0 ,j= 0;
            int target1 = 0;
            int target2 = 0;

            while ((i+j)<=x){
                //边界判定
                if (i>m-1){
                    target2 = nums2[j];
                    j++;
                    continue;
                }

                if (j>n-1){
                    target1 = nums1[i];
                    i++;

                    continue;
                }


                if (nums1[i]<=nums2[j]){
                    target1 = nums1[i];
                    i++;

                }else {
                    target2 = nums2[j];
                    j++;
                }
            }


            return (double)(target1+target2)/2;
        }else {//奇数情况

            int x = (m+n-1)/2;
            int i=0 ,j= 0;

            int target = 0;
            while ((i+j)<=x){
                //边界判定
                if (i>m-1){
                    target = nums2[j];
                    j++;
                    continue;
                }

                if (j>n-1){
                    target = nums1[i];
                    i++;
                    continue;
                }

                if (nums1[i]<=nums2[j] ){
                    target = nums1[i];
                    i++;
                }else {
                    target = nums2[j];
                    j++;

                }
            }

            return  target;


        }
    }


    public static void main(String[] args) {


        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{2,3}));
    }

}

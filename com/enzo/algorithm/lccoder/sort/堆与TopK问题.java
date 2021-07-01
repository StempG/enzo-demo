package com.enzo.algorithm.lccoder.sort;


/**
 * 215. 数组中的第K个最大元素
 * 难度
 * 中等
 * <p>
 * 1151
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/
 */
public class 堆与TopK问题 {


    public static void main(String[] args) {
        //https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/xie-gei-qian-duan-tong-xue-de-ti-jie-yi-kt5p2/

        /*

        堆有以下特点

        特点

        第 n 个元素的 左子节点 为 2*n+1
        第 n 个元素的 右子节点 为 2*n+2
        第 n 个元素的 父节点 为 (n-1)/2
        最后一个非叶子节点为 Math.floor(arr.length/2)-1

        作者：gang-feng
        链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/xie-gei-qian-duan-tong-xue-de-ti-jie-yi-kt5p2/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */


        /*
         * 构建堆的时候。一般遵循 从上而下。从左到右的比较顺序。
         */


        //已AC
        int result = findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        System.out.println(result);


    }

    public static int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;


        buildMaxHeap(nums, heapSize);//构建大顶堆，构建完以后，a[0]是最大的数值

        for (int i = nums.length-1 ;i>=nums.length-k+1;i--){
            swap(nums, 0, i);//将堆顶元素（最大值）下沉到数组尾部
            --heapSize;// 下沉后的元素不参与到大顶堆的调整
            maxHeapify(nums, 0, heapSize);
        }



        return nums[0];

    }

    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void maxHeapify(int[] a, int parentIndex, int heapSize){
        int leftChildIndex = parentIndex *2+1;
        int rightChildIndex = parentIndex * 2 +2;
        int largestIndex = parentIndex;

        //从左到右,因此是先left判断，再是right判断
        if (leftChildIndex < heapSize && a[leftChildIndex] >= a[largestIndex]){
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < heapSize && a[rightChildIndex] >= a[largestIndex]){
            largestIndex = rightChildIndex;
        }

        if (parentIndex != largestIndex){
            swap(a, largestIndex, parentIndex);
            maxHeapify(a, largestIndex, heapSize);//从上到下调整
        }



    }

    private static void buildMaxHeap(int[] a, int heapSize) {
        int nonLeafIndex = (int) (Math.floor(heapSize/2)-1);
        for (int i = nonLeafIndex;i>=0;i--){
            maxHeapify(a, i, heapSize);
        }

    }
}

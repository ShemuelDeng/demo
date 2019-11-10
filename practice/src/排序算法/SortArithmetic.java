package 排序算法;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 十大排序算法
 * 默认都升序排序
 */
public class SortArithmetic {

    /**
     * 冒泡排序
     * 两两比较，大的往后放，是为冒泡
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        if (array.length == 0)
            return array;
        // 外层循环控制次数
        int times = 0;
        for (int i = 0 ;i<array.length;i++){
            // 第一次循环只需要比较 length-1次，后面每次都少比较一次，所以减i
            // 内层循环两两比较，每次将最大的移到后面
            for (int j = 0 ;j<array.length-1-i;j++){
                if (array[j]>array[j+1]){
                    int temp = array[j];
                    array[j]= array[j+1];
                    array[j+1]= temp;
                    times++;
                }
            }
        }
        System.out.println("排序完成,本次总共用了"+times+"次排序完成。");
        return array;
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，跟排序序列的起始位置交换，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array){
        if (array.length == 0 )
            return array;
        int length = array.length;
        for (int i = 0; i <length ; i++) {
            int minIndex = i;
            for (int j = i ;j<length;j++){
                if (array[j]<array[minIndex]){ // 两两比较，找到最大的索引
                    minIndex = j;
                }
            }
            // 循环完成后，索引记录的是最大元素的索引，然后跟元素序列的起始元素交换
            int temp = array[minIndex];
            array[minIndex]= array[i];
            array[i]= temp;
        }
        return array;
    }

    /**
     * 插入排序插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，
     * 通常采用in-place排序（即只需用到O(1)的额外空间的排序），
     * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array){
        if (array.length == 0 )
            return array;
        for (int i=0;i<array.length-1;i++){
            // 默认第一个是已经排序好的，从第二个开始跟第一个比较
            int current = array[i+1];
            int preIndex = i; // 当前值的前一个，
            while (preIndex>=0 && current<array[preIndex]){ // 当前值小于前面的，则往后挪
                array[preIndex+1]=array[preIndex];
                preIndex--;
            }
            array[preIndex+1]=current;
        }
        return array;
    }


    /**
     * 希尔排序，通过一个增量（常用希尔增量，即数组的长度的一半，并且每次缩小一半）进行分组，
     * 各个分组再进行插入排序
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array){
        if (array.length == 0 )
            return array;
        int length = array.length;
        int gap = length/2;
        while (gap>0){
            for (int i = gap;i<length;i++){
                int current = array[i];
                int preIndex = i-gap; // 前一个的索引，
                while (preIndex>=0 && array[preIndex]>current ){
                    array[preIndex+gap]= array[preIndex];
                    preIndex-=gap;
                }
                array[preIndex+gap]=current;
            }
            gap/=2;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] test = {9,8,7,6,5,4,1,1,3};
        for (int i : shellSort(test)) {
            System.out.print(i+"\t");
        }
        int i = 1;
        i = i++;
        i=i++;
        System.out.println();
        System.out.println(i);

    }


}

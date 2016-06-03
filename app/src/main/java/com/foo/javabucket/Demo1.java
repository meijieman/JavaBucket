package com.foo.javabucket;

import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * @Since: 2016/6/3 19:36
 */
public class Demo1 {

    /*
     * 有两个无序数组 a, b 长度都为 n,数组元素的值任意整形数，通过交换 a, b 中的元素，使 sum(a) - sum(b) 最小。

        当前数组a和数组b的和之差为 A = sum(a) - sum(b)
        a的第i个元素和b的第j个元素交换后，a和b的和之差为
        A' = sum(a) - a[i] + b[j] - (sum(b) - b[j] + a[i]))
               = sum(a) - sum(b) - 2 (a[i] - b[j])
               = A - 2 (a[i] - b[j])
        设x = a[i] - b[j]
        |A| - |A'| = |A| - |A-2x|
        假设A > 0,
        当x 在 (0,A)之间时，做这样的交换才能使得交换后的a和b的和之差变小，x越接近A/2效果越好,
        如果找不到在(0,A)之间的x，则当前的a和b就是答案。

        所以算法大概如下：
        在a和b中寻找使得x在(0,A)之间并且最接近A/2的i和j，交换相应的i和j元素，重新计算A后，重复前面的步骤直至找不到(0,A)之间的x为止。
     */
    public static void main(String[] args) {
        int[] arg1 = new int[]{4, 3, 30, 8};
        int[] arg2 = new int[]{20, 1, 50, 9};
        method(arg2, arg1);
    }

    public static void method(@NonNull int[] arg1, @NonNull int[] arg2) {
        if (arg1.length != arg2.length) {
            throw new RuntimeException("args length not the same");
        }

        System.out.println("begin --- arg1 " + Arrays.toString(arg1) + " --- arg2 " + Arrays.toString(arg2));

        int length = arg1.length;
        int pos1 = 0; // 等待交换的a[i] 下标
        int pos2 = 0; // 等待交换的b[j] 下标
        float minN = 0.0f; // 最接近a/2的a[i]-b[j]值

        while (true) {
            boolean have = false; // 是否能有解
            int sum1 = sumArray(arg1); // a[]的和
            int sum2 = sumArray(arg2); // b[]的和
            int minus = sum1 - sum2; // 和之差

            for (int i = 0; i < length; ++i) { // 找最接近 a/2 的 a[i]-b[j]
                for (int j = 0; j < length; ++j) {
                    boolean minusMoreThan0 = minus > 0; // 和之差是否大于0
                    int temp = arg1[i] - arg2[j];
                    // 如果 a[i] - b[j] 在 (0, a) 之间  (超出的就没有意义了)
                    if ((minusMoreThan0 && temp > 0 && temp < minus) || (!minusMoreThan0 && temp < 0 && temp > minus)) {
                        float abs = Math.abs(temp - minus / 2.0f);
                        if (have && abs < minN) { // 若比之前的a[i]-b[j]更接近a/2 则更新

                        } else {
                            have = true;
                        }
                        minN = abs;
                        pos1 = i;
                        pos2 = j;
                    }
                }
            }

            if (!have) { // 若找不到符合条件的a[i] - b[j]了 则结束
                System.out.println("result --- arg1 " + Arrays.toString(arg1) + " --- arg2 " + Arrays.toString(arg2));
                break;
            }

            swap(arg1, pos1, arg2, pos2);

            System.out.println("continue --- arg1 " + Arrays.toString(arg1) + " --- arg2 " + Arrays.toString(arg2));
        }
    }

    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return sum;
    }

    public static void swap(int[] a, int pos1, int[] b, int pos2) {
        int tmp = a[pos1];
        a[pos1] = b[pos2];
        b[pos2] = tmp;
    }
}
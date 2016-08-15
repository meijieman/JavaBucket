package com.foo.javabucket;

/**
 * 100盏灯泡的开关问题
 * <p/>
 * 问题：
 * 有100盏灯泡，第一轮点亮所有电灯，
 * 第二轮每两盏灯熄灭一盏，即熄灭第2盏，第4盏，以此类推，
 * 第三轮改变编号为3的倍数的电灯，第3盏，第6盏，如果原来那盏灯是亮的，就熄灭它，如果原来是灭的，就点亮它，
 * 以此类推，直到第100轮。问第100结束后，还有多少盏灯泡是亮的？
 * <p/>
 * 最简单的做法就是模拟一下情景，这个方法最简单也最容易想到，可是效率有点低。
 * 其实可以计算一下每个开关被按下的次数，因为刚开始的时候所有的灯泡都是关着的，所以如果某个灯泡的开关被按了奇数次，
 * 那么这个灯泡最终就是开着的，否则就是关着的。那怎么计算每个灯泡开关被按下的次数呢？
 * 因为每次都会按下第n次的倍数的开关，即如果灯泡编号是第n次的倍数，就会被按下开关，
 * 也就是说如果第n次编号是灯泡编号的约数，就会被按下开关。
 * 那么统计一下灯泡编号约数的个数就可以了，约数个数为计数的灯泡最后开着，约数个数为偶数的灯泡最后关着。
 *
 *还有更简单的算法，因为一个数的约数都成成对出现的，也就是说如果n存在一个约数p，那么一定有一个q与之相对应且满足n=pq，
 * 所以n约数的个数一定是偶数，但是有一种情况例外，那就是p=q，所以只有编号为<b>完全平方数</b>的灯泡亮着。
 *
 *@See 完全平方数：一个数如果是另一个整数的完全平方，那么我们就称这个数为完全平方数，也叫做平方数
 */
public class Demo2 {

    public static void main(String[] args) {
        int result = 0;
        for (int i = 1; i <= 100; i++) {
            if (isOdd(getFactorNum(i))) {
                System.out.print(i + "\t");
                result += 1;
            }
        }
        System.out.println();
        System.out.println(result);

        get1();
    }

    //求n约数的个数
    public static int getFactorNum(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                result += 1;
            }
        }
        return result;
    }

    //判断n是否为奇数
    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }


    // 求 100 以内的完全平方数
    public static void get(){
        for (int i = 1; i < 100; i++) {
            int sqrt = (int)Math.pow(i, 2);
            if(sqrt <= 100){
                System.out.print(sqrt + " \t");
            } else {
                break;
            }
        }
    }

    public static void get1(){
        for (int i = 1; i <= 100; i++) {
            double sqrt = Math.sqrt(i);
            if ((int)sqrt == sqrt) {
                System.out.print((int)sqrt + "\t");
            }
        }
    }
}

package com.algorithm;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by RobinLee on 2017/6/15.
 *
 * <p>Bit-Map algorithm, from <<Programming Pearls>>(《编程珠玑》).</p>
 *
 * <p>左移n位就是乘以2的n次方；右移n位就是除以2的n次方；</p>
 */
public class AlgorithmBitMap {

    private static final String TAG = "AlgorithmBitMap";

    private static int BITSPERWORD = 32;
    private static int       SHIFT = 5;
    private static int        MASK = 0x1F;
    private static int           N = 99999999;

    static int[] bitMapCache = new int[1 + N/BITSPERWORD];

    public static void main(String[] args){

        for (int i = 0; i < N; i++) {
            init(i);
        }

        set(0);
        set(1);
        set(2);

        // 1. Java log system
        Logger logger = Logger.getLogger(AlgorithmBitMap.class.getSimpleName());
        logger.log(Level.INFO, Integer.toBinaryString(get(0)));
        logger.log(Level.INFO, Integer.toBinaryString(get(1)));
        logger.log(Level.INFO, Integer.toBinaryString(get(2)));

        // 2. Java system out
        System.out.println(Integer.toBinaryString(get(0)));
        System.out.println(Integer.toBinaryString(get(1)));
        System.out.println(Integer.toBinaryString(get(2)));

    }

    private static void init(int i){
        bitMapCache[i>>SHIFT] &= ~(1<<(i & MASK));
    }

    /**
     * <p>1、i>>SHIFT      : i右移5位，2的5次方为32，相当于i/32，即求出十进制i对应在数组a中的下标。</p>
     *
     * <p>2、i & MASK      : MASK = 0x1F，十六进制转化为十进制即31，二进制为 0001 1111，i & (0001 1111)相当于保留i的后5位。
     *                    i & MASK相当于 i % 32。</p>
     *
     * <p>3、i<<(i & MASK) : 相当于把1左移(i & MASK)位。这里将 bitMapCache[i/32] |= (1<<M);第M位置1。</p>
     *
     * @param i
     */
    private static void set(int i){
        bitMapCache[i>>SHIFT] |= (1<<(i & MASK));
    }

    private static int get(int i){
        return (bitMapCache[i >> SHIFT] & (1 << (i & MASK)));
    }

}

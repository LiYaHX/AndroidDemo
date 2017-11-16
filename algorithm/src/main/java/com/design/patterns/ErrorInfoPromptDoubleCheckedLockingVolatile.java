package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  4. 双重检验锁原子性（Double Checked Locking Pattern）：同步块加锁
 *     缺点：Java 5之前无法通过添加 volatile 关键字 解决重排序问题，因为 Java 5以前的JVM（Java内存模型）存在缺陷
 *     优点：添加 volatile 关键字，解决 Java 内存模型允许"无序写入"导致的 变量 读写不一致问题
 *
 */
public class ErrorInfoPromptDoubleCheckedLockingVolatile {

    private volatile static ErrorInfoPromptDoubleCheckedLockingVolatile ERROR_INFO_PROMPT;

    public static ErrorInfoPromptDoubleCheckedLockingVolatile getInstance(){

        if(ERROR_INFO_PROMPT == null){              // Single Checked
            synchronized (ErrorInfoPromptDoubleCheckedLockingVolatile.class){

                if(ERROR_INFO_PROMPT == null){      // Double Checked
                    ERROR_INFO_PROMPT = new ErrorInfoPromptDoubleCheckedLockingVolatile();
                }
            }
        }
        return ERROR_INFO_PROMPT;
    }

    private ErrorInfoPromptDoubleCheckedLockingVolatile() {

    }
}

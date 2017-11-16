package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  3. 双重检验锁（Double Checked Locking Pattern）：同步块加锁
 *     缺点：理论上很完美。但 <code>ERROR_INFO_PROMPT = new ErrorInfoPromptDoubleCheckedLocking();<code/> 并非原子操作。
 *     优点：比懒汉式线程安全写法高效
 *
 *     概念解释：非原子操作——JVM 的即时编译器中存在指令重排序的优化
 *
 *              Peter Haggar在DeveloperWorks上的一篇文章，对二次检查的解释非常的详细：
 *
 *              “双重检查锁定背后的理论是完美的。不幸地是，现实完全不同。双重检查锁定的问题是：并不能保证它会在单处理器或多处理器计算机上顺利运行。
 *              双重检查锁定失败的问题并不归咎于 JVM 中的实现 bug，而是归咎于 Java 平台内存模型。内存模型允许所谓的“无序写入”，这也是这些习语失
 *              败的一个主要原因。”
 *
 *              使用二次检查的方法也不是完全安全的，原因是 java 平台内存模型中允许所谓的“无序写入”会导致二次检查失败，所以使用二次检查的想法也行不通了。
 *
 *              Peter Haggar在最后提出这样的观点：“无论以何种形式，都不应使用双重检查锁定，因为您不能保证它在任何 JVM 实现上都能顺利运行。”
 *
 *              假设线程A执行到了判断对象为空，于是线程A执行去初始化这个对象，但初始化是需要耗费时间的，但是这个对象的地址其实已经存在了。此时线程B也
 *              执行到了判断不为空，于是直接跳到后面去得到了这个对象。但是，这个对象还没有被完整的初始化！得到一个没有初始化完全的对象有什么用！！
 *
 *              <link ref="https://yq.aliyun.com/articles/240819">1</link>
 *              <link ref="http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/">2</link>
 */
public class ErrorInfoPromptDoubleCheckedLocking {

    private static ErrorInfoPromptDoubleCheckedLocking ERROR_INFO_PROMPT;

    public static ErrorInfoPromptDoubleCheckedLocking getInstance(){

        if(ERROR_INFO_PROMPT == null){              // Single Checked
            synchronized (ErrorInfoPromptDoubleCheckedLocking.class){

                if(ERROR_INFO_PROMPT == null){      // Double Checked
                    ERROR_INFO_PROMPT = new ErrorInfoPromptDoubleCheckedLocking();
                }
            }
        }
        return ERROR_INFO_PROMPT;
    }

    private ErrorInfoPromptDoubleCheckedLocking() {

    }
}

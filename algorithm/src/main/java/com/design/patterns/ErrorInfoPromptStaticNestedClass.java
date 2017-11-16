package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  6. 静态内部类 (static nested class)
 *     优点：《Effective Java》推荐
 *     原理描述：
 *            （1）使用JVM本身机制保证了线程安全问题；
 *            （2）ErrorInfoPromptStaticNestedClassHolder是私有的 ，除了 getInstance() 没有办法访问它，因此它是懒汉式的；
 *            （3）读取实例是不会进行同步，没有性能缺陷；
 *            （4）不依赖 JDK 版本；
 */
public class ErrorInfoPromptStaticNestedClass {

    private static class ErrorInfoPromptStaticNestedClassHolder{

        private static final ErrorInfoPromptStaticNestedClass ERROR_INFO_PROMPT = new ErrorInfoPromptStaticNestedClass();
    }

    public static final ErrorInfoPromptStaticNestedClass getInstance(){

        return ErrorInfoPromptStaticNestedClassHolder.ERROR_INFO_PROMPT;
    }

    private ErrorInfoPromptStaticNestedClass() {

    }
}

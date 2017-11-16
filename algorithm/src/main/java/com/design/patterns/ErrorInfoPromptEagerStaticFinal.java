package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  5. 饿汉式 static final field（Eager Singleton static final field）
 *     缺点：（1）类加载后一开始就被初始化，不频繁使用的话徒耗内存；
 *          （2）某些场景无法使用：Singleton 实例的创建依赖参数和配置文件；
 *     优点：由static final修饰变量，因此在第一次加载类到内存时就会被初始化，所以创建实例本身线程安全
 */
public class ErrorInfoPromptEagerStaticFinal {

    private static final ErrorInfoPromptEagerStaticFinal ERROR_INFO_PROMPT = new ErrorInfoPromptEagerStaticFinal();

    public static synchronized ErrorInfoPromptEagerStaticFinal getInstance(){
        return ERROR_INFO_PROMPT;
    }

    private ErrorInfoPromptEagerStaticFinal() {

    }
}

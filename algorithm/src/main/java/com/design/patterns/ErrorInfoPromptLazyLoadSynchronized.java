package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  2. 懒汉式（Lazy-out Singleton）
 *     缺点：不高效。因为同步操作只有在第一次创建单例对象时才被需要。因此，可以考虑双重检验锁写法。
 *     优点：线程安全，并解决了多实例问题
 */
public class ErrorInfoPromptLazyLoadSynchronized {

    private static ErrorInfoPromptLazyLoadSynchronized ERROR_INFO_PROMPT;

    public static synchronized ErrorInfoPromptLazyLoadSynchronized getInstance(){
        if(ERROR_INFO_PROMPT == null){
            ERROR_INFO_PROMPT = new ErrorInfoPromptLazyLoadSynchronized();
        }
        return ERROR_INFO_PROMPT;
    }

    private ErrorInfoPromptLazyLoadSynchronized() {

    }
}
